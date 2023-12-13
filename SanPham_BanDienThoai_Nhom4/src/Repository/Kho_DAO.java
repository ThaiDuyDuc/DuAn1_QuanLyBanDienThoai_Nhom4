package Repository;

import Model.Kho;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Kho_DAO extends ServiceDAO<Kho, String>{

    final String insert_sql = "INSERT INTO Kho (MaKho, TenKho, DiaChiKho)  VALUES (?,?,?);";
    final String update_sql = "UPDATE Kho SET  TenKho = ?, DiaChi =? WHERE MaKho = ? ";
    final String delete_sql = "DELETE FROM Kho WHERE MaKho = ? ";
    final String select_All = "SELECT * FROM Kho";
    final String select_Search = "SELECT * FROM Kho WHERE MaKho like ? or TenKho like ? ";

    @Override
    public void insert(Kho x) {
        JDBC.JDBCHelPer.update(insert_sql, x.getMaKho(), x.getTenKho(), x.getDiaChi());
    }

     public Kho getSingleLichSuDienThoai(String Xoa) {
        List<Kho> danhSachKetQua = select_Search(Xoa);

        if (!danhSachKetQua.isEmpty()) {
            return danhSachKetQua.get(0);
        } else {
            return null;
        }
    }
    @Override
    public void update(Kho x) {
        JDBC.JDBCHelPer.update(update_sql, x.getTenKho(), x.getDiaChi(), x.getMaKho());
    }

    @Override
    public void delete(String id) {
        JDBC.JDBCHelPer.update(delete_sql, id);
    }

    @Override
    public List<Kho> selectAll() {
        return selecBySQL(select_All);
    }

    @Override
    public List<Kho> select_Search(String key) {
        return selecBySQL(select_Search, "%" + key + "%", "%" + key + "%");
    }

    @Override
    public List<Kho> selecBySQL(String sql, Object... args) {
         ArrayList<Kho> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.JDBCHelPer.query(sql, args);
            while (rs.next()) {
                String makho = rs.getString(1);
                String tenkho = rs.getString(2);
                String diachi = rs.getString(3);

                Kho o = new Kho(makho, tenkho, diachi);
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }

}
