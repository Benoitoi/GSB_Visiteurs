package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import modele.dao.DaoVisiteur;
import modele.metier.Laboratoire;
import modele.metier.Secteur;
import modele.metier.Visiteur;
import vues.VueVisiteur;

/**
 * @author bjaouen
 * @version decembre 2016 - 1
 */
public class CtrlVisiteur implements WindowListener {

    private VueVisiteur vue; // LA VUE
    private final CtrlPrincipal ctrlPrincipal;
    private final Ecouteur ecouteur;
    private DefaultComboBoxModel modeleJComboBoxNomsPrenomsVisiteurs = new DefaultComboBoxModel();
    private ArrayList<Visiteur> lesVisiteurs;
    private final DefaultComboBoxModel modeleJComboBoxSecteurs = new DefaultComboBoxModel();
    private ArrayList<Secteur> lesSecteurs;
    private final DefaultComboBoxModel modeleJComboBoxLaboratoires = new DefaultComboBoxModel();
    private ArrayList<Laboratoire> lesLaboratoires;
    private final ArrayList<String> listeVisiteurs = new ArrayList<>();
    private final ArrayList<Visiteur> lesVisiteursTrouvee = new ArrayList<>();
    private final ArrayList<String> nomPrenomTrouve = new ArrayList<>();
    private boolean rechercheFocused = false;
    private boolean comboBoxVisiteursFocused = false;

    /**
     *
     * @param vue
     * @param ctrl
     */
    public CtrlVisiteur(VueVisiteur vue, CtrlPrincipal ctrl) {
        this.ecouteur = new Ecouteur();
        this.vue = vue;
        this.ctrlPrincipal = ctrl;
        
        this.vue.addWindowListener(this);

        vue.getjComboBoxSecteur().setModel(modeleJComboBoxSecteurs);
        vue.getjComboBoxLabo().setModel(modeleJComboBoxLaboratoires);
        vue.getjComboBoxChercher().setModel(modeleJComboBoxNomsPrenomsVisiteurs);
        remplirJComboBoxs();

        vue.getjButtonQuitter().addActionListener(ecouteur);
        vue.getjButtonOk().addActionListener(ecouteur);
        vue.getjButtonPrecedent().addActionListener(ecouteur);
        vue.getjButtonSuivant().addActionListener(ecouteur);
        vue.getjComboBoxChercher().addActionListener(ecouteur);
        vue.getjComboBoxLabo().addActionListener(ecouteur);
        vue.getjComboBoxSecteur().addActionListener(ecouteur);
        vue.getjButtonMenuGeneral().addActionListener(ecouteur);

        vue.getjComboBoxChercher().addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                comboBoxVisiteursFocused = true;
            }

