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
import javax.swing.RowSorter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
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
    private DefaultComboBoxModel modeleJComboBoxMedicament = new DefaultComboBoxModel();
    private DefaultTableModel modeleJTableOffreEchantillons = new DefaultTableModel();
    private ArrayList<RapportVisite> lesRapportsVisite = new ArrayList<>();
    private ArrayList<Praticien> tousPraticiens = new ArrayList<>();
    private ArrayList<Medicament> tousMedicaments = new ArrayList<>();
    private ArrayList<Offre> lesOffresEchantillons = new ArrayList<>();
    private ArrayList<Offre> offresEchantillons = new ArrayList<>();
    private int indexRapport = 0;
    private boolean creationMode = false;
    private boolean editMode = false;
    private int maxNum = 0;
    private final TableCellEditor editor;
    private boolean hasValidate = false;
    private JTextField offreEchantillon;
    private JComboBox JComboBoxLesMedicaments = new JComboBox();
    private final TableCellEditor cellEditor;
    private final TableCellRenderer cellRenderer;
    private final TableCellRenderer renderer;
    private final Visiteur connectedVisiteur;

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

        connectedVisiteur = FileReader.getConnectedVisiteur(vue);

        this.vue.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent fe) {
                updateWindows();//si l'on a quitté la fenetre l'on peut avoir modifié des données sur d'autres vu (praticien, médicaments) on raffraichit donc la page
            }

            @Override
            public void focusLost(FocusEvent fe) {
            }
        });

        editor = vue.getjTableOffreEchantillons().getDefaultEditor(Object.class);
        renderer = vue.getjTableOffreEchantillons().getDefaultRenderer(Object.class);

        vue.getjComboBoxPraticien().setModel(modeleJComboBoxPraticiens);
        vue.getjTableOffreEchantillons().setModel(modeleJTableOffreEchantillons);
        vue.getjTableOffreEchantillons().putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

        modeleJTableOffreEchantillons.addColumn("Médicaments");
        modeleJTableOffreEchantillons.addColumn("Nb échantillon(s)");

        TableColumn column = vue.getjTableOffreEchantillons().getColumnModel().getColumn(0);
        cellEditor = column.getCellEditor();
        cellRenderer = column.getCellRenderer();
        TableRowSorter <TableModel> sorter = new TableRowSorter<>(vue.getjTableOffreEchantillons().getModel());
        sorter.setSortable(0, false); 
        sorter.setSortable(1, false); 
        
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
        vue.getjButtonSupprimer().addActionListener(ecouteur);
        JComboBoxLesMedicaments.addActionListener(ecouteur);

        JTextFieldDateEditor jDateChooserEditor = (JTextFieldDateEditor) vue.getjDateChooserDateRapport().getDateEditor();
        jDateChooserEditor.setEditable(false);

        vue.getjTableOffreEchantillons().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (!creationMode) {
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
        isEditing(creationMode);
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

    private void showPrevious() throws SQLException {
        getAllOffres();
        getAllRapports();
        rechercherIndexVisiteurConnecte();
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
        modeleJComboBoxMedicament.removeAllElements();
        modeleJComboBoxMedicament.addElement("Choisissez un médicament");
        for (Medicament unMedicament : tousMedicaments) {
            modeleJComboBoxMedicament.addElement(unMedicament.getDepotLegal());
        }
    }

    private void getAllPraticiens() throws SQLException {
        tousPraticiens = DaoPraticien.getAllPraticiens();
        modeleJComboBoxPraticiens.removeAllElements();
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
        vue.getjDateChooserDateRapport().setEnabled(b);
        vue.getjTextFieldMotifVisite().setEditable(b);
        vue.getjTextAreaBilan().setEditable(b);
        vue.getjComboBoxPraticien().setEnabled(b);
        vue.getjButtonPrecedent().setEnabled(!b);
        vue.getjButtonSuivant().setEnabled(!b);
        vue.getjButtonPlus().setVisible(b);
        vue.getjButtonMoins().setVisible(b);
        if (b) {
            vue.getjButtonNouveau().setText("Annuler");
            vue.getjButtonValider().setText("Valider");
            vue.getjTableOffreEchantillons().setDefaultEditor(Object.class, editor);
            if (creationMode) {
                formulaire();
            } else {

            }
        } else {
            vue.getjButtonNouveau().setText("Nouveau");
            vue.getjButtonValider().setText("Editer");
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
        vue.getjButtonValider().setVisible(true);
        vue.getjTextFieldNumeroRapport().setText(String.valueOf(maxNum + 1));
        vue.getjComboBoxPraticien().setSelectedIndex(0);
        vue.getjDateChooserDateRapport().setCalendar(null);
        vue.getjTextFieldMotifVisite().setText("");
        vue.getjTextAreaBilan().setText("");
        offresEchantillons = lesOffresEchantillons;
        modeleJTableOffreEchantillons.setRowCount(0);
    }

    private void guidage() {
        vue.getjDateChooserDateRapport().setToolTipText("Date du rapport");
        vue.getjTextAreaBilan().setToolTipText("Bilan du rapport");
    }

    private class Ecouteur implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evenement) {
            if (evenement.getSource() == JComboBoxLesMedicaments) {

            }
            if (evenement.getSource() == vue.getjButtonSupprimer()) {
                int numeroRapport = Integer.valueOf(vue.getjTextFieldNumeroRapport().getText());
                int rep = JOptionPane.showConfirmDialog(getVue(), "Voulez vous vraiment supprimer le rapport n°" + numeroRapport + "?", "Supprimer?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (rep == JOptionPane.YES_OPTION) {
                    try {
                        DaoRapportVisite.delete(numeroRapport);
                        showPrevious();
                        JOptionPane.showMessageDialog(vue, "Succès");
                    } catch (SQLException ex) {
                        Logger.getLogger(CtrlRapportVisite.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (rep == JOptionPane.NO_OPTION) {

                }
            }
            if (evenement.getSource() == vue.getjButtonToutSupprimer()) {
                int rowCount = modeleJTableOffreEchantillons.getRowCount();
                for (int i = rowCount - 1; i >= 0; i--) {
                    modeleJTableOffreEchantillons.removeRow(i);
                }
                vue.getjButtonToutSupprimer().setVisible(false);
            }
            if (evenement.getSource() == vue.getjButtonValider()) {
                if (creationMode) {
                    String message = "Veuillez préciser :\n";
                    boolean erreur = false;
                    if (vue.getjTableOffreEchantillons().getRowCount() == 0) {
                        /*message += "-l'offre échantillon du rapport\n";
                         erreur = true;*/
                    } else {
                        int rowCount = vue.getjTableOffreEchantillons().getRowCount();
                        int columnCount = vue.getjTableOffreEchantillons().getColumnCount();
                        for (int row = 0; row < rowCount; row++) {
                            if (JComboBoxLesMedicaments.getSelectedIndex() == 0) {
                                message += "-le médicament\n";
                                JComboBoxLesMedicaments.requestFocus();
                                JComboBoxLesMedicaments.showPopup();
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
                            creationMode = false;
                            isEditing(creationMode);
                            afficherLeRapport();
                        }
                    } else {

                        //creer le nouveau rapport
                        Praticien lePraticien = tousPraticiens.get(vue.getjComboBoxPraticien().getSelectedIndex() - 1);
                        int numeroRapport = Integer.valueOf(vue.getjTextFieldNumeroRapport().getText());
                        int numeroPraticien = lePraticien.getNumero();
                        Date dateRapport = null;
                        if (vue.getjDateChooserDateRapport().getDate() != null) {
                            dateRapport = new Date(vue.getjDateChooserDateRapport().getDate().getTime());
                        }
                        String motifRapport = vue.getjTextFieldMotifVisite().getText();
                        String bilanRapport = vue.getjTextAreaBilan().getText();
                        Praticien praticien = null;
                        try {
                            praticien = DaoPraticien.getOneByNum(numeroPraticien);
                        } catch (SQLException ex) {
                            Logger.getLogger(CtrlRapportVisite.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        RapportVisite nouveauRapportVisite = new RapportVisite(connectedVisiteur, numeroRapport, praticien, dateRapport, motifRapport, bilanRapport);
                        System.out.println(nouveauRapportVisite);
                        try {
                            DaoRapportVisite.insert(nouveauRapportVisite);
                        } catch (SQLException ex) {
                            Logger.getLogger(CtrlRapportVisite.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        SwingUtilities.invokeLater(new Runnable() {

                            @Override
                            public void run() {
                                //creer la nouvelle offre
                                for (int i = 0; i < vue.getjTableOffreEchantillons().getRowCount(); i++) {
                                    Medicament medicament = tousMedicaments.get(JComboBoxLesMedicaments.getSelectedIndex() - 1);
                                    int nbEchantillons = -1;
                                    if (modeleJTableOffreEchantillons.getValueAt(i, 1) != null) {
                                        nbEchantillons = Integer.valueOf((String) modeleJTableOffreEchantillons.getValueAt(i, 1));
                                    }
                                    Offre nouvelleOffre = new Offre(connectedVisiteur, nouveauRapportVisite, medicament, nbEchantillons);
                                    System.out.println(nouvelleOffre);
                                    try {
                                        DaoOffre.insert(nouvelleOffre);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(CtrlRapportVisite.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        });

                        creationMode = false;
                        isEditing(creationMode);
                        try {
                            showNew(numeroRapport);
                        } catch (SQLException ex) {
                            Logger.getLogger(CtrlRapportVisite.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    if (editMode) {
                        editMode = false;
                        isEditing(editMode);
                    } else {
                        editMode = true;
                        isEditing(editMode);
                    }
                }
            } else if (evenement.getSource() == vue.getjButtonPlus()) {
                Object[] rowData = new Object[2];
                JComboBoxLesMedicaments.addItem("Sélectionnez un médicament");
                for (Medicament unMedicament : tousMedicaments) {
                    JComboBoxLesMedicaments.addItem(unMedicament.getDepotLegal());
                }

                TableColumn medicaments = vue.getjTableOffreEchantillons().getColumnModel().getColumn(0);
                rowData[0] = medicaments;
                medicaments.setCellEditor(new DefaultCellEditor(JComboBoxLesMedicaments));
                medicaments.setCellRenderer(new CheckBoxCellRenderer(JComboBoxLesMedicaments));
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
                if (creationMode) {
                    int rep = JOptionPane.showConfirmDialog(getVue(), "Vous êtes toujours mode édition, êtes vous sur ?", "Mode édition", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (rep == JOptionPane.YES_OPTION) {
                        // fermer
                        ctrlPrincipal.fermer(getVue());
                    }
                } else {
                    ctrlPrincipal.fermer(getVue());
                }
            } else if (evenement.getSource() == vue.getjButtonDetailsPraticien()) {
                if (!creationMode) {
                    ctrlPrincipal.afficherPraticien(lesRapportsVisite.get(indexRapport).getPraticien().getNumero());
                } else {
                    if (creationMode) {
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
                if (creationMode || editMode) {
                    int rep = JOptionPane.showConfirmDialog(getVue(), "Êtes vous sûr ?", "Annuler", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (rep == JOptionPane.YES_OPTION) {
                        creationMode = false;
                        editMode = false;
                        isEditing(creationMode);
                        afficherLeRapport();
                    }
                } else {
                    creationMode = true;
                    isEditing(creationMode);
                }
            } else if (evenement.getSource() == vue.getjButtonMenuGeneral()) {
                if (creationMode) {
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
            if (!rapportAffiche.getVisiteur().getMatricule().equals(connectedVisiteur.getMatricule())) {
                vue.getjButtonSupprimer().setVisible(false);
                vue.getjButtonValider().setVisible(false);
            } else {
                vue.getjButtonSupprimer().setVisible(true);
                vue.getjButtonValider().setVisible(true);
            }
            // Une ligne de la table est un tableau d'objets
            Object[] rowData = new Object[2];
            offresEchantillons.clear();
            for (Offre uneOffre : lesOffresEchantillons) {
                if (uneOffre.getRapport().getNumeroRapport() == rapportAffiche.getNumeroRapport()) {
                    rowData[0] = uneOffre.getMedicament().getDepotLegal();
                    rowData[1] = uneOffre.getQuantiteOffre();
                    offresEchantillons.add(uneOffre);
                    modeleJTableOffreEchantillons.addRow(rowData);
                }
            }
            vue.getjTextFieldNumeroRapport().setText(String.valueOf(rapportAffiche.getNumeroRapport()));
            int i = 0;
            for (Praticien unPraticien : tousPraticiens) {
                if (unPraticien.getNumero() == rapportAffiche.getPraticien().getNumero()) {
                    vue.getjComboBoxPraticien().setSelectedIndex(i + 1);
                }
                i++;
            }
            vue.getjDateChooserDateRapport().setDate(rapportAffiche.getDateRapport());
            vue.getjTextFieldMotifVisite().setText(rapportAffiche.getMotifRapport());
            vue.getjTextAreaBilan().setText(rapportAffiche.getBilanRapport());
            vue.getjTextAreaBilan().setCaretPosition(0);
        }
    }

    // méthodes d'action
    private void rechercherIndexVisiteurConnecte() {
        String matriculeVisiteur = connectedVisiteur.getMatricule();
        int indexVisiteur = 0;
        for (RapportVisite unRapportVisite : lesRapportsVisite) {
            if (unRapportVisite.getVisiteur().getMatricule().equals(matriculeVisiteur)) {
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
