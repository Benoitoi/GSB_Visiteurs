/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author btssio
 */
public class VueVisiteur extends javax.swing.JFrame {

    /**
     * Creates new form JFrameVisiteur
     */
    public VueVisiteur() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jComboBoxChercher = new javax.swing.JComboBox();
        jButtonSuivant = new javax.swing.JButton();
        jButtonPrecedent = new javax.swing.JButton();
        jButtonFermer = new javax.swing.JButton();
        jButtonOk = new javax.swing.JButton();
        jSeparator = new javax.swing.JSeparator();
        jLabelNom = new javax.swing.JLabel();
        jLabelPrenom = new javax.swing.JLabel();
        jLabelChercherVisiteurs = new javax.swing.JLabel();
        jLabelChercher = new javax.swing.JLabel();
        jLabelAdresse = new javax.swing.JLabel();
        jLabelVille = new javax.swing.JLabel();
        jLabelSecteur = new javax.swing.JLabel();
        jLabelLabo = new javax.swing.JLabel();
        jComboBoxSecteur = new javax.swing.JComboBox();
        jComboBoxLabo = new javax.swing.JComboBox();
        jTextFieldNom = new javax.swing.JTextField();
        jTextFieldPrenom = new javax.swing.JTextField();
        jTextFieldAdresse = new javax.swing.JTextField();
        jTextFieldCodePostal = new javax.swing.JTextField();
        jTextFieldVille = new javax.swing.JTextField();
        jLabelBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Visiteurs");
        setResizable(false);

        jPanel1.setLayout(null);

