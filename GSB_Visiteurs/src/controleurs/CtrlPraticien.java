package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import modele.dao.DaoPraticien;
import modele.dao.DaoTypePraticien;
import modele.metier.Praticien;
import modele.metier.TypePraticien;
import vues.VuePraticien;

/**
 * @author bjaouen
 * @version decembre 2016 - 1
 */
public class CtrlPraticien implements WindowListener {

    private VuePraticien vue; // LA VUE
    private final CtrlPrincipal ctrlPrincipal;
    private final Ecouteur ecouteur;
    private DefaultComboBoxModel modeleJComboBoxNomsPrenomsPraticiens = new DefaultComboBoxModel();
    private ArrayList<Praticien> lesPraticiens = null;
    private final ArrayList<Praticien> lesPraticiensTrouvee = new ArrayList<>();
    private final DefaultComboBoxModel modeleJComboBoxLieux = new DefaultComboBoxModel();
    private ArrayList<TypePraticien> lesTypesPraticiens = null;
    private final ArrayList<String> listePraticiens = new ArrayList<>();
    private final ArrayList<String> nomPrenomTrouve = new ArrayList<>();
    private boolean rechercheFocused = false;
    private boolean editMode = false;
    private boolean detailMode = false;

    /**
     *
     * @param vue
     * @param ctrl
     */
    public CtrlPraticien(final VuePraticien vue, CtrlPrincipal ctrl) {
        this.ecouteur = new Ecouteur();
        this.vue = vue;
        this.vue.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/images/gsb_logo.png")).getImage());
        this.ctrlPrincipal = ctrl;
        // le contrôleur écoute la vue
        this.vue.addWindowListener(this);
        vue.getjButtonOk().setVisible(false);

        vue.getjComboBoxLieuExercice().setModel(modeleJComboBoxLieux);
        vue.getjComboBoxChercher().setModel(modeleJComboBoxNomsPrenomsPraticiens);

        vue.getjButtonQuitter().addActionListener(ecouteur);
        vue.getjButtonOk().addActionListener(ecouteur);
        vue.getjButtonPrecedent().addActionListener(ecouteur);
        vue.getjButtonSuivant().addActionListener(ecouteur);
        vue.getjComboBoxChercher().addActionListener(ecouteur);
        vue.getjComboBoxLieuExercice().addActionListener(ecouteur);
        vue.getjButtonMenuGeneral().addActionListener(ecouteur);

        vue.getjTextFieldRechercher().addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                rechercheFocused = true;
                afficherRecherche();
                if (vue.getjTextFieldRechercher().getText().equals("Nom Prénom")) {
                    vue.getjTextFieldRechercher().setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                rechercheFocused = false;
                afficherRecherche();
                if (vue.getjTextFieldRechercher().getText().equals("")) {
                    vue.getjTextFieldRechercher().setText("Nom Prénom");
                }
            }
        });

        vue.getjTextFieldRechercher().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    afficherLePraticien(vue.getjComboBoxChercher().getSelectedIndex());
                    vue.getjComboBoxChercher().hidePopup();
                }
                if (ke.getKeyCode() == KeyEvent.VK_UP) {
                    int precedent = vue.getjComboBoxChercher().getSelectedIndex() - 1;
                    if (precedent < 0) {
                        vue.getjComboBoxChercher().setSelectedIndex(modeleJComboBoxNomsPrenomsPraticiens.getSize() - 1);
                    } else {
                        vue.getjComboBoxChercher().setSelectedIndex(precedent);
                    }
                    afficherLePraticien(vue.getjComboBoxChercher().getSelectedIndex());
                }
                if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
                    int suivant = vue.getjComboBoxChercher().getSelectedIndex() + 1;
                    if (suivant > modeleJComboBoxNomsPrenomsPraticiens.getSize() - 1) {
                        vue.getjComboBoxChercher().setSelectedIndex(0);
                    } else {
                        vue.getjComboBoxChercher().setSelectedIndex(suivant);
                    }
                    afficherLePraticien(vue.getjComboBoxChercher().getSelectedIndex());
                }
            }
        });

        vue.getjTextFieldRechercher().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                afficherRecherche();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                afficherRecherche();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                afficherRecherche();
            }
        });
        //initialisation des données
        init();
        //remplir les listes déroulantes
        remplirJComboBoxs();
        // préparer l'état iniitial de la vue
        afficherLePraticien(0);
        isEditing(editMode);
    }

    private void init() {
        try {
            lesPraticiens = DaoPraticien.getAllPraticiens();
            lesTypesPraticiens = DaoTypePraticien.getAll();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlPraticien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void isEditing(boolean b) {
        vue.getjTextFieldNom().setEditable(b);
        vue.getjTextFieldPrenom().setEditable(b);
        vue.getjTextFieldAdresse().setEditable(b);
        vue.getjTextFieldCodePostal().setEditable(b);
        vue.getjTextFieldVille().setEditable(b);
        vue.getjTextFieldCoefNotoriete().setEditable(b);
        vue.getjComboBoxLieuExercice().setEnabled(b);
    }

    /**
     *
     */
    private class Ecouteur implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evenement) {
            if (evenement.getSource() == vue.getjButtonQuitter()) {
                if (detailMode) {
                    getVue().dispose();
                    detailMode = false;
                    detailMode(detailMode);
                } else {
                    ctrlPrincipal.quitterApplication();
                }
            } else if (evenement.getSource() == vue.getjButtonOk()) {
                afficherLePraticien(vue.getjComboBoxChercher().getSelectedIndex());
            } else if (evenement.getSource() == vue.getjButtonPrecedent()) {
                int precedent = vue.getjComboBoxChercher().getSelectedIndex() - 1;
                if (precedent < 0) {
                    afficherLePraticien(modeleJComboBoxNomsPrenomsPraticiens.getSize() - 1);
                } else {
                    afficherLePraticien(precedent);
                }
            } else if (evenement.getSource() == vue.getjButtonSuivant()) {
                int suivant = vue.getjComboBoxChercher().getSelectedIndex() + 1;
                if (suivant > modeleJComboBoxNomsPrenomsPraticiens.getSize() - 1) {
                    afficherLePraticien(0);
                } else {
                    afficherLePraticien(suivant);
                }
            } else if (evenement.getSource() == vue.getjComboBoxChercher()) {
                int index = vue.getjComboBoxChercher().getSelectedIndex();
                if (index != -1) {
                    afficherLePraticien(index);
                }
            } else if (evenement.getSource() == vue.getjComboBoxLieuExercice()) {
                //
            } else if (evenement.getSource() == vue.getjButtonMenuGeneral()) {
                ctrlPrincipal.afficherMenuGeneral(vue);
            }
        }
    }

    /**
     *
     * @param indexPraticien
     */
    private void afficherLePraticien(int indexPraticien) {
        if (lesPraticiensTrouvee.size() > 0) {
            Praticien praticienAffiche = lesPraticiensTrouvee.get(indexPraticien);
            String codeTypePraticien = praticienAffiche.getType().getTypeCode();
            int indexLieu = 0;
            int indexLieuPraticien = 0;
            for (TypePraticien unTypePraticien : lesTypesPraticiens) {
                if (unTypePraticien.getTypeCode().equals(codeTypePraticien)) {
                    indexLieuPraticien = indexLieu;
                }
                indexLieu++;
            }

            vue.getjComboBoxLieuExercice().setSelectedIndex(indexLieuPraticien);
            vue.getjComboBoxChercher().setSelectedIndex(indexPraticien);

            vue.getjTextFieldNom().setText(praticienAffiche.getNom());
            vue.getjTextFieldPrenom().setText(praticienAffiche.getPrenom());
            vue.getjTextFieldAdresse().setText(praticienAffiche.getAdresse());
            vue.getjTextFieldCodePostal().setText(String.valueOf(praticienAffiche.getCodePostal()));
            vue.getjTextFieldVille().setText(praticienAffiche.getVille());
            vue.getjTextFieldCoefNotoriete().setText(String.valueOf(praticienAffiche.getCoefficientNotoriete()));
        }
    }

    /**
     *
     */
    private void remplirJComboBoxPraticiens() {
        listePraticiens.clear();
        lesPraticiensTrouvee.clear();
        modeleJComboBoxNomsPrenomsPraticiens.removeAllElements();
        for (Praticien unPraticien : lesPraticiens) {
            lesPraticiensTrouvee.add(unPraticien);
            listePraticiens.add(unPraticien.getNom() + " " + unPraticien.getPrenom());
            modeleJComboBoxNomsPrenomsPraticiens.addElement(unPraticien.getNom() + " " + unPraticien.getPrenom());
        }
    }

    /**
     *
     */
    private void remplirJComboBoxs() {
        modeleJComboBoxLieux.removeAllElements();
        for (TypePraticien unTypePraticien : lesTypesPraticiens) {
            modeleJComboBoxLieux.addElement(unTypePraticien.getTypeLieu());
        }
        remplirJComboBoxPraticiens();
    }

    /**
     *
     * @param numPraticien
     */
    public void detailPraticien(int numPraticien) {
        int indexPraticien = 0;
        int indexConnectedPraticien = 0;
        for (Praticien unPraticien : lesPraticiens) {
            if (unPraticien.getNumero() == numPraticien) {
                indexConnectedPraticien = indexPraticien;
            }
            indexPraticien++;
        }
        afficherLePraticien(indexConnectedPraticien);
        detailMode = true;
        detailMode(detailMode);
    }

    /**
     *
     * @param b
     */
    public void detailMode(boolean b) {
        if (b) {
            vue.getjButtonQuitter().setText("Fermer");
        } else {
            vue.getjButtonQuitter().setText("Quitter");
        }
        vue.getjLabelChercher().setVisible(!b);
        vue.getjLabelRechercher().setVisible(!b);
        vue.getjButtonMenuGeneral().setVisible(!b);
        vue.getjTextFieldRechercher().setVisible(!b);
        vue.getjComboBoxChercher().setVisible(!b);
        vue.getjButtonOk().setVisible(!b);
        vue.getjButtonPrecedent().setVisible(!b);
        vue.getjButtonSuivant().setVisible(!b);
    }

    /**
     *
     */
    private void afficherRecherche() {
        if (vue.getjTextFieldRechercher().getText().replaceAll("\\s{2,}", " ").trim().equals("") || vue.getjTextFieldRechercher().getText().equals("Nom Prénom") || !rechercheFocused) {
            remplirJComboBoxPraticiens();
        } else {
            nomPrenomTrouve.clear();
            lesPraticiensTrouvee.clear();
            for (int i = 0; i < listePraticiens.size(); i++) {
                if (listePraticiens.get(i).toLowerCase().contains(vue.getjTextFieldRechercher().getText().toLowerCase())) {
                    nomPrenomTrouve.add(listePraticiens.get(i));
                    lesPraticiensTrouvee.add(lesPraticiens.get(i));
                }
            }
            modeleJComboBoxNomsPrenomsPraticiens.removeAllElements();
            if (nomPrenomTrouve.isEmpty()) {
                modeleJComboBoxNomsPrenomsPraticiens.addElement("Aucun résultat");
            }
            for (String nomPrenomTrouve1 : nomPrenomTrouve) {
                modeleJComboBoxNomsPrenomsPraticiens.addElement(nomPrenomTrouve1);
            }

        }
        if (modeleJComboBoxNomsPrenomsPraticiens.getSize() < 8) {
            vue.getjComboBoxChercher().setMaximumRowCount(modeleJComboBoxNomsPrenomsPraticiens.getSize());
        } else {
            vue.getjComboBoxChercher().setMaximumRowCount(8);
        }
        if (rechercheFocused) {
            vue.getjComboBoxChercher().showPopup();
        }
    }

    // ACCESSEURS et MUTATEURS
    /**
     *
     * @return
     */
    public VuePraticien getVue() {
        return vue;
    }

    /**
     *
     * @param vue
     */
    public void setVue(VuePraticien vue) {
        this.vue = vue;
    }

    // REACTIONS EVENEMENTIELLES
    /**
     *
     * @param e
     */
    @Override
    public void windowOpened(WindowEvent e) {
    }

    /**
     *
     * @param e
     */
    @Override
    public void windowClosing(WindowEvent e) {
        if (detailMode) {
            getVue().dispose();
            detailMode = false;
            detailMode(detailMode);
        } else {
            ctrlPrincipal.quitterApplication();
        }
    }

    /**
     *
     * @return
     */
    public boolean isDetailMode() {
        return detailMode;
    }

    /**
     *
     * @param detailMode
     */
    public void setDetailMode(boolean detailMode) {
        this.detailMode = detailMode;
    }

    /**
     *
     * @param e
     */
    @Override
    public void windowClosed(WindowEvent e) {
    }

    /**
     *
     * @param e
     */
    @Override
    public void windowIconified(WindowEvent e) {
    }

    /**
     *
     * @param e
     */
    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    /**
     *
     * @param e
     */
    @Override
    public void windowActivated(WindowEvent e) {
    }

    /**
     *
     * @param e
     */
    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
