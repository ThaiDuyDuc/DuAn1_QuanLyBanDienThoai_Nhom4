package Repository;

import Model.LichSuDienThoai_ChiTiet;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import javax.crypto.AEADBadTagException;

public class LichSuDienThoai_ChiTiet_DAO extends ServiceDAO<LichSuDienThoai_ChiTiet, String>{

    final String delete_sql = "DELETE FROM LichSuDienThoaiChiTiet WHERE MaDTCT = ? ";
    final String select_All = "SELECT * FROM LichSuDienThoaiChiTiet";
    final String select_Search = "SELECT * FROM LichSuDienThoaiChiTiet WHERE MaDTCT = ? ";
    final String insert_sql = "{call KhoiPhucDienThoaiChiTiet(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
    
    public LichSuDienThoai_ChiTiet getSingleLichSuDienThoai(String khoiphuc) {
        List<LichSuDienThoai_ChiTiet> danhSachKetQua = select_Search(khoiphuc);
        if (!danhSachKetQua.isEmpty()) {
            return danhSachKetQua.get(0);
        } else {
            return null;
        }
    }
    
    @Override
    public void insert(LichSuDienThoai_ChiTiet x) {
        JDBC.JDBCHelPer.update(insert_sql, x.getMaDTCT(), x.getMaDT(), x.getGiaBan(), x.getHinhAnh(), x.getMauSac(), x.getSoLuongTonKho(), x.getMaBoNho(),x.getMaKho() ,x.getMaNSX(), x.getMaPhieuBaoHanh(),x.isTrangThai(), x.getMoTa(), x.getThoigianxoa());
    }

    @Override
    public void update(LichSuDienThoai_ChiTiet x) {

    }
    
    @Override
    public void delete(String id) {
        JDBC.JDBCHelPer.update(delete_sql, id);
    }

    @Override
    public List<LichSuDienThoai_ChiTiet> selectAll() {
        return selecBySQL(select_All);
    }

    @Override
    public List<LichSuDienThoai_ChiTiet> select_Search(String key) {
        return selecBySQL(select_Search, key);
    }

    @Override
    public List<LichSuDienThoai_ChiTiet> selecBySQL(String sql, Object... args) {
         ArrayList<LichSuDienThoai_ChiTiet> list = new ArrayList<>();
        
        try {
            ResultSet rs = JDBC.JDBCHelPer.query(sql, args);
            while (rs.next()) {
                LichSuDienThoai_ChiTiet o =new LichSuDienThoai_ChiTiet();
                o.setMaDTCT(rs.getString("MaDTCT"));
                o.setMaDT(rs.getString("MaDT"));
                o.setGiaBan(rs.getBigDecimal("GiaBan"));
                o.setHinhAnh(rs.getString("HinhAnh"));
                o.setMauSac(rs.getString("MauSac"));
                o.setSoLuongTonKho(rs.getInt("SLTonKho"));
                o.setMaBoNho(rs.getString("MaBoNho"));
                o.setMaKho(rs.getString("MaKho"));
                o.setMaNSX(rs.getString("MaNSX"));
                o.setMaPhieuBaoHanh(rs.getString("MaPhieuBaoHanh"));
                o.setTrangThai(rs.getBoolean("TrangThai"));
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