            @Override
            public void focusLost(FocusEvent e) {
                comboBoxVisiteursFocused = false;
            }
        });

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
                    afficherLeVisiteur(vue.getjComboBoxChercher().getSelectedIndex());
                    vue.getjComboBoxChercher().hidePopup();
                }
                if (ke.getKeyCode() == KeyEvent.VK_UP) {
                    int precedent = vue.getjComboBoxChercher().getSelectedIndex() - 1;
                    if (precedent < 0) {
                        vue.getjComboBoxChercher().setSelectedIndex(modeleJComboBoxNomsPrenomsVisiteurs.getSize() - 1);
                    } else {
                        vue.getjComboBoxChercher().setSelectedIndex(precedent);
                    }
                    afficherLeVisiteur(vue.getjComboBoxChercher().getSelectedIndex());
                }
                if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
                    int suivant = vue.getjComboBoxChercher().getSelectedIndex() + 1;
                    if (suivant > modeleJComboBoxNomsPrenomsVisiteurs.getSize() - 1) {
                        vue.getjComboBoxChercher().setSelectedIndex(0);
                    } else {
                        vue.getjComboBoxChercher().setSelectedIndex(suivant);
                    }
                    afficherLeVisiteur(vue.getjComboBoxChercher().getSelectedIndex());
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
        // préparer l'état iniitial de la vue : on affiche la fiche du visiteur connecté
        rechercherIndexVisiteurConnecte();
    }

    /**
     *
     * @param indexVisiteur
     */
    private void afficherLeVisiteur(int indexVisiteur) {
        if (lesVisiteursTrouvee.size() > 0) {
            Visiteur visiteurAffiche = lesVisiteursTrouvee.get(indexVisiteur);

            String codeSecteurVisiteur = visiteurAffiche.getCodeSecteur();
            int indexSecteurVisiteur = 0;
            if (codeSecteurVisiteur != null) {
                int indexSecteur = 1;
                for (Secteur unSecteur : lesSecteurs) {
                    if (unSecteur.getCodeSecteur().equals(codeSecteurVisiteur)) {
                        indexSecteurVisiteur = indexSecteur;
                    }
                    indexSecteur++;
                }
            }

            String codeLaboratoireVisiteur = visiteurAffiche.getCodeLaboratoire();
            int indexLaboratoire = 0;
            int indexLaboratoireVisiteur = 0;
            for (Laboratoire unLaboratoire : lesLaboratoires) {
                if (unLaboratoire.getCodeLaboratoire().equals(codeLaboratoireVisiteur)) {
                    indexLaboratoireVisiteur = indexLaboratoire;
                }
                indexLaboratoire++;
            }

            vue.getjComboBoxSecteur().setSelectedIndex(indexSecteurVisiteur);
            vue.getjComboBoxLabo().setSelectedIndex(indexLaboratoireVisiteur);
            vue.getjComboBoxChercher().setSelectedIndex(indexVisiteur);

            vue.getjTextFieldNom().setText(visiteurAffiche.getNom());
            vue.getjTextFieldPrenom().setText(visiteurAffiche.getPrenom());
            vue.getjTextFieldAdresse().setText(visiteurAffiche.getAdresse());
            vue.getjTextFieldCodePostal().setText(visiteurAffiche.getCodePostal());
            vue.getjTextFieldVille().setText(visiteurAffiche.getVille());
        }
    }

    /**
     *
     */
    private void remplirJComboBoxVisiteurs() {
        try {
            lesVisiteurs = DaoVisiteur.getAllVisiteurs();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlVisiteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        listeVisiteurs.clear();
        lesVisiteursTrouvee.clear();
        modeleJComboBoxNomsPrenomsVisiteurs.removeAllElements();
        for (Visiteur unVisiteur : lesVisiteurs) {
            lesVisiteursTrouvee.add(unVisiteur);
            listeVisiteurs.add(unVisiteur.getNom() + " " + unVisiteur.getPrenom());
            modeleJComboBoxNomsPrenomsVisiteurs.addElement(unVisiteur.getNom() + " " + unVisiteur.getPrenom());
        }
    }

    /**
     *
     */
    private void remplirJComboBoxs() {
        remplirJComboBoxVisiteurs();

        try {
            lesSecteurs = DaoVisiteur.getAllSecteurs();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlVisiteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        modeleJComboBoxSecteurs.removeAllElements();
        modeleJComboBoxSecteurs.addElement("Aucun");
        for (Secteur unSecteur : lesSecteurs) {
            modeleJComboBoxSecteurs.addElement(unSecteur.getLibelleSecteur());
        }

        try {
            lesLaboratoires = DaoVisiteur.getAllLaboratoires();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlVisiteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        modeleJComboBoxLaboratoires.removeAllElements();
        for (Laboratoire unLaboratoire : lesLaboratoires) {
            modeleJComboBoxLaboratoires.addElement(unLaboratoire.getNomLaboratoire());
        }
    }

    /**
     *
     */
    private void rechercherIndexVisiteurConnecte() {
        Visiteur visiteurLu = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("visiteur.data"));
            visiteurLu = (Visiteur) ois.readObject();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Impossible de lire le fichier contenant l'état de l'objet visiteur." + ex);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erreur impossible de charger le fichier contenant l'état de l'objet du visiteur\n" + ex);
        }
        String matriculeVisiteur = visiteurLu.getMatricule();
        int indexVisiteur = 0;
        int indexConnectedVisiteur = 0;
        for (Visiteur unVisiteur : lesVisiteurs) {
            if (unVisiteur.getMatricule().equals(matriculeVisiteur)) {
                indexConnectedVisiteur = indexVisiteur;
            }
            indexVisiteur++;
        }
        afficherLeVisiteur(indexConnectedVisiteur);
    }

    /**
     *
     */
    private void afficherRecherche() {
        if (vue.getjTextFieldRechercher().getText().replaceAll("\\s{2,}", " ").trim().equals("") || vue.getjTextFieldRechercher().getText().equals("Nom Prénom") || !rechercheFocused) {
            remplirJComboBoxVisiteurs();
        } else {
            nomPrenomTrouve.clear();
            lesVisiteursTrouvee.clear();
            for (int i = 0; i < listeVisiteurs.size(); i++) {
                if (listeVisiteurs.get(i).toLowerCase().contains(vue.getjTextFieldRechercher().getText().toLowerCase())) {
                    nomPrenomTrouve.add(listeVisiteurs.get(i));
                    lesVisiteursTrouvee.add(lesVisiteurs.get(i));
                }
            }
            modeleJComboBoxNomsPrenomsVisiteurs.removeAllElements();
            if (nomPrenomTrouve.size() == 0) {
                modeleJComboBoxNomsPrenomsVisiteurs.addElement("Aucun résultat");
            }
            for (int i = 0; i < nomPrenomTrouve.size(); i++) {
                modeleJComboBoxNomsPrenomsVisiteurs.addElement(nomPrenomTrouve.get(i));
            }

        }
        if (modeleJComboBoxNomsPrenomsVisiteurs.getSize() < 8) {
            vue.getjComboBoxChercher().setMaximumRowCount(modeleJComboBoxNomsPrenomsVisiteurs.getSize());
        } else {
            vue.getjComboBoxChercher().setMaximumRowCount(8);
        }
        if (rechercheFocused) {
            vue.getjComboBoxChercher().showPopup();
        }
    }

    /**
     * 
     */
    private class Ecouteur implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evenement) {
            if (evenement.getSource() == vue.getjButtonQuitter()) {
                ctrlPrincipal.quitterApplication();
            } else if (evenement.getSource() == vue.getjButtonOk()) {
                afficherLeVisiteur(vue.getjComboBoxChercher().getSelectedIndex());
            } else if (evenement.getSource() == vue.getjButtonPrecedent()) {
                int precedent = vue.getjComboBoxChercher().getSelectedIndex() - 1;
                if (precedent < 0) {
                    afficherLeVisiteur(modeleJComboBoxNomsPrenomsVisiteurs.getSize() - 1);
                } else {
                    afficherLeVisiteur(precedent);
                }
            } else if (evenement.getSource() == vue.getjButtonSuivant()) {
                int suivant = vue.getjComboBoxChercher().getSelectedIndex() + 1;
                if (suivant > modeleJComboBoxNomsPrenomsVisiteurs.getSize() - 1) {
                    afficherLeVisiteur(0);
                } else {
                    afficherLeVisiteur(suivant);
                }
            } else if (evenement.getSource() == vue.getjComboBoxChercher() && comboBoxVisiteursFocused) {
                afficherLeVisiteur(vue.getjComboBoxChercher().getSelectedIndex());
            } else if (evenement.getSource() == vue.getjComboBoxLabo()) {
                //
            } else if (evenement.getSource() == vue.getjComboBoxSecteur()) {
                //
            } else if (evenement.getSource() == vue.getjButtonMenuGeneral()) {
                ctrlPrincipal.afficherMenuGeneral(vue);
            }
        }
    }

    // ACCESSEURS et MUTATEURS
    public VueVisiteur getVue() {
        return vue;
    }

    public void setVue(VueVisiteur vue) {
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
