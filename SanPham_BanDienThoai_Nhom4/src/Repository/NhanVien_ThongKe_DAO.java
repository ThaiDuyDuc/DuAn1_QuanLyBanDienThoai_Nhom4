package Repository;

import Model.NhanVien_thongKe;
import java.math.BigDecimal;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class NhanVien_ThongKe_DAO extends ServiceDAO<NhanVien_thongKe, String>{

    final String insert_sql = "";
    final String update_sql = "";
    final String delete_sql = "";
    final String select_All = "SELECT * FROM NhanVien";
    final String select_Bay = "SELECT TOP 5 NV.MaNV, NV.TenNV, SUM(HD.TongTienSauGG) AS TongDoanhSo FROM NhanVien NV JOIN HoaDon HD ON NV.MaNV = HD.MaNV "
            + "WHERE HD.NgayLap >= DATEADD(DAY, -7, GETDATE())  -- Lấy 7 ngày gần nhất \n"
            + "GROUP BY NV.MaNV, NV.TenNV ORDER BY TongDoanhSo DESC;";
    final String select_Search = "SELECT * FROM NhanVien WHERE MaNV = ? ";
    
    final String select_Top5_HomNay = "SELECT TOP 5 NV.MaNV, NV.TenNV, SUM(HD.TongTienSauGG) AS TongDoanhSo "
            + " FROM NhanVien NV "
            + " JOIN HoaDon HD ON NV.MaNV = HD.MaNV "
            + " WHERE CONVERT(NVARCHAR, HD.NgayLap, 23) = CONVERT(NVARCHAR, GETDATE(), 23) "
            + " GROUP BY NV.MaNV, NV.TenNV "
            + " ORDER BY TongDoanhSo DESC;";
    
    final String select_Top5_NamNay = "SELECT TOP 5 NV.MaNV, NV.TenNV, SUM(HD.TongTienSauGG) AS TongDoanhSo "
            + " FROM NhanVien NV "
            + " JOIN HoaDon HD ON NV.MaNV = HD.MaNV "
            + " WHERE YEAR(HD.NgayLap) = YEAR(GETDATE()) "
            + " GROUP BY NV.MaNV, NV.TenNV "
            + " ORDER BY TongDoanhSo DESC;";
    final String select_Top5_QuyHienTai = "SELECT TOP 5 NV.MaNV, NV.TenNV, SUM(HD.TongTienSauGG) AS TongDoanhSo "
            + " FROM NhanVien NV "
            + " JOIN HoaDon HD ON NV.MaNV = HD.MaNV "
            + " WHERE DATEPART(QUARTER, HD.NgayLap) = DATEPART(QUARTER, GETDATE()) "
            + " AND YEAR(HD.NgayLap) = YEAR(GETDATE()) "
            + " GROUP BY NV.MaNV, NV.TenNV "
            + " ORDER BY TongDoanhSo DESC;";

    @Override
    public void insert(NhanVien_thongKe x) {
    }

    @Override
    public void update(NhanVien_thongKe x) {
    }

    @Override
    public void delete(String id) {
    }

    @Override
    public List<NhanVien_thongKe> selectAll() {
        return selecBySQL(select_All);
    }

    @Override
    public List<NhanVien_thongKe> select_Search(String key) {
        return null;
    }

    @Override
    public List<NhanVien_thongKe> selecBySQL(String sql, Object... args) {
        ArrayList<NhanVien_thongKe> list = new ArrayList<>();

        try {
            ResultSet rs = JDBC.JDBCHelPer.query(sql, args);
            while (rs.next()) {
                String ma = rs.getString(1);
                String ten = rs.getString(2);
                BigDecimal doanhthu = rs.getBigDecimal(3);

                NhanVien_thongKe o = new NhanVien_thongKe(ma, ten, doanhthu);
                list.add(o);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }

    public List<NhanVien_thongKe> selectAll_Bay() {
        return selecBySQL(select_Bay);
    }


    public List<NhanVien_thongKe> selectAll_HomNay() {
        return selecBySQL(select_Top5_HomNay);
    }
    
    public List<NhanVien_thongKe> selectAll_Thang() {
        return selecBySQL(select_Top5_NamNay);
    }
    
     public List<NhanVien_thongKe> selectAll_Quy() {
        return selecBySQL(select_Top5_QuyHienTai);
    }

}
