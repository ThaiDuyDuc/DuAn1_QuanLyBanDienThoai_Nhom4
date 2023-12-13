package View;

import Extensions.XImage;
import Extensions.quyenDanhNhap;
import Model.ManHinh;
import Repository.ManHinh_DAO;
import Repository.ServiceDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class JDialog_Manhinh extends javax.swing.JDialog {

    ServiceDAO service = new ManHinh_DAO();
    
    public JDialog_Manhinh(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        init();
        LoadTable();
    }
    
    void init() {
        setIconImage(XImage.getAppIcon().getImage());
        setTitle("Màn hình");
        setLocationRelativeTo(null);
    }

    public void LoadTable() {
        DefaultTableModel model = (DefaultTableModel) this.tblBang.getModel();
        List<ManHinh> list = service.selectAll();
        model.setRowCount(0);
        for (ManHinh o : list) {
            Object[] rowData = {
                o.getMaManHinh(),
                o.getCongNgheManHinh(),
                o.getDoPhanGiai(),
                o.getKichThuoc(),
                o.getDoSang(),
                o.getManHinhCamUng()
            };
            model.addRow(rowData);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCongNghe = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDoPhanGiai = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtKichThuoc = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDoSang = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        rdoCo = new javax.swing.JRadioButton();
        rdoKhong = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblBang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Công nghệ", "Độ phân giải", "Kích thước", "Độ sáng", "Cảm ứng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBang);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Thông số màn hình");

        jLabel2.setText("Mã");

        jLabel3.setText("Công nghệ");

        jLabel4.setText("Độ phân giải");

        jLabel5.setText("Kích thước");

        jLabel6.setText("Độ sáng");

        jLabel7.setText("Cảm ứng");

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoCo);
        rdoCo.setSelected(true);
        rdoCo.setText("Có cảm ứng");

        buttonGroup1.add(rdoKhong);
        rdoKhong.setText("Không có cảm ứng");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(45, 45, 45)
                        .addComponent(txtKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(45, 45, 45)
                        .addComponent(txtDoPhanGiai, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(45, 45, 45)
                        .addComponent(txtCongNghe, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(45, 45, 45)
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rdoCo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rdoKhong))
                            .addComponent(txtDoSang, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa))
                .addGap(43, 43, 43))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCongNghe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDoPhanGiai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDoSang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(rdoCo)
                    .addComponent(rdoKhong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
         ManHinh bn = DataForm();
        if (bn == null) {
            return;
        }
        try {
            service.insert(bn);
            LoadTable();
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi hệ thống");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
         int row = this.tblBang.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng để sửa");
            return;
        }
        ManHinh bn = DataForm();
        if (bn == null) {
            return;
        }
        try {

            service.update(bn);
            LoadTable();
            JOptionPane.showMessageDialog(this, "Sửa thành công");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi hệ thống");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void tblBangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangMouseClicked
          int row = this.tblBang.getSelectedRow();
        if (row == -1) {
            return;
        }
        txtMa.setText(tblBang.getValueAt(row, 0).toString());
        txtCongNghe.setText(tblBang.getValueAt(row, 1).toString());
        txtDoPhanGiai.setText(tblBang.getValueAt(row, 2).toString());
        txtKichThuoc.setText(tblBang.getValueAt(row, 3).toString());
        txtDoSang.setText(tblBang.getValueAt(row, 4).toString());
        if(tblBang.getValueAt(row, 5).toString().equalsIgnoreCase("Có cảm ứng")){
            rdoCo.setSelected(true);
        }else{
            rdoKhong.setSelected(true);
        }
    }//GEN-LAST:event_tblBangMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
            // Kiểm tra quyền quản lý trước khi xóa sản phẩm
    if (!quyenDanhNhap.isManager("Quản lý")) {
        JOptionPane.showMessageDialog(this, "Bạn không có quyền Xóa.");
        return;
    }
        int row = this.tblBang.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng để xóa");
            return;
        }

        try {
            
            int index = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không","Thông báo", JOptionPane.YES_NO_OPTION);
            if(index == 0){
            service.delete(txtMa.getText());
            LoadTable();
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            }else{
                JOptionPane.showMessageDialog(this, "Xóa không thành công");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi hệ thống");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    ManHinh DataForm(){
        String ma = txtMa.getText();
        String congnghe = txtCongNghe.getText();
        String dpg = txtDoPhanGiai.getText();
        String kichthuoc = txtKichThuoc.getText();
        String dosang = txtDoSang.getText();
        String camung =  rdoCo.isSelected() == true ? "Có cảm ứng": "Không có cảm ứng";
        
        if(ma.isEmpty()){
            JOptionPane.showMessageDialog(this, "Mã không được để trống");
            txtMa.requestFocus();
            return null;
        }
        
        if(congnghe.isEmpty()){
            JOptionPane.showMessageDialog(this, "Công nghệ không được để trống");
            txtCongNghe.requestFocus();
            return null;
        }
        
        if(dpg.isEmpty()){
            JOptionPane.showMessageDialog(this, "Độ phân giải không được để trống");
            txtDoPhanGiai.requestFocus();
            return null;
        }
        
        if(kichthuoc.isEmpty()){
            JOptionPane.showMessageDialog(this, "Kích thước không được để trống");
            txtKichThuoc.requestFocus();
            return null;
        }
        
        try {
            ManHinh o = new ManHinh(ma, congnghe, dpg, kichthuoc, dosang, camung);
            return o;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
            java.util.logging.Logger.getLogger(JDialog_Manhinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialog_Manhinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialog_Manhinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialog_Manhinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialog_Manhinh dialog = new JDialog_Manhinh(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoCo;
    private javax.swing.JRadioButton rdoKhong;
    private javax.swing.JTable tblBang;
    private javax.swing.JTextField txtCongNghe;
    private javax.swing.JTextField txtDoPhanGiai;
    private javax.swing.JTextField txtDoSang;
    private javax.swing.JTextField txtKichThuoc;
    private javax.swing.JTextField txtMa;
    // End of variables declaration//GEN-END:variables
}
