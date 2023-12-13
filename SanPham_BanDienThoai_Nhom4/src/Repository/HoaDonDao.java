package Repository;

import JDBC.JDBCUtil;
import Model.DonHang;
import Model.HoaDonChiTiet;
import Model.KhachHang;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Administrator
 */
public class HoaDonDao implements DAOInterface<DonHang, String> {

    private ArrayList<DonHang> getList(String sql) {
        ArrayList<DonHang> list = new ArrayList<>();
        Connection cn = JDBCUtil.getConnection();
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String ma = rs.getString(1);
                Date ngayLap = rs.getDate("NgayLap");
                String maKH = rs.getString("MaKhachHang");
                String maNV = rs.getString("MaNV");
                String maPGG = rs.getString("MaPhieuGiamGia");
                String ghiChu = rs.getString("GhiChu");
                String trangThai = rs.getString("TrangThai");
                String phuongThucThanhToan = rs.getString("PhuongThucThanhToan");
                double thanhTien = rs.getDouble("TongTien");
                double tongTienSauGG = rs.getDouble("TongTienSauGG");
                KhachHang khachHang = new KhachHangDAO().getByID(maKH);
                DonHang dh = new DonHang(ma, ngayLap, khachHang, maNV, thanhTien, maPGG, tongTienSauGG, phuongThucThanhToan, ghiChu, trangThai);

                list.add(dh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return list;
    }

    @Override
    public ArrayList<DonHang> getAll() {
        String sql = "SELECT * FROM HoaDon";
        ArrayList<DonHang> list = getList(sql);
        return list;
    }

    public ArrayList<DonHang> getPhanTrang(int row, int page) {
        String sql = "DECLARE @PageSize INT = " + row + "; "
                + "DECLARE @PageNumber INT = " + page + "; "
                + "SELECT *  FROM HoaDon ORDER BY NgayLap DESC  "
                + "OFFSET (@PageNumber - 1) * @PageSize ROWS "
                + "FETCH NEXT @PageSize ROWS ONLY;";
        ArrayList<DonHang> list = getList(sql);
        return list;
    }

    public ArrayList<HoaDonChiTiet> getAllHoaDonChiTietByMa(String maHD) {
        String sql = "SELECT * FROM HoaDonChiTiet WHERE MaHoaDon = '" + maHD + "'";
        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
        Connection cn = JDBCUtil.getConnection();
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String ma = rs.getString(1);
                String maDTCT = rs.getString("MaDTCT");
                int soLuong = rs.getInt("SoLuong");
                double donGia = rs.getDouble("DonGia");
                double thanhTien = rs.getDouble("ThanhTien");
                String ghiChu = rs.getString("GhiChu");

                HoaDonChiTiet hdct = new HoaDonChiTiet(ma, maDTCT, soLuong, String.valueOf(donGia), String.valueOf(thanhTien), ghiChu);
                list.add(hdct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return list;
    }

    public HoaDonChiTiet getHDCTByMaHDMaDT(String maHD, String maDT) {
        String sql = "SELECT * FROM HoaDonChiTiet WHERE MaHoaDon = '" + maHD + "' AND MaDTCT = '" + maDT + "'";
        HoaDonChiTiet hdct = null;
        Connection cn = JDBCUtil.getConnection();
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String ma = rs.getString(1);
                String maDTCT = rs.getString("MaDTCT");
                int soLuong = rs.getInt("SoLuong");
                double donGia = rs.getDouble("DonGia");
                double thanhTien = rs.getDouble("ThanhTien");
                String ghiChu = rs.getString("GhiChu");

                hdct = new HoaDonChiTiet(ma, maDTCT, soLuong, String.valueOf(donGia), String.valueOf(thanhTien), ghiChu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return hdct;
    }

    @Override
    public int insert(DonHang e) {
        int row = 0;
        Connection cn = JDBCUtil.getConnection();
        String sql = "INSERT INTO HoaDon (MaHoaDon, Ngaylap, MaKhachHang, MaNV, MaDotGiamGia, MaPhieuGiamGia, MaPhieuGiaoHang, GhiChu, TrangThai) VALUES (?,?,?,?, ?,?,?,?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, e.getMaHD());
            ps.setDate(2, e.getNgayLap());
            ps.setString(3, e.getKhachHang().getMaKH());
            ps.setString(4, e.getMaNV());
            ps.setString(5, "DG001");
            ps.setString(6, e.getPhieuGiamGia());
            ps.setString(7, "PGH001");
            ps.setString(8, e.getGhiChu());
            ps.setString(9, e.getTrangThai());

            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public int insertHDCT(HoaDonChiTiet e) {
        int row = 0;
        Connection cn = JDBCUtil.getConnection();
        String sql = "INSERT INTO HoaDonChiTiet VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, e.getMaHoaDon());
            ps.setString(2, e.getMaDTCT());
            ps.setInt(3, e.getSoLuong());
            ps.setString(4, e.getDonGia());
            ps.setString(5, e.getGhiChu());
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    @Override
    public int update(DonHang e) {
        int row = 0;
        Connection cn = JDBCUtil.getConnection();
        String sql = "UPDATE HoaDon SET Ngaylap = ?, MaKhachHang= ?, MaNV= ?, MaDotGiamGia= ?, MaPhieuGiamGia= ?, MaPhieuGiaoHang= ?, "
                + "GhiChu= ?, TrangThai= ? WHERE MaHoaDon = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(9, e.getMaHD());
            ps.setDate(1, e.getNgayLap());
            ps.setString(2, e.getKhachHang().getMaKH());
            ps.setString(3, e.getMaNV());
            ps.setString(4, "DG001");
            ps.setString(5, e.getPhieuGiamGia());
            ps.setString(6, "PGH001");
            ps.setString(7, e.getGhiChu());
            ps.setString(8, e.getTrangThai());

            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public int updateHDCT(HoaDonChiTiet e) {
        int row = 0;
        Connection cn = JDBCUtil.getConnection();
        String sql = "UPDATE HoaDonChiTiet SET SoLuong = ?, DonGia=?, GhiChu= ? WHERE MaHoaDon = ? AND MaDTCT = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(4, e.getMaHoaDon());
            ps.setString(5, e.getMaDTCT());
            ps.setInt(1, e.getSoLuong());
            ps.setString(2, e.getDonGia());
            ps.setString(3, e.getGhiChu());

            row = ps.executeUpdate();
//            System.out.println(row);
//            System.out.println(sql);
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return row;
    }

    @Override
    public int delete(String k) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void Delete(String title) {
        String sql = """
                    DECLARE @MaHoaDonToDelete NCHAR(20);
                    SET @MaHoaDonToDelete = ?;
                    DELETE FROM HoaDonChiTiet WHERE MaHoaDon = @MaHoaDonToDelete;
                    DELETE FROM HoaDon WHERE MaHoaDon = @MaHoaDonToDelete;""";
        try (Connection con = JDBC.JDBCHelPer.openConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, title);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<DonHang> Search(String title) {
        String sql = "SELECT * FROM HoaDon WHERE MaHoaDon LIKE ? OR NgayLap like ?  OR MaKhachHang like ? OR MaNV like ?";
        ArrayList<DonHang> list = new ArrayList<>();

        try (Connection con = JDBC.JDBCHelPer.openConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, "%" + title + "%");
            ps.setString(2, "%" + title + "%");
            ps.setString(3, "%" + title + "%");
            ps.setString(4, "%" + title + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString(1);
                Date ngayLap = rs.getDate("NgayLap");
                String maKH = rs.getString("MaKhachHang");
                String maNV = rs.getString("MaNV");
                String maPGG = rs.getString("MaPhieuGiamGia");
                String ghiChu = rs.getString("GhiChu");
                String trangThai = rs.getString("TrangThai");
                String phuongThucThanhToan = rs.getString("PhuongThucThanhToan");
                double thanhTien = rs.getDouble("TongTien");
                double tongTienSauGG = rs.getDouble("TongTienSauGG");
                KhachHang khachHang = new KhachHangDAO().getByID(maKH);
                DonHang dh = new DonHang(ma, ngayLap, khachHang, maNV, thanhTien, maPGG, tongTienSauGG, phuongThucThanhToan, ghiChu, trangThai);

                list.add(dh);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int deleteHDCT(String maHD, String maDT) {
        int row = 0;
        Connection cn = JDBCUtil.getConnection();
        String sql = "DELETE HoaDonChiTiet WHERE MaHoaDon = '" + maHD + "' AND MaDTCT = '" + maDT + "'";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);

            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return row;
    }

    @Override
    public DonHang getByID(String k) {
        DonHang dh = null;
        Connection cn = JDBCUtil.getConnection();
        String sql = "SELECT * FROM HoaDon WHERE MaHoaDon = '" + k + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String ma = rs.getString(1);
                Date ngayLap = rs.getDate("NgayLap");
                String maKH = rs.getString("MaKhachHang");
                String maNV = rs.getString("MaNV");
                String maPGG = rs.getString("MaPhieuGiamGia");
                String ghiChu = rs.getString("GhiChu");
                String trangThai = rs.getString("TrangThai");
                String phuongThucThanhToan = rs.getString("PhuongThucThanhToan");
                double thanhTien = rs.getDouble("TongTien");
                double tongTienSauGG = rs.getDouble("TongTienSauGG");
                KhachHang khachHang = new KhachHangDAO().getByID(maKH);
                dh = new DonHang(ma, ngayLap, khachHang, maNV, thanhTien, maPGG, tongTienSauGG, phuongThucThanhToan, ghiChu, trangThai);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return dh;
    }

    public ArrayList<DonHang> searchByDate(Date start, Date end, String maKH) {
        String sql = "SELECT * FROM HoaDon WHERE NgayLap BETWEEN '" + start + "' AND '" + end + "' AND MaKhachHang = '" + maKH + "'";
        ArrayList<DonHang> list = getList(sql);
        return list;
    }

    public ArrayList<DonHang> searchByID(String maKH, String maHD) {
        String sql = "SELECT * FROM HoaDon WHERE MaHoaDon Like '%" + maHD + "%' AND MaKhachHang = '" + maKH + "'";
        ArrayList<DonHang> list = getList(sql);
        return list;
    }

    // thống kê=============================================================================================================================================
    public DefaultCategoryDataset getChartData_Ngay() throws Exception {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String sqlQuery = "SELECT TOP 7 CONVERT(NVARCHAR, NgayLap, 23) AS NamThangNgay, SUM(TongTienSauGG) AS TongTien "
                + "FROM HoaDon "
                + "WHERE NgayLap >= DATEADD(DAY, -7, GETDATE()) "
                + "GROUP BY CONVERT(NVARCHAR, NgayLap, 23) "
                + "ORDER BY NamThangNgay DESC;";

        try (Connection cn = JDBC.DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sqlQuery); ResultSet resultSet = ps.executeQuery()) {

            while (resultSet.next()) {
                String ngayLap = resultSet.getString("NamThangNgay"); // Đọc dữ liệu ngày làm chuỗi
                double tongTien = resultSet.getDouble("TongTien");

                dataset.addValue(tongTien, "Tổng Tiền", ngayLap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }

    public DefaultCategoryDataset getChartData_ThangTrongNam() throws Exception {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String sqlQuery = "SELECT CONVERT(NVARCHAR(7), NgayLap, 126) AS Thang, SUM(TongTienSauGG) AS TongTien "
                + "FROM HoaDon "
                + "WHERE YEAR(NgayLap) = YEAR(GETDATE()) "
                + "GROUP BY CONVERT(NVARCHAR(7), NgayLap, 126) "
                + "ORDER BY Thang DESC;";

        try (Connection cn = JDBC.DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sqlQuery); ResultSet resultSet = ps.executeQuery()) {

            while (resultSet.next()) {
                String thang = resultSet.getString("Thang"); // Đọc dữ liệu tháng làm chuỗi
                double tongTien = resultSet.getDouble("TongTien");

                dataset.addValue(tongTien, "Tổng Tiền", thang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }

    public DefaultCategoryDataset getChartData_NgayHienTai() throws Exception {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String sqlQuery = "SELECT CONVERT(NVARCHAR(10), NgayLap, 23) AS Ngay, SUM(TongTienSauGG) AS TongTien "
                + "FROM HoaDon "
                + "WHERE NgayLap = CONVERT(DATE, GETDATE()) "
                + "GROUP BY CONVERT(NVARCHAR(10), NgayLap, 23) "
                + "ORDER BY Ngay DESC;";

        try (Connection cn = JDBC.DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sqlQuery); ResultSet resultSet = ps.executeQuery()) {

            while (resultSet.next()) {
                String ngay = resultSet.getString("Ngay"); // Đọc dữ liệu ngày làm chuỗi
                double tongTien = resultSet.getDouble("TongTien");

                dataset.addValue(tongTien, "Tổng Tiền", ngay);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }

    public DefaultCategoryDataset getChartData_QuyTrongNam() throws Exception {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String sqlQuery = "SELECT DATEPART(QUARTER, NgayLap) AS Quy, SUM(TongTienSauGG) AS TongTien "
                + "FROM HoaDon "
                + "WHERE YEAR(NgayLap) = YEAR(GETDATE()) "
                + "GROUP BY DATEPART(QUARTER, NgayLap) "
                + "ORDER BY Quy ASC;";

        try (Connection cn = JDBC.DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sqlQuery); ResultSet resultSet = ps.executeQuery()) {

            while (resultSet.next()) {
                int quy = resultSet.getInt("Quy"); // Đọc dữ liệu quý
                double tongTien = resultSet.getDouble("TongTien");

                dataset.addValue(tongTien, "Tổng Tiền", "Quý " + quy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    ////---------------------------------------------------------------------------------
    final String tdt_7n = "SELECT SUM(TongTienSauGG) AS TongDoanhThu FROM   HoaDon WHERE    NgayLap BETWEEN DATEADD(day, -7, GETDATE()) AND GETDATE(); ";
    final String tdt_hn = "SELECT SUM(TongTienSauGG) AS TongDoanhThu FROM HoaDon WHERE NgayLap = CONVERT(date, GETDATE());";
    final String tdt_cttn = "SELECT MONTH(NgayLap) AS Thang, SUM(TongTienSauGG) AS TongDoanhThu "
            + "FROM HoaDon "
            + "WHERE YEAR(NgayLap) = YEAR(GETDATE()) "
            + "GROUP BY MONTH(NgayLap);";
    final String tdt_quy = "SELECT "
            + "    DATEPART(QUARTER, NgayLap) AS Quy, "
            + "    SUM(TongTienSauGG) AS TongDoanhThu "
            + "FROM  "
            + "    HoaDon "
            + "WHERE  "
            + "    YEAR(NgayLap) = YEAR(GETDATE()) "
            + "    AND DATEPART(QUARTER, NgayLap) = DATEPART(QUARTER, GETDATE()) "
            + "GROUP BY "
            + "    DATEPART(QUARTER, NgayLap);";

    public double getTongDoanhThu_7N() {
        double tongDoanhThu = 0;
        Connection cn = JDBCUtil.getConnection();

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(tdt_7n);

            if (rs.next()) {
                tongDoanhThu = rs.getDouble("TongDoanhThu");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCUtil.closeConnection(cn);
        }

        return tongDoanhThu;
    }

    public double getTongDoanhThuHomNay() {
        double tongDoanhThu = 0;
        Connection cn = JDBCUtil.getConnection();

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(tdt_hn);

            if (rs.next()) {
                tongDoanhThu = rs.getDouble("TongDoanhThu");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCUtil.closeConnection(cn);
        }

        return tongDoanhThu;
    }

    public Map<Integer, Double> getTongDoanhThuCacThangTrongNam() {
        Map<Integer, Double> tongDoanhThuTheoThang = new HashMap<>();
        Connection cn = JDBCUtil.getConnection();

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(tdt_cttn);

            while (rs.next()) {
                int thang = rs.getInt("Thang");
                double tongDoanhThu = rs.getDouble("TongDoanhThu");
                tongDoanhThuTheoThang.put(thang, tongDoanhThu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCUtil.closeConnection(cn);
        }

        return tongDoanhThuTheoThang;
    }

//    final String tdt_quy = "SELECT "
//            + "    SUM(TongTienSauGG) AS TongDoanhThu "
//            + "FROM  "
//            + "    HoaDon "
//            + "WHERE  "
//            + "    YEAR(NgayLap) = YEAR(GETDATE()) "
//            + "    AND DATEPART(QUARTER, NgayLap) = DATEPART(QUARTER, GETDATE());";
    public Map<Integer, Double> getTongDoanhThuCacQuyTrongNam() {
        Map<Integer, Double> tongDoanhThuTheoQuy = new HashMap<>();
        Connection cn = JDBCUtil.getConnection();

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(tdt_quy);

            while (rs.next()) {
                int quy = rs.getInt("Quy");
                double tongDoanhThu = rs.getDouble("TongDoanhThu");
                tongDoanhThuTheoQuy.put(quy, tongDoanhThu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCUtil.closeConnection(cn);
        }

        return tongDoanhThuTheoQuy;
    }

    // --------------------------------------- tong hóa đơn --------------------------
    final String thd_7N = "SELECT COUNT(*) AS TongSoHoaDon "
            + "FROM HoaDon "
            + "WHERE NgayLap BETWEEN DATEADD(day, -7, GETDATE()) AND GETDATE();";
    final String thd_cttn = "SELECT MONTH(NgayLap) AS Thang, COUNT(*) AS TongSoHoaDon "
            + "FROM HoaDon "
            + "WHERE YEAR(NgayLap) = YEAR(GETDATE()) "
            + "GROUP BY MONTH(NgayLap) "
            + "ORDER BY Thang;";

    final String thd_quy = "SELECT "
            + "    DATEPART(QUARTER, NgayLap) AS Quy, "
            + "    COUNT(*) AS TongSoHoaDon "
            + "FROM "
            + "    HoaDon "
            + "WHERE "
            + "    YEAR(NgayLap) = YEAR(GETDATE()) "
            + "    AND DATEPART(QUARTER, NgayLap) = DATEPART(QUARTER, GETDATE()) "
            + "GROUP BY "
            + "    DATEPART(QUARTER, NgayLap) "
            + "ORDER BY "
            + "    Quy;";

    final String thd_hn = "SELECT  "
            + "    COUNT(*) AS TongSoHoaDon "
            + "FROM  "
            + "    HoaDon "
            + "WHERE  "
            + "    CONVERT(DATE, NgayLap) = CONVERT(DATE, GETDATE());";

    public int getTongHoaDon_7N() {
        int tongHD = 0;
        Connection cn = JDBCUtil.getConnection();

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(thd_7N);

            if (rs.next()) {
                tongHD = rs.getInt("TongSoHoaDon");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCUtil.closeConnection(cn);
        }

        return tongHD;
    }

    public Map<Integer, Integer> getTongHoaDonCacThangTrongNam() {
        Map<Integer, Integer> tongHoaDonTheoThang = new HashMap<>();
        Connection cn = JDBCUtil.getConnection();

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(thd_cttn);

            while (rs.next()) {
                int thang = rs.getInt("Thang");
                int tongHoaDon = rs.getInt("TongSoHoaDon");
                tongHoaDonTheoThang.put(thang, tongHoaDon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCUtil.closeConnection(cn);
        }

        return tongHoaDonTheoThang;
    }

    public Map<Integer, Integer> getTongHoaDonQuy() {
        Map<Integer, Integer> tongHoaDonTheoQuy = new HashMap<>();
        Connection cn = JDBCUtil.getConnection();

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(thd_quy);

            while (rs.next()) {
                int Quy = rs.getInt("Quy");
                int tongHoaDon = rs.getInt("TongSoHoaDon");
                tongHoaDonTheoQuy.put(Quy, tongHoaDon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCUtil.closeConnection(cn);
        }

        return tongHoaDonTheoQuy;
    }

    public int getTongHoaDon_HN() {
        int tongHD = 0;
        Connection cn = JDBCUtil.getConnection();

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(thd_hn);

            if (rs.next()) {
                tongHD = rs.getInt("TongSoHoaDon");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCUtil.closeConnection(cn);
        }

        return tongHD;
    }
}
