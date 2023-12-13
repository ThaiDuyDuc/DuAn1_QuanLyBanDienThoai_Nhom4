package View;

import Bean.DanhMuc;
import ConTroller.ChuyenManHinh;
import Extensions.XImage;
import Extensions.quyenDanhNhap;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Main extends javax.swing.JFrame {

    ThongTinCaNhan_JDialog ttcn = new ThongTinCaNhan_JDialog(this, true);

    public Main() {
        initComponents();
//        this.openLogin();
        setLocationRelativeTo(null);
        init();
        ChuyenManHinh();
        jLabel1.setText(quyenDanhNhap.user.getTenNV().trim());
        lblMaNV.setText(quyenDanhNhap.user.getMaNV().trim());
        lblChucVu.setText(quyenDanhNhap.user.getChucVu().trim());
        
        
        java.awt.Image orImage = new ImageIcon(".\\src\\anh\\"+quyenDanhNhap.user.getAnh()).getImage();
        java.awt.Image resize = orImage.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        lblAnh.setImage(new ImageIcon(resize));
    }

    void init() {
        setIconImage(XImage.getAppIcon().getImage());
        setTitle("Hệ thống quản lý bán điện thoại");
        setLocationRelativeTo(null);
    }

    public void ChuyenManHinh() {
        ChuyenManHinh control = new ChuyenManHinh(panelView);
        control.setView(Panel_BanHang, lblLoGoBanHang, lblBanHang);
        ArrayList<DanhMuc> listItem = new ArrayList<>();
        listItem.add(new DanhMuc("BanHang", Panel_BanHang, lblLoGoBanHang, lblBanHang));
        listItem.add(new DanhMuc("SanPham", Panel_SanPham, lblLoGoSanPham, lblSanPham));
        listItem.add(new DanhMuc("KhachHang", Panel_KhachHang, lblLoGoKhachHang, lblKhachHang));
        listItem.add(new DanhMuc("HoaDon", Panel_HoaDon, lblLoGoHoaDon, lblHoaDon));
        listItem.add(new DanhMuc("GiamGia", Panel_GiamGia, lblLoGoGiamGia, lblGiamGia));
        listItem.add(new DanhMuc("NhanVien", Panel_NhanVien, lblLoGoNhanVien, lblNhanVien));
        listItem.add(new DanhMuc("ThongKe", Panel_ThongKe, lblLoGoThongKe, lblThongKe));

        control.setEvent(listItem);
    }

   

    // Phương thức này thực hiện công việc tương tự doClick cho JLabel
    private void doClickForLabel(JLabel label, JLabel label1) {
        // Xử lý sự kiện tương ứng với label
        if (label == lblLoGoNhanVien) {
            // Chuyển đến trang Nhân viên
            ChuyenManHinh control = new ChuyenManHinh(panelView);
           
            control.setViewNhanVien(Panel_NhanVien, lblLoGoNhanVien, lblNhanVien);

            // Tắt form ThongTinCaNhan_JDialog
            dispose();
        }
        // Thêm xử lý cho các label khác nếu cần
    }
//
//    void openLogin() {
//        new Loign_JDialog(this, true).setVisible(true);
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Panel_BanHang = new javax.swing.JPanel();
        lblLoGoBanHang = new javax.swing.JLabel();
        lblBanHang = new javax.swing.JLabel();
        Panel_SanPham = new javax.swing.JPanel();
        lblLoGoSanPham = new javax.swing.JLabel();
        lblSanPham = new javax.swing.JLabel();
        Panel_KhachHang = new javax.swing.JPanel();
        lblLoGoKhachHang = new javax.swing.JLabel();
        lblKhachHang = new javax.swing.JLabel();
        Panel_HoaDon = new javax.swing.JPanel();
        lblLoGoHoaDon = new javax.swing.JLabel();
        lblHoaDon = new javax.swing.JLabel();
        Panel_GiamGia = new javax.swing.JPanel();
        lblLoGoGiamGia = new javax.swing.JLabel();
        lblGiamGia = new javax.swing.JLabel();
        Panel_NhanVien = new javax.swing.JPanel();
        lblLoGoNhanVien = new javax.swing.JLabel();
        lblNhanVien = new javax.swing.JLabel();
        Panel_ThongKe = new javax.swing.JPanel();
        lblLoGoThongKe = new javax.swing.JLabel();
        lblThongKe = new javax.swing.JLabel();
        lblAnh = new Model.ImageAvatar();
        lblMaNV = new javax.swing.JLabel();
        lblChucVu = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        panelView = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Panel_BanHang.setLayout(new java.awt.GridLayout(1, 2));

        lblLoGoBanHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLoGoBanHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/BanHang.png"))); // NOI18N
        Panel_BanHang.add(lblLoGoBanHang);

        lblBanHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBanHang.setText("Bán hàng");
        Panel_BanHang.add(lblBanHang);

        Panel_SanPham.setLayout(new java.awt.GridLayout(1, 2));

        lblLoGoSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLoGoSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/SanPham.png"))); // NOI18N
        Panel_SanPham.add(lblLoGoSanPham);

        lblSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSanPham.setText("Sản phẩm");
        Panel_SanPham.add(lblSanPham);

        Panel_KhachHang.setLayout(new java.awt.GridLayout(1, 2));

        lblLoGoKhachHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLoGoKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/KhachHang.png"))); // NOI18N
        Panel_KhachHang.add(lblLoGoKhachHang);

        lblKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblKhachHang.setText("Khách hàng");
        Panel_KhachHang.add(lblKhachHang);

        Panel_HoaDon.setLayout(new java.awt.GridLayout(1, 2));

        lblLoGoHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLoGoHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/HoaDon.png"))); // NOI18N
        Panel_HoaDon.add(lblLoGoHoaDon);

        lblHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHoaDon.setText("Hóa đơn");
        Panel_HoaDon.add(lblHoaDon);

        Panel_GiamGia.setLayout(new java.awt.GridLayout(1, 2));

        lblLoGoGiamGia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLoGoGiamGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/GiamGia.png"))); // NOI18N
        Panel_GiamGia.add(lblLoGoGiamGia);

        lblGiamGia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblGiamGia.setText("Giảm giá");
        Panel_GiamGia.add(lblGiamGia);

        Panel_NhanVien.setLayout(new java.awt.GridLayout(1, 2));

        lblLoGoNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLoGoNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/manager (1).png"))); // NOI18N
        Panel_NhanVien.add(lblLoGoNhanVien);

        lblNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNhanVien.setText("Nhân viên");
        Panel_NhanVien.add(lblNhanVien);

        Panel_ThongKe.setLayout(new java.awt.GridLayout(1, 2));

        lblLoGoThongKe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLoGoThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/chart.png"))); // NOI18N
        Panel_ThongKe.add(lblLoGoThongKe);

        lblThongKe.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblThongKe.setText("Thống kê");
        Panel_ThongKe.add(lblThongKe);

        lblAnh.setBorderSize(3);
        lblAnh.setBorderSpace(4);
        lblAnh.setImage(new javax.swing.ImageIcon(getClass().getResource("/Imgs/Staff avatar.png"))); // NOI18N
        lblAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhMouseClicked(evt);
            }
        });

        lblMaNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMaNV.setText("PH44564");

        lblChucVu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblChucVu.setText("Nhân viên bán hàng");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Panel_HoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Panel_GiamGia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Panel_NhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Panel_ThongKe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Panel_BanHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Panel_SanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Panel_KhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(lblMaNV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(lblChucVu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(Panel_BanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel_KhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel_GiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel_ThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(127, Short.MAX_VALUE))
        );

        panelView.setBackground(new java.awt.Color(255, 255, 255));
        panelView.setPreferredSize(new java.awt.Dimension(1060, 624));

        javax.swing.GroupLayout panelViewLayout = new javax.swing.GroupLayout(panelView);
        panelView.setLayout(panelViewLayout);
        panelViewLayout.setHorizontalGroup(
            panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1086, Short.MAX_VALUE)
        );
        panelViewLayout.setVerticalGroup(
            panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 674, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelView, javax.swing.GroupLayout.DEFAULT_SIZE, 1086, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelView, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhMouseClicked
        ThongTinCaNhan_JDialog ttcn = new ThongTinCaNhan_JDialog(this, true);
        ttcn.setVisible(true);
    }//GEN-LAST:event_lblAnhMouseClicked

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_BanHang;
    private javax.swing.JPanel Panel_GiamGia;
    private javax.swing.JPanel Panel_HoaDon;
    private javax.swing.JPanel Panel_KhachHang;
    private javax.swing.JPanel Panel_NhanVien;
    private javax.swing.JPanel Panel_SanPham;
    private javax.swing.JPanel Panel_ThongKe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private Model.ImageAvatar lblAnh;
    private javax.swing.JLabel lblBanHang;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblGiamGia;
    private javax.swing.JLabel lblHoaDon;
    private javax.swing.JLabel lblKhachHang;
    private javax.swing.JLabel lblLoGoBanHang;
    private javax.swing.JLabel lblLoGoGiamGia;
    private javax.swing.JLabel lblLoGoHoaDon;
    private javax.swing.JLabel lblLoGoKhachHang;
    private javax.swing.JLabel lblLoGoNhanVien;
    private javax.swing.JLabel lblLoGoSanPham;
    private javax.swing.JLabel lblLoGoThongKe;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblSanPham;
    private javax.swing.JLabel lblThongKe;
    private javax.swing.JPanel panelView;
    // End of variables declaration//GEN-END:variables
}
