package controleurs;

import gsb_visiteurs.Connexion;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import vues.VueConnexion;
import modele.dao.DaoConnexion;
import modele.dao.Jdbc;
import modele.metier.Visiteur;

/**
 * @author bjaouen
 * @version 7/12/2016 - 1.0
 */
public class CtrlConnexion implements WindowListener, ActionListener, KeyListener, MouseListener, FocusListener {

    /**
     * La vue connexion
     */
    private VueConnexion vue; // LA VUE
    private final CtrlPrincipal ctrlPrincipal;
    private boolean loginFocused = false;
    private boolean mdpFocused = false;
    private boolean loginCorrect = false;
    private boolean mdpCorrect = false;
    private int bddSelected = -1;
    private final String PROD_BDD = "oracleProduction";
    private final String DEV_BDD = "oracleExpress";
    private boolean isConnected = false;
    private TimerTask timerTask;
    private int timerTime;
    private Timer timer;
    private boolean up;
    private String loading = "Connexion";

    /**
     * Constructeur du controlleur connexion
     *
     * @param vue
     * @param ctrl
     */
    public CtrlConnexion(final VueConnexion vue, CtrlPrincipal ctrl) {
        this.vue = vue;
        this.ctrlPrincipal = ctrl;
        this.vue.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/images/gsb_logo.png")).getImage());

        this.vue.addWindowListener(this);

        // ajout des boutons de la vue au listener
        enable();
        vue.getjButtonOk().addActionListener(this);
        vue.getjButtonQuitter().addActionListener(this);
        vue.getjButtonChangeBdd().addActionListener(this);
        vue.getjCheckBoxAfficher().addActionListener(this);
        vue.getjButtonReconnection().addActionListener(this);
        vue.getjCheckBoxSouvenirId().addActionListener(this);
        vue.getjButtonChangeBdd().setVisible(false);
        vue.getjLabelBaseDeDonnee().setVisible(false);
        vue.getjCheckBoxSouvenirMdp().setEnabled(false);
        vue.getjLabelBdd().setVisible(false);
        vue.getjButtonReconnection().setVisible(false);
        //paramétrage des composant a selectionné lors d'une tabulation pour rendre plus rapide la saisie
        vue.getjTextFieldLogin().setNextFocusableComponent(vue.getjPasswordMdp());
        vue.getjPasswordMdp().setNextFocusableComponent(vue.getjButtonOk());

        //REMPLISSAGE AUTOMATIQUE DES IDENTIFIANTS POUR LES TESTS
        //vue.getjTextFieldLogin().setText("swiss");
        //vue.getjPasswordMdp().setText("18-jun-2003");
        //affichée les données sauvegardées
        displaySavedData();

        checkSelectedBdd();

        vue.getjTextFieldLogin().addKeyListener(this);

        vue.getjPasswordMdp().addKeyListener(this);

        //vérification de la validité du login à chaque caractère saisi
        /*vue.getjTextFieldLogin().getDocument().addDocumentListener(new DocumentListener() {
         public void changedUpdate(DocumentEvent e) {
         checkLogin();
         }

         public void removeUpdate(DocumentEvent e) {
         checkLogin();
         }

         public void insertUpdate(DocumentEvent e) {
         checkLogin();
         }
         });

         //vérification de la validité du mot de passe à chaque caractère saisi
         vue.getjPasswordMdp().getDocument().addDocumentListener(new DocumentListener() {
         public void changedUpdate(DocumentEvent e) {
         checkMdp();
         }

         public void removeUpdate(DocumentEvent e) {
         checkMdp();
         }

         public void insertUpdate(DocumentEvent e) {
         checkMdp();
         }
         });*/
        vue.getjTextFieldLogin().addFocusListener(this);

        vue.getjPasswordMdp().addFocusListener(this);

        vue.getjButtonAfficherMdp().addMouseListener(this);
    }

