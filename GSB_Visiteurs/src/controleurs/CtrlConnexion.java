package controleurs;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import vues.VueConnexion;
import modele.dao.DaoConnexion;
import modele.metier.Visiteur;

/**
 * @author bjaouen
 * @version 7/12/2016 - 1.0
 */
public class CtrlConnexion implements WindowListener {

    private VueConnexion vue; // LA VUE
    private final CtrlPrincipal ctrlPrincipal;
    private final Ecouteur ecouteur;
    private boolean loginFocused = false;
    private boolean mdpFocused = false;
    private boolean loginCorrect = false;
    private boolean mdpCorrect = false;

    /**
     *
     * @param vue
     * @param ctrl
     */
    public CtrlConnexion(final VueConnexion vue, CtrlPrincipal ctrl) {
        this.ecouteur = new Ecouteur();
        this.vue = vue;
        this.ctrlPrincipal = ctrl;

        this.vue.addWindowListener(this);

        // ajout des boutons de la vue au listener
        vue.getjButtonOk().addActionListener(ecouteur);
        vue.getjButtonQuitter().addActionListener(ecouteur);
        vue.getjButtonChangeBdd().addActionListener(ecouteur);

        //paramétrage des composant a selectionné lors d'une tabulation pour rendre plus rapide la saisie
        vue.getjTextFieldLogin().setNextFocusableComponent(vue.getjPasswordMdp());
        vue.getjPasswordMdp().setNextFocusableComponent(vue.getjButtonOk());

        //REMPLISSAGE AUTOMATIQUE DES IDENTIFIANTS POUR LES TESTS
        //vue.getjTextFieldLogin().setText("swiss");
        //vue.getjPasswordMdp().setText("18-jun-2003");
        
        //affichée les données sauvegardées
        displaySavedData();
                
        vue.getjTextFieldLogin().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    connection();
                }
            }
        });

        vue.getjPasswordMdp().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    connection();
                }
            }
        });

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
        
        vue.getjTextFieldLogin().addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                loginFocused = true;
            }

            @Override
            public void focusLost(FocusEvent e) {
                loginFocused = false;
                checkLogin();
            }

        });

        vue.getjPasswordMdp().addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                mdpFocused = true;
            }

            @Override
            public void focusLost(FocusEvent e) {
                mdpFocused = false;
                checkMdp();
            }
        });

        vue.getjButtonAfficherMdp().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                vue.getjPasswordMdp().setEchoChar('•');
            }

            @Override
            public void mousePressed(MouseEvent me) {
                vue.getjPasswordMdp().setEchoChar((char) 0);
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
    }

    /**
     *
     */
    private void displaySavedData() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("logins.data"));
            String login = (String) ois.readObject();
            vue.getjTextFieldLogin().setText(login);
            if (!login.equals("")) {
                vue.getjCheckBoxSouvenirId().setSelected(true);
            }
            ois = new ObjectInputStream(new FileInputStream("mdps.data"));
            String mdp = (String) ois.readObject();
            vue.getjPasswordMdp().setText(mdp);
            if (!mdp.equals("")) {
                vue.getjCheckBoxSouvenirMdp().setSelected(true);
            }
        }catch (FileNotFoundException fnfe){
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
        String ObjButtons[] = {"ORACLE eXpress", "ORACLE Production"};
        int PromptResult = JOptionPane.showOptionDialog(getVue(), "Choissiez la base de donnée sur laquelle exécuter l'application :", "Changement base de donnée", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, ObjButtons, ObjButtons[1]);
        if (PromptResult == JOptionPane.YES_OPTION) {
            String oracleBDD = "oracleExpress";
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("OracleBDD.data"));
                oos.writeObject(oracleBDD);
            } catch (IOException ex) {
                Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (PromptResult == JOptionPane.NO_OPTION) {
            String oracleBDD = "oracleProduction";
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("OracleBDD.data"));
                oos.writeObject(oracleBDD);
            } catch (IOException ex) {
                Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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

    /**
     *
     */
    private class Ecouteur implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evenement) {
            if (evenement.getSource() == vue.getjButtonOk()) {
                connection();
            }
            if (evenement.getSource() == vue.getjButtonQuitter()) {
                ctrlPrincipal.fermer(getVue());
            }
            if (evenement.getSource() == vue.getjButtonChangeBdd()) {
                changeBdd();
            }
        }
    }

    private void checkSelectedBdd(){
        
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
            JOptionPane.showMessageDialog(null, "Veuillez spécifier votre login et votre mot de passe.");
            vue.getjTextFieldLogin().requestFocus();
            vue.getjTextFieldLogin().selectAll();
        } else if (mdp.equals("")) {
            if (!loginFocused) {
                JOptionPane.showMessageDialog(null, "Veuillez spécifier votre mot de passe.");
            }
            vue.getjPasswordMdp().requestFocus();
            vue.getjPasswordMdp().selectAll();
        } else if (login.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez spécifier votre login.");
            vue.getjTextFieldLogin().requestFocus();
            vue.getjTextFieldLogin().selectAll();
        } else {
            try {
                //ouvre le menu général si les données sont correctes
                int code = DaoConnexion.verifierInfosConnexion(login, mdp);
                if (code == 11) {//bon login et mdp
                    openApp();
                } else if (code == 0) {//mauvais login et mdp
                    JOptionPane.showMessageDialog(null, "Votre login et votre mot de passe ne sont pas corrects.");
                    vue.getjTextFieldLogin().requestFocus();
                    vue.getjTextFieldLogin().selectAll();
                } else if (code == 1) {//mauvais login bon mdp
                    JOptionPane.showMessageDialog(null, "Votre login est incorrect.");
                    vue.getjTextFieldLogin().requestFocus();
                    vue.getjTextFieldLogin().selectAll();
                } else if (code == 10) {//bon login mauvais mdp
                    JOptionPane.showMessageDialog(null, "Votre mot de passe est incorrect.");
                    vue.getjPasswordMdp().requestFocus();
                    vue.getjPasswordMdp().selectAll();
                }
                checkLogin();
                checkMdp();

            } catch (SQLException ex) {
                Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, ex);
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
                JOptionPane.showMessageDialog(null, "Impossible de sauvegarder l'état de l'objet visiteur." + ex);
                Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erreur SQL " + ex);
            Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        saveData();
        // afficher la vue
        ctrlPrincipal.afficherMenuGeneral(getVue());
        Visiteur visiteurLu = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("visiteur.data"));
            visiteurLu = (Visiteur) ois.readObject();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Impossible de lire le fichier contenant l'état de l'objet visiteur." + ex);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erreur impossible de charger le fichier contenant l'état de l'objet du visiteur\n" + ex);
        }
        JOptionPane.showMessageDialog(ctrlPrincipal.getCtrlMenuGeneral().getVue(), "Bienvenue sur l'application GSB Visiteur Monsieur " + visiteurLu.getNom(), "Bienvenue", JOptionPane.INFORMATION_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Impossible de sauvegarder les informations saisies." + ex);
        }
    }

    // ACCESSEURS et MUTATEURS
    public VueConnexion getVue() {
        return vue;
    }

    public void setVue(VueConnexion vue) {
        this.vue = vue;
    }

    // REACTIONS EVENEMENTIELLES
    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        ctrlPrincipal.quitterApplication();
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
