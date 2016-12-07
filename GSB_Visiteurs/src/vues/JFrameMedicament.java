/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author btssio
 */
public class JFrameMedicament extends javax.swing.JFrame {

    /**
     * Creates new form JFrameMedicament
     */
    public JFrameMedicament() {
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
        jLabel1 = new javax.swing.JLabel();
        jLabelCode = new javax.swing.JLabel();
        jLabelNomCommercial = new javax.swing.JLabel();
        jLabelFamille = new javax.swing.JLabel();
        jLabelComposition = new javax.swing.JLabel();
        jLabelEffetIndesirables = new javax.swing.JLabel();
        jLabelContreIndications = new javax.swing.JLabel();
        jLabelPrixEchantillon = new javax.swing.JLabel();
        jButtonPrecedent = new javax.swing.JButton();
        jButtonSuivant = new javax.swing.JButton();
        jButtonQuitter = new javax.swing.JButton();
        jTextFieldCode = new javax.swing.JTextField();
        jTextFieldNomCommercial = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaContreIndications = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaEffetsIndesirables = new javax.swing.JTextArea();
        jTextFieldPrixEchantillon = new javax.swing.JTextField();
        jTextFieldComposition = new javax.swing.JTextField();
        jLabelBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Médicament");
        setResizable(false);

        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("Médicament");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(220, 0, 235, 44);

        jLabelCode.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelCode.setForeground(new java.awt.Color(0, 0, 153));
        jLabelCode.setText("Code");
        jPanel1.add(jLabelCode);
        jLabelCode.setBounds(150, 50, 50, 14);

        jLabelNomCommercial.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelNomCommercial.setForeground(new java.awt.Color(0, 0, 153));
        jLabelNomCommercial.setText("Nom commercial");
        jPanel1.add(jLabelNomCommercial);
        jLabelNomCommercial.setBounds(150, 90, 100, 14);

        jLabelFamille.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelFamille.setForeground(new java.awt.Color(0, 0, 153));
        jLabelFamille.setText("Famille");
        jPanel1.add(jLabelFamille);
        jLabelFamille.setBounds(150, 140, 60, 14);

        jLabelComposition.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelComposition.setForeground(new java.awt.Color(0, 0, 153));
        jLabelComposition.setText("Composition");
        jPanel1.add(jLabelComposition);
        jLabelComposition.setBounds(150, 190, 80, 14);

        jLabelEffetIndesirables.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelEffetIndesirables.setForeground(new java.awt.Color(0, 0, 153));
        jLabelEffetIndesirables.setText("Effets indésirables");
        jPanel1.add(jLabelEffetIndesirables);
        jLabelEffetIndesirables.setBounds(150, 250, 110, 14);

        jLabelContreIndications.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelContreIndications.setForeground(new java.awt.Color(0, 0, 153));
        jLabelContreIndications.setText("Contre indications");
        jPanel1.add(jLabelContreIndications);
        jLabelContreIndications.setBounds(150, 320, 110, 14);

        jLabelPrixEchantillon.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelPrixEchantillon.setForeground(new java.awt.Color(0, 0, 153));
        jLabelPrixEchantillon.setText("Prix échantillon");
        jPanel1.add(jLabelPrixEchantillon);
        jLabelPrixEchantillon.setBounds(150, 380, 100, 14);

        jButtonPrecedent.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonPrecedent.setForeground(new java.awt.Color(0, 0, 153));
        jButtonPrecedent.setText("Précédent");
        jPanel1.add(jButtonPrecedent);
        jButtonPrecedent.setBounds(130, 440, 100, 23);

        jButtonSuivant.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonSuivant.setForeground(new java.awt.Color(0, 0, 153));
        jButtonSuivant.setText("Suivant");
        jPanel1.add(jButtonSuivant);
        jButtonSuivant.setBounds(250, 440, 90, 23);

        jButtonQuitter.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonQuitter.setForeground(new java.awt.Color(0, 0, 153));
        jButtonQuitter.setText("Quitter");
        jPanel1.add(jButtonQuitter);
        jButtonQuitter.setBounds(590, 450, 90, 23);
        jPanel1.add(jTextFieldCode);
        jTextFieldCode.setBounds(280, 50, 93, 20);
        jPanel1.add(jTextFieldNomCommercial);
        jTextFieldNomCommercial.setBounds(280, 90, 93, 20);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBox1);
        jComboBox1.setBounds(280, 140, 93, 20);

        jTextAreaContreIndications.setColumns(20);
        jTextAreaContreIndications.setRows(5);
        jScrollPane1.setViewportView(jTextAreaContreIndications);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(280, 300, 348, 51);

        jTextAreaEffetsIndesirables.setColumns(20);
        jTextAreaEffetsIndesirables.setRows(5);
        jScrollPane2.setViewportView(jTextAreaEffetsIndesirables);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(280, 230, 348, 51);
        jPanel1.add(jTextFieldPrixEchantillon);
        jTextFieldPrixEchantillon.setBounds(280, 380, 88, 20);
        jPanel1.add(jTextFieldComposition);
        jTextFieldComposition.setBounds(280, 190, 90, 20);

        jLabelBackground.setIcon(new javax.swing.ImageIcon("C:\\Users\\Benoit\\Pictures\\gsb_background.png")); // NOI18N
        jPanel1.add(jLabelBackground);
        jLabelBackground.setBounds(0, 0, 700, 490);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(JFrameMedicament.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameMedicament.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameMedicament.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameMedicament.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameMedicament().setVisible(true);
            }
        });
    }

    public JButton getjButtonPrecedent() {
        return jButtonPrecedent;
    }

    public JButton getjButtonQuitter() {
        return jButtonQuitter;
    }

    public JButton getjButtonSuivant() {
        return jButtonSuivant;
    }

    public JComboBox getjComboBox1() {
        return jComboBox1;
    }

    public JTextArea getjTextAreaContreIndications() {
        return jTextAreaContreIndications;
    }

    public JTextArea getjTextAreaEffetsIndesirables() {
        return jTextAreaEffetsIndesirables;
    }

    public JTextField getjTextFieldCode() {
        return jTextFieldCode;
    }

    public JTextField getjTextFieldComposition() {
        return jTextFieldComposition;
    }

    public JTextField getjTextFieldNomCommercial() {
        return jTextFieldNomCommercial;
    }

    public JTextField getjTextFieldPrixEchantillon() {
        return jTextFieldPrixEchantillon;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPrecedent;
    private javax.swing.JButton jButtonQuitter;
    private javax.swing.JButton jButtonSuivant;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelBackground;
    private javax.swing.JLabel jLabelCode;
    private javax.swing.JLabel jLabelComposition;
    private javax.swing.JLabel jLabelContreIndications;
    private javax.swing.JLabel jLabelEffetIndesirables;
    private javax.swing.JLabel jLabelFamille;
    private javax.swing.JLabel jLabelNomCommercial;
    private javax.swing.JLabel jLabelPrixEchantillon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaContreIndications;
    private javax.swing.JTextArea jTextAreaEffetsIndesirables;
    private javax.swing.JTextField jTextFieldCode;
    private javax.swing.JTextField jTextFieldComposition;
    private javax.swing.JTextField jTextFieldNomCommercial;
    private javax.swing.JTextField jTextFieldPrixEchantillon;
    // End of variables declaration//GEN-END:variables
}
