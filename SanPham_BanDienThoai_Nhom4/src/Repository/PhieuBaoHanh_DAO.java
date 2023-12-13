package Repository;

import Model.PhieuBaoHanh;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class PhieuBaoHanh_DAO extends ServiceDAO<PhieuBaoHanh, String>{

    final String insert_sql = "INSERT INTO PhieuBaoHanh (MaPhieuBaoHanh, NgayMua, NgayBatDauBaoHanh,NgayKetThuc, TrangThai, GhiChu)  VALUES (?,?,?,?, ?, ?);";
    final String update_sql = "UPDATE PhieuBaoHanh SET  NgayMua = ?, NgayBatDauBaoHanh =?,NgayKetThuc = ?, TrangThai = ?, GhiChu = ? WHERE MaPhieuBaoHanh = ? ";
    final String delete_sql = "DELETE FROM PhieuBaoHanh WHERE MaPhieuBaoHanh = ? ";
    final String select_All = "SELECT * FROM PhieuBaoHanh";
    final String select_Search = "SELECT * FROM PhieuBaoHanh WHERE MaPhieuBaoHanh like ? or NgayMua like ? or NgayBatDauBaoHanh like ? or NgayKetThuc like ? ";

    @Override
    public void insert(PhieuBaoHanh x) {
        JDBC.JDBCHelPer.update(insert_sql, x.getMaPhieuBaoHanh(), x.getNgayMua(), x.getNgayBatDauBaoHanh(), x.getNgayKetThuc(), x.isTrangThai(), x.getGhiChu());
    }

    @Override
    public void update(PhieuBaoHanh x) {
        JDBC.JDBCHelPer.update(update_sql, x.getNgayMua(), x.getNgayBatDauBaoHanh(), x.getNgayKetThuc(), x.isTrangThai(), x.getGhiChu(), x.getMaPhieuBaoHanh());
    }

    @Override
    public void delete(String id) {
        JDBC.JDBCHelPer.update(delete_sql, id);
    }

    @Override
    public List<PhieuBaoHanh> selectAll() {
        return selecBySQL(select_All);
    }

    @Override
    public List<PhieuBaoHanh> select_Search(String key) {
        return selecBySQL(select_Search, "%" + key + "%", "%" + key + "%", "%" + key + "%", "%" + key + "%");
    }

    @Override
    public List<PhieuBaoHanh> selecBySQL(String sql, Object... args) {
        ArrayList<PhieuBaoHanh> list = new ArrayList<>();
        try  {
            ResultSet rs = JDBC.JDBCHelPer.query(sql, args);
            while (rs.next()) {
                String mapbh = rs.getString(1);
                Date ngaymua = rs.getDate(2);
                Date ngaybd = rs.getDate(3);
                Date ngaykt = rs.getDate(4);
                boolean trangthai = rs.getBoolean(5);
                String ghichu = rs.getString(6);

                PhieuBaoHanh o = new PhieuBaoHanh(mapbh, ngaymua, ngaybd, ngaykt, trangthai, ghichu);
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }


}
