package View;

import Model.DonHang;
import Model.NhanVien;
import Model.NhanVien_thongKe;
import Repository.DienThoaiChiTiet_DAO;
import Repository.HoaDonDao;
import Repository.NhanVien_DAO;
import Repository.NhanVien_ThongKe_DAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartTheme;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author thaid
 */
public class ThongKe_JPanel extends javax.swing.JPanel {

    HoaDonDao service_HD = new HoaDonDao();
    NhanVien_ThongKe_DAO service_nv = new NhanVien_ThongKe_DAO();
    DienThoaiChiTiet_DAO service_DTCT = new DienThoaiChiTiet_DAO();

    public ThongKe_JPanel() throws Exception {
        initComponents();
        tblThongKeNhanVien.setRowHeight(25);
        loadNhanVienTheoTuan();
        BieuDoTheo_BayNgay();
        jComboBox1.setSelectedIndex(1);
        BieuDoTron_Tuan();
    }

    public void BieuDoTheo_HomNay() throws Exception {
        DefaultCategoryDataset dataset = service_HD.getChartData_NgayHienTai();

        JFreeChart chart = ChartFactory.createBarChart(
                null,
                "Tháng",
                "Tổng tiền",
                dataset
        );

        CategoryPlot plot = chart.getCategoryPlot();
        // Đặt màu nền cho biểu đồ
        plot.setBackgroundPaint(new Color(225, 225, 225));

        ChartPanel chartPanel = new ChartPanel(chart);
        jPanel_BieuDo.removeAll();
        jPanel_BieuDo.add(chartPanel, BorderLayout.CENTER);
        jPanel_BieuDo.validate();
    }

    public void BieuDoTheo_Thang() throws Exception {
        DefaultCategoryDataset dataset = service_HD.getChartData_ThangTrongNam();

        JFreeChart chart = ChartFactory.createBarChart(
                null,
                "Tháng",
                "Tổng tiền",
                dataset
        );

        CategoryPlot plot = chart.getCategoryPlot();
        // Đặt màu nền cho biểu đồ
        plot.setBackgroundPaint(new Color(225, 225, 225));

        ChartPanel chartPanel = new ChartPanel(chart);
        jPanel_BieuDo.removeAll();
        jPanel_BieuDo.add(chartPanel, BorderLayout.CENTER);
        jPanel_BieuDo.validate();
    }

    public void BieuDoTheo_BayNgay() throws Exception {
        DefaultCategoryDataset dataset = service_HD.getChartData_Ngay();

        JFreeChart chart = ChartFactory.createBarChart(
                null,
                "Ngày",
                "Tổng tiền",
                dataset
        );

        CategoryPlot plot = chart.getCategoryPlot();
        // Đặt màu nền cho biểu đồ
        plot.setBackgroundPaint(new Color(225, 225, 225));

        ChartPanel chartPanel = new ChartPanel(chart);
        jPanel_BieuDo.removeAll();
        jPanel_BieuDo.add(chartPanel, BorderLayout.CENTER);
        jPanel_BieuDo.validate();
    }

    public void BieuDoTheo_TheoQuy() throws Exception {
        DefaultCategoryDataset dataset = service_HD.getChartData_QuyTrongNam();

        JFreeChart chart = ChartFactory.createBarChart(
                null,
                "Quý",
                "Tổng tiền",
                dataset
        );

        CategoryPlot plot = chart.getCategoryPlot();
        // Đặt màu nền cho biểu đồ
        plot.setBackgroundPaint(new Color(225, 225, 225));

        ChartPanel chartPanel = new ChartPanel(chart);
        jPanel_BieuDo.removeAll();
        jPanel_BieuDo.add(chartPanel, BorderLayout.CENTER);
        jPanel_BieuDo.validate();
    }

// thống kê nhân viên
    public void loadNhanVienTheo_HomNay() {
        List<NhanVien_thongKe> lisnv = service_nv.selectAll_HomNay();
        DefaultTableModel model = (DefaultTableModel) this.tblThongKeNhanVien.getModel();
        model.setRowCount(0);

        for (NhanVien_thongKe o : lisnv) {
            Object[] rowData = {
                o.getManv(),
                o.getTennv(),
                o.getDoanhthu()
            };
            model.addRow(rowData);
        }
    }

