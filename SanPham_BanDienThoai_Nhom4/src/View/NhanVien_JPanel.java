package View;

import Extensions.quyenDanhNhap;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Model.Nhan_Vien;
import Repository.FileChoose;
import Repository.Nhan_Vien_Service;


public class NhanVien_JPanel extends javax.swing.JPanel {

  
    private ArrayList<Nhan_Vien> list = new ArrayList();
    private Nhan_Vien_Service nvsv = new Nhan_Vien_Service();
    private DefaultTableModel model = new DefaultTableModel();
    
    int index = 1;
    String duongdananh = ".\\src\\anh\\";
    String duongdananh1 = "";
    int page = 1;
    boolean check = false;

    public NhanVien_JPanel() {
        initComponents();
        list = (ArrayList<Nhan_Vien>) nvsv.paging(page, 2);
        fillTable();
    }

    
    public void fillTable() {
        model = (DefaultTableModel) tbl_Nhan_Vien.getModel();
        model.setRowCount(0);
        list = (ArrayList<Nhan_Vien>) nvsv.paging(page, 2);
        for (Nhan_Vien nhan_Vien : list) {
            model.addRow(new Object[]{
                nhan_Vien.getMaNV(),
                nhan_Vien.getTenNV(),
                nhan_Vien.getDiaChi(),
                nhan_Vien.getNgaySinh(),
                nhan_Vien.getSDT(),
                nhan_Vien.getAnh(),
                nhan_Vien.getGioiTinh(),
                nhan_Vien.getMatKhau(),
                nhan_Vien.getNgayTao(),
                nhan_Vien.getTrangThai(),
                nhan_Vien.getEmaill(),
                nhan_Vien.getChucVu(),});

        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_Nhan_Vien = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        rdoNhanVien = new javax.swing.JRadioButton();
        rdoQuanLy = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtNgayTao = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtEmaill = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        cbbTrangThai = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        txtSDT1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        cbbTrangThai1 = new javax.swing.JComboBox<>();
        btnTimKiem = new javax.swing.JButton();
        lbl_anh = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        tbl_Nhan_Vien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã", "Họ Tên", "Địa chỉ", "Ngày sinh", "SDT", "Ảnh", "Giới tính", "Mật khẩu", "Ngày tạo", "Trạng thái", "Emaill", "Chức vụ"
            }
        ));
        tbl_Nhan_Vien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_Nhan_VienMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbl_Nhan_VienMouseEntered(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_Nhan_Vien);

        jLabel12.setText("Tên NV");

        jLabel13.setText("Chức vụ");

        buttonGroup1.add(rdoNhanVien);
        rdoNhanVien.setSelected(true);
        rdoNhanVien.setText("Nhân Viên");

        buttonGroup1.add(rdoQuanLy);
        rdoQuanLy.setText("Quản Lý");

        jLabel14.setText("Giới tính");

        buttonGroup2.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        buttonGroup2.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel15.setText("Mã NV");

        jLabel16.setText("Địa chỉ");

        jLabel17.setText("Ngày Sinh");

        jLabel18.setText("SDT");

        jLabel19.setText("Ngày Tạo");

        jLabel20.setText("Mật khẩu");

        jLabel21.setText("Emaill");

        jLabel22.setText("Trạng thái");

        cbbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nghỉ làm", "Đang làm việc" }));

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/NhanVien/icon/Add.png"))); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/NhanVien/icon/Delete.png"))); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/NhanVien/icon/Edit.png"))); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(204, 255, 204));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton1.setText("<<");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText(">>");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        cbbTrangThai1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nghỉ làm", "Đang làm việc" }));
        cbbTrangThai1.setBorder(new javax.swing.border.MatteBorder(null));
        cbbTrangThai1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbTrangThai1ItemStateChanged(evt);
            }
        });

        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        lbl_anh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton3.setText("Chọn ảnh");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(lbl_anh, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(98, 98, 98)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSDT1)
                                    .addComponent(txtMa)
                                    .addComponent(txtDiaChi)
                                    .addComponent(txtNgaySinh))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel19)
                                                .addComponent(jLabel12))
                                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTen)
                                    .addComponent(txtNgayTao)
                                    .addComponent(txtMatKhau)
                                    .addComponent(txtEmaill)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(cbbTrangThai1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 267, Short.MAX_VALUE)
                                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(68, 68, 68))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(rdoNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2)
                                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton3))
                        .addGap(64, 64, 64))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbTrangThai1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmaill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40))
                    .addComponent(lbl_anh, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtSDT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel15)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdoNhanVien)
                        .addComponent(rdoQuanLy)))
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(rdoNam)
                        .addComponent(rdoNu))
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton1))))
                .addGap(24, 24, 24))
        );

        jTabbedPane1.addTab("Nhân Viên", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_Nhan_VienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_Nhan_VienMouseClicked
        int row = tbl_Nhan_Vien.getSelectedRow();
        Nhan_Vien nv = list.get(row);
        String ma = (String) tbl_Nhan_Vien.getValueAt(row, 0);
        txtMa.setText(ma);
        String ten = (String) tbl_Nhan_Vien.getValueAt(row, 1);
        txtTen.setText(ten);
        String diachi = (String) tbl_Nhan_Vien.getValueAt(row, 2);
        txtDiaChi.setText(diachi);
        String ngaySinh = (String) tbl_Nhan_Vien.getValueAt(row, 3);
        txtNgaySinh.setText(ngaySinh);
        String sdt = (String) tbl_Nhan_Vien.getValueAt(row, 4);
        txtSDT1.setText(sdt);
        String gioiTinh = (String) tbl_Nhan_Vien.getValueAt(row, 5);
        if (gioiTinh.equals("1")) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        String matKhau = (String) tbl_Nhan_Vien.getValueAt(row, 7);
        txtMatKhau.setText(matKhau);
        String ngayTao = (String) tbl_Nhan_Vien.getValueAt(row, 8);
        txtNgayTao.setText(ngayTao);
        String trangThai = (String) tbl_Nhan_Vien.getValueAt(row, 9);
        cbbTrangThai.setSelectedItem(trangThai);
        String Emaill = (String) tbl_Nhan_Vien.getValueAt(row, 10);
        txtEmaill.setText(Emaill);
        String chucVu = (String) tbl_Nhan_Vien.getValueAt(row, 11);
        if (chucVu.equals("Nhân viên")) {
            rdoNhanVien.setSelected(true);
        } else {
            rdoQuanLy.setSelected(true);
        }
        java.awt.Image orImage = new ImageIcon(duongdananh + nv.getAnh()).getImage();
        System.out.println(duongdananh + nv.getAnh());
        java.awt.Image resize = orImage.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        lbl_anh.setIcon(new ImageIcon(resize));
    }//GEN-LAST:event_tbl_Nhan_VienMouseClicked

    private void tbl_Nhan_VienMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_Nhan_VienMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_Nhan_VienMouseEntered

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (nvsv.add(this.readForm()) > 0) {
            JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không");
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            fillTable();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
            // Kiểm tra quyền quản lý trước khi xóa sản phẩm
    if (!quyenDanhNhap.isManager("Quản lý")) {
        JOptionPane.showMessageDialog(this, "Bạn không có quyền Xóa.");
        return;
    }
        index = tbl_Nhan_Vien.getSelectedRow();
        String ma;
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "bạn chưa chọn dòng để xóa");
        } else { //đã chọn
            // lấy mã sinh viên từ table
            ma = (String) tbl_Nhan_Vien.getValueAt(index, 0);
            if (nvsv.deleteSV(ma) > 0) { //xóa được
                JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không");
                JOptionPane.showMessageDialog(this, "Xóa thành công");
                fillTable();

            } else // không xóa được
            {
                JOptionPane.showMessageDialog(this, "Xóa thất bại");
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        index = tbl_Nhan_Vien.getSelectedRow();
        String ma;
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "bạn chưa chọn dòng để sửa");
        } else { //đã chọn
            // lấy mã sinh viên từ table
            ma = (String) tbl_Nhan_Vien.getValueAt(index, 0);
            if (nvsv.updateSV(ma, this.readForm()) > 0) { //xóa được
                JOptionPane.showConfirmDialog(this, "Ban có muốn sửa không");
                JOptionPane.showMessageDialog(this, "Sửa thành công");
                fillTable();

            } else // không xóa được
            {
                JOptionPane.showMessageDialog(this, "Sửa thất bại");
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        txtDiaChi.setText("");
        txtMa.setText("");
        txtTen.setText("");
        txtEmaill.setText("");
        txtMatKhau.setText("");
        txtNgaySinh.setText("");
        txtNgayTao.setText("");
        txtSDT1.setText("");
        cbbTrangThai.setSelectedItem("");
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.page--;
        fillTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.page++;
        fillTable();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbbTrangThai1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbTrangThai1ItemStateChanged

    }//GEN-LAST:event_cbbTrangThai1ItemStateChanged

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        //        String tk = "";
        //        tk = cbbTrangThai1.getSelectedItem() + "";
        //        fillTable();
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        duongdananh1 = FileChoose.chooseFile();
        java.awt.Image orImage = new ImageIcon(duongdananh + duongdananh1).getImage();
        java.awt.Image resize = orImage.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        lbl_anh.setIcon(new ImageIcon(resize));
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbTrangThai;
    private javax.swing.JComboBox<String> cbbTrangThai1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbl_anh;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNhanVien;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JRadioButton rdoQuanLy;
    private javax.swing.JTable tbl_Nhan_Vien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmaill;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtSDT1;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables

    public Nhan_Vien readForm() {
        String ma, ten, diachi, ngaysinh, sdt, gioitinh, matkhau, ngaytao, trangthai, emaill, chucvu;
        ma = txtMa.getText().trim();
        ten = txtTen.getText();
        diachi = txtDiaChi.getText();
        ngaysinh = txtNgaySinh.getText();
        sdt = txtSDT1.getText();
        if (rdoNam.isSelected()) {
            gioitinh = "1";
        } else {
            gioitinh = "0";
        }
        matkhau = txtMatKhau.getText();
        ngaytao = txtNgayTao.getText();
        trangthai = cbbTrangThai.getSelectedItem().toString();
        emaill = txtEmaill.getText();
        if (rdoNhanVien.isSelected()) {
            chucvu = "Nhân viên";
        } else {
            chucvu = "Quản lý";
        }

        return new Nhan_Vien(ma, ten, diachi, ngaysinh, sdt, duongdananh1, gioitinh, matkhau, ngaytao, trangthai, emaill, chucvu);

    }
}
