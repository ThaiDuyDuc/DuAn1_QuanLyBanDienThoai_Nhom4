package View;

import Extensions.XImage;
import Model.BoNho;
import Model.DienThoai;
import Model.DienThoaiChiTiet;
import Model.DonHang;
import Model.HoaDonChiTiet;
import Model.KhachHang;
import Repository.HoaDonDao;
import Repository.KhachHangDAO;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class LichSuMuaHang extends javax.swing.JFrame {

    DefaultTableModel modelTblDH = new DefaultTableModel();
    DefaultTableModel modelTblHDCT = new DefaultTableModel();
    KhachHangDAO khDao = new KhachHangDAO();
    HoaDonDao hdao = new HoaDonDao();

    public LichSuMuaHang() {
        initComponents();
        init();
    }
    KhachHang khachHang;
    ArrayList<DonHang> dhlist;

    void init() {
        setIconImage(XImage.getAppIcon().getImage());
        setTitle("Lịch sử mua hàng");
        setLocationRelativeTo(null);
    }
    public LichSuMuaHang(KhachHang khach) {
        initComponents();
        khachHang = khach;
        setLocationRelativeTo(null);
        modelTblDH = (DefaultTableModel) tblOrders.getModel();
        modelTblHDCT = (DefaultTableModel) tblHDCT.getModel();
        khachHang = khach;
        dhlist = khDao.getListDHByMaKH(khachHang.getMaKH());
        loadBill(dhlist);
        if (tblOrders.getRowCount() > 0) {
            tblOrders.setRowSelectionInterval(0, 0);
            showHDCT();
        }
    }

    private void loadBill(ArrayList<DonHang> list) {
        modelTblDH.setRowCount(0);
        for (DonHang dh : list) {
            Object data[] = {dh.getMaHD(), dh.getNgayLap(), dh.getMaNV(), dh.getTongTien(), dh.getTrangThai()};
            modelTblDH.addRow(data);
        }
    }

    private void showHDCT() {
        modelTblHDCT.setRowCount(0);
        int index = tblOrders.getSelectedRow();
        String maHD = tblOrders.getValueAt(index, 0).toString();
        ArrayList<HoaDonChiTiet> list = khDao.getAllHoaDonChiTietByMa(maHD);
        for (HoaDonChiTiet hdct : list) {
            DienThoaiChiTiet dtct = khDao.getDTCTById(hdct.getMaDTCT());
            DienThoai dt = khDao.getDienThoaiByID(dtct.getMaDT());
            BoNho bn = khDao.getBoNhoByID(dtct.getMaBoNho());
            double donGia = Double.parseDouble(hdct.getDonGia());
            int soLuong = hdct.getSoLuong();
            BigDecimal giaTienTungSP = BigDecimal.valueOf(donGia).multiply(BigDecimal.valueOf(soLuong));
            Object data[] = {hdct.getMaDTCT(),
                dt.getTenDienThoai(),
                bn.getRAM(),
                bn.getDungLuongLuuTru(),
                dtct.getMauSac(),
                hdct.getSoLuong(),
                giaTienTungSP + ""};
            modelTblHDCT.addRow(data);
        }
    }

    private void searchByDate(){
        Date start = txtStart.getDate();
        Date end = txtEnd.getDate();

        modelTblDH.setRowCount(0);
        for (DonHang dh : dhlist) {
            if (start == null || end == null || start.after(end)) {
                return;
            }
            if (dh.getNgayLap().after(start) && dh.getNgayLap().before(end)) {
                Object data[] = {dh.getMaHD(), dh.getNgayLap(), dh.getMaNV(), dh.getTongTien(), dh.getTrangThai()};
                modelTblDH.addRow(data);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHDCT = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblOrders = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cboState = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtStart = new com.toedter.calendar.JDateChooser();
        txtEnd = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("Địa Chỉ");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setText("Lịch sử mua hàng");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi tiết hóa đơn"));

        tblHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "RAM", "Dung lượng", "Màu sắc", "Số lượng", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblHDCT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách hóa đơn"));

        jScrollPane4.setBorder(null);

        tblOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã đơn hàng", "Ngày tạo", "Người tạo", "Giá trị", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblOrders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOrdersMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblOrders);

        txtSearch.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtSearch.setText("Tìm kiếm theo mã");
        txtSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSearchMouseClicked(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchKeyTyped(evt);
            }
        });

        jLabel3.setText("Trạng thái:");

        cboState.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Chờ thanh toán", "Đã thanh toán" }));
        cboState.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboStateItemStateChanged(evt);
            }
        });

        jLabel4.setText("Thời gian:");

        txtStart.setDateFormatString("yyyy-MM-dd");

        txtEnd.setDateFormatString("yyyy-MM-dd");

        jLabel5.setText("-");

        btnSearch.setText("Tìm");
        btnSearch.setMargin(new java.awt.Insets(2, 5, 2, 5));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStart, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSearch)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboState, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(cboState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(btnSearch))
                    .addComponent(txtStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(279, 279, 279))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblOrdersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrdersMouseClicked

        showHDCT();
    }//GEN-LAST:event_tblOrdersMouseClicked

    private void txtSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMouseClicked
        txtSearch.setText("");
    }//GEN-LAST:event_txtSearchMouseClicked

    private void txtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyTyped
        modelTblDH.setRowCount(0);
        for (DonHang dh : dhlist) {
            if (dh.getMaHD().contains(txtSearch.getText())) {
                Object data[] = {dh.getMaHD(), dh.getNgayLap(), dh.getMaNV(), dh.getTongTien(), dh.getTrangThai()};
                modelTblDH.addRow(data);
            }
        }
    }//GEN-LAST:event_txtSearchKeyTyped

    private void cboStateItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboStateItemStateChanged
        if (cboState.getSelectedItem().equals("Chờ thanh toán")) {
            modelTblDH.setRowCount(0);
            for (DonHang dh : dhlist) {
                if (dh.getTrangThai().equals("Chờ thanh toán") || dh.getTrangThai().equals("Chưa thanh toán")) {
                    Object data[] = {dh.getMaHD(), dh.getNgayLap(), dh.getMaNV(), dh.getTongTien(), dh.getTrangThai()};
                    modelTblDH.addRow(data);
                }
            }
        } else if (cboState.getSelectedItem().equals("Đã thanh toán")) {
            modelTblDH.setRowCount(0);
            for (DonHang dh : dhlist) {
                if (dh.getTrangThai().equals("Đã hoàn thành") || dh.getTrangThai().equals("Đã thanh toán")) {
                    Object data[] = {dh.getMaHD(), dh.getNgayLap(), dh.getMaNV(), dh.getTongTien(), dh.getTrangThai()};
                    modelTblDH.addRow(data);
                }
            }
        } else if (cboState.getSelectedItem().equals("Tất cả")) {
            loadBill(dhlist);
            if (tblOrders.getRowCount() > 0) {
                tblOrders.setRowSelectionInterval(0, 0);
                showHDCT();
            }
        }
    }//GEN-LAST:event_cboStateItemStateChanged

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        searchByDate();
    }//GEN-LAST:event_btnSearchActionPerformed

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
//            java.util.logging.Logger.getLogger(LichSuMuaHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(LichSuMuaHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(LichSuMuaHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(LichSuMuaHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new LichSuMuaHang().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cboState;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tblHDCT;
    private javax.swing.JTable tblOrders;
    private com.toedter.calendar.JDateChooser txtEnd;
    private javax.swing.JTextField txtSearch;
    private com.toedter.calendar.JDateChooser txtStart;
    // End of variables declaration//GEN-END:variables

}
