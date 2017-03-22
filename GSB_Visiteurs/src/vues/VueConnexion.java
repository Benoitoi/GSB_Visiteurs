/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author tlauterbach
 */
public class VueConnexion extends javax.swing.JFrame {

    /**
     * Creates new form JFrameConnexion
     */
    public VueConnexion() {
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

        jPanelConnexion = new javax.swing.JPanel();
        jLabelConnexion = new javax.swing.JLabel();
        jTextFieldLogin = new javax.swing.JTextField();
        jButtonOk = new javax.swing.JButton();
        jButtonQuitter = new javax.swing.JButton();
        jPasswordMdp = new javax.swing.JPasswordField();
        jButtonAfficherMdp = new javax.swing.JButton();
        jLabelCheckLogin = new javax.swing.JLabel();
        jLabelCheckMdp = new javax.swing.JLabel();
        jButtonChangeBdd = new javax.swing.JButton();
        jCheckBoxSouvenirId = new javax.swing.JCheckBox();
        jCheckBoxSouvenirMdp = new javax.swing.JCheckBox();
        jCheckBoxAfficher = new javax.swing.JCheckBox();
        jLabelBaseDeDonnee = new javax.swing.JLabel();
        jLabelBdd = new javax.swing.JLabel();
        jLabelBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Identifiez-vous");
        setResizable(false);

        jPanelConnexion.setLayout(null);

        jLabelConnexion.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabelConnexion.setForeground(new java.awt.Color(0, 0, 153));
        jLabelConnexion.setText("Identifiez-vous");
        jPanelConnexion.add(jLabelConnexion);
        jLabelConnexion.setBounds(250, 20, 290, 44);
        jPanelConnexion.add(jTextFieldLogin);
        jTextFieldLogin.setBounds(270, 140, 140, 30);

        jButtonOk.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonOk.setForeground(new java.awt.Color(0, 0, 153));
        jButtonOk.setText("OK");
        jPanelConnexion.add(jButtonOk);
        jButtonOk.setBounds(440, 180, 90, 30);

        jButtonQuitter.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonQuitter.setForeground(new java.awt.Color(0, 0, 153));
        jButtonQuitter.setText("Quitter");
        jButtonQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQuitterActionPerformed(evt);
            }
        });
        jPanelConnexion.add(jButtonQuitter);
        jButtonQuitter.setBounds(580, 430, 90, 23);
        jPanelConnexion.add(jPasswordMdp);
        jPasswordMdp.setBounds(271, 180, 140, 30);

        jButtonAfficherMdp.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonAfficherMdp.setForeground(new java.awt.Color(0, 51, 153));
        jButtonAfficherMdp.setText("Afficher");
        jPanelConnexion.add(jButtonAfficherMdp);
        jButtonAfficherMdp.setBounds(140, 180, 90, 30);
        jPanelConnexion.add(jLabelCheckLogin);
        jLabelCheckLogin.setBounds(420, 140, 50, 40);
        jPanelConnexion.add(jLabelCheckMdp);
        jLabelCheckMdp.setBounds(420, 180, 40, 40);

        jButtonChangeBdd.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonChangeBdd.setForeground(new java.awt.Color(0, 51, 153));
        jButtonChangeBdd.setText("Changer de base de donnée");
        jPanelConnexion.add(jButtonChangeBdd);
        jButtonChangeBdd.setBounds(20, 420, 190, 23);

        jCheckBoxSouvenirId.setText("Se souvenir");
        jPanelConnexion.add(jCheckBoxSouvenirId);
        jCheckBoxSouvenirId.setBounds(540, 140, 130, 30);

        jCheckBoxSouvenirMdp.setText("Se souvenir");
        jPanelConnexion.add(jCheckBoxSouvenirMdp);
        jCheckBoxSouvenirMdp.setBounds(540, 180, 130, 30);
        jPanelConnexion.add(jCheckBoxAfficher);
        jCheckBoxAfficher.setBounds(240, 190, 20, 20);

        jLabelBaseDeDonnee.setForeground(new java.awt.Color(255, 0, 51));
        jPanelConnexion.add(jLabelBaseDeDonnee);
        jLabelBaseDeDonnee.setBounds(230, 420, 160, 20);

        jLabelBdd.setText("Base de données utilisée :");
        jPanelConnexion.add(jLabelBdd);
        jLabelBdd.setBounds(230, 400, 170, 14);

        jLabelBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gsb_background.png"))); // NOI18N
        jPanelConnexion.add(jLabelBackground);
        jLabelBackground.setBounds(0, 0, 700, 480);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelConnexion, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelConnexion, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQuitterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonQuitterActionPerformed

    /**
     *
     * @return
     */
    public JButton getjButtonOk() {
        return jButtonOk;
    }

    /**
     *
     * @return
     */
    public JButton getjButtonQuitter() {
        return jButtonQuitter;
    }

    /**
     *
     * @return
     */
    public JTextField getjTextFieldLogin() {
        return jTextFieldLogin;
    }
    
    /**
     *
     * @return
     */
    public JPasswordField getjPasswordMdp() {
        return jPasswordMdp;
    }

    /**
     *
     * @return
     */
    public JButton getjButtonAfficherMdp() {
        return jButtonAfficherMdp;
    }

    /**
     *
     * @return
     */
    public JLabel getjLabelCheckLogin() {
        return jLabelCheckLogin;
    }

    /**
     *
     * @return
     */
    public JLabel getjLabelCheckMdp() {
        return jLabelCheckMdp;
    }

    /**
     *
     * @return
     */
    public JButton getjButtonChangeBdd() {
        return jButtonChangeBdd;
    }

    /**
     *
     * @return
     */
    public JCheckBox getjCheckBoxSouvenirId() {
        return jCheckBoxSouvenirId;
    }

    /**
     *
     * @return
     */
    public JCheckBox getjCheckBoxSouvenirMdp() {
        return jCheckBoxSouvenirMdp;
    }

    /**
     *
     * @return
     */
    public JCheckBox getjCheckBoxAfficher() {
        return jCheckBoxAfficher;
    }

    /**
     *
     * @return
     */
    public JLabel getjLabelBaseDeDonnee() {
        return jLabelBaseDeDonnee;
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VueConnexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VueConnexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VueConnexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VueConnexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VueConnexion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAfficherMdp;
    private javax.swing.JButton jButtonChangeBdd;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JButton jButtonQuitter;
    private javax.swing.JCheckBox jCheckBoxAfficher;
    private javax.swing.JCheckBox jCheckBoxSouvenirId;
    private javax.swing.JCheckBox jCheckBoxSouvenirMdp;
    private javax.swing.JLabel jLabelBackground;
    private javax.swing.JLabel jLabelBaseDeDonnee;
    private javax.swing.JLabel jLabelBdd;
    private javax.swing.JLabel jLabelCheckLogin;
    private javax.swing.JLabel jLabelCheckMdp;
    private javax.swing.JLabel jLabelConnexion;
    private javax.swing.JPanel jPanelConnexion;
    private javax.swing.JPasswordField jPasswordMdp;
    private javax.swing.JTextField jTextFieldLogin;
    // End of variables declaration//GEN-END:variables
}
