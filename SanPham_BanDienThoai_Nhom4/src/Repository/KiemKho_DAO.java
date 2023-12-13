package Repository;

import Model.BoNho;
import Model.CPU;
import Model.Kho;
import Model.KiemKho;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KiemKho_DAO extends ServiceDAO<KiemKho, String>{

    final String select_All = "SELECT a.MaDT, a.TenDienThoai, a.ThuongHieu, a.HeDieuHanh, b.MauSac, b.GiaBan, a.MaCPU, b.MaBonho, b.MaKho, b.SLTonKho  FROM DienThoai a JOIN DienThoaiChiTiet b on a.MaDT= b.MaDT ORDER BY b.SLTonKho  ASC;";
    final String sql_Seach = "SELECT a.MaDT, a.TenDienThoai, a.ThuongHieu, a.HeDieuHanh, b.MauSac, b.GiaBan, a.MaCPU, b.MaBonho, b.MaKho, b.SLTonKho  FROM DienThoai a JOIN DienThoaiChiTiet b on a.MaDT= b.MaDT WHERE MaDT = ?";
    @Override
    public void insert(KiemKho x) {
        
    }

    @Override
    public void update(KiemKho x) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<KiemKho> selectAll() {
        return selecBySQL(select_All);
    }

    @Override
    public List<KiemKho> select_Search(String key) {
        return selecBySQL(sql_Seach, key);
    }

    @Override
    public List<KiemKho> selecBySQL(String sql, Object... args) {
        List<KiemKho> list = new ArrayList<>();
       
        try {
            ResultSet rs = JDBC.JDBCHelPer.query(sql, args);
            while (rs.next()) {
                String cpu = rs.getString("MaCPU");
                String bn = rs.getString("MaBoNho");
                String k = rs.getString("MaKho");
                
                
                String madt = rs.getString("MaDT");
                String ten = rs.getString("TenDienThoai");
                String thuonghieu = rs.getString("ThuongHieu");
                String hdh = rs.getString("HeDieuHanh");
                String mau = rs.getString("MauSac");
                BigDecimal gia = rs.getBigDecimal("GiaBan");
                CPU chip = new Chip_DAO().getSingleLichSuDienThoai(cpu);
                BoNho bonho = new BoNho_DAO().getSingleLichSuDienThoai(bn);
                Kho kho = new Kho_DAO().getSingleLichSuDienThoai(k);
                int slton = rs.getInt("SLTonKho");
                
                KiemKho o = new KiemKho(madt, ten, thuonghieu, hdh, mau, gia, chip, bonho, kho, slton);
                list.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KiemKho_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
