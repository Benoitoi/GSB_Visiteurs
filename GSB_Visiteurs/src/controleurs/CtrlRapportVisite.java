package controleurs;

import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.PlainDocument;
import modele.dao.DaoMedicament;
import modele.dao.DaoOffre;
import modele.dao.DaoPraticien;
import modele.dao.DaoRapportVisite;
import modele.metier.Medicament;
import modele.metier.Offre;
import modele.metier.Praticien;
import modele.metier.RapportVisite;
import modele.metier.Visiteur;
import util.FileReader;
import util.IntTextFieldFilter;
import vues.VueRapportVisite;

/**
 * @author bjaouen
 * @version fevrier 2016 - 1
 */
public class CtrlRapportVisite implements WindowListener {

    private VueRapportVisite vue; // LA VUE
    private CtrlPrincipal ctrlPrincipal;
    private final Ecouteur ecouteur = new Ecouteur();
    private DefaultComboBoxModel modeleJComboBoxPraticiens = new DefaultComboBoxModel();
    private DefaultComboBoxModel modeleJComboBoxLePraticien = new DefaultComboBoxModel();
    private DefaultTableModel modeleJTableOffreEchantillons = new DefaultTableModel();
    private ArrayList<RapportVisite> lesRapportsVisite = new ArrayList<>();
    private ArrayList<Praticien> tousPraticiens = new ArrayList<>();
    private ArrayList<Medicament> tousMedicaments = new ArrayList<>();
    private ArrayList<Offre> lesOffresEchantillons = new ArrayList<>();
    private int indexRapport = 0;
    private boolean editMode = false;
    private int maxNum = 0;
    private final TableCellEditor editor;
    private boolean hasValidate = false;
    private JTextField offreEchantillon;
    private JComboBox lesMedicaments;
    private final TableCellEditor cellEditor;
    private final TableCellRenderer cellRenderer;
    private final TableCellRenderer renderer;

    /**
     *
     * @param vue
     * @param ctrl
     */
    public CtrlRapportVisite(VueRapportVisite vue, CtrlPrincipal ctrl) {
        this.vue = vue;
        // le contrôleur écoute la vue
        this.vue.addWindowListener(this);
        this.ctrlPrincipal = ctrl;

        this.vue.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent fe) {
                updateWindows();//si l'on a quitté la fenetre l'on peut avoir modifié des données sur d'autres vu (praticien, médicaments) on raffraichit donc la page
            }