    public void loadNhanVienTheoTuan() {
        List<NhanVien_thongKe> lisnv = service_nv.selectAll_Bay();
        DefaultTableModel model = (DefaultTableModel) this.tblThongKeNhanVien.getModel();
        model.setRowCount(0);

        for (NhanVien_thongKe o : lisnv) {
            Object[] rowData = {
                o.getManv(),
                o.getTennv(),
                o.getDoanhthu()
            };
            model.addRow(rowData);
        }
    }

    public void loadNhanVienTheoQuy() {
        List<NhanVien_thongKe> lisnv = service_nv.selectAll_Quy();
        DefaultTableModel model = (DefaultTableModel) this.tblThongKeNhanVien.getModel();
        model.setRowCount(0);

        for (NhanVien_thongKe o : lisnv) {
            Object[] rowData = {
                o.getManv(),
                o.getTennv(),
                o.getDoanhthu()
            };
            model.addRow(rowData);
        }
    }

    public void loadNhanVienTheoThang() {
        List<NhanVien_thongKe> lisnv = service_nv.selectAll_Bay();
        DefaultTableModel model = (DefaultTableModel) this.tblThongKeNhanVien.getModel();
        model.setRowCount(0);

        for (NhanVien_thongKe o : lisnv) {
            Object[] rowData = {
                o.getManv(),
                o.getTennv(),
                o.getDoanhthu()
            };
            model.addRow(rowData);
        }
    }

    // =============================== Bieu đồ tròn =============================
    public void BieuDoTron_HienTai() throws Exception {

        // Tạo mô hình dữ liệu
        DefaultPieDataset dataset = service_DTCT.getChartData_NgayHienTai();

        // Tạo biểu đồ hình tròn
        JFreeChart chart = ChartFactory.createPieChart(
                null,
                dataset,
                true,
                true,
                false
        );

        // Áp dụng style cho biểu đồ
//        ChartTheme theme = new StandardChartTheme("StyledChart");
//        theme.apply(chart);
        // Lấy PiePlot từ biểu đồ
        PiePlot plot = (PiePlot) chart.getPlot();

        // Đặt màu nền cho biểu đồ
        plot.setBackgroundPaint(new Color(225, 225, 225));
        ChartPanel chartPanel = new ChartPanel(chart);
        jPanel_BieuDoTron.removeAll();
        jPanel_BieuDoTron.add(chartPanel, BorderLayout.CENTER);
        jPanel_BieuDoTron.validate();
    }

    public void BieuDoTron_Tuan() throws Exception {

        // Tạo mô hình dữ liệu
        DefaultPieDataset dataset = service_DTCT.getChartData_Tuan();

        // Tạo biểu đồ hình tròn
        JFreeChart chart = ChartFactory.createPieChart(
                null,
                dataset,
                true,
                true,
                false
        );

        // Áp dụng style cho biểu đồ
//        ChartTheme theme = new StandardChartTheme("StyledChart");
//        theme.apply(chart);
        // Lấy PiePlot từ biểu đồ
        PiePlot plot = (PiePlot) chart.getPlot();

        // Đặt màu nền cho biểu đồ
        plot.setBackgroundPaint(new Color(225, 225, 225));
        ChartPanel chartPanel = new ChartPanel(chart);
        jPanel_BieuDoTron.removeAll();
        jPanel_BieuDoTron.add(chartPanel, BorderLayout.CENTER);
        jPanel_BieuDoTron.validate();
    }