    /**
     *
     */
    public void connecter() {
        loading();
        try {
            Jdbc.getInstance().connecter();
            isConnected = true;
            enable();
        } catch (ClassNotFoundException | SQLException ex) {
            vue.getjButtonReconnection().setVisible(true);
            vue.getjLabelEtat().setText("Échec de la connexion !");
            Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        stopLoading();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource().equals(vue.getjTextFieldLogin())) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                connection();
            }
        }
        if (e.getSource().equals(vue.getjPasswordMdp())) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                connection();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource().equals(vue.getjButtonAfficherMdp())) {
            vue.getjPasswordMdp().setEchoChar((char) 0);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource().equals(vue.getjButtonAfficherMdp())) {
            if (!vue.getjCheckBoxAfficher().isSelected()) {
                vue.getjPasswordMdp().setEchoChar('•');
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource().equals(vue.getjTextFieldLogin())) {
            loginFocused = true;
            vue.getjLabelCheckLogin().setVisible(false);
        }
        if (e.getSource().equals(vue.getjPasswordMdp())) {
            mdpFocused = true;
            vue.getjLabelCheckMdp().setVisible(false);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource().equals(vue.getjTextFieldLogin())) {
            vue.getjLabelCheckLogin().setVisible(true);
            loginFocused = false;
            checkLogin();
        }
        if (e.getSource().equals(vue.getjPasswordMdp())) {
            vue.getjLabelCheckMdp().setVisible(true);
            mdpFocused = false;
            if (loginCorrect) {
                checkMdp();
            }
        }
    }

    class timerTask extends TimerTask {

        @Override
        public void run() {
            doTimer();
        }

    }

    private void doTimer() {
        if (timerTime == 0) {
            up = true;
        } else if (timerTime == 5) {
            up = false;
        }
        if (up) {
            timerTime++;
            loading += ".";
            vue.getjLabelEtat().setText(loading);
        } else {
            timerTime--;
            loading = loading.substring(0, loading.length() - 1);
            vue.getjLabelEtat().setText(loading);
        }
    }

    private void loading() {
        vue.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        timerTime = 0;

        timer = new Timer();
        timerTask = new timerTask();
        timer.scheduleAtFixedRate(timerTask, 0, 250);
    }

    /**
     *
     */
    public void stopLoading() {
        timerTask.cancel();
        timer.cancel();
        vue.getContentPane().setCursor(null);
    }

    private void enable() {
        vue.getjLabelEtat().setVisible(!isConnected);
        vue.getjButtonReconnection().setVisible(!isConnected);
        vue.getjButtonOk().setEnabled(isConnected);
        vue.getjButtonAfficherMdp().setEnabled(isConnected);
        vue.getjTextFieldLogin().setEnabled(isConnected);
        vue.getjCheckBoxAfficher().setEnabled(isConnected);
        vue.getjCheckBoxSouvenirId().setEnabled(isConnected);
        vue.getjCheckBoxSouvenirMdp().setEnabled(isConnected);
        vue.getjPasswordMdp().setEnabled(isConnected);
    }

    /**
     *
     */
    private void displaySavedData() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("login.data"));
            String login = (String) ois.readObject();
            vue.getjTextFieldLogin().setText(login);
            if (!login.equals("")) {
                vue.getjCheckBoxSouvenirMdp().setEnabled(isConnected);
                vue.getjCheckBoxSouvenirId().setSelected(true);
            }
            ois = new ObjectInputStream(new FileInputStream("mdp.data"));
            String mdp = (String) ois.readObject();
            vue.getjPasswordMdp().setText(mdp);
            if (!mdp.equals("")) {
                vue.getjCheckBoxSouvenirMdp().setSelected(true);
            }
        } catch (FileNotFoundException fnfe) {
            Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, fnfe);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     */
    private void changeBdd() {
        String[] options = null;
        if (bddSelected == 1) {
            options = new String[]{"ORACLE eXpress"};
        } else if (bddSelected == 0) {
            options = new String[]{"ORACLE Production"};
        } else {
            options = new String[]{"ORACLE eXpress", "ORACLE Production"};
        }
        String ObjButtons[] = options;
        int PromptResult = JOptionPane.showOptionDialog(getVue(), "Choissiez la base de donnée sur laquelle exécuter l'application :", "Changement base de donnée", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, ObjButtons, ObjButtons);
        if (PromptResult == JOptionPane.YES_OPTION) {
            if (options.length == 1) {
                doChange(bddSelected);
            } else {
                doChange(0);
            }
        } else if (PromptResult == JOptionPane.NO_OPTION) {
            doChange(1);
        }
    }

    private void doChange(int bdd) {
        String nomBdd = "";
        if (bdd == 1) {
            bddSelected = 0;
            write(DEV_BDD);
            vue.getjLabelBaseDeDonnee().setText(DEV_BDD);
            nomBdd = DEV_BDD;
        } else {
            bddSelected = 1;
            vue.getjLabelBaseDeDonnee().setText(PROD_BDD);
            write(PROD_BDD);
            nomBdd = PROD_BDD;
        }
        ctrlPrincipal.doWait(true);
        try {
            Jdbc.creer();
            Jdbc.getInstance().connecter();
            JOptionPane.showMessageDialog(vue, "Base de donnée utilisée : " + nomBdd);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(vue, ex, "Main - classe JDBC non trouvée ", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vue, ex, "Main - échec de connexion ", JOptionPane.ERROR_MESSAGE);
        }

        ctrlPrincipal.doWait(false);

    }

    private void write(String oracleBDD) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("OracleBDD.data"));
            oos.writeObject(oracleBDD);
        } catch (IOException ex) {
            Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private ObjectInputStream read() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("OracleBDD.data"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, ex);
            vue.getjLabelBaseDeDonnee().setText("Aucune");
        } catch (IOException ex) {
            Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, ex);
            vue.getjLabelBaseDeDonnee().setText("Aucune");
        }
        return ois;
    }

    /**
     *
     */
    private void checkLogin() {
        try {
            int code = DaoConnexion.verifierInfosConnexion(vue.getjTextFieldLogin().getText(), vue.getjPasswordMdp().getText());
            if (code == 1 || code == 0) {//mauvais login bon mdp
                vue.getjLabelCheckLogin().setText("✘");
                vue.getjLabelCheckLogin().setForeground(Color.red);
                loginCorrect = false;
            } else {
                vue.getjLabelCheckLogin().setText("✔");
                vue.getjLabelCheckLogin().setForeground(Color.green);
                loginCorrect = true;
                /*if (mdpCorrect) {
                 openApp();
                 }*/
            }
        } catch (SQLException ex) {
            Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    private void checkMdp() {
        try {
            int code = DaoConnexion.verifierInfosConnexion(vue.getjTextFieldLogin().getText(), vue.getjPasswordMdp().getText());
            if (code == 10 || code == 0) {//mauvais login bon mdp
                vue.getjLabelCheckMdp().setText("✘");
                vue.getjLabelCheckMdp().setForeground(Color.red);
                mdpCorrect = false;
            } else {
                vue.getjLabelCheckMdp().setText("✔");
                vue.getjLabelCheckMdp().setForeground(Color.green);
                mdpCorrect = true;
                /*if (loginCorrect) {
                 openApp();
                 }*/
            }
        } catch (SQLException ex) {
            Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evenement) {
        if (evenement.getSource().equals(vue.getjButtonReconnection())) {
            connecter();
        }
        if (evenement.getSource() == vue.getjButtonQuitter()) {
            ctrlPrincipal.quitterApplication();
        }
        if (isConnected) {
            if (evenement.getSource() == vue.getjButtonOk()) {
                connection();
            }
            if (evenement.getSource() == vue.getjButtonChangeBdd()) {
                changeBdd();
            }
            if (evenement.getSource().equals(vue.getjCheckBoxSouvenirId())) {
                if (vue.getjCheckBoxSouvenirId().isSelected()) {
                    vue.getjCheckBoxSouvenirMdp().setEnabled(true);
                } else {
                    vue.getjCheckBoxSouvenirMdp().setEnabled(false);
                    vue.getjCheckBoxSouvenirMdp().setSelected(false);
                }
            }
            if (evenement.getSource() == vue.getjCheckBoxAfficher()) {
                if (vue.getjCheckBoxAfficher().isSelected()) {
                    vue.getjButtonAfficherMdp().setEnabled(false);
                    vue.getjPasswordMdp().setEchoChar((char) 0);
                } else {
                    vue.getjButtonAfficherMdp().setEnabled(true);
                    vue.getjPasswordMdp().setEchoChar('•');
                }
            }
        }
    }

    private void checkSelectedBdd() {
        try {
            String bdd = (String) read().readObject();
            if (bdd != null) {
                switch (bdd) {
                    case DEV_BDD:
                        vue.getjLabelBaseDeDonnee().setText(DEV_BDD);
                        bddSelected = 0;
                        break;
                    case PROD_BDD:
                        vue.getjLabelBaseDeDonnee().setText(PROD_BDD);
                        bddSelected = 1;
                        break;
                    default:
                        vue.getjLabelBaseDeDonnee().setText("Aucune base de donnée sélectionnée");
                        bddSelected = -1;
                        break;
                }
            }
        } catch (IOException | ClassNotFoundException | NullPointerException ex) {
            Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Tentative de connection
     */
    private void connection() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("OracleBDD.data"));
            ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            changeBdd();//si le chargement des infos de la base de donnée n'a pas fonctionné on demande à l'utilisateur de choisir
        }

        String login = vue.getjTextFieldLogin().getText().replaceAll("\\s{2,}", "").trim();
        String mdp = vue.getjPasswordMdp().getText().replaceAll("\\s{2,}", "").trim();
        if (mdp.equals("") && login.equals("")) {
            JOptionPane.showMessageDialog(vue, "Veuillez spécifier votre login et votre mot de passe.");
            vue.getjTextFieldLogin().requestFocus();
            vue.getjTextFieldLogin().selectAll();
        } else if (mdp.equals("")) {
            if (!loginFocused) {
                JOptionPane.showMessageDialog(vue, "Veuillez spécifier votre mot de passe.");
            }
            vue.getjPasswordMdp().requestFocus();
            vue.getjPasswordMdp().selectAll();
        } else if (login.equals("")) {
            JOptionPane.showMessageDialog(vue, "Veuillez spécifier votre login.");
            vue.getjTextFieldLogin().requestFocus();
            vue.getjTextFieldLogin().selectAll();
        } else {
            try {
                //ouvre le menu général si les données sont correctes
                int code = DaoConnexion.verifierInfosConnexion(login, mdp);
                if (code == 11) {//bon login et mdp
                    openApp();
                } else if (code == 0) {//mauvais login et mdp
                    JOptionPane.showMessageDialog(vue, "Votre login et votre mot de passe ne sont pas corrects.");
                    vue.getjTextFieldLogin().requestFocus();
                    vue.getjTextFieldLogin().selectAll();
                } else if (code == 1) {//mauvais login bon mdp
                    JOptionPane.showMessageDialog(vue, "Votre login est incorrect.");
                    vue.getjTextFieldLogin().requestFocus();
                    vue.getjTextFieldLogin().selectAll();
                } else if (code == 10) {//bon login mauvais mdp
                    JOptionPane.showMessageDialog(vue, "Votre mot de passe est incorrect.");
                    vue.getjPasswordMdp().requestFocus();
                    vue.getjPasswordMdp().selectAll();
                }
                checkLogin();
                if (loginCorrect) {
                    checkMdp();
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(vue, ex, "Erreur de communication avec la base de donnée. ", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     *
     */
    public void openApp() {
        try {
            Visiteur leVisiteur = DaoConnexion.getConnectedVisiteur(vue.getjTextFieldLogin().getText(), vue.getjPasswordMdp().getText());
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("visiteur.data"));
                oos.writeObject(leVisiteur);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(vue, ex, "Impossible de sauvegarder l'état de l'objet visiteur.", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vue, ex, "Erreur SQL ", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        saveData();
        // afficher la vue
        ctrlPrincipal.afficherMenuGeneral(getVue());
    }

    /**
     *
     */
    private void saveData() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("login.data"));
            if (vue.getjCheckBoxSouvenirId().isSelected()) {
                oos.writeObject(vue.getjTextFieldLogin().getText());
            } else {
                oos.writeObject("");
            }
            oos = new ObjectOutputStream(new FileOutputStream("mdp.data"));
            if (vue.getjCheckBoxSouvenirMdp().isSelected()) {
                oos.writeObject(vue.getjPasswordMdp().getText());
            } else {
                oos.writeObject("");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(vue, ex, "Impossible de sauvegarder les informations saisies.", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ACCESSEURS et MUTATEURS
    /**
     *
     * @return
     */
    public VueConnexion getVue() {
        return vue;
    }

    /**
     *
     * @param vue
     */
    public void setVue(VueConnexion vue) {
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
