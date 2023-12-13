/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Extensions.Validation;
import Extensions.XImage;
import Extensions.quyenDanhNhap;
import Model.DiaChi;
import Model.KhachHang;
import Repository.KhachHangDAO;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class DiaChiView extends javax.swing.JFrame {

    /**
     * Creates new form DiaChiView
     */
    DefaultTableModel modelTblDC = new DefaultTableModel();
    KhachHangDAO khDao = new KhachHangDAO();
    Validation validation = new Validation();
//    public DiaChiView() {
//        
//    }
    KhachHang khachHang;

    public DiaChiView(KhachHang khach) {
        initComponents();
        setLocationRelativeTo(null);
        init();
        modelTblDC = (DefaultTableModel) tblAddress.getModel();
        khachHang = khach;
        loadAddress();
        if (tblAddress.getRowCount() > 0) {
            tblAddress.setRowSelectionInterval(0, 0);
            showDetailsAddress();
        }
    }
    
    void init() {
        setIconImage(XImage.getAppIcon().getImage());
        setTitle("Địa chỉ");
        setLocationRelativeTo(null);
    }

    private void loadAddress() {
        modelTblDC.setRowCount(0);
        ArrayList<Model.DiaChi> list = khDao.getListDCByMaKH(khachHang.getMaKH());
        for (DiaChi dc : list) {
            Object data[] = {dc.getMaDC(), dc.getThanhPho(), dc.getQuan(), dc.getPhuong(),
                dc.getToaNha(), dc.getGhiChu()};
            modelTblDC.addRow(data);
        }
    }

    private Model.DiaChi getAddress() {
        int count = 0;
        String city = txtCity.getText();
        String district = txtDistrict.getText();
        String ward = txtWard.getText();
        String details = txaDetailsAddress.getText();
        String description = txaDescription.getText();
        count = validation.checkNull(txtCity, errorCity)
                + validation.checkNull(txtDistrict, errorDistrict)
                + validation.checkNull(txtWard, errorWard);
        if (count == 0) {
            Model.DiaChi dc = new Model.DiaChi(null, city, district, ward, details, description);
            return dc;
        }
        return null;
    }

    private void showDetailsAddress() {
        int index = tblAddress.getSelectedRow();
        txtCity.setText(tblAddress.getValueAt(index, 1) + "");
        txtDistrict.setText(tblAddress.getValueAt(index, 2) + "");
        txtWard.setText(tblAddress.getValueAt(index, 3) + "");
        txaDetailsAddress.setText(tblAddress.getValueAt(index, 4) + "");
        txaDescription.setText(tblAddress.getValueAt(index, 5) + "");
    }

    private void insertDetailedAddress(String idKH, String idDC, String msg1, String msg2) {
        if (khDao.insertDCCT(idKH, idDC) > 0) {
            JOptionPane.showMessageDialog(this, msg1);
            loadAddress();
            if (tblAddress.getRowCount() > 0) {
                tblAddress.setRowSelectionInterval(0, 0);
                showDetailsAddress();
            }
        } else {
            JOptionPane.showMessageDialog(this, msg2);
        }
    }

    private void insertAddress() {
        String idKH = khachHang.getMaKH();
        Random rd = new Random();
        String currentTimeMillis = System.currentTimeMillis() + "";
        String id = "DC" + currentTimeMillis.substring(6) + rd.nextInt(1000) + "";
        Model.DiaChi dc = getAddress();
        try {
            if (dc != null) {
                Model.DiaChi o = khDao.getDC(dc);
                System.out.println(o);
                if (o == null) {
                    dc.setMaDC(id);
                    if (khDao.insertDC(dc) > 0) {
                        insertDetailedAddress(idKH, id, "Thêm địa chỉ mới thành công!", "Thêm địa chỉ mới thất bại!");
                        errorCity.setText(" ");
                        errorDistrict.setText(" ");
                        errorWard.setText(" ");
                    }
                } else {
                    insertDetailedAddress(idKH, o.getMaDC(), "Thêm địa chỉ mới thành công!", "Thêm địa chỉ mới thất bại!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateAddress() {
        DiaChi dc = getAddress();
        String maKH = khachHang.getMaKH();
        String maDC = tblAddress.getValueAt(tblAddress.getSelectedRow(), 0) + "";
        int result = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật?");
        try {
            if (dc != null) {
                if (result == JOptionPane.YES_OPTION) {
                    DiaChi o = khDao.getDC(dc);
                    if (o == null) {
                        Random rd = new Random();
                        String ctm = System.currentTimeMillis() + "";
                        String idDC = "DC" + ctm.substring(6) + rd.nextInt(1000) + "";
                        dc.setMaDC(idDC);
                        if (khDao.insertDC(dc) > 0) {
                            khDao.deleteDCCT(maKH, maDC);
                            insertDetailedAddress(maKH, dc.getMaDC(), "Cập nhật địa chỉ mới thành công!", "Cập nhật địa chỉ mới thất bại!");
                            loadAddress();
                            errorCity.setText(" ");
                            errorDistrict.setText(" ");
                            errorWard.setText(" ");
                        }
                    } else {
                        dc.setMaDC(o.getMaDC());
                        khDao.deleteDCCT(maKH, maDC);
                        insertDetailedAddress(maKH, dc.getMaDC(), "Cập nhật địa chỉ mới thành công!", "Cập nhật địa chỉ mới thất bại!");
                        loadAddress();
                        errorCity.setText(" ");
                        errorDistrict.setText(" ");
                        errorWard.setText(" ");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteAddress() {
         // Kiểm tra quyền quản lý trước khi xóa sản phẩm
    if (!quyenDanhNhap.isManager("Quản lý")) {
        JOptionPane.showMessageDialog(this, "Bạn không có quyền Xóa.");
        return;
    }
        String idKH = khachHang.getMaKH();
        int index = tblAddress.getSelectedRow();
        String id = tblAddress.getValueAt(index, 0) + "";
        if (khDao.deleteDCCT(idKH, id) > 0) {
//            if (khDao.deleteDC(id) > 0) {
            JOptionPane.showMessageDialog(this, "Xóa địa chỉ thành công!");
            loadAddress();
            if (tblAddress.getRowCount() > 0) {
                tblAddress.setRowSelectionInterval(0, 0);
                showDetailsAddress();
            }
//            }
        } else {
            JOptionPane.showMessageDialog(this, "Xóa địa chỉ thất bại!");
        }
    }

    private void clear() {
        txtCity.setText("");
        txtDistrict.setText("");
        txtWard.setText("");
        txaDescription.setText("");
        txaDetailsAddress.setText("");
        errorCity.setText(" ");
        errorDistrict.setText(" ");
        errorWard.setText(" ");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCity = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDistrict = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtWard = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaDescription = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAddress = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txaDetailsAddress = new javax.swing.JTextArea();
        errorDistrict = new javax.swing.JLabel();
        errorCity = new javax.swing.JLabel();
        errorWard = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("Địa Chỉ");

        jLabel4.setText("Tỉnh/TP:");

        jLabel5.setText("Quận/Huyện:");

        jLabel6.setText("Xã/Phường:");

        jLabel7.setText("Đường/Tòa nhà:");

        jLabel2.setText("Ghi chú:");

        txaDescription.setColumns(20);
        txaDescription.setRows(5);
        jScrollPane1.setViewportView(txaDescription);

        tblAddress.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã DC", "Thành phố", "Quận", "Tỉnh", "Đường, tòa nhà", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAddress.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAddressMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblAddress);

        jPanel1.setLayout(new java.awt.GridLayout(1, 4, 5, 0));

        btnAdd.setText("Thêm ĐC");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel1.add(btnAdd);

        btnUpdate.setText("Cập nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdate);

        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(btnDelete);

        btnClear.setText("Mới");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        jPanel1.add(btnClear);

        txaDetailsAddress.setColumns(20);
        txaDetailsAddress.setRows(5);
        jScrollPane3.setViewportView(txaDetailsAddress);

        errorDistrict.setForeground(new java.awt.Color(255, 0, 0));
        errorDistrict.setText("       ");

        errorCity.setForeground(new java.awt.Color(255, 0, 0));
        errorCity.setText("       ");

        errorWard.setForeground(new java.awt.Color(255, 0, 0));
        errorWard.setText("       ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(errorDistrict)
                                            .addComponent(txtDistrict, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(errorWard)
                                            .addComponent(txtWard, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(errorCity)
                                            .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(54, 54, 54)
                                        .addComponent(jScrollPane1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(354, 354, 354)
                                .addComponent(jLabel1)))
                        .addGap(32, 32, 32)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(214, 214, 214))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(5, 5, 5)
                        .addComponent(errorCity)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDistrict, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel5)))
                        .addGap(5, 5, 5)
                        .addComponent(errorDistrict)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtWard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addGap(7, 7, 7)
                .addComponent(errorWard)
                .addGap(7, 7, 7)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblAddressMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAddressMouseClicked
        showDetailsAddress();
        errorCity.setText(" ");
        errorDistrict.setText(" ");
        errorWard.setText(" ");
    }//GEN-LAST:event_tblAddressMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        insertAddress();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        updateAddress();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        deleteAddress();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    /**
     * @param args the command line arguments
     */
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
//            java.util.logging.Logger.getLogger(DiaChiView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(DiaChiView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(DiaChiView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(DiaChiView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new DiaChiView().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel errorCity;
    private javax.swing.JLabel errorDistrict;
    private javax.swing.JLabel errorWard;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblAddress;
    private javax.swing.JTextArea txaDescription;
    private javax.swing.JTextArea txaDetailsAddress;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtDistrict;
    private javax.swing.JTextField txtWard;
    // End of variables declaration//GEN-END:variables
}
