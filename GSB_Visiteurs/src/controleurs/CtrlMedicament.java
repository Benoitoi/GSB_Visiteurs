package controleurs;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Font;
import java.awt.event.KeyListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
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
public class CtrlMedicament implements WindowListener, ActionListener, FocusListener, KeyListener {

    private VueMedicament vue; // LA VUE
    private final CtrlPrincipal ctrlPrincipal;
    private DefaultComboBoxModel modeleJComboBoxNomsPrenomsMedicaments = new DefaultComboBoxModel();
    private ArrayList<Medicament> lesMedicaments = null;
    private final ArrayList<Medicament> lesMedicamentsTrouvee = new ArrayList<>();
    private final DefaultComboBoxModel modeleJComboBoxFamille = new DefaultComboBoxModel();
    private ArrayList<Famille> lesFamilles = null;
    private final ArrayList<String> listeMedicaments = new ArrayList<>();
    private final ArrayList<String> nomPrenomTrouve;
    private boolean rechercheFocused = false;
    private boolean editMode = false;
    private boolean detailMode = false;

    /**
     *
     * @param vue
     * @param ctrl
     */
    public CtrlMedicament(final VueMedicament vue, CtrlPrincipal ctrl) {
        this.nomPrenomTrouve = new ArrayList<>();
        this.vue = vue;
        this.vue.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/images/gsb_logo.png")).getImage());
        this.ctrlPrincipal = ctrl;
        // le contrôleur écoute la vue
        this.vue.addWindowListener(this);
        vue.getjButtonOk().setVisible(false);

        vue.getjComboBoxFamille().setModel(modeleJComboBoxFamille);
        vue.getjComboBoxChercher().setModel(modeleJComboBoxNomsPrenomsMedicaments);
        remplirJComboBoxs();

        vue.getjButtonQuitter().addActionListener(this);
        vue.getjButtonOk().addActionListener(this);
        vue.getjButtonPrecedent().addActionListener(this);
        vue.getjButtonSuivant().addActionListener(this);
        vue.getjComboBoxChercher().addActionListener(this);
        vue.getjComboBoxFamille().addActionListener(this);
        vue.getjButtonMenuGeneral().addActionListener(this);

        vue.getjTextFieldRechercher().setText("Nom commercial");

        vue.getjTextFieldRechercher().addFocusListener(this);

        vue.getjTextFieldRechercher().addKeyListener(this);

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
        isEditing(editMode);
    }

    private void isEditing(boolean b) {
        vue.getjTextFieldCode().setEditable(b);
        vue.getjTextFieldNomCommercial().setEditable(b);
        vue.getjComboBoxFamille().setEnabled(b);
        vue.getjTextFieldComposition().setEditable(b);
        vue.getjTextAreaEffetsIndesirables().setEditable(b);
        vue.getjTextAreaContreIndications().setEditable(b);
        vue.getjTextFieldPrixEchantillon().setEditable(b);
    }

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

    // contrôle de la vue
    /**
     * Remplir le composant JTable avec les clients
     *
     * @param desAdresses liste des clients à afficher
     */
    private void afficherLeMedicament(int indexMedicament) {
        if (lesMedicamentsTrouvee.size() > 0) {
            Medicament medicamentAffiche = lesMedicamentsTrouvee.get(indexMedicament);

            String codeFamilleMedicament = medicamentAffiche.getFamille().getCodeFamille();
            int indexFamille = 0;
            int indexFamilleMedicament = 0;
            for (Famille unFamille : lesFamilles) {
                if (unFamille.getCodeFamille().equals(codeFamilleMedicament)) {
                    indexFamilleMedicament = indexFamille;
                }
                indexFamille++;
            }

            int width = 0;
            final Font FONT = new JLabel().getFont();
            AffineTransform affinetransform = new AffineTransform();
            FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
            vue.getjComboBoxFamille().setSelectedIndex(indexFamilleMedicament);
            vue.getjComboBoxChercher().setSelectedIndex(indexMedicament);

            vue.getjTextFieldCode().setText(medicamentAffiche.getDepotLegal());
            vue.getjTextFieldNomCommercial().setText(medicamentAffiche.getNomCommercial());
            vue.getjTextFieldComposition().setText(medicamentAffiche.getComposition());
            width = (int) (FONT.getStringBounds(medicamentAffiche.getComposition(), frc).getWidth()) + 10;
            vue.getjTextFieldComposition().setSize(width, vue.getjTextFieldComposition().getHeight());
            vue.getjTextAreaEffetsIndesirables().setText(medicamentAffiche.getEffets());
            vue.getjTextAreaEffetsIndesirables().setCaretPosition(0);
            vue.getjTextAreaContreIndications().setText(medicamentAffiche.getContreIndication());
            vue.getjTextAreaContreIndications().setCaretPosition(0);
            vue.getjTextFieldPrixEchantillon().setText(String.valueOf(medicamentAffiche.getPrixEchantillon()));
        }
    }

    private void doRightDisplay(Component c) {

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
        detailMode = true;
        detailMode(detailMode);
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

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource().equals(vue.getjTextFieldRechercher())) {
            rechercheFocused = true;
            afficherRecherche();
            if (vue.getjTextFieldRechercher().getText().equals("Nom commercial")) {
                vue.getjTextFieldRechercher().setText("");
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource().equals(vue.getjTextFieldRechercher())) {
            rechercheFocused = false;
            afficherRecherche();
            if (vue.getjTextFieldRechercher().getText().equals("")) {
                vue.getjTextFieldRechercher().setText("Nom commercial");
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource().equals(vue.getjTextFieldRechercher())) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                afficherLeMedicament(vue.getjComboBoxChercher().getSelectedIndex());
                vue.getjComboBoxChercher().hidePopup();
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                int precedent = vue.getjComboBoxChercher().getSelectedIndex() - 1;
                if (precedent < 0) {
                    vue.getjComboBoxChercher().setSelectedIndex(modeleJComboBoxNomsPrenomsMedicaments.getSize() - 1);
                } else {
                    vue.getjComboBoxChercher().setSelectedIndex(precedent);
                }
                afficherLeMedicament(vue.getjComboBoxChercher().getSelectedIndex());
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                int suivant = vue.getjComboBoxChercher().getSelectedIndex() + 1;
                if (suivant > modeleJComboBoxNomsPrenomsMedicaments.getSize() - 1) {
                    vue.getjComboBoxChercher().setSelectedIndex(0);
                } else {
                    vue.getjComboBoxChercher().setSelectedIndex(suivant);
                }
                afficherLeMedicament(vue.getjComboBoxChercher().getSelectedIndex());
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