            @Override
            public void focusLost(FocusEvent fe) {
            }
        });

        vue.getjTableOffreEchantillons().setModel(modeleJTableOffreEchantillons);
        editor = vue.getjTableOffreEchantillons().getDefaultEditor(Object.class);
        renderer = vue.getjTableOffreEchantillons().getDefaultRenderer(Object.class);

        modeleJTableOffreEchantillons.addColumn("Médicaments");
        modeleJTableOffreEchantillons.addColumn("Nb échantillon(s)");

        TableColumn column = vue.getjTableOffreEchantillons().getColumnModel().getColumn(0);
        cellEditor = column.getCellEditor();
        cellRenderer = column.getCellRenderer();

        vue.getjButtonFermer().addActionListener(ecouteur);
        vue.getjButtonDetailsPraticien().addActionListener(ecouteur);
        vue.getjButtonPrecedent().addActionListener(ecouteur);
        vue.getjButtonSuivant().addActionListener(ecouteur);
        vue.getjButtonNouveau().addActionListener(ecouteur);
        vue.getjButtonMoins().addActionListener(ecouteur);
        vue.getjButtonPlus().addActionListener(ecouteur);
        vue.getjButtonValider().addActionListener(ecouteur);
        vue.getjButtonMenuGeneral().addActionListener(ecouteur);
        vue.getjButtonToutSupprimer().addActionListener(ecouteur);

        JTextFieldDateEditor jDateChooserEditor = (JTextFieldDateEditor) vue.getjDateChooserDateRapport().getDateEditor();
        jDateChooserEditor.setEditable(false);

        vue.getjTableOffreEchantillons().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (!editMode) {
                    if (evt.getClickCount() == 2 && modeleJTableOffreEchantillons.getRowCount() != 0) {
                        ctrlPrincipal.afficherMedicament((String) modeleJTableOffreEchantillons.getValueAt(vue.getjTableOffreEchantillons().getSelectedRow(), 0));
                    }
                } else {

                }
            }
        });
        init();
        // préparer l'état iniitial de la vue
        rechercherIndexVisiteurConnecte();
        isEditing(editMode);
    }

    private void init() {
        try {
            getAllRapports();
            getAllOffres();
            getAllMedicaments();
            getAllPraticiens();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlRapportVisite.class.getName()).log(Level.SEVERE, null, ex);
        }
        maxNum();

    }

    private void showNew(int numeroRapport) throws SQLException {
        if (vue.getjTableOffreEchantillons().getRowCount() != 0) {
            getAllOffres();
        }
        getAllRapports();
        int i = 0;
        for (RapportVisite unRapportVisite : lesRapportsVisite) {
            if (unRapportVisite.getNumeroRapport() == numeroRapport) {
                indexRapport = i;
            }
            i++;
        }
        afficherLeRapport();
    }

    private void updateWindows() {
        init();
        afficherLeRapport();
    }

    private void getAllRapports() throws SQLException {
        lesRapportsVisite = DaoRapportVisite.getAllRapportsVisite();
    }

    private void getAllOffres() throws SQLException {
        lesOffresEchantillons = DaoOffre.getAllOffres();
    }

    private void getAllMedicaments() throws SQLException {
        tousMedicaments = DaoMedicament.getAllMedicaments();
    }

    private void getAllPraticiens() throws SQLException {
        tousPraticiens = DaoPraticien.getAllPraticiens();
        modeleJComboBoxPraticiens.addElement("Choisissez un praticien");
        for (Praticien unPraticien : tousPraticiens) {
            modeleJComboBoxPraticiens.addElement(unPraticien.getNom());
        }
    }

    private void maxNum() {
        for (RapportVisite unRapportVisite : lesRapportsVisite) {
            int num = unRapportVisite.getNumeroRapport();
            if (num > maxNum) {
                maxNum = num;
            }
        }
    }

    private void isEditing(boolean b) {
        vue.getjComboBoxPraticien().setEditable(b);
        vue.getjDateChooserDateRapport().setEnabled(b);
        vue.getjTextFieldMotifVisite().setEditable(b);
        vue.getjTextAreaBilan().setEditable(b);
        vue.getjComboBoxPraticien().setEnabled(b);
        vue.getjButtonPrecedent().setEnabled(!b);
        vue.getjButtonSuivant().setEnabled(!b);
        vue.getjButtonValider().setVisible(b);
        vue.getjButtonPlus().setVisible(b);
        vue.getjButtonMoins().setVisible(b);
        if (b) {
            vue.getjButtonNouveau().setText("Annuler");
            vue.getjTableOffreEchantillons().setDefaultEditor(Object.class, editor);
            formulaire();
        } else {
            vue.getjButtonNouveau().setText("Nouveau");
            vue.getjTableOffreEchantillons().setDefaultRenderer(Object.class, renderer);
            vue.getjTableOffreEchantillons().setDefaultEditor(Object.class, null);
            TableColumn medicaments = vue.getjTableOffreEchantillons().getColumnModel().getColumn(0);
            medicaments.setCellEditor(null);
            medicaments.setCellRenderer(renderer);
            TableColumn echantillons = vue.getjTableOffreEchantillons().getColumnModel().getColumn(1);
            echantillons.setCellEditor(null);
            echantillons.setCellRenderer(renderer);
        }
        if (vue.getjTableOffreEchantillons().getRowCount() < 6) {
            vue.getjButtonToutSupprimer().setVisible(false);
        }
    }

    private void formulaire() {
        vue.getjTextFieldNumeroRapport().setText(String.valueOf(maxNum + 1));
        vue.getjComboBoxPraticien().setModel(modeleJComboBoxPraticiens);
        vue.getjComboBoxPraticien().setSelectedIndex(0);
        vue.getjDateChooserDateRapport().setCalendar(null);
        vue.getjTextFieldMotifVisite().setText("");
        vue.getjTextAreaBilan().setText("");
        modeleJTableOffreEchantillons.setRowCount(0);
    }

    private void guidage() {
        vue.getjDateChooserDateRapport().setToolTipText("Date du rapport");
        vue.getjTextAreaBilan().setToolTipText("Bilan du rapport");
    }

    private class Ecouteur implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evenement) {
            if (evenement.getSource() == vue.getjButtonToutSupprimer()) {
                int rowCount = modeleJTableOffreEchantillons.getRowCount();
                for (int i = rowCount - 1; i >= 0; i--) {
                    modeleJTableOffreEchantillons.removeRow(i);
                }
                vue.getjButtonToutSupprimer().setVisible(false);
            }
            if (evenement.getSource() == vue.getjButtonValider()) {
                String message = "Veuillez préciser :\n";
                boolean erreur = false;
                if (vue.getjTableOffreEchantillons().getRowCount() == 0) {
                    /*message += "-l'offre échantillon du rapport\n";
                     erreur = true;*/
                } else {
                    int rowCount = vue.getjTableOffreEchantillons().getRowCount();
                    int columnCount = vue.getjTableOffreEchantillons().getColumnCount();
                    for (int row = 0; row < rowCount; row++) {
                        if (lesMedicaments.getSelectedIndex() == 0) {
                            message += "-le médicament\n";
                            erreur = true;
                        }
                        if (offreEchantillon.getText().equals("")) {
                            message += "-le nombre d'échantillons\n";
                            //erreur = true; //nullable
                        }
                    }
                }
                if (vue.getjTextFieldMotifVisite().getText().equals("")) {
                    vue.getjTextFieldMotifVisite().requestFocus();
                    message += "-le motif du rapport\n";
                    //erreur = true;//nullable
                }
                if (!(vue.getjTextAreaBilan().getText().equals(""))) {
                    vue.getjTextAreaBilan().requestFocus();
                    message += "-le bilan du rapport\n";
                    //erreur = true;//nullable
                }
                if (vue.getjDateChooserDateRapport().getDate() == null) {
                    vue.getjDateChooserDateRapport().requestFocus();
                    //ouvrir popup
                    message += "-La date du rapport\n";
                    //erreur = true;//nullable
                }
                if (vue.getjComboBoxPraticien().getSelectedIndex() == 0) {
                    message += "-le praticien du rapport\n";
                    vue.getjComboBoxPraticien().requestFocus();
                    vue.getjComboBoxPraticien().showPopup();
                    erreur = true;
                }
                if (erreur) {
                    int rep = JOptionPane.showConfirmDialog(getVue(), "Il y a des erreurs dans le formulaire, recommencer ?\n" + message, "Erreur de saisie", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (rep == JOptionPane.YES_OPTION) {

                    } else if (rep == JOptionPane.NO_OPTION) {
                        editMode = false;
                        isEditing(editMode);
                        afficherLeRapport();
                    }
                } else {

                    //creer le nouveau rapport
                    Visiteur visiteurLu = FileReader.getConnectedVisiteur(vue);
                    Praticien lePraticien = tousPraticiens.get(vue.getjComboBoxPraticien().getSelectedIndex() - 1);
                    String matriculeVisiteur = visiteurLu.getMatricule();
                    int numeroRapport = Integer.valueOf(vue.getjTextFieldNumeroRapport().getText());
                    int numeroPraticien = lePraticien.getNumero();
                    Date dateRapport = new Date(vue.getjDateChooserDateRapport().getDate().getTime());
                    String motifRapport = vue.getjTextFieldMotifVisite().getText();
                    String bilanRapport = vue.getjTextAreaBilan().getText();
                    RapportVisite nouveauRapportVisite = new RapportVisite(matriculeVisiteur, numeroRapport, numeroPraticien, dateRapport, motifRapport, bilanRapport);
                    try {
                        DaoRapportVisite.insert(nouveauRapportVisite);
                    } catch (SQLException ex) {
                        Logger.getLogger(CtrlRapportVisite.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //creer la nouvelle offre
                    for (int i = 0; i < vue.getjTableOffreEchantillons().getRowCount(); i++) {

                        Offre nouvelleOffre = new Offre(matriculeVisiteur, numeroRapport, (String) modeleJTableOffreEchantillons.getValueAt(i, 0), Integer.valueOf((String) modeleJTableOffreEchantillons.getValueAt(i, 1)));
                        try {
                            DaoOffre.insert(nouvelleOffre);
                        } catch (SQLException ex) {
                            Logger.getLogger(CtrlRapportVisite.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    editMode = false;
                    isEditing(editMode);
                    try {
                        showNew(numeroRapport);
                    } catch (SQLException ex) {
                        Logger.getLogger(CtrlRapportVisite.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if (evenement.getSource() == vue.getjButtonPlus()) {
                Object[] rowData = new Object[2];
                lesMedicaments = new JComboBox();
                lesMedicaments.addItem("Sélectionnez un médicament");
                for (Medicament unMedicament : tousMedicaments) {
                    lesMedicaments.addItem(unMedicament.getNomCommercial());
                }

                TableColumn medicaments = vue.getjTableOffreEchantillons().getColumnModel().getColumn(0);
                rowData[0] = medicaments;
                medicaments.setCellEditor(new DefaultCellEditor(lesMedicaments));
                medicaments.setCellRenderer(new CheckBoxCellRenderer(lesMedicaments));
                vue.getjTableOffreEchantillons().repaint();

                offreEchantillon = new JTextField();
                TableColumn nbEchantillons = vue.getjTableOffreEchantillons().getColumnModel().getColumn(1);
                nbEchantillons.setCellEditor(new DefaultCellEditor(offreEchantillon));
                PlainDocument doc = (PlainDocument) offreEchantillon.getDocument();
                doc.setDocumentFilter(new IntTextFieldFilter(3));
                rowData[0] = nbEchantillons;

                modeleJTableOffreEchantillons.addRow(rowData);

                if (vue.getjTableOffreEchantillons().getRowCount() > 6) {
                    vue.getjButtonToutSupprimer().setVisible(true);
                }
                /*//infobulles sur les éléments
                 DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
                 medicaments.setCellRenderer(renderer);
                 renderer.setToolTipText("Cliquez pour dérouler la liste");*/
            } else if (evenement.getSource() == vue.getjButtonMoins()) {
                int[] selectedRows = vue.getjTableOffreEchantillons().getSelectedRows();
                if (selectedRows.length != 0) {
                    int i = 0;
                    for (i = 0; i < selectedRows.length; i++) {
                        modeleJTableOffreEchantillons.removeRow(selectedRows[i]);
                    }
                    for (int k = 0; k < vue.getjTableOffreEchantillons().getRowCount(); k++) {
                        if (k <= selectedRows[i - 1]) {
                            vue.getjTableOffreEchantillons().setRowSelectionInterval(k, k);
                        }
                    }
                    if (vue.getjTableOffreEchantillons().getRowCount() < 6) {
                        vue.getjButtonToutSupprimer().setVisible(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(vue, "Veuillez sélectionner au moins une ligne du tableau à retirer");
                }
            } else if (evenement.getSource() == vue.getjButtonFermer()) {
                if (editMode) {
                    int rep = JOptionPane.showConfirmDialog(getVue(), "Vous êtes toujours mode édition, êtes vous sur ?", "Mode édition", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (rep == JOptionPane.YES_OPTION) {
                        // fermer
                        ctrlPrincipal.fermer(getVue());
                    }
                } else {
                    ctrlPrincipal.fermer(getVue());
                }
            } else if (evenement.getSource() == vue.getjButtonDetailsPraticien()) {
                if (!editMode) {
                    ctrlPrincipal.afficherPraticien(lesRapportsVisite.get(indexRapport).getNumeroPraticien());
                } else {
                    if (editMode) {
                        int index = vue.getjComboBoxPraticien().getSelectedIndex();
                        if (index == 0) {
                            JOptionPane.showMessageDialog(vue, "Veuillez sélectionner au moins une ligne du tableau à retirer");
                        } else {
                            ctrlPrincipal.afficherPraticien(tousPraticiens.get(vue.getjComboBoxPraticien().getSelectedIndex() - 1).getNumero());
                        }
                    } else {
                        ctrlPrincipal.afficherPraticien(tousPraticiens.get(vue.getjComboBoxPraticien().getSelectedIndex() - 1).getNumero());
                    }
                }
            } else if (evenement.getSource() == vue.getjButtonPrecedent()) {
                indexRapport -= 1;
                if (indexRapport < 0) {
                    indexRapport = lesRapportsVisite.size() - 1;
                    afficherLeRapport();
                } else {
                    afficherLeRapport();
                }
            } else if (evenement.getSource() == vue.getjButtonSuivant()) {
                indexRapport += 1;
                if (indexRapport > lesRapportsVisite.size() - 1) {
                    indexRapport = 0;
                    afficherLeRapport();
                } else {
                    afficherLeRapport();
                }
            } else if (evenement.getSource() == vue.getjButtonNouveau()) {
                if (editMode) {
                    int rep = JOptionPane.showConfirmDialog(getVue(), "Êtes vous sûr ?", "Annuler", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (rep == JOptionPane.YES_OPTION) {
                        editMode = false;
                        isEditing(editMode);
                        afficherLeRapport();
                    }
                } else {
                    editMode = true;
                    isEditing(editMode);
                }
            } else if (evenement.getSource() == vue.getjButtonMenuGeneral()) {
                if (editMode) {
                    int rep = JOptionPane.showConfirmDialog(getVue(), "Vous êtes toujours mode édition, êtes vous sur ?", "Mode édition", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (rep == JOptionPane.YES_OPTION) {
                        // afficher menu général
                        ctrlPrincipal.afficherMenuGeneral(vue);
                    }
                } else {
                    ctrlPrincipal.afficherMenuGeneral(vue);
                }
            }
        }
    }

    /**
     * Classe personnalisée pour ajouter les éléments dans la combobox
     */
    class CheckBoxCellRenderer implements TableCellRenderer {

        JComboBox combo;

        public CheckBoxCellRenderer(JComboBox comboBox) {
            this.combo = new JComboBox();
            for (int i = 0; i < comboBox.getItemCount(); i++) {
                combo.addItem(comboBox.getItemAt(i));
            }
        }

        @Override
        public Component getTableCellRendererComponent(JTable jtable,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row, int column) {
            combo.setSelectedItem(value);
            return combo;
        }
    }

    // contrôle de la vue
    /**
     * Afficher la page initiale correspondant à un rapport de visite lié au
     * visiteur
     */
    private void afficherLeRapport() {
        if (lesRapportsVisite.size() > 0) {
            RapportVisite rapportAffiche = lesRapportsVisite.get(indexRapport);
            modeleJTableOffreEchantillons.setRowCount(0);
            // Une ligne de la table est un tableau d'objets
            Object[] rowData = new Object[2];
            for (Offre uneOffre : lesOffresEchantillons) {
                if (uneOffre.getNumeroRapport() == rapportAffiche.getNumeroRapport()) {
                    rowData[0] = uneOffre.getMedicament();
                    rowData[1] = uneOffre.getQuantiteOffre();
                    modeleJTableOffreEchantillons.addRow(rowData);
                }
            }
            vue.getjTextFieldNumeroRapport().setText(String.valueOf(rapportAffiche.getNumeroRapport()));
            vue.getjComboBoxPraticien().setModel(modeleJComboBoxLePraticien);
            modeleJComboBoxLePraticien.removeAllElements();
            for (Praticien unPraticien : tousPraticiens) {
                if (unPraticien.getNumero() == rapportAffiche.getNumeroPraticien()) {
                    modeleJComboBoxLePraticien.addElement(unPraticien.getNom());
                }
            }
            vue.getjDateChooserDateRapport().setDate(rapportAffiche.getDateRapport());
            vue.getjTextFieldMotifVisite().setText(rapportAffiche.getMotifRapport());
            vue.getjTextAreaBilan().setText(rapportAffiche.getBilanRapport());
        }
    }

    // méthodes d'action
    private void rechercherIndexVisiteurConnecte() {
        Visiteur visiteurLu = FileReader.getConnectedVisiteur(vue);
        String matriculeVisiteur = visiteurLu.getMatricule();
        int indexVisiteur = 0;
        for (RapportVisite unRapportVisite : lesRapportsVisite) {
            if (unRapportVisite.getMatriculeVisiteur().equals(matriculeVisiteur)) {
                indexRapport = indexVisiteur;
            }
            indexVisiteur++;
        }
        afficherLeRapport();
    }

    // ACCESSEURS et MUTATEURS
    /**
     *
     * @return
     */
    public VueRapportVisite getVue() {
        return vue;
    }

    /**
     *
     * @param vue
     */
    public void setVue(VueRapportVisite vue) {
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
