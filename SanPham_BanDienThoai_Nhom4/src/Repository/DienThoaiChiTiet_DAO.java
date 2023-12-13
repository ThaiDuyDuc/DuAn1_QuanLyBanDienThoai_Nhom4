package Repository;

import JDBC.JDBCUtil;
import Model.DienThoaiChiTiet;
import java.math.BigDecimal;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.jfree.data.general.DefaultPieDataset;

public class DienThoaiChiTiet_DAO extends ServiceDAO<DienThoaiChiTiet, String> {

    final String insert_sql = "INSERT INTO DienThoaiChiTiet (MaDTCT, MaDT, GiaBan, HinhAnh, MauSac, SLTonKho, MaBoNho, MaKho, MaNSX, MaPhieuBaoHanh, TrangThai, MoTa ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    final String update_sql = "UPDATE DienThoaiChiTiet SET MaDT = ?, GiaBan =?, HinhAnh=?, MauSac=?, SLTonKho=?, MaBoNho=?, MaKho=?, MaNSX=?,  MaPhieuBaoHanh=?, TrangThai=?, MoTa=? where MaDTCT = ? ";
    final String delete_sql_insert = "{call XoaDTCT_Save_LSDTCT(?,?,?,?,?,?,?,?,?,?,?,?)}";
    final String select_All = "SELECT * FROM DienThoaiChiTiet";
    final String select_All_GiaTang = "SELECT * FROM DienThoaiChiTiet ORDER BY GiaBan ASC;";
    final String select_All_GiaGiam = "SELECT * FROM DienThoaiChiTiet ORDER BY GiaBan DESC;";
    final String select_All_TonKhoTang = "SELECT * FROM DienThoaiChiTiet ORDER BY SLTonKho ASC;";
    final String select_All_TonKhoGiam = "SELECT * FROM DienThoaiChiTiet ORDER BY SLTonKho DESC;";
    final String select_Search = "SELECT * FROM DienThoaiChiTiet WHERE MaDTCT like ? or MaDT like ? or GiaBan like ? or MauSac like ? or MaBoNho like ? or MaKho like ? or MaNSX like ? ";
    final String select_helphd = "select * from DienThoaiChiTiet where madtct = ?";
    final String select_madtct = "select * from DienThoaiChiTiet where madt = ?";
    final String update_bf_thanhtoan = "update DienThoaiChiTiet set SLTonKho = ? where MaDTCT = ?";
    
    @Override
    public void insert(DienThoaiChiTiet x) {
        JDBC.JDBCHelPer.update(insert_sql, x.getMaDTCT(), x.getMaDT(), x.getGiaBan(), x.getHinhAnh(), x.getMauSac(), x.getSoLuongTonKho(), x.getMaBoNho(), x.getMaKho(), x.getMaNSX(), x.getMaPhieuBaoHanh(), x.isTrangThai(), x.getMoTa());
    }

    public DienThoaiChiTiet getSingleLichSuDienThoai(String Xoa) {
        List<DienThoaiChiTiet> danhSachKetQua = select_Search(Xoa);

        if (!danhSachKetQua.isEmpty()) {
            return danhSachKetQua.get(0);
        } else {
            return null;
        }
    }

    public void update_bf_thanhtoan(String sl, String madtct){
        JDBC.JDBCHelPer.update(update_bf_thanhtoan, sl,madtct);
    }
    public List<DienThoaiChiTiet> getmadtct(String madt){
        return selecBySQL(select_madtct, madt);
    }
    public List<DienThoaiChiTiet> budtct(String madtct){
        return selecBySQL(select_helphd, madtct);
    }
    
    public void delete(DienThoaiChiTiet x) {
        JDBC.JDBCHelPer.update(delete_sql_insert, x.getMaDTCT(), x.getMaDT(), x.getGiaBan(), x.getHinhAnh(), x.getMauSac(), x.getSoLuongTonKho(), x.getMaBoNho(), x.getMaKho(), x.getMaNSX(), x.getMaPhieuBaoHanh(), x.isTrangThai(), x.getMoTa());
    }

    @Override
    public void update(DienThoaiChiTiet x) {
        JDBC.JDBCHelPer.update(update_sql, x.getMaDT(), x.getGiaBan(), x.getHinhAnh(), x.getMauSac(), x.getSoLuongTonKho(), x.getMaBoNho(), x.getMaKho(), x.getMaNSX(), x.getMaPhieuBaoHanh(), x.isTrangThai(), x.getMoTa(), x.getMaDTCT());
    }

    @Override
    public void delete(String id) {
//        JDBC.JDBCHelPer.update(delete_sql, id);
    }

    @Override
    public List<DienThoaiChiTiet> selectAll() {
        return selecBySQL(select_All);
    }

    @Override
    public List<DienThoaiChiTiet> select_Search(String key) {
        return selecBySQL(select_Search, "%" + key + "%", "%" + key + "%", "%" + key + "%", "%" + key + "%", "%" + key + "%", "%" + key + "%", "%" + key + "%");
    }