    public void BieuDoTron_Thang() throws Exception {

        // Tạo mô hình dữ liệu
        DefaultPieDataset dataset = service_DTCT.getChartData_ThangTrongNam();

        // Tạo biểu đồ hình tròn
        JFreeChart chart = ChartFactory.createPieChart(
                null,
                dataset,
                true,
                true,
                false
        );

        // Áp dụng style cho biểu đồ
//        ChartTheme theme = new StandardChartTheme("StyledChart");
//        theme.apply(chart);
        // Lấy PiePlot từ biểu đồ
        PiePlot plot = (PiePlot) chart.getPlot();

        // Đặt màu nền cho biểu đồ
        plot.setBackgroundPaint(new Color(225, 225, 225));
        ChartPanel chartPanel = new ChartPanel(chart);
        jPanel_BieuDoTron.removeAll();
        jPanel_BieuDoTron.add(chartPanel, BorderLayout.CENTER);
        jPanel_BieuDoTron.validate();
    }

    public void BieuDoTron_Quy() throws Exception {

        // Tạo mô hình dữ liệu
        DefaultPieDataset dataset = service_DTCT.getChartData_QuyTrongNam();

        // Tạo biểu đồ hình tròn
        JFreeChart chart = ChartFactory.createPieChart(
                null,
                dataset,
                true,
                true,
                false
        );

        // Áp dụng style cho biểu đồ
//        ChartTheme theme = new StandardChartTheme("StyledChart");
//        theme.apply(chart);
        // Lấy PiePlot từ biểu đồ
        PiePlot plot = (PiePlot) chart.getPlot();

        // Đặt màu nền cho biểu đồ
        plot.setBackgroundPaint(new Color(225, 225, 225));
        ChartPanel chartPanel = new ChartPanel(chart);
        jPanel_BieuDoTron.removeAll();
        jPanel_BieuDoTron.add(chartPanel, BorderLayout.CENTER);
        jPanel_BieuDoTron.validate();
    }

    // - ------------------------------------------------- LBL tổng doanh thu ------------------------------------------------
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelAll = new javax.swing.JPanel();
        jPanel_BieuDo = new javax.swing.JPanel();
        jPanel_ThongKeDoanhSo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblTongDoanhThu = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongKeNhanVien = new javax.swing.JTable();
        jPanel_BieuDoTron = new javax.swing.JPanel();
        jPanel_ThongKeDoanhSo1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblTongHoaDon = new javax.swing.JLabel();
        jPanel_ThongKeDoanhSo2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblTongDienThoai = new javax.swing.JLabel();

        jPanelAll.setBackground(new java.awt.Color(255, 255, 255));

        jPanel_BieuDo.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_BieuDo.setLayout(new java.awt.BorderLayout());

