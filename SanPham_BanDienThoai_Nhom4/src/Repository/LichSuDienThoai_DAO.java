package Repository;

import Model.LichSuDienThoai;
import Model.DienThoai;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class LichSuDienThoai_DAO extends ServiceDAO<LichSuDienThoai, String> {

    final String delete_sql = "DELETE FROM LichSuDienThoai WHERE MaDT = ? ";
    final String select_All = "SELECT * FROM LichSuDienThoai";
    final String select_Search = "SELECT * FROM LichSuDienThoai WHERE MaDT = ? ";
    final String insert_sql = "{call KhoiPhucDienThoai(?,?,?,?,?,?,?,?,?,?,?,?)}";
    final String delete_insert = "{call XoaDT_Save_LSDT(?,?,?,?,?,?,?,?,?,?,?)}";

    @Override
    public void insert(LichSuDienThoai x) {
        JDBC.JDBCHelPer.update(insert_sql, x.getMaDT(), x.getTenDienThoai(), x.getThuongHieu(), x.getMaCamera(), x.getMaKetNoi(), x.getMaPinSac(), x.getHeDieuHanh(), x.getMaCPU(), x.getMaTienIch(), x.getManHinh(), x.getMoTa(), x.getThoigianxoa());
    }

    public LichSuDienThoai getSingleLichSuDienThoai(String khoiphuc) {
        List<LichSuDienThoai> danhSachKetQua = select_Search(khoiphuc);

        if (!danhSachKetQua.isEmpty()) {
            return danhSachKetQua.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void update(LichSuDienThoai x) {

    }

    @Override
    public void delete(String id) {
        JDBC.JDBCHelPer.update(delete_sql, id);
    }

    @Override
    public List<LichSuDienThoai> selectAll() {
        return selecBySQL(select_All);
    }

    @Override
    public List<LichSuDienThoai> select_Search(String key) {
        return selecBySQL(select_Search, key);
    }

    @Override
    public List<LichSuDienThoai> selecBySQL(String sql, Object... args) {
        ArrayList<LichSuDienThoai> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.JDBCHelPer.query(sql, args);
            while (rs.next()) {
                LichSuDienThoai o = new LichSuDienThoai();
                o.setMaDT(rs.getString("MaDT"));
                o.setTenDienThoai(rs.getString("TenDienThoai"));
                o.setThuongHieu(rs.getString("ThuongHieu"));
                o.setMaCamera(rs.getString("MaCamera"));
                o.setMaKetNoi(rs.getString("MaKetNoi"));
                o.setMaPinSac(rs.getString("MaPin_Sac"));
                o.setHeDieuHanh(rs.getString("HeDieuHanh"));
                o.setMaCPU(rs.getString("MaCPU"));
                o.setMaTienIch(rs.getString("MaTienIch"));
                o.setManHinh(rs.getString("MaManHinh"));
                o.setMoTa(rs.getString("MoTa"));
                o.setThoigianxoa(rs.getTimestamp("NgayXoa"));

                list.add(o);

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }
}