    @Override
    public List<DienThoaiChiTiet> selecBySQL(String sql, Object... args) {
        ArrayList<DienThoaiChiTiet> list = new ArrayList<>();

        try {
            ResultSet rs = JDBC.JDBCHelPer.query(sql, args);
            while (rs.next()) {
                String madtct = rs.getString(1);
                String maht = rs.getString(2);
                BigDecimal gia = rs.getBigDecimal(3);
                String hinhanh = rs.getString(4);
                String mausac = rs.getString(5);
                int Soluong = rs.getInt(6);
                String bonho = rs.getString(7);
                String kho = rs.getString(8);
                String nsx = rs.getString(9);
                String baohanh = rs.getString(10);
                boolean trangthai = rs.getBoolean(11);
                String mota = rs.getString(12);

                DienThoaiChiTiet o = new DienThoaiChiTiet(madtct, maht, gia, hinhanh, mausac, Soluong, bonho, kho, nsx, baohanh, trangthai, mota);
                list.add(o);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }

    public List<DienThoaiChiTiet> select_GiaTang() {
        return selecBySQL(select_All_GiaTang);
    }

    public List<DienThoaiChiTiet> select_GiaGiam() {
        return selecBySQL(select_All_GiaGiam);
    }

    public List<DienThoaiChiTiet> select_TonKho_Tang() {
        return selecBySQL(select_All_TonKhoTang);
    }

    public List<DienThoaiChiTiet> select_TonKho_Giam() {
        return selecBySQL(select_All_TonKhoGiam);
    }

    // ----------------------------------- THỐNG KÊ -------------------------------------
    public DefaultPieDataset getChartData_Tuan() throws Exception {
        DefaultPieDataset dataset = new DefaultPieDataset();
        String sqlQuery = "SELECT TOP 3 DT.TenDienThoai, SUM(HDCT.SoLuong) AS SoLuongBan "
                + "FROM HoaDonChiTiet HDCT "
                + "JOIN DienThoaiChiTiet DTC ON HDCT.MaDTCT = DTC.MaDTCT "
                + "JOIN HoaDon HD ON HDCT.MaHoaDon = HD.MaHoaDon "
                + "JOIN DienThoai DT ON DTC.MaDT = DT.MaDT "
                + "WHERE HD.NgayLap BETWEEN DATEADD(DAY, -6, CAST(GETDATE() AS DATE)) AND CAST(GETDATE() AS DATE) "
                + "GROUP BY DT.TenDienThoai "
                + "ORDER BY SoLuongBan DESC;";

        try (Connection cn = JDBC.DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sqlQuery); ResultSet resultSet = ps.executeQuery()) {

            while (resultSet.next()) {
                String tenDienThoai = resultSet.getString("TenDienThoai");
                int soLuongBan = resultSet.getInt("SoLuongBan");

                dataset.setValue(tenDienThoai, soLuongBan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }

    public DefaultPieDataset getChartData_ThangTrongNam() throws Exception {
        DefaultPieDataset dataset = new DefaultPieDataset();
        String sqlQuery = "SELECT TOP 3 DT.TenDienThoai, SUM(HDCT.SoLuong) AS SoLuongBan "
                + "FROM HoaDonChiTiet HDCT "
                + "JOIN DienThoaiChiTiet DTC ON HDCT.MaDTCT = DTC.MaDTCT "
                + "JOIN HoaDon HD ON HDCT.MaHoaDon = HD.MaHoaDon "
                + "JOIN DienThoai DT ON DTC.MaDT = DT.MaDT "
                + "WHERE YEAR(HD.NgayLap) = YEAR(GETDATE()) "
                + "GROUP BY DT.TenDienThoai "
                + "ORDER BY SoLuongBan DESC;";

        try (Connection cn = JDBC.DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sqlQuery); ResultSet resultSet = ps.executeQuery()) {

            while (resultSet.next()) {
                String tenDienThoai = resultSet.getString("TenDienThoai");
                int soLuongBan = resultSet.getInt("SoLuongBan");

                dataset.setValue(tenDienThoai, soLuongBan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }

    public DefaultPieDataset getChartData_QuyTrongNam() throws Exception {
        DefaultPieDataset dataset = new DefaultPieDataset();
        String sqlQuery = "SELECT TOP 3 DT.TenDienThoai, SUM(HDCT.SoLuong) AS SoLuongBan "
                + "FROM HoaDonChiTiet HDCT "
                + "JOIN DienThoaiChiTiet DTC ON HDCT.MaDTCT = DTC.MaDTCT "
                + "JOIN HoaDon HD ON HDCT.MaHoaDon = HD.MaHoaDon "
                + "JOIN DienThoai DT ON DTC.MaDT = DT.MaDT "
                + "WHERE YEAR(HD.NgayLap) = YEAR(GETDATE()) "
                + "GROUP BY DT.TenDienThoai, DATEPART(QUARTER, HD.NgayLap) "
                + "ORDER BY SoLuongBan DESC;";

        try (Connection cn = JDBC.DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sqlQuery); ResultSet resultSet = ps.executeQuery()) {

            while (resultSet.next()) {
                String tenDienThoai = resultSet.getString("TenDienThoai");
                int soLuongBan = resultSet.getInt("SoLuongBan");

                dataset.setValue(tenDienThoai, soLuongBan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }

    public DefaultPieDataset getChartData_NgayHienTai() throws Exception {
        DefaultPieDataset dataset = new DefaultPieDataset();
        String sqlQuery = "SELECT TOP 3 DT.TenDienThoai, SUM(HDCT.SoLuong) AS SoLuongBan "
                + "FROM HoaDonChiTiet HDCT "
                + "JOIN DienThoaiChiTiet DTC ON HDCT.MaDTCT = DTC.MaDTCT "
                + "JOIN HoaDon HD ON HDCT.MaHoaDon = HD.MaHoaDon "
                + "JOIN DienThoai DT ON DTC.MaDT = DT.MaDT "
                + "WHERE CONVERT(NVARCHAR, HD.NgayLap, 23) = CONVERT(NVARCHAR, GETDATE(), 23) "
                + "GROUP BY DT.TenDienThoai "
                + "ORDER BY SoLuongBan DESC;";

        try (Connection cn = JDBC.DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sqlQuery); ResultSet resultSet = ps.executeQuery()) {

            while (resultSet.next()) {
                String tenDienThoai = resultSet.getString("TenDienThoai");
                int soLuongBan = resultSet.getInt("SoLuongBan");

                dataset.setValue(tenDienThoai, soLuongBan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }

    // ---------------------------------------------- SỐ LƯỢNG BÁN ĐIỆN THOẠI ---------------------------------------------------
    final String tdt_7N = "SELECT "
            + "    SUM(SoLuong) AS TongSoDienThoaiBan "
            + "FROM "
            + "    HoaDonChiTiet hct "
            + "    JOIN HoaDon hd ON hct.MaHoaDon = hd.MaHoaDon "
            + "WHERE "
            + "    hd.NgayLap BETWEEN DATEADD(day, -7, GETDATE()) AND GETDATE();";
    final String tdt_cttn = "SELECT "
            + "    MONTH(hd.NgayLap) AS Thang, "
            + "    SUM(hct.SoLuong) AS TongSoDienThoaiBan "
            + "FROM "
            + "    HoaDonChiTiet hct "
            + "    JOIN HoaDon hd ON hct.MaHoaDon = hd.MaHoaDon "
            + "WHERE "
            + "    YEAR(hd.NgayLap) = YEAR(GETDATE()) "
            + "GROUP BY "
            + "    MONTH(hd.NgayLap);";
    final String tdt_quy = "SELECT "
            + "    DATEPART(QUARTER, hd.NgayLap) AS Quy, "
            + "    SUM(hct.SoLuong) AS TongSoDienThoaiBan "
            + "FROM "
            + "    HoaDonChiTiet hct "
            + "    JOIN HoaDon hd ON hct.MaHoaDon = hd.MaHoaDon "
            + "WHERE "
            + "    YEAR(hd.NgayLap) = YEAR(GETDATE()) "
            + "    AND DATEPART(QUARTER, hd.NgayLap) = DATEPART(QUARTER, GETDATE()) "
            + "GROUP BY "
            + "    DATEPART(QUARTER, hd.NgayLap);";
    final String tdt_hn = "SELECT "
            + "    SUM(hct.SoLuong) AS TongSoDienThoaiBan "
            + "FROM "
            + "    HoaDonChiTiet hct "
            + "    JOIN HoaDon hd ON hct.MaHoaDon = hd.MaHoaDon "
            + "WHERE "
            + "    CONVERT(DATE, hd.NgayLap) = CONVERT(DATE, GETDATE());";

    public int getTongDienThoai_7N() {
        int tongHD = 0;
        Connection cn = JDBCUtil.getConnection();

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(tdt_7N);

            if (rs.next()) {
                tongHD = rs.getInt("TongSoDienThoaiBan");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(cn);
        }

        return tongHD;
    }

    public Map<Integer, Integer> getTongDienThoaiCacThangTrongNam() {
        Map<Integer, Integer> tongHoaDonTheoThang = new HashMap<>();
        Connection cn = JDBCUtil.getConnection();

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(tdt_cttn);

            while (rs.next()) {
                int thang = rs.getInt("Thang");
                int tongHoaDon = rs.getInt("TongSoDienThoaiBan");
                tongHoaDonTheoThang.put(thang, tongHoaDon);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(cn);
        }

        return tongHoaDonTheoThang;
    }

    public Map<Integer, Integer> getTongDienThoaiQuy() {
        Map<Integer, Integer> tongHoaDonTheoQuy = new HashMap<>();
        Connection cn = JDBCUtil.getConnection();

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(tdt_quy);

            while (rs.next()) {
                int Quy = rs.getInt("Quy");
                int tongHoaDon = rs.getInt("TongSoDienThoaiBan");
                tongHoaDonTheoQuy.put(Quy, tongHoaDon);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
            ResultSet rs = st.executeQuery(tdt_hn);

            if (rs.next()) {
                tongHD = rs.getInt("TongSoDienThoaiBan");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(cn);
        }

        return tongHD;
    }
}
