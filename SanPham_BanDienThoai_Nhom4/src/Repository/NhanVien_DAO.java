package Repository;

import Model.NhanVien;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class NhanVien_DAO extends ServiceDAO<NhanVien, String> {

    final String insert_sql = "";
    final String update_sql = "";
    final String delete_sql = "";
    final String select_All = "SELECT * FROM NhanVien";
    final String select_Bay = "SELECT TOP 5 NV.MaNV, NV.TenNV, SUM(HD.TongTienSauGG) AS TongDoanhSo FROM NhanVien NV JOIN HoaDon HD ON NV.MaNV = HD.MaNV "
            + "WHERE HD.NgayLap >= DATEADD(DAY, -7, GETDATE())  -- Lấy 7 ngày gần nhất \n"
            + "GROUP BY NV.MaNV, NV.TenNV ORDER BY TongDoanhSo DESC;";
    final String select_Search = "SELECT * FROM NhanVien WHERE MaNV = ? ";

    @Override
    public void insert(NhanVien x) {

    }

    @Override
    public void update(NhanVien x) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<NhanVien> selectAll() {
        return selecBySQL(select_All);
    }

     public NhanVien getSingleLichSuDienThoai(String Xoa) {
        List<NhanVien> danhSachKetQua = select_Search(Xoa);

        if (!danhSachKetQua.isEmpty()) {
            return danhSachKetQua.get(0);
        } else {
            return null;
        }
    }
     
    @Override
    public List<NhanVien> select_Search(String key) {
        return selecBySQL(select_Search, key);
    }

    @Override
    public List<NhanVien> selecBySQL(String sql, Object... args) {
        ArrayList<NhanVien> list = new ArrayList<>();

        try {
            ResultSet rs = JDBC.JDBCHelPer.query(sql, args);
            while (rs.next()) {
                String ma = rs.getString(1);
                String ten = rs.getString(2);
                String dc = rs.getString(3);
                String ns = rs.getString(4);
                String sdt = rs.getString(5);
                String anh = rs.getString(6);
                String gt = rs.getString(7);
                String mk = rs.getString(8);
                String nt = rs.getString(9);
                String tt = rs.getString(10);
                String email = rs.getString(11);
                String cv = rs.getString(12);

                NhanVien o = new NhanVien(ma, ten, dc, ns, anh, sdt, gt, mk, nt, tt, email, cv);
                list.add(o);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }

    public List<NhanVien> selectAll_Bay() {
        return selecBySQL(select_Bay);
    }
}