        jPanel_ThongKeDoanhSo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Tổng doanh thu");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/ThongKe/money (1).png"))); // NOI18N

        jLabel4.setText("ĐV : VND");

        lblTongDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTongDoanhThu.setText("100 000 000");

        javax.swing.GroupLayout jPanel_ThongKeDoanhSoLayout = new javax.swing.GroupLayout(jPanel_ThongKeDoanhSo);
        jPanel_ThongKeDoanhSo.setLayout(jPanel_ThongKeDoanhSoLayout);
        jPanel_ThongKeDoanhSoLayout.setHorizontalGroup(
            jPanel_ThongKeDoanhSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ThongKeDoanhSoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_ThongKeDoanhSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_ThongKeDoanhSoLayout.createSequentialGroup()
                        .addGroup(jPanel_ThongKeDoanhSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_ThongKeDoanhSoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel_ThongKeDoanhSoLayout.setVerticalGroup(
            jPanel_ThongKeDoanhSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ThongKeDoanhSoLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel_ThongKeDoanhSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel_ThongKeDoanhSoLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thống kê hôm nay", "Thống kê 7 ngày gần nhất", "Thống kê các tháng", "Thống kê theo quý" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thống kê 5 nhân viên doanh thu cao nhất", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblThongKeNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Doanh thu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblThongKeNhanVien);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
        );

        jPanel_BieuDoTron.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_BieuDoTron.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Điện thoại bán chạy nhất", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanel_BieuDoTron.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel_BieuDoTron, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_BieuDoTron, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
        );

        jPanel_ThongKeDoanhSo1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Tổng hóa đơn");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/ThongKe/money (1).png"))); // NOI18N

        jLabel15.setText("ĐV : Hóa đơn");

        lblTongHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTongHoaDon.setText("100 000 000");

        javax.swing.GroupLayout jPanel_ThongKeDoanhSo1Layout = new javax.swing.GroupLayout(jPanel_ThongKeDoanhSo1);
        jPanel_ThongKeDoanhSo1.setLayout(jPanel_ThongKeDoanhSo1Layout);
        jPanel_ThongKeDoanhSo1Layout.setHorizontalGroup(
            jPanel_ThongKeDoanhSo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ThongKeDoanhSo1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_ThongKeDoanhSo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_ThongKeDoanhSo1Layout.createSequentialGroup()
                        .addGroup(jPanel_ThongKeDoanhSo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel15))
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_ThongKeDoanhSo1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblTongHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_ThongKeDoanhSo1Layout.setVerticalGroup(
            jPanel_ThongKeDoanhSo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ThongKeDoanhSo1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel_ThongKeDoanhSo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel_ThongKeDoanhSo1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTongHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel_ThongKeDoanhSo2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Tổng sản phẩm bán");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/ThongKe/money (1).png"))); // NOI18N

        jLabel8.setText("ĐV : Cái");

        lblTongDienThoai.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTongDienThoai.setText("100 000 000");

        javax.swing.GroupLayout jPanel_ThongKeDoanhSo2Layout = new javax.swing.GroupLayout(jPanel_ThongKeDoanhSo2);
        jPanel_ThongKeDoanhSo2.setLayout(jPanel_ThongKeDoanhSo2Layout);
        jPanel_ThongKeDoanhSo2Layout.setHorizontalGroup(
            jPanel_ThongKeDoanhSo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ThongKeDoanhSo2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_ThongKeDoanhSo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addGroup(jPanel_ThongKeDoanhSo2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblTongDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel_ThongKeDoanhSo2Layout.setVerticalGroup(
            jPanel_ThongKeDoanhSo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ThongKeDoanhSo2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel_ThongKeDoanhSo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel_ThongKeDoanhSo2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTongDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelAllLayout = new javax.swing.GroupLayout(jPanelAll);
        jPanelAll.setLayout(jPanelAllLayout);
        jPanelAllLayout.setHorizontalGroup(
            jPanelAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAllLayout.createSequentialGroup()
                .addGroup(jPanelAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel_BieuDo, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
                    .addGroup(jPanelAllLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel_ThongKeDoanhSo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(jPanel_ThongKeDoanhSo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)))
                .addGroup(jPanelAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAllLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelAllLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jPanel_ThongKeDoanhSo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanelAllLayout.setVerticalGroup(
            jPanelAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAllLayout.createSequentialGroup()
                .addGroup(jPanelAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel_ThongKeDoanhSo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel_ThongKeDoanhSo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel_ThongKeDoanhSo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanelAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_BieuDo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelAllLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        if (jComboBox1.getSelectedIndex() == 0) {
            try {
                loadNhanVienTheo_HomNay();
                BieuDoTheo_HomNay();
                BieuDoTron_HienTai();
                jPanel_BieuDo.validate();

                // thống kê doanh thu
                double doanhthu = service_HD.getTongDoanhThuHomNay();
                BigDecimal ht = new BigDecimal(doanhthu);
                lblTongDoanhThu.setText(String.valueOf(ht));

                // thống kê hóa đơn
                lblTongHoaDon.setText(String.valueOf(service_HD.getTongHoaDon_HN()));

                // thống kê điện thoại
                lblTongDienThoai.setText(String.valueOf(service_DTCT.getTongHoaDon_HN()));
            } catch (Exception ex) {
                Logger.getLogger(ThongKe_JPanel.class.getName()).log(Level.SEVERE, "Thống kê xảy ra lỗi hệ thống", ex);
            }
        } else if (jComboBox1.getSelectedIndex() == 1) {
            try {
                loadNhanVienTheoTuan();
                BieuDoTheo_BayNgay();
                BieuDoTron_Tuan();
                jPanel_BieuDo.validate();

                // thống kê doanh thu
                double doanhthu = service_HD.getTongDoanhThu_7N();
                BigDecimal ht = new BigDecimal(doanhthu);
                lblTongDoanhThu.setText(String.valueOf(ht));

                // thống kê hóa đơn
                lblTongHoaDon.setText(String.valueOf(service_HD.getTongHoaDon_7N()));

                // thống kê điện thoại
                lblTongDienThoai.setText(String.valueOf(service_DTCT.getTongDienThoai_7N()));
            } catch (Exception ex) {
                Logger.getLogger(ThongKe_JPanel.class.getName()).log(Level.SEVERE, "Thống kê xảy ra lỗi hệ thống", ex);
            }
        } else if (jComboBox1.getSelectedIndex() == 2) {
            try {
                loadNhanVienTheoThang();
                BieuDoTheo_Thang();
                BieuDoTron_Thang();
                jPanel_BieuDo.validate();

                // thống kê doanh thu
                Map<Integer, Double> doanhthu = service_HD.getTongDoanhThuCacThangTrongNam();
                // Tính tổng doanh thu
                double tongDoanhThu = doanhthu.values().stream().mapToDouble(Double::doubleValue).sum();
                // Sử dụng BigDecimal để định dạng số
                BigDecimal ht = new BigDecimal(tongDoanhThu);
                lblTongDoanhThu.setText(String.valueOf(ht));

                // thống kê hóa đơn
                Map<Integer, Integer> hoadon = service_HD.getTongHoaDonCacThangTrongNam();
                int tonghoadon = hoadon.values().stream().mapToInt(Integer::intValue).sum();
                lblTongHoaDon.setText(String.valueOf(tonghoadon));

                // thống kê điện thoại 
                Map<Integer, Integer> dienthoai = service_DTCT.getTongDienThoaiCacThangTrongNam();
                int tongdienthoai = dienthoai.values().stream().mapToInt(Integer::intValue).sum();
                lblTongDienThoai.setText(String.valueOf(tongdienthoai));
            } catch (Exception ex) {
                Logger.getLogger(ThongKe_JPanel.class.getName()).log(Level.SEVERE, "Thống kê xảy ra lỗi hệ thống", ex);
            }
        } else {
            try {
                loadNhanVienTheoQuy();
                BieuDoTheo_TheoQuy();
                BieuDoTron_Quy();
                jPanel_BieuDo.validate();

                // Tính tổng doanh thu
                Map<Integer, Double> doanhthu = service_HD.getTongDoanhThuCacQuyTrongNam();
                double tongDoanhThu = doanhthu.values().stream().mapToDouble(Double::doubleValue).sum();
                // Sử dụng BigDecimal để định dạng số
                BigDecimal ht = new BigDecimal(tongDoanhThu);
                lblTongDoanhThu.setText(String.valueOf(ht));

                // thống kê hóa đơn
                Map<Integer, Integer> hoadon = service_HD.getTongHoaDonQuy();
                int tonghoadon = hoadon.values().stream().mapToInt(Integer::intValue).sum();
                lblTongHoaDon.setText(String.valueOf(tonghoadon));

                // thống kê điện thoại 
                Map<Integer, Integer> dienthoai = service_DTCT.getTongDienThoaiQuy();
                int tongdienthoai = dienthoai.values().stream().mapToInt(Integer::intValue).sum();
                lblTongDienThoai.setText(String.valueOf(tongdienthoai));
            } catch (Exception ex) {
                Logger.getLogger(ThongKe_JPanel.class.getName()).log(Level.SEVERE, "Thống kê xảy ra lỗi hệ thống", ex);
            }
        }

    }//GEN-LAST:event_jComboBox1ItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelAll;
    private javax.swing.JPanel jPanel_BieuDo;
    private javax.swing.JPanel jPanel_BieuDoTron;
    private javax.swing.JPanel jPanel_ThongKeDoanhSo;
    private javax.swing.JPanel jPanel_ThongKeDoanhSo1;
    private javax.swing.JPanel jPanel_ThongKeDoanhSo2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTongDienThoai;
    private javax.swing.JLabel lblTongDoanhThu;
    private javax.swing.JLabel lblTongHoaDon;
    private javax.swing.JTable tblThongKeNhanVien;
    // End of variables declaration//GEN-END:variables
}
