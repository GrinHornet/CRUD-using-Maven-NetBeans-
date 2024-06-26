/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.damayo.basiccrud;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class logInFrame extends javax.swing.JFrame {

    /**
     * Creates new form logInFrame
     */
    public logInFrame() {
        initComponents();
        center();
    }
    public void center(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2,dim.height/2-this.getSize().height/2);
    }
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        uName = new javax.swing.JTextField();
        logInBtn = new javax.swing.JButton();
        message = new javax.swing.JLabel();
        pWord = new javax.swing.JPasswordField();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jMenu3.setText("jMenu3");

        jMenu4.setText("jMenu4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Username");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Password");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, -1, -1));

        uName.setBackground(new java.awt.Color(255, 255, 255));
        uName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uName.setForeground(new java.awt.Color(0, 0, 0));
        uName.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        uName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uNameActionPerformed(evt);
            }
        });
        jPanel1.add(uName, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 190, 36));

        logInBtn.setBackground(new java.awt.Color(0, 153, 255));
        logInBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        logInBtn.setForeground(new java.awt.Color(255, 255, 255));
        logInBtn.setText("Login");
        logInBtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        logInBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logInBtnActionPerformed(evt);
            }
        });
        jPanel1.add(logInBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 90, 36));

        message.setForeground(new java.awt.Color(255, 51, 0));
        jPanel1.add(message, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 190, 30));

        pWord.setBackground(new java.awt.Color(255, 255, 255));
        pWord.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pWord.setForeground(new java.awt.Color(0, 0, 0));
        pWord.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pWord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pWordActionPerformed(evt);
            }
        });
        jPanel1.add(pWord, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 190, 36));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void logInBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logInBtnActionPerformed
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM employee WHERE username = ? AND password = ? AND status = 1";
            connection = DatabaseUtil.getConnection();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, uName.getText());
            preparedStatement.setString(2, pWord.getText());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String userType = resultSet.getString("usertype");

                if ("Admin".equals(userType)) {
                    NameLogged.loggedInAs = resultSet.getString("fname");
                    adminFrame in = new adminFrame();
                    in.show();
                    this.dispose();
                    uName.setText("");
                    pWord.setText("");
                    message.setText("Successfully Login!");
                } else {
                    message.setText("Invalid Username or Password!");
                }
            } else {
                message.setText("Invalid Username or Password!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(adminFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Close resources in the reverse order of their creation to avoid resource leaks
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(adminFrame.class.getName()).log(Level.SEVERE, null, e);
            }
        }               
    }//GEN-LAST:event_logInBtnActionPerformed

    private void pWordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pWordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pWordActionPerformed

    private void uNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uNameActionPerformed

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
            java.util.logging.Logger.getLogger(logInFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(logInFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(logInFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(logInFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new logInFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton logInBtn;
    private javax.swing.JLabel message;
    private javax.swing.JPasswordField pWord;
    private javax.swing.JTextField uName;
    // End of variables declaration//GEN-END:variables
}
