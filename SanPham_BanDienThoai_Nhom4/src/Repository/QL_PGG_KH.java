package Repository;

import java.sql.ResultSet;
import JDBC.jdbchelper1;
import java.util.ArrayList;
import java.util.List;
import Model.PGG_KH;

/**
 *
 * @author ADMIN
 */
public class QL_PGG_KH extends EduSysDao<PGG_KH, Object> {

    String select_mapgg = """
                          select ma_khachhang,Ma_PhieuGiamGia,giatri,ngayhethan  from PHIEUGIAM_KHACHHANG
                          where Ma_PhieuGiamGia = ?
                          """;
    String select_mapgg_makh = """
                          select ma_khachhang,Ma_PhieuGiamGia,giatri,ngayhethan from PHIEUGIAM_KHACHHANG
                          """;
    String insert = """
                    IF NOT EXISTS (
                        SELECT 1
                        FROM PHIEUGIAM_KHACHHANG
                        WHERE Ma_KhachHang = ?
                        AND Ma_PhieuGiamGia = ?
                    )
                    BEGIN
                        INSERT INTO PHIEUGIAM_KHACHHANG (Ma_KhachHang, Ma_PhieuGiamGia, Giatri, NgayTao, NgayBatDau, NgayHetHan)
                        VALUES (?, ?, ?, ?, ?, ?);
                    END""";
    String delete = "delete PHIEUGIAM_KHACHHANG where Ma_KhachHang = ? and Ma_PhieuGiamGia = ?";
    String select_phieu = "select ma_khachhang,Ma_PhieuGiamGia,Giatri,ngayhethan from PHIEUGIAM_KHACHHANG where Ma_KhachHang = ?";

    @Override
    public void insert(PGG_KH entily) {
        jdbchelper1.update(insert, entily.getMakh(), entily.getMapgg(), entily.getMakh(), entily.getMapgg(), entily.getGtr(), entily.getNgaytao(), entily.getNgaybatdau(), entily.getNgaykth());
    }

    @Override
    public void update(PGG_KH entily) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PGG_KH> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<PGG_KH> select_mapgg(String ma) {
        return select_sql(select_mapgg, ma);
    }

    public List<PGG_KH> select_mapgg_makh() {
        return select_sql(select_mapgg_makh);
    }

    public List<PGG_KH> select_pggcuakh(String makh) {
        return select_sql(select_phieu, makh);
    }

    @Override
    public PGG_KH select_id(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void delete_double(String makh, String mapgg) {
        jdbchelper1.update(delete, makh, mapgg);
    }

    @Override
    public List<PGG_KH> select_sql(String sql, Object... args) {
        List<PGG_KH> list = new ArrayList<>();
        try {
            ResultSet rs = jdbchelper1.query(sql, args);
            while (rs.next()) {
                PGG_KH pgg = new PGG_KH();
                pgg.setMakh(rs.getString("Ma_khachhang"));
                pgg.setMapgg(rs.getString("Ma_PhieuGiamGia"));
                pgg.setGtr(rs.getString("giatri"));
//                pgg.setMota(rs.getString("mota"));
//                pgg.setNgaytao(rs.getString("ngaytao"));
//                pgg.setNgaybatdau(rs.getString("ngaybatdau"));
                pgg.setNgaykth(rs.getString("ngayhethan"));
                list.add(pgg);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
