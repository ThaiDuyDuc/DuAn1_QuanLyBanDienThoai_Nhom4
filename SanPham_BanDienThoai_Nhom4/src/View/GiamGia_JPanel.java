package View;

import javax.swing.table.DefaultTableModel;
import Repository.qlphieugiamgia;
import Model.phieugiamgia1;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import com.toedter.calendar.JDateChooser;
import java.util.Arrays;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import Model.Khachhang_phieugg;
import Model.PGG_KH;
import Model.model_dieukienapdung;
import Repository.qldieukienapdung;
import Repository.ql_KH;
import Repository.QL_PGG_KH;
import java.awt.Frame;
import java.util.Locale;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class GiamGia_JPanel extends javax.swing.JPanel {

    ArrayList<Khachhang_phieugg> list_data = new ArrayList<>();
    qlphieugiamgia qlpgg = new qlphieugiamgia();
    qldieukienapdung ql_dk = new qldieukienapdung();
    ql_KH qlkh = new ql_KH();
    QL_PGG_KH qlpggkh = new QL_PGG_KH();
    ArrayList<String> ar_madk = new ArrayList<>();

    public GiamGia_JPanel() {
        initComponents();
        tbl_dskh.setRowHeight(30);
        tbl_dspgg.setRowHeight(30);
        loadtable_pgg((ArrayList<phieugiamgia1>) qlpgg.selectAll(), null);
        load_data_KH((ArrayList<Khachhang_phieugg>) qlkh.selectAll());
        load_combobox();
        if(cbb_loaigg.getSelectedItem().toString().equalsIgnoreCase("Phan Tram")){
            txt_gtr.setVisible(false);
            cbb_gg.setVisible(true);
        }
    }

    public String check_tt(String date_check) {
        LocalDateTime endTime = null;
        try {
            endTime = LocalDateTime.parse(date_check, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
        } catch (Exception e) {
            endTime = LocalDateTime.parse(date_check, DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:ss.S"));
        }

        LocalDateTime now = LocalDateTime.now();

        if (now.isAfter(endTime)) {
            return "Hết hạn";
        } else {
            return "Chưa hết hạn";
        }
    }

    public void load_combobox() {
        ArrayList<String> list_makh = new ArrayList<>();
        for (model_dieukienapdung x : ql_dk.selectAll()) {
            String dieukien_new;
            ar_madk.add(x.getMadk());
            if(x.getLoaidk().equalsIgnoreCase("Giảm khi sinh nhật")){
                dieukien_new = x.getLoaidk();
            }
            else{
                String dieukien = x.getSl() + x.getGtrsl();
                dieukien_new = dieukien.replace("null", "");
            }
            String gtr_sl = dieukien_new + " - "  + x.getHang();
            list_makh.add(gtr_sl);
        }
        DefaultComboBoxModel<String> model_kh = new DefaultComboBoxModel<>(list_makh.toArray(String[]::new));
        cbb_dkad.setModel(model_kh);

    }

    public void load_data_KH(ArrayList<Khachhang_phieugg> list) {
        DefaultTableModel dtm = (DefaultTableModel) tbl_dskh.getModel();
        dtm.setRowCount(0);
        for (Khachhang_phieugg x : list) {
            dtm.addRow(new Object[]{
                x.getMakh(),
                x.getTenkh(),
                x.getSdt(),
                x.getEmail()
            });
        }
    }

    public void loadtable_pgg(ArrayList<phieugiamgia1> list, String check) {
        DefaultTableModel dtm = (DefaultTableModel) tbl_dspgg.getModel();
        dtm.setRowCount(0);
        for (phieugiamgia1 x : list) {
            String tt = check_tt(x.getNgayketthuc());
            if (check == null) {
                dtm.addRow(new Object[]{
                    x.getMakm(),
                    x.getTenkhm(),
                    x.getNgaybatdau(),
                    x.getNgaytao(),
                    x.getNgayketthuc(),
                    x.getGiatri(),
                    tt,
                    x.getMota(),
                    x.getHang()
                }
                );
            }
            if (tt.equalsIgnoreCase(check)) {
                dtm.addRow(new Object[]{
                    x.getMakm(),
                    x.getTenkhm(),
                    x.getNgaybatdau(),
                    x.getNgaytao(),
                    x.getNgayketthuc(),
                    x.getGiatri(),
                    tt,
                    x.getMota(),
                    x.getHang()
                }
                );
            }

        }
    }

    public void set_status_phieu(String mapgg) {
        ArrayList<String> list_data = new ArrayList<>();
        for (PGG_KH x : qlpggkh.select_mapgg(mapgg)) {
            list_data.add(x.getMakh());
        }
        for (int i = 0; i < tbl_dskh.getRowCount(); i++) {
            if (list_data.contains(tbl_dskh.getValueAt(i, 0).toString())) {
                tbl_dskh.setValueAt(true, i, 4);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_dspgg = new javax.swing.JTable();
        cbb_ttp = new javax.swing.JComboBox<>();
        cbb_tt = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        txt_tengg = new javax.swing.JTextField();
        jdc_ngaybd = new com.toedter.calendar.JDateChooser();
        jdc_ngaykt = new com.toedter.calendar.JDateChooser();
        cbb_loaigg = new javax.swing.JComboBox<>();
        jLabel80 = new javax.swing.JLabel();
        txt_gtr = new javax.swing.JTextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        tp_mota9 = new javax.swing.JTextPane();
        jPanel23 = new javax.swing.JPanel();
        cbb_dkad = new javax.swing.JComboBox<>();
        jButton18 = new javax.swing.JButton();
        jLabel81 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        cbb_gg = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_dskh = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        txt_timkiem = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách PhiếuGG", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tbl_dspgg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã", "Tên", "Ngày Bắt Đầu", "Ngày Tạo", "Ngày Kết Thúc", "GIá Trị", "Trạng Thái", "Mô Tả", "Hạng"
            }
        ));
        tbl_dspgg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_dspggMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_dspgg);

        cbb_ttp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Hết hạn", "Chưa hết hạn" }));
        cbb_ttp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_ttpActionPerformed(evt);
            }
        });

        cbb_tt.setText("Lọc Trạng thái phiếu");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cbb_tt, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbb_ttp, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbb_ttp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_tt))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Phiếu giảm giá", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanel22.setToolTipText("");

        jLabel74.setText("Tên Giảm giá");

        jLabel75.setText("Ngày Bắt Đầu");

        jLabel76.setText("Ngày Kết Thúc");

        jLabel78.setText("Loại giảm giá");

        jLabel79.setText("Mô tả");

        txt_tengg.setToolTipText("");

        cbb_loaigg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Phan Tram", "Tien" }));
        cbb_loaigg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_loaiggActionPerformed(evt);
            }
        });

        jLabel80.setText("Giá trị");

        jScrollPane12.setViewportView(tp_mota9);

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Điều kiện áp dụng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 204, 255))); // NOI18N

        cbb_dkad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbb_dkadMouseClicked(evt);
            }
        });
        cbb_dkad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_dkadActionPerformed(evt);
            }
        });

        jButton18.setText("Chỉnh sửa");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jLabel81.setText("Điều kiện - Hạng");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel81)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(cbb_dkad, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton18))))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel81)
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbb_dkad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton18))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Sửa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Xóa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Làm mới");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        cbb_gg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70", "75", "80", "85", "90", "95", "100" }));
        cbb_gg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_ggActionPerformed(evt);
            }
        });

        jLabel1.setText("Phần trăm");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel74, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel75, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel76, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                    .addComponent(jLabel79, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel80, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_tengg)
                                    .addComponent(jdc_ngaybd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jdc_ngaykt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbb_loaigg, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_gtr)
                                    .addComponent(cbb_gg, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12))))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(txt_tengg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel75)
                    .addComponent(jdc_ngaybd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdc_ngaykt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel76))
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel78)
                    .addComponent(cbb_loaigg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80)
                    .addComponent(txt_gtr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbb_gg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel79)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(24, 24, 24))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tbl_dskh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KH", "Tên KH", "SĐT", "Email", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tbl_dskh);

        jButton6.setText("Update");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Chọn hết");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton9.setText("Loại bỏ hết");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        txt_timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timkiemActionPerformed(evt);
            }
        });
        txt_timkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_timkiemKeyPressed(evt);
            }
        });

        jLabel9.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9)
                        .addGap(74, 74, 74)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_timkiem, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton9)
                    .addComponent(txt_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_dspggMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_dspggMouseClicked
        // TODO add your handling code here:
        int row = tbl_dspgg.getSelectedRow();
        txt_tengg.setText(tbl_dspgg.getValueAt(row, 1).toString());

        try {
            SimpleDateFormat sdf_bd = new SimpleDateFormat("yyyy-MM-dd");
            Date ngaybd = sdf_bd.parse(tbl_dspgg.getValueAt(row, 2).toString());
            jdc_ngaybd.setDate(ngaybd);
            SimpleDateFormat sdf_kt = new SimpleDateFormat("yyyy-MM-dd");
            Date Ngaykth = sdf_kt.parse(tbl_dspgg.getValueAt(row, 4).toString());
            jdc_ngaykt.setDate(Ngaykth);

        } catch (ParseException e) {
            e.printStackTrace();

        }
        tp_mota9.setText(tbl_dspgg.getValueAt(row, 7).toString());
        cbb_dkad.setSelectedItem(ABORT);
        if (tbl_dspgg.getValueAt(row, 5).toString().contains("%")) {
            cbb_loaigg.setSelectedIndex(0);
        } else {
            cbb_loaigg.setSelectedIndex(1);
        }
        if(cbb_loaigg.getSelectedItem().toString().equalsIgnoreCase("Phan Tram")){
            cbb_gg.setSelectedItem(tbl_dspgg.getValueAt(row, 5).toString().replace("%", ""));
        }
        else{
            txt_gtr.setText(tbl_dspgg.getValueAt(row, 5).toString());
        }
        String hang = tbl_dspgg.getValueAt(row, 8).toString();
        list_data = (ArrayList<Khachhang_phieugg>) qlkh.select_KH_rank(hang);
        load_data_KH(list_data);
        ComboBoxModel<String> model = cbb_dkad.getModel();
        for (int i = 0; i < cbb_dkad.getItemCount(); i++) {
            if (model.getElementAt(i).contains(hang)) {
                cbb_dkad.setSelectedItem(model.getElementAt(i));
            }
        }
        set_status_phieu(tbl_dspgg.getValueAt(row, 0).toString());
    }//GEN-LAST:event_tbl_dspggMouseClicked

    private void cbb_ttpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_ttpActionPerformed
        // TODO add your handling code here:
        if(cbb_ttp.getSelectedItem().toString().equalsIgnoreCase("Tất cả")){
            loadtable_pgg((ArrayList<phieugiamgia1>) qlpgg.selectAll(), null);
        }
        else{
            loadtable_pgg((ArrayList<phieugiamgia1>) qlpgg.selectAll(), cbb_ttp.getSelectedItem().toString());
        }
    }//GEN-LAST:event_cbb_ttpActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        int row = tbl_dspgg.getSelectedRow();
        for (int i = 0; i < tbl_dskh.getRowCount(); i++) {
            try {
                boolean check = Boolean.parseBoolean(tbl_dskh.getValueAt(i, 4).toString());
                if (check) {
                    PGG_KH data_pggkh = new PGG_KH(tbl_dskh.getValueAt(i, 0).toString(), tbl_dspgg.getValueAt(row, 0).toString(), tbl_dspgg.getValueAt(row, 5).toString(), tbl_dspgg.getValueAt(row, 3).toString(), tbl_dspgg.getValueAt(row, 2).toString(), tbl_dspgg.getValueAt(row, 4).toString(), tbl_dspgg.getValueAt(row, 7).toString());
                    try{
                        qlpggkh.insert(data_pggkh);
                    }
                    catch(Exception e){
                    }
                } else {
                    qlpggkh.delete_double(tbl_dskh.getValueAt(i, 0).toString(), tbl_dspgg.getValueAt(row, 0).toString());
                }
            } catch (Exception e) {

            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int rowCount = tbl_dskh.getRowCount();
        for (int a = 0; a < rowCount; a++) {
            tbl_dskh.setValueAt(true, a, 4);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        int rowCount = tbl_dskh.getRowCount();
        for (int a = 0; a < rowCount; a++) {
            tbl_dskh.setValueAt(false, a, 4);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void txt_timkiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timkiemKeyPressed
        // TODO add your handling code here:
        ArrayList<Khachhang_phieugg> new_list = new ArrayList<>();
        String timkiem = txt_timkiem.getText();
        for (Khachhang_phieugg x : list_data) {
            if (x.getSdt().contains(timkiem)) {
                new_list.add(x);
            }
        }
        load_data_KH(new_list);
    }//GEN-LAST:event_txt_timkiemKeyPressed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        Frame GiamGia_JFrame = new JFrame(); // Tạo một JFrame mới
        DieuKienApDung_JDialog dk = new DieuKienApDung_JDialog(GiamGia_JFrame, true);
        dk.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Sử dụng JDialog.DISPOSE_ON_CLOSE
        dk.setVisible(true);
    }//GEN-LAST:event_jButton18ActionPerformed
    public boolean check_pgg_them(){
        if(txt_tengg.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this, "Tên giảm giá đang trống");
            return false;
        }
        else if(String.valueOf(jdc_ngaybd.getDate()).equalsIgnoreCase("null")){
            JOptionPane.showMessageDialog(this, "Chọn ngày bắt đầu");
            return false;
        }
        else if(String.valueOf(jdc_ngaykt.getDate()).equalsIgnoreCase("null")){
            JOptionPane.showMessageDialog(this, "Chọn ngày kết thúc");
            return false;
        }
        else if(txt_gtr.getText().equalsIgnoreCase("") && cbb_gg.getSelectedItem().toString().equalsIgnoreCase("0")){
            JOptionPane.showMessageDialog(this, "Nhập giá trị giảm giá");
            return false;
        }
        else if(tp_mota9.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this, "Nhập mô tả");
            return false;
        }
        else{
            return true;
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(check_pgg_them()){
            try{
                LocalDateTime now = LocalDateTime.now();

                // Định dạng ngày giờ
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                // In ra ngày giờ hiện tại theo định dạng đã chọn
                String ngaytao = now.format(formatter);

                String ten = txt_tengg.getText();
                String ngaybd = String.valueOf(jdc_ngaybd.getDate());
                String ngaykt = String.valueOf(jdc_ngaykt.getDate());
                SimpleDateFormat inputFormat = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy",Locale.US);
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date = inputFormat.parse(ngaybd);
                    ngaybd = outputFormat.format(date);
                } catch (ParseException e) {
                    
                }
                try {
                    Date date = inputFormat.parse(ngaykt);
                    ngaykt = outputFormat.format(date);
                } catch (ParseException e) {
                }
                //        String loaigiamgia = txt_giatri.getText();

                String mota = tp_mota9.getText();
                String gtri;
                if(cbb_loaigg.getSelectedItem().toString().equalsIgnoreCase("Phan Tram")){
                    gtri = cbb_gg.getSelectedItem().toString() + "%";
                }
                else{
                    gtri = txt_gtr.getText();
                }
                String loaigg = cbb_loaigg.getSelectedItem().toString();
                List<phieugiamgia1> l = qlpgg.select_id();
                String id = l.get(0).getMakm();
                // Tách phần ký tự và phần số
                String alphaPart = id.replaceAll("[^A-Za-z]", ""); // Lấy phần ký tự
                String numericPart = id.replaceAll("[^0-9]", ""); // Lấy phần số

                // Tăng giá trị số lên 1
                int numericValue = Integer.parseInt(numericPart);
                numericValue++;

                // Tạo chuỗi mới
                String newString = alphaPart + String.format("%03d", numericValue); // Định dạng số với 3 chữ số
                String gtrdk = cbb_dkad.getSelectedItem().toString();
                String[] pats = gtrdk.split(" - ");
                String hang = pats[1];
                String madk = ar_madk.get(cbb_dkad.getSelectedIndex());

                phieugiamgia1 pgg = new phieugiamgia1(newString, ten, "KH005", madk, mota, gtri, loaigg, hang, ngaytao, ngaybd, ngaykt, Integer.parseInt("0"));
                qlpgg.insert(pgg);
                loadtable_pgg((ArrayList<phieugiamgia1>) qlpgg.selectAll(), null);
                JOptionPane.showMessageDialog(this, "THêm thành công");
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(this, "THêm thất bại");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(check_pgg_them()){
            try{
                LocalDateTime now = LocalDateTime.now();

                // Định dạng ngày giờ
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                // In ra ngày giờ hiện tại theo định dạng đã chọn
                String ngaytao = now.format(formatter);

                String ten = txt_tengg.getText();
                String ngaybd = String.valueOf(jdc_ngaybd.getDate());
                String ngaykt = String.valueOf(jdc_ngaykt.getDate());
                SimpleDateFormat inputFormat = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy",Locale.US);
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date = inputFormat.parse(ngaybd);
                    ngaybd = outputFormat.format(date);
                } catch (ParseException e) {
                }
                try {
                    Date date = inputFormat.parse(ngaykt);
                    ngaykt = outputFormat.format(date);
                } catch (ParseException e) {
                }
                System.out.println("ngày"+ngaykt);
                //        String loaigiamgia = txt_giatri.getText();

                String mota = tp_mota9.getText();
                String gtri;
                if(cbb_loaigg.getSelectedItem().toString().equalsIgnoreCase("Phan Tram")){
                    gtri = cbb_gg.getSelectedItem().toString() + "%";
                }
                else{
                    gtri = txt_gtr.getText();
                }
                String loaigg = cbb_loaigg.getSelectedItem().toString();
                List<phieugiamgia1> l = qlpgg.select_id();

                int row = tbl_dspgg.getSelectedRow();
                String id_phieu = tbl_dspgg.getValueAt(row, 0).toString();

                String gtrdk = cbb_dkad.getSelectedItem().toString();
                String[] pats = gtrdk.split(" - ");
                String hang = pats[1];
                String madk = ar_madk.get(cbb_dkad.getSelectedIndex());
                phieugiamgia1 pgg = new phieugiamgia1(id_phieu, ten, "KH005", madk, mota, gtri, loaigg, hang, ngaytao, ngaybd, ngaykt, Integer.parseInt("0"));
                qlpgg.update(pgg);
                loadtable_pgg((ArrayList<phieugiamgia1>) qlpgg.selectAll(), null);
                for(int i = 0 ;i < tbl_dskh.getRowCount();i++){
                    try {
                        boolean check = Boolean.parseBoolean(tbl_dskh.getValueAt(i, 4).toString());
                        if (check) {
                            PGG_KH data_pggkh = new PGG_KH(tbl_dskh.getValueAt(i, 0).toString(), tbl_dspgg.getValueAt(row, 0).toString(), tbl_dspgg.getValueAt(row, 5).toString(), tbl_dspgg.getValueAt(row, 3).toString(), tbl_dspgg.getValueAt(row, 2).toString(), tbl_dspgg.getValueAt(row, 4).toString(), tbl_dspgg.getValueAt(row, 7).toString());
                            qlpggkh.update(data_pggkh);
                        }
                    }
                    catch(Exception e){

                    }
                }
                JOptionPane.showMessageDialog(this, "Sửa thành công");
            }
            catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Sửa thất bại");
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try{
            int row = tbl_dspgg.getSelectedRow();
            String id_phieu = tbl_dspgg.getValueAt(row, 0).toString();
            qlpgg.delete(id_phieu);
            loadtable_pgg((ArrayList<phieugiamgia1>) qlpgg.selectAll(), null);
            JOptionPane.showMessageDialog(this, "Xóa thành công");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, "Hãy Xóa khách hàng ra phiếu giảm giá");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        txt_gtr.setText("");
        txt_tengg.setText("");
        tp_mota9.setText("");
        cbb_dkad.setSelectedIndex(0);
        cbb_loaigg.setSelectedIndex(0);
        cbb_ttp.setSelectedIndex(0);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void cbb_dkadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_dkadActionPerformed


    }//GEN-LAST:event_cbb_dkadActionPerformed

    private void cbb_dkadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbb_dkadMouseClicked
        load_combobox();
    }//GEN-LAST:event_cbb_dkadMouseClicked

    private void txt_timkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timkiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timkiemActionPerformed

    private void cbb_loaiggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_loaiggActionPerformed
        // TODO add your handling code here:
        if(cbb_loaigg.getSelectedItem().toString().equalsIgnoreCase("Phan Tram")){
            txt_gtr.setVisible(false);
            cbb_gg.setVisible(true);
            txt_gtr.setText("");
        }
        else{
            txt_gtr.setVisible(true);
            cbb_gg.setVisible(false);
            cbb_gg.setSelectedIndex(0);
        }
    }//GEN-LAST:event_cbb_loaiggActionPerformed

    private void cbb_ggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_ggActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbb_ggActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbb_dkad;
    private javax.swing.JComboBox<String> cbb_gg;
    private javax.swing.JComboBox<String> cbb_loaigg;
    private javax.swing.JLabel cbb_tt;
    private javax.swing.JComboBox<String> cbb_ttp;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane4;
    private com.toedter.calendar.JDateChooser jdc_ngaybd;
    private com.toedter.calendar.JDateChooser jdc_ngaykt;
    private javax.swing.JTable tbl_dskh;
    private javax.swing.JTable tbl_dspgg;
    private javax.swing.JTextPane tp_mota9;
    private javax.swing.JTextField txt_gtr;
    private javax.swing.JTextField txt_tengg;
    private javax.swing.JTextField txt_timkiem;
    // End of variables declaration//GEN-END:variables
}
