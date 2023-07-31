package Store;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class View_Stores extends javax.swing.JFrame {

    Service_Stores service = new Service_Stores();

    public View_Stores() {
        initComponents();
        this.loadTable();
        this.setLocationRelativeTo(null);
    }

    public void loadTable() {
        DefaultTableModel model = (DefaultTableModel) this.tblListStores.getModel();
        model.setRowCount(0);
        // ArrayList<Stores> list = service.getAll();
        for (Stores s : service.getAll()) {    //for(Stores s : list)
            Object[] rowData = {s.getId(), s.getCode(), s.getName(), s.getAdress(), s.getCity(), s.getCountry()};
            model.addRow(rowData);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListStores = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCode = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtAdress = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCity = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCountry = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Stores manager");

        tblListStores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Code", "Name", "Adress", "City", "Country"
            }
        ));
        tblListStores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListStoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblListStores);

        jLabel2.setText("ID : ");

        txtID.setEditable(false);

        jLabel3.setText("Code :");

        jLabel4.setText("Adress :");

        jLabel5.setText("City :");

        jLabel6.setText("Country :");

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jLabel7.setText("Name :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(196, 196, 196)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCity)
                                    .addComponent(txtCountry)
                                    .addComponent(txtID)
                                    .addComponent(txtCode)
                                    .addComponent(txtAdress)
                                    .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                                .addGap(97, 97, 97)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnUpdate)
                                        .addComponent(btnDelete)
                                        .addComponent(btnClear, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(btnAdd))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAdd, btnClear, btnDelete, btnUpdate});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(btnDelete)
                    .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtAdress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAdd, btnClear, btnDelete, btnUpdate});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblListStoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListStoresMouseClicked
        int row = this.tblListStores.getSelectedRow();
        if (row == -1) {
            return;
        }

        txtID.setText(tblListStores.getValueAt(row, 0).toString());
        txtCode.setText(tblListStores.getValueAt(row, 1).toString());
        txtName.setText(tblListStores.getValueAt(row, 2).toString());
        txtAdress.setText(tblListStores.getValueAt(row, 3).toString());
        txtCity.setText(tblListStores.getValueAt(row, 4).toString());
        txtCountry.setText(tblListStores.getValueAt(row, 5).toString());
    }//GEN-LAST:event_tblListStoresMouseClicked

    public Stores LoadData() {
        String code = txtCode.getText().trim();
        String name = txtName.getText().trim();
        String adress = txtAdress.getText().trim();
        String city = txtCity.getText().trim();
        String country = txtCountry.getText().trim();

        if (code.isEmpty()
                || name.isEmpty()
                || adress.isEmpty()
                || city.isEmpty()
                || country.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn đang điền trường rỗng!");
            return null;
        }

        Stores s = new Stores(null, code, name, adress, city, country);
        return s;
    }
    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        CleadForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        Stores s = LoadData();
        if (s == null) {
            return;
        }
        if (service.Add(s) != null) {
            service.Add(s);
            loadTable();
            JOptionPane.showMessageDialog(this, "Thêm thành công!");
        }else{
            
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        Stores s = LoadData();
        if(s == null){
            return;
        }
        int row = this.tblListStores.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(this, "Bạn cần chọn dòng dể Update!");
            return;
        }
        
        String id = tblListStores.getValueAt(row, 0).toString();
        s.setId(id);
        if(service.Update(s) != null){
            loadTable();
            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
            CleadForm();
        }else{
            JOptionPane.showMessageDialog(this, "Cập nhât thất bại!");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    public void CleadForm() {
        txtID.setText("");
        txtCode.setText("");
        txtName.setText("");
        txtAdress.setText("");
        txtCity.setText("");
        txtCountry.setText("");
    }

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
            java.util.logging.Logger.getLogger(View_Stores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View_Stores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View_Stores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View_Stores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View_Stores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblListStores;
    private javax.swing.JTextField txtAdress;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtCountry;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
