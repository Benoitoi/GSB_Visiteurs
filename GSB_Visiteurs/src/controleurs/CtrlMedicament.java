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
import modele.dao.DaoFamille;
import modele.dao.DaoMedicament;
import modele.metier.Famille;
import modele.metier.Medicament;
import vues.VueMedicament;

/**
 * @author bjaouen
 * @version decembre 2016 - 1
 */
public class CtrlMedicament implements WindowListener {

    private VueMedicament vue; // LA VUE
    private final CtrlPrincipal ctrlPrincipal;
    private final Ecouteur ecouteur;
    private DefaultComboBoxModel modeleJComboBoxNomsPrenomsMedicaments = new DefaultComboBoxModel();
    private ArrayList<Medicament> lesMedicaments = null;
    private final ArrayList<Medicament> lesMedicamentsTrouvee = new ArrayList<>();
    private final DefaultComboBoxModel modeleJComboBoxFamille = new DefaultComboBoxModel();
    private ArrayList<Famille> lesFamilles = null;
    private final ArrayList<String> listeMedicaments = new ArrayList<>();
    private final ArrayList<String> nomPrenomTrouve;
    private boolean rechercheFocused = false;

    /**
     *
     * @param vue
     * @param ctrl
     */
    public CtrlMedicament(VueMedicament vue, CtrlPrincipal ctrl) {
        this.nomPrenomTrouve = new ArrayList<>();
        this.ecouteur = new Ecouteur();
        this.vue = vue;
        this.ctrlPrincipal = ctrl;
        // le contrôleur écoute la vue
        this.vue.addWindowListener(this);

        vue.getjComboBoxFamille().setModel(modeleJComboBoxFamille);
        vue.getjComboBoxChercher().setModel(modeleJComboBoxNomsPrenomsMedicaments);
        remplirJComboBoxs();

        vue.getjButtonFermer().addActionListener(ecouteur);
        vue.getjButtonOk().addActionListener(ecouteur);
        vue.getjButtonPrecedent().addActionListener(ecouteur);
        vue.getjButtonSuivant().addActionListener(ecouteur);
        vue.getjComboBoxChercher().addActionListener(ecouteur);
        vue.getjComboBoxFamille().addActionListener(ecouteur);
        vue.getjButtonMenuGeneral().addActionListener(ecouteur);

        vue.getjTextFieldRechercher().setText("Nom commercial");

        vue.getjTextFieldRechercher().addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                rechercheFocused = true;
                afficherRecherche();
                if (vue.getjTextFieldRechercher().getText().equals("Nom commercial")) {
                    vue.getjTextFieldRechercher().setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                rechercheFocused = false;
                afficherRecherche();
                if (vue.getjTextFieldRechercher().getText().equals("")) {
                    vue.getjTextFieldRechercher().setText("Nom commercial");
                }
            }
        });

        vue.getjTextFieldRechercher().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    afficherLeMedicament(vue.getjComboBoxChercher().getSelectedIndex());
                    vue.getjComboBoxChercher().hidePopup();
                }
                if (ke.getKeyCode() == KeyEvent.VK_UP) {
                    int precedent = vue.getjComboBoxChercher().getSelectedIndex() - 1;
                    if (precedent < 0) {
                        vue.getjComboBoxChercher().setSelectedIndex(modeleJComboBoxNomsPrenomsMedicaments.getSize() - 1);
                    } else {
                        vue.getjComboBoxChercher().setSelectedIndex(precedent);
                    }
                    afficherLeMedicament(vue.getjComboBoxChercher().getSelectedIndex());
                }
                if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
                    int suivant = vue.getjComboBoxChercher().getSelectedIndex() + 1;
                    if (suivant > modeleJComboBoxNomsPrenomsMedicaments.getSize() - 1) {
                        vue.getjComboBoxChercher().setSelectedIndex(0);
                    } else {
                        vue.getjComboBoxChercher().setSelectedIndex(suivant);
                    }
                    afficherLeMedicament(vue.getjComboBoxChercher().getSelectedIndex());
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

        // préparer l'état iniitial de la vue
        afficherLeMedicament(0);
    }

    private class Ecouteur implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evenement) {
            if (evenement.getSource() == vue.getjButtonFermer()) {
                ctrlPrincipal.fermer(getVue());
            } else if (evenement.getSource() == vue.getjButtonOk()) {
                afficherLeMedicament(vue.getjComboBoxChercher().getSelectedIndex());
            } else if (evenement.getSource() == vue.getjButtonPrecedent()) {
                int precedent = vue.getjComboBoxChercher().getSelectedIndex() - 1;
                if (precedent < 0) {
                    afficherLeMedicament(modeleJComboBoxNomsPrenomsMedicaments.getSize() - 1);
                } else {
                    afficherLeMedicament(precedent);
                }
            } else if (evenement.getSource() == vue.getjButtonSuivant()) {
                int suivant = vue.getjComboBoxChercher().getSelectedIndex() + 1;
                if (suivant > modeleJComboBoxNomsPrenomsMedicaments.getSize() - 1) {
                    afficherLeMedicament(0);
                } else {
                    afficherLeMedicament(suivant);
                }
            } else if (evenement.getSource() == vue.getjComboBoxChercher()) {
                int index = vue.getjComboBoxChercher().getSelectedIndex();
                if (index != -1) {
                    afficherLeMedicament(index);
                }
            } else if (evenement.getSource() == vue.getjComboBoxFamille()) {
                //
            } else if (evenement.getSource() == vue.getjButtonMenuGeneral()) {
                ctrlPrincipal.afficherMenuGeneral(vue);
            }
        }
    }

    // contrôle de la vue
    /**
     * Remplir le composant JTable avec les clients
     *
     * @param desAdresses liste des clients à afficher
     */
    private void afficherLeMedicament(int indexMedicament) {
        if (lesMedicamentsTrouvee.size() > 0) {
            Medicament medicamentAffiche = lesMedicamentsTrouvee.get(indexMedicament);
            //        Medicament medicamentAffiche = null;
            //        try {
            //            medicamentAffiche = DaoMedicament.selectOneByRow(rowNumber);
            //        } catch (SQLException ex) {
            //            Logger.getLogger(CtrlMedicament.class.getName()).log(Level.SEVERE, null, ex);
            //        }

            String codeFamilleMedicament = medicamentAffiche.getCodeFamille();
            int indexFamille = 0;
            int indexFamilleMedicament = 0;
            for (Famille unFamille : lesFamilles) {
                if (unFamille.getCodeFamille().equals(codeFamilleMedicament)) {
                    indexFamilleMedicament = indexFamille;
                }
                indexFamille++;
            }

            vue.getjComboBoxFamille().setSelectedIndex(indexFamilleMedicament);
            vue.getjComboBoxChercher().setSelectedIndex(indexMedicament);

            vue.getjTextFieldCode().setText(medicamentAffiche.getDepotLegal());
            vue.getjTextFieldNomCommercial().setText(medicamentAffiche.getNomCommercial());
            vue.getjTextFieldComposition().setText(medicamentAffiche.getComposition());
            vue.getjTextAreaEffetsIndesirables().setText(medicamentAffiche.getEffets());
            vue.getjTextAreaContreIndications().setText(medicamentAffiche.getContreIndication());
            vue.getjTextFieldPrixEchantillon().setText(String.valueOf(medicamentAffiche.getPrixEchantillon()));
        }
    }

    private void remplirJComboBoxMedicaments() {
        try {
            lesMedicaments = DaoMedicament.getAllMedicaments();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlMedicament.class.getName()).log(Level.SEVERE, null, ex);
        }
        listeMedicaments.clear();
        lesMedicamentsTrouvee.clear();
        modeleJComboBoxNomsPrenomsMedicaments.removeAllElements();
        for (Medicament unMedicament : lesMedicaments) {
            lesMedicamentsTrouvee.add(unMedicament);
            listeMedicaments.add(unMedicament.getNomCommercial());
            modeleJComboBoxNomsPrenomsMedicaments.addElement(unMedicament.getNomCommercial());
        }
    }

    private void remplirJComboBoxs() {
        remplirJComboBoxMedicaments();

        try {
            lesFamilles = DaoFamille.getAllFamilles();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlMedicament.class.getName()).log(Level.SEVERE, null, ex);
        }
        modeleJComboBoxFamille.removeAllElements();
        for (Famille uneFamille : lesFamilles) {
            modeleJComboBoxFamille.addElement(uneFamille.getLibelleFamille());
        }
    }

    // méthodes d'action
    private void afficherRecherche() {
        if (vue.getjTextFieldRechercher().getText().replaceAll("\\s{2,}", " ").trim().equals("") || vue.getjTextFieldRechercher().getText().equals("Nom Prénom") || !rechercheFocused) {
            remplirJComboBoxMedicaments();
        } else {
            nomPrenomTrouve.clear();
            lesMedicamentsTrouvee.clear();
            for (int i = 0; i < listeMedicaments.size(); i++) {
                if (listeMedicaments.get(i).toLowerCase().contains(vue.getjTextFieldRechercher().getText().toLowerCase())) {
                    nomPrenomTrouve.add(listeMedicaments.get(i));
                    lesMedicamentsTrouvee.add(lesMedicaments.get(i));
                }
            }
            modeleJComboBoxNomsPrenomsMedicaments.removeAllElements();
            if (nomPrenomTrouve.isEmpty()) {
                modeleJComboBoxNomsPrenomsMedicaments.addElement("Aucun résultat");
            }
            for (int i = 0; i < nomPrenomTrouve.size(); i++) {
                modeleJComboBoxNomsPrenomsMedicaments.addElement(nomPrenomTrouve.get(i));
            }

        }
        if (modeleJComboBoxNomsPrenomsMedicaments.getSize() < 8) {
            vue.getjComboBoxChercher().setMaximumRowCount(modeleJComboBoxNomsPrenomsMedicaments.getSize());
        } else {
            vue.getjComboBoxChercher().setMaximumRowCount(8);
        }
        if (rechercheFocused) {
            vue.getjComboBoxChercher().showPopup();
        }
    }

    /**
     *
     * @param depotLegal
     */
    public void detailMedicament(String depotLegal) {
        int indexMedoc = 0;
        int indexLeMedoc = 0;
        for (Medicament unMedoc : lesMedicaments) {
            if (unMedoc.getDepotLegal().equals(depotLegal)) {
                indexLeMedoc = indexMedoc;
            }
            indexMedoc++;
        }
        afficherLeMedicament(indexLeMedoc);
    }

    // ACCESSEURS et MUTATEURS

    /**
     *
     * @return
     */
        public VueMedicament getVue() {
        return vue;
    }

    /**
     *
     * @param vue
     */
    public void setVue(VueMedicament vue) {
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
        ctrlPrincipal.quitterApplication();
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
