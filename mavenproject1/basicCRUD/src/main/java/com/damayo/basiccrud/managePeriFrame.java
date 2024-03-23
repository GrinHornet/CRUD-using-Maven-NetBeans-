/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.damayo.basiccrud;

import java.util.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

public class managePeriFrame extends javax.swing.JInternalFrame {

    /**
     * Creates new form search
     */
    public managePeriFrame() {
        initComponents();
        displayToTable();
        statusBox.setSelectedItem("Available");
        typeBox.setSelectedItem("All");
    }
   public void displayToTable() {
    DefaultTableModel model = (DefaultTableModel) periTable.getModel();
    
    try {
        model.setRowCount(0);
        String query= "";
        if(statusBox.getSelectedItem().toString().equals("All") && typeBox.getSelectedItem().toString().equals("All")){
            query = "SELECT * FROM `peripheral`;";
            addBtn.setVisible(false);
            updateBtn.setVisible(false);
            deleteBtn.setVisible(false);
        }else if(statusBox.getSelectedItem().toString().equals("Available") && typeBox.getSelectedItem().toString().equals("All")){
            query = "SELECT * FROM `peripheral` WHERE `status`= 1;";
            addBtn.setVisible(true);
            updateBtn.setVisible(true);
            deleteBtn.setVisible(true);
        }else if(statusBox.getSelectedItem().toString().equals("Assigned") && typeBox.getSelectedItem().toString().equals("All")){
            query = "SELECT * FROM `peripheral` WHERE `status`= 2;";
            addBtn.setVisible(false);
            updateBtn.setVisible(false);
            deleteBtn.setVisible(false);
        }else if(statusBox.getSelectedItem().toString().equals("Deleted") && typeBox.getSelectedItem().toString().equals("All")){
            query = "SELECT * FROM `peripheral` WHERE `status`= 3;";
            addBtn.setVisible(false);
            updateBtn.setVisible(false);
            deleteBtn.setVisible(false);
        }else if(statusBox.getSelectedItem().toString().equals("All") && typeBox.getSelectedItem().toString().equals("Input")){
            query = "SELECT * FROM `peripheral` WHERE type LIKE 'Input';";
            addBtn.setVisible(false);
            updateBtn.setVisible(false);
            deleteBtn.setVisible(false);
        }else if(statusBox.getSelectedItem().toString().equals("Available") && typeBox.getSelectedItem().toString().equals("Input")){
            query = "SELECT * FROM `peripheral` WHERE `status`= 1 AND type LIKE 'Input';";
            addBtn.setVisible(true);
            updateBtn.setVisible(true);
            deleteBtn.setVisible(true);
        }else if(statusBox.getSelectedItem().toString().equals("Assigned") && typeBox.getSelectedItem().toString().equals("Input")){
            query = "SELECT * FROM `peripheral` WHERE `status`= 2 AND type LIKE 'Input';";
            addBtn.setVisible(false);
            updateBtn.setVisible(false);
            deleteBtn.setVisible(false);
        }else if(statusBox.getSelectedItem().toString().equals("Deleted") && typeBox.getSelectedItem().toString().equals("Input")){
            query = "SELECT * FROM `peripheral` WHERE `status`= 3 AND type LIKE 'Input';";
            addBtn.setVisible(false);
            updateBtn.setVisible(false);
            deleteBtn.setVisible(false);
        }else if(statusBox.getSelectedItem().toString().equals("All") && typeBox.getSelectedItem().toString().equals("Output")){
            query = "SELECT * FROM `peripheral` WHERE type LIKE 'Output';";
            addBtn.setVisible(false);
            updateBtn.setVisible(false);
            deleteBtn.setVisible(false);
        }else if(statusBox.getSelectedItem().toString().equals("Available") && typeBox.getSelectedItem().toString().equals("Output")){
            query = "SELECT * FROM `peripheral` WHERE `status`= 1 AND type LIKE 'Output';";
            addBtn.setVisible(true);
            updateBtn.setVisible(true);
            deleteBtn.setVisible(true);
        }else if(statusBox.getSelectedItem().toString().equals("Assigned") && typeBox.getSelectedItem().toString().equals("Output")){
            query = "SELECT * FROM `peripheral` WHERE `status`= 2 AND type LIKE 'Output';";
            addBtn.setVisible(false);
            updateBtn.setVisible(false);
            deleteBtn.setVisible(false);
        }else if(statusBox.getSelectedItem().toString().equals("Deleted") && typeBox.getSelectedItem().toString().equals("Output")){
            query = "SELECT * FROM `peripheral` WHERE `status`= 3 AND type LIKE 'Output';";
            addBtn.setVisible(false);
            updateBtn.setVisible(false);
            deleteBtn.setVisible(false);
        }
        
        ResultSet resultSet = DatabaseUtil.executeQuery(query);

        while (resultSet.next()) {
            String peripId = resultSet.getString("peripheral_id");
            String fullName = resultSet.getString("name");
            String type1 = resultSet.getString("type");
            String category1 = resultSet.getString("category");
            String date = resultSet.getString("date_added");
            
            model.addRow(new Object[]{peripId, fullName, type1, category1, date});
        }
    } catch (SQLException ex) {
        Logger.getLogger(managePeriFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
}
public void getPeriTableRecord() {
    int selectedRow = periTable.getSelectedRow();

    if (selectedRow != -1) { // Ensure a row is selected
    DefaultTableModel model = (DefaultTableModel) periTable.getModel();

    // Retrieve data from the selected row
    String periId = model.getValueAt(selectedRow, 0).toString();

    // Query the database using empId to get the details
        String query = "SELECT * FROM employeeoop.peripheral WHERE peripheral_id = ?";
        System.out.println("Selected Peri_Id: " + periId);
        try {
            // Use a prepared statement to avoid SQL injection
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, periId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Your existing code to retrieve and set values
                periId = resultSet.getString("peripheral_id");
                String fullName = resultSet.getString("name");
                String type1 = resultSet.getString("type");
                String category1 = resultSet.getString("category");
                String date = resultSet.getString("date_added");

                // Set values to text fields and comboBoxes
                name.setText(fullName);
                type.setSelectedItem(type1);
                category.setSelectedItem(category1);
                dateField.setText(date);
            }
        } catch (SQLException ex) {
            Logger.getLogger(managePeriFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    
    private void insertDataIntoDatabase() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            String query = "INSERT INTO `peripheral`(`peripheral_id`, `name`, `type`, `category`, `date_added`, `status`) VALUES (?,?,?,?,?,?)";
            connection = DatabaseUtil.getConnection();
            
            if(name.getText().isEmpty() ||dateField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this, "Please fillout important fields.");
            }else{
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, null);
                preparedStatement.setString(2, name.getText());
                preparedStatement.setString(3, type.getSelectedItem().toString());
                preparedStatement.setString(4, category.getSelectedItem().toString());
                preparedStatement.setString(5, dateField.getText());
                preparedStatement.setString(6, "1");
            }
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                // Data inserted successfully
                JOptionPane.showMessageDialog(this, "Data inserted successfully");
                
                // Clear text fields after successful insertion if needed
                name.setText("");
                category.setSelectedItem("Keyboard");
                type.setSelectedItem("Input");
                dateField.setText("");
                searchField.setText("");
                displayToTable();
                
            } else {
                // No rows affected, insertion failed
                JOptionPane.showMessageDialog(this, "Failed to insert data");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in the reverse order of their creation
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
        searchField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        periTable = new javax.swing.JTable();
        dateField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        clearBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        typeBox = new javax.swing.JComboBox<>();
        category = new javax.swing.JComboBox<>();
        addBtn = new javax.swing.JButton();
        statusBox = new javax.swing.JComboBox<>();
        type = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        searchField.setBackground(new java.awt.Color(255, 255, 255));
        searchField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        searchField.setForeground(new java.awt.Color(0, 0, 0));
        searchField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchFieldKeyReleased(evt);
            }
        });
        jPanel1.add(searchField, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 140, 30));

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Search Name:");
        jLabel1.setVerifyInputWhenFocusTarget(false);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 90, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("MANAGE EPERIPHERAL RECORDS");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 460, 44));

        periTable.setBackground(new java.awt.Color(255, 255, 255));
        periTable.setForeground(new java.awt.Color(0, 0, 0));
        periTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Type", "Category", "Date Added"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        periTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        periTable.setGridColor(new java.awt.Color(51, 51, 51));
        periTable.setIntercellSpacing(new java.awt.Dimension(2, 2));
        periTable.setShowGrid(true);
        periTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                periTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(periTable);
        if (periTable.getColumnModel().getColumnCount() > 0) {
            periTable.getColumnModel().getColumn(0).setMinWidth(40);
            periTable.getColumnModel().getColumn(0).setMaxWidth(40);
            periTable.getColumnModel().getColumn(1).setMinWidth(140);
            periTable.getColumnModel().getColumn(1).setMaxWidth(140);
            periTable.getColumnModel().getColumn(2).setMinWidth(80);
            periTable.getColumnModel().getColumn(2).setMaxWidth(80);
            periTable.getColumnModel().getColumn(3).setMinWidth(90);
            periTable.getColumnModel().getColumn(3).setMaxWidth(90);
            periTable.getColumnModel().getColumn(4).setMinWidth(90);
            periTable.getColumnModel().getColumn(4).setMaxWidth(90);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 440, 390));

        dateField.setBackground(new java.awt.Color(255, 255, 255));
        dateField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dateField.setForeground(new java.awt.Color(0, 0, 0));
        dateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateFieldActionPerformed(evt);
            }
        });
        jPanel1.add(dateField, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 240, 200, 40));

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Name *");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 60, 20));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Date Added *");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 220, 90, -1));

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Type");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, 40, -1));

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Category");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 150, 50, -1));

        name.setBackground(new java.awt.Color(255, 255, 255));
        name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        name.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, 200, 40));

        clearBtn.setBackground(new java.awt.Color(204, 102, 0));
        clearBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(255, 255, 255));
        clearBtn.setText("CLEAR");
        clearBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 440, 200, 40));

        updateBtn.setBackground(new java.awt.Color(0, 204, 102));
        updateBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        updateBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateBtn.setText("UPDATE");
        updateBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });
        jPanel1.add(updateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 340, 200, 40));

        deleteBtn.setBackground(new java.awt.Color(204, 51, 0));
        deleteBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setText("DELETE");
        deleteBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel1.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 390, 200, 40));

        typeBox.setBackground(new java.awt.Color(0, 153, 255));
        typeBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        typeBox.setForeground(new java.awt.Color(255, 255, 255));
        typeBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Input", "Output" }));
        typeBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeBoxActionPerformed(evt);
            }
        });
        jPanel1.add(typeBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 100, 30));

        category.setBackground(new java.awt.Color(0, 153, 255));
        category.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        category.setForeground(new java.awt.Color(255, 255, 255));
        category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Keyboard", "Mouse", "Speaker", "Scanner", "Printer", "Monitor" }));
        jPanel1.add(category, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 170, 200, 40));

        addBtn.setBackground(new java.awt.Color(0, 204, 204));
        addBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        addBtn.setForeground(new java.awt.Color(255, 255, 255));
        addBtn.setText("ADD");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 290, 200, 40));

        statusBox.setBackground(new java.awt.Color(0, 153, 255));
        statusBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        statusBox.setForeground(new java.awt.Color(255, 255, 255));
        statusBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Available", "Assigned", "Deleted" }));
        statusBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                statusBoxItemStateChanged(evt);
            }
        });
        statusBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusBoxActionPerformed(evt);
            }
        });
        jPanel1.add(statusBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 100, 30));

        type.setBackground(new java.awt.Color(0, 153, 255));
        type.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        type.setForeground(new java.awt.Color(255, 255, 255));
        type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Input", "Output" }));
        type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeActionPerformed(evt);
            }
        });
        jPanel1.add(type, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 100, 200, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchFieldActionPerformed

    private void periTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_periTableMouseClicked
    getPeriTableRecord();
    addBtn.setVisible(false);
    }//GEN-LAST:event_periTableMouseClicked

    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyReleased
  
    DefaultTableModel model = (DefaultTableModel) periTable.getModel();
    
    try {
        model.setRowCount(0);
        String query= "";
        if(statusBox.getSelectedItem().toString().equals("All") && typeBox.getSelectedItem().toString().equals("All")){
            query = "SELECT * FROM `peripheral`;";
            addBtn.setVisible(false);
            updateBtn.setVisible(false);
            deleteBtn.setVisible(false);
        }else if(statusBox.getSelectedItem().toString().equals("Available") && typeBox.getSelectedItem().toString().equals("All")){
            query = "SELECT * FROM `peripheral` WHERE `status`= 1;";
            addBtn.setVisible(true);
            updateBtn.setVisible(true);
            deleteBtn.setVisible(true);
        }else if(statusBox.getSelectedItem().toString().equals("Assigned") && typeBox.getSelectedItem().toString().equals("All")){
            query = "SELECT * FROM `peripheral` WHERE `status`= 2;";
            addBtn.setVisible(false);
            updateBtn.setVisible(false);
            deleteBtn.setVisible(false);
        }else if(statusBox.getSelectedItem().toString().equals("Deleted") && typeBox.getSelectedItem().toString().equals("All")){
            query = "SELECT * FROM `peripheral` WHERE `status`= 3;";
            addBtn.setVisible(false);
            updateBtn.setVisible(false);
            deleteBtn.setVisible(false);
        }else if(statusBox.getSelectedItem().toString().equals("All") && typeBox.getSelectedItem().toString().equals("Input")){
            query = "SELECT * FROM `peripheral` WHERE type LIKE 'Input';";
            addBtn.setVisible(false);
            updateBtn.setVisible(false);
            deleteBtn.setVisible(false);
        }else if(statusBox.getSelectedItem().toString().equals("Available") && typeBox.getSelectedItem().toString().equals("Input")){
            query = "SELECT * FROM `peripheral` WHERE `status`= 1 AND type LIKE 'Input';";
            addBtn.setVisible(true);
            updateBtn.setVisible(true);
            deleteBtn.setVisible(true);
        }else if(statusBox.getSelectedItem().toString().equals("Assigned") && typeBox.getSelectedItem().toString().equals("Input")){
            query = "SELECT * FROM `peripheral` WHERE `status`= 2 AND type LIKE 'Input';";
            addBtn.setVisible(false);
            updateBtn.setVisible(false);
            deleteBtn.setVisible(false);
        }else if(statusBox.getSelectedItem().toString().equals("Deleted") && typeBox.getSelectedItem().toString().equals("Input")){
            query = "SELECT * FROM `peripheral` WHERE `status`= 3 AND type LIKE 'Input';";
            addBtn.setVisible(false);
            updateBtn.setVisible(false);
            deleteBtn.setVisible(false);
        }else if(statusBox.getSelectedItem().toString().equals("All") && typeBox.getSelectedItem().toString().equals("Output")){
            query = "SELECT * FROM `peripheral` WHERE type LIKE 'Output';";
            addBtn.setVisible(false);
            updateBtn.setVisible(false);
            deleteBtn.setVisible(false);
        }else if(statusBox.getSelectedItem().toString().equals("Available") && typeBox.getSelectedItem().toString().equals("Output")){
            query = "SELECT * FROM `peripheral` WHERE `status`= 1 AND type LIKE 'Output';";
            addBtn.setVisible(true);
            updateBtn.setVisible(true);
            deleteBtn.setVisible(true);
        }else if(statusBox.getSelectedItem().toString().equals("Assigned") && typeBox.getSelectedItem().toString().equals("Output")){
            query = "SELECT * FROM `peripheral` WHERE `status`= 2 AND type LIKE 'Output';";
            addBtn.setVisible(false);
            updateBtn.setVisible(false);
            deleteBtn.setVisible(false);
        }else if(statusBox.getSelectedItem().toString().equals("Deleted") && typeBox.getSelectedItem().toString().equals("Output")){
            query = "SELECT * FROM `peripheral` WHERE `status`= 3 AND type LIKE 'Output';";
            addBtn.setVisible(false);
            updateBtn.setVisible(false);
            deleteBtn.setVisible(false);
        }
        ResultSet resultSet = DatabaseUtil.executeQuery(query);

        while (resultSet.next()) {
            String periId = resultSet.getString("peripheral_id");
                String fullName = resultSet.getString("name");
                String type1 = resultSet.getString("type");
                String category1 = resultSet.getString("category");
                String date = resultSet.getString("date_added");
            
            if(fullName.toLowerCase().contains(searchField.getText().toLowerCase())){
                model.addRow(new Object[]{periId, fullName, type1, category1, date,});
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(managePeriFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_searchFieldKeyReleased

    private void dateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateFieldActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        name.setText("");
        category.setSelectedItem("Keyboard");
        type.setSelectedItem("Input");
        statusBox.setSelectedItem("Available");
        typeBox.setSelectedItem("All");
        dateField.setText("");
        searchField.setText("");
        addBtn.setVisible(true);
    }//GEN-LAST:event_clearBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to perform this action?", "Confirmation", JOptionPane.YES_NO_OPTION);
        int selectedRow = periTable.getSelectedRow();
        if (selectedRow != -1) { // Ensure a row is selected
         DefaultTableModel model = (DefaultTableModel) periTable.getModel();

    // Retrieve data from the selected row
        String periId = model.getValueAt(selectedRow, 0).toString();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
if (confirmation == JOptionPane.YES_OPTION) {
    // User clicked 'Yes' for confirmation
    try {
        // Assuming 'id' is the primary key of your table and is used to identify the record to update
        String query = "UPDATE `peripheral` SET `name`=?, `type`=?, `category`=?, `date_added`=?  WHERE `peripheral_id`=?";
        connection = DatabaseUtil.getConnection();
        
            if(name.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this, "Please fillout important fields.");
            }else{
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, name.getText());
                preparedStatement.setString(2, type.getSelectedItem().toString());
                preparedStatement.setString(3, category.getSelectedItem().toString());
                preparedStatement.setString(4, dateField.getText());
                preparedStatement.setString(5, periId); 
            }
        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            // Data updated successfully
            JOptionPane.showMessageDialog(this, "Action performed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            displayToTable();
            name.setText("");
            category.setSelectedItem("Input");
            type.setSelectedItem("Keyboard");
            dateField.setText("");
            searchField.setText("");
        } else {
            // No rows affected, update failed
            JOptionPane.showMessageDialog(this, "Failed to perform action", "Failure", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        } finally {
            // Close resources in the reverse order of their creation
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
} else {
    // User clicked 'No' for confirmation
    JOptionPane.showMessageDialog(this, "Action cancelled", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
}
        }

        
        
    }//GEN-LAST:event_updateBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
         int selectedRow = periTable.getSelectedRow();

if (selectedRow != -1) { // Ensure a row is selected
    DefaultTableModel model = (DefaultTableModel) periTable.getModel();

    // Retrieve data from the selected row
    String periId = model.getValueAt(selectedRow, 0).toString();
    
    // Confirmation dialog
    int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this record?", "Confirmation", JOptionPane.YES_NO_OPTION);

    if (confirmation == JOptionPane.YES_OPTION) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Assuming 'id' is the primary key of your table and is used to identify the record to update
            String query = "UPDATE `peripheral` SET `status`=? WHERE `peripheral_id`=?";
            connection = DatabaseUtil.getConnection();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "3");
            preparedStatement.setString(2, periId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                // Data updated successfully
                JOptionPane.showMessageDialog(this, "Data Deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                displayToTable();
                name.setText("");
                category.setSelectedItem("Input");
                type.setSelectedItem("Keyboard");
                dateField.setText("");
                searchField.setText("");
            } else {
                // No rows affected, update failed
                JOptionPane.showMessageDialog(this, "Failed to update data", "Failure", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Close resources in the reverse order of their creation
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

    }//GEN-LAST:event_deleteBtnActionPerformed

    private void typeBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeBoxActionPerformed
        displayToTable();
    }//GEN-LAST:event_typeBoxActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // add filter to not add if deleted already
        insertDataIntoDatabase();
    }//GEN-LAST:event_addBtnActionPerformed

    private void statusBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_statusBoxItemStateChanged
    displayToTable();
    }//GEN-LAST:event_statusBoxItemStateChanged

    private void statusBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusBoxActionPerformed

    private void typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JComboBox<String> category;
    private javax.swing.JButton clearBtn;
    private javax.swing.JTextField dateField;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField name;
    private javax.swing.JTable periTable;
    private javax.swing.JTextField searchField;
    private javax.swing.JComboBox<String> statusBox;
    private javax.swing.JComboBox<String> type;
    private javax.swing.JComboBox<String> typeBox;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