        jComboBoxChercher.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBoxChercher);
        jComboBoxChercher.setBounds(320, 130, 56, 20);

        jButtonSuivant.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonSuivant.setForeground(new java.awt.Color(0, 0, 153));
        jButtonSuivant.setText("Suivant");
        jPanel1.add(jButtonSuivant);
        jButtonSuivant.setBounds(390, 430, 90, 23);

        jButtonPrecedent.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonPrecedent.setForeground(new java.awt.Color(0, 0, 153));
        jButtonPrecedent.setText("Précédent");
        jPanel1.add(jButtonPrecedent);
        jButtonPrecedent.setBounds(250, 430, 100, 23);

        jButtonFermer.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonFermer.setForeground(new java.awt.Color(0, 0, 153));
        jButtonFermer.setText("Fermer");
        jButtonFermer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFermerActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonFermer);
        jButtonFermer.setBounds(617, 420, 80, 23);

        jButtonOk.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonOk.setForeground(new java.awt.Color(0, 0, 153));
        jButtonOk.setText("OK");
        jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonOk);
        jButtonOk.setBounds(400, 130, 47, 23);

        jSeparator.setBackground(new java.awt.Color(51, 51, 255));
        jSeparator.setForeground(new java.awt.Color(0, 0, 153));
        jPanel1.add(jSeparator);
        jSeparator.setBounds(150, 162, 480, 10);

        jLabelNom.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelNom.setForeground(new java.awt.Color(0, 0, 153));
        jLabelNom.setText("NOM");
        jPanel1.add(jLabelNom);
        jLabelNom.setBounds(180, 180, 80, 14);

        jLabelPrenom.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelPrenom.setForeground(new java.awt.Color(0, 0, 153));
        jLabelPrenom.setText("PRENOM");
        jPanel1.add(jLabelPrenom);
        jLabelPrenom.setBounds(180, 220, 50, 14);

        jLabelChercherVisiteurs.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabelChercherVisiteurs.setForeground(new java.awt.Color(0, 0, 153));
        jLabelChercherVisiteurs.setText("Visiteurs");
        jPanel1.add(jLabelChercherVisiteurs);
        jLabelChercherVisiteurs.setBounds(300, 10, 230, 50);

        jLabelChercher.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelChercher.setForeground(new java.awt.Color(0, 0, 153));
        jLabelChercher.setText("Chercher");
        jPanel1.add(jLabelChercher);
        jLabelChercher.setBounds(250, 130, 60, 14);

        jLabelAdresse.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelAdresse.setForeground(new java.awt.Color(0, 0, 153));
        jLabelAdresse.setText("ADRESSE");
        jPanel1.add(jLabelAdresse);
        jLabelAdresse.setBounds(180, 260, 60, 14);

        jLabelVille.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelVille.setForeground(new java.awt.Color(0, 0, 153));
        jLabelVille.setText("VILLE");
        jPanel1.add(jLabelVille);
        jLabelVille.setBounds(180, 300, 30, 14);

        jLabelSecteur.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelSecteur.setForeground(new java.awt.Color(0, 0, 153));
        jLabelSecteur.setText("SECTEUR");
        jPanel1.add(jLabelSecteur);
        jLabelSecteur.setBounds(180, 340, 60, 14);

        jLabelLabo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelLabo.setForeground(new java.awt.Color(0, 0, 153));
        jLabelLabo.setText("LABO");
        jPanel1.add(jLabelLabo);
        jLabelLabo.setBounds(180, 380, 29, 14);

        jComboBoxSecteur.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBoxSecteur);
        jComboBoxSecteur.setBounds(270, 340, 56, 20);

        jComboBoxLabo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBoxLabo);
        jComboBoxLabo.setBounds(270, 380, 56, 20);

        jTextFieldNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldNom);
        jTextFieldNom.setBounds(270, 170, 110, 20);
        jPanel1.add(jTextFieldPrenom);
        jTextFieldPrenom.setBounds(270, 210, 110, 20);

        jTextFieldAdresse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAdresseActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldAdresse);
        jTextFieldAdresse.setBounds(270, 260, 110, 20);

        jTextFieldCodePostal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCodePostalActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldCodePostal);
        jTextFieldCodePostal.setBounds(270, 300, 50, 20);

        jTextFieldVille.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldVilleActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldVille);
        jTextFieldVille.setBounds(330, 300, 110, 20);

        jLabelBackground.setIcon(new javax.swing.ImageIcon("C:\\Users\\Benoit\\Documents\\NetBeansProjects\\GSB_Visiteurs\\GSB_Visiteurs\\images\\gsb_background.png")); // NOI18N
        jPanel1.add(jLabelBackground);
        jLabelBackground.setBounds(0, 0, 750, 480);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 759, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonOkActionPerformed

    private void jButtonFermerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFermerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonFermerActionPerformed

    private void jTextFieldCodePostalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCodePostalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCodePostalActionPerformed

    private void jTextFieldAdresseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAdresseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldAdresseActionPerformed

    private void jTextFieldVilleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldVilleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldVilleActionPerformed

    private void jTextFieldNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomActionPerformed

    public JButton getjButtonFermer() {
        return jButtonFermer;
    }

    public JButton getjButtonOk() {
        return jButtonOk;
    }

    public JButton getjButtonPrecedent() {
        return jButtonPrecedent;
    }

    public JButton getjButtonSuivant() {
        return jButtonSuivant;
    }

    public JComboBox getjComboBoxChercher() {
        return jComboBoxChercher;
    }

    public JComboBox getjComboBoxLabo() {
        return jComboBoxLabo;
    }

    public JComboBox getjComboBoxSecteur() {
        return jComboBoxSecteur;
    }

    public JTextField getjTextFieldAdresse() {
        return jTextFieldAdresse;
    }

    public JTextField getjTextFieldCodePostal() {
        return jTextFieldCodePostal;
    }

    public JTextField getjTextFieldNom() {
        return jTextFieldNom;
    }

    public JTextField getjTextFieldPrenom() {
        return jTextFieldPrenom;
    }

    public JTextField getjTextFieldVille() {
        return jTextFieldVille;
    }
    
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(VueVisiteur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(VueVisiteur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(VueVisiteur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(VueVisiteur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new VueVisiteur().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonFermer;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JButton jButtonPrecedent;
    private javax.swing.JButton jButtonSuivant;
    private javax.swing.JComboBox jComboBoxChercher;
    private javax.swing.JComboBox jComboBoxLabo;
    private javax.swing.JComboBox jComboBoxSecteur;
    private javax.swing.JLabel jLabelAdresse;
    private javax.swing.JLabel jLabelBackground;
    private javax.swing.JLabel jLabelChercher;
    private javax.swing.JLabel jLabelChercherVisiteurs;
    private javax.swing.JLabel jLabelLabo;
    private javax.swing.JLabel jLabelNom;
    private javax.swing.JLabel jLabelPrenom;
    private javax.swing.JLabel jLabelSecteur;
    private javax.swing.JLabel jLabelVille;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator;
    private javax.swing.JTextField jTextFieldAdresse;
    private javax.swing.JTextField jTextFieldCodePostal;
    private javax.swing.JTextField jTextFieldNom;
    private javax.swing.JTextField jTextFieldPrenom;
    private javax.swing.JTextField jTextFieldVille;
    // End of variables declaration//GEN-END:variables
}
