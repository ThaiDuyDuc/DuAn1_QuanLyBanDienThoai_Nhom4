package Repository;

import Model.CPU;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Chip_DAO extends ServiceDAO<CPU, String> {

    final String insert_sql = "INSERT INTO CPU (MaCPU, TenHDH, CPU, GPU)  VALUES (?,?,?,?);";
    final String update_sql = "UPDATE CPU SET  TenHDH = ?, CPU = ?, GPU = ?  WHERE MaCPU = ? ";
    final String delete_sql = "DELETE FROM CPU WHERE MaCPU = ? ";
    final String select_All = "SELECT * FROM CPU";
    final String select_Search = "SELECT * FROM CPU WHERE MaCPU like ? ";

    @Override
    public void insert(CPU x) {
        JDBC.JDBCHelPer.update(insert_sql, x.getMaCPU(), x.getHeDieuHanh(), x.getCPU(), x.getGPU());
    }

    public CPU getSingleLichSuDienThoai(String Xoa) {
        List<CPU> danhSachKetQua = select_Search(Xoa);

        if (!danhSachKetQua.isEmpty()) {
            return danhSachKetQua.get(0);
        } else {
            return null;
        }
    }
    
    @Override
    public void update(CPU x) {
        JDBC.JDBCHelPer.update(insert_sql, x.getHeDieuHanh(), x.getCPU(), x.getGPU(), x.getMaCPU());
    }

    @Override
    public void delete(String id) {
        JDBC.JDBCHelPer.update(delete_sql, id);
    }

    @Override
    public List<CPU> selectAll() {
        return selecBySQL(select_All);
    }

    @Override
    public List<CPU> select_Search(String key) {
        return selecBySQL(select_Search, "%" + key + "%");
    }

    @Override
    public List<CPU> selecBySQL(String sql, Object... args) {
        ArrayList<CPU> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.JDBCHelPer.query(sql, args);
            while (rs.next()) {
                String macpu = rs.getString(1);
                String tenhdh = rs.getString(2);
                String cpu = rs.getString(3);
                String gpu = rs.getString(4);

                CPU o = new CPU(macpu, tenhdh, cpu, gpu);
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }

}
