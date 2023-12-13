/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import JDBC.jdbchelper1;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Model.model_dieukienapdung;
import Model.phieugiamgia1;


/**
 *
 * @author ADMIN
 */
public class qlphieugiamgia extends EduSysDao<phieugiamgia1, String> {
    String Insert_sql ="INSERT INTO PhieuGiamGia(MaPhieuGiamGia,MaNhanVien,MaDieuKienGiamGia,TenPhieu,LoaiGiamGia,Giatri,NgayTao,NgayBatDau,NgayHetHan,MoTa,SoLanSuDung,hang) VALUES (?, 'NV001', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String update_sql ="update PhieuGiamGia set MaNhanVien = 'NV001', MaDieuKienGiamGia = ?, TenPhieu = ?, LoaiGiamGia = ?, Giatri = ?, NgayTao = ?, NgayBatDau = ?, NgayHetHan = ?, MoTa = ?, SoLanSuDung = ?, hang = ? where MaPhieuGiamGia = ?";
    String delete_sql = """
                        DECLARE @MaPhieuGiamGia VARCHAR(255);
                        SELECT @MaPhieuGiamGia = MaPhieuGiamGia
                        FROM PhieuGiamGia
                        WHERE MaPhieuGiamGia = ?;
                        DELETE FROM HoaDonChiTiet WHERE MaHoaDon IN (SELECT MaHoaDon FROM HoaDon WHERE MaPhieuGiamGia = @MaPhieuGiamGia);
                        DELETE FROM HoaDon WHERE MaPhieuGiamGia = @MaPhieuGiamGia;
                        DELETE FROM PhieuGiamGia WHERE MaPhieuGiamGia = @MaPhieuGiamGia;""";
    
    String select_all = """
                        select * from PhieuGiamGia 
                        inner join DieuKienApDung
                        on PhieuGiamGia.MaDieuKienGiamGia = DieuKienApDung.MaDieuKienGiamGia
                        inner join HangKhachHang
                        on HangKhachHang.MaHang = DieuKienApDung.MaHangKhachHang""";
    String select_count = """
                        select top 1 * from PhieuGiamGia 
                        inner join DieuKienApDung
                        on PhieuGiamGia.MaDieuKienGiamGia = DieuKienApDung.MaDieuKienGiamGia
                        inner join HangKhachHang
                        on HangKhachHang.MaHang = DieuKienApDung.MaHangKhachHang
                        ORDER BY MaPhieuGiamGia DESC""";
    String select_dkad = "select * from DieuKienApDung where GiaTriDonHangToiThieu = ? and SoLuongSanPhamToiThieu = ?";
    String select_madkad = "select * from phieugiamgia where MaPhieuGiamGia = ?";
    
    @Override
    public void insert(phieugiamgia1 entily) {
        jdbchelper1.update(Insert_sql, entily.getMakm(),entily.getMaDieuKienGiamGia(),entily.getTenkhm(),entily.getLoaigiamgia(),entily.getGiatri(),entily.getNgaytao(),entily.getNgaybatdau(),entily.getNgayketthuc(),entily.getMota(),entily.getSolanSudung(),entily.getHang());
    }

    @Override
    public void update(phieugiamgia1 entily) {
        jdbchelper1.update(update_sql,entily.getMaDieuKienGiamGia(),entily.getTenkhm(),entily.getLoaigiamgia(),entily.getGiatri(),entily.getNgaytao(),entily.getNgaybatdau(),entily.getNgayketthuc(),entily.getMota(),entily.getSolanSudung(),entily.getHang(),entily.getMakm());
    }

    @Override
    public void delete(String id) {
        jdbchelper1.update(delete_sql, id);
    }

    @Override
    public List<phieugiamgia1> selectAll() {
        return select_sql(select_all);
    }
    public List<phieugiamgia1> select_madkad(String mapgg) {
        return select_sql(select_madkad,mapgg);
    }
    public List<phieugiamgia1> select_id() {
        return select_sql(select_count);
    }
    
    public model_dieukienapdung select_dkgg(String gtr, String sl) {
        List<model_dieukienapdung> list = new ArrayList<>();
        try{
            ResultSet rs = jdbchelper1.query(select_dkad, gtr,sl);
            while(rs.next()){
                model_dieukienapdung dkad = new model_dieukienapdung();
                dkad.setMadk(rs.getString("MaDieuKienGiamGia"));
                dkad.setGtrsl(rs.getString("GiaTriDonHangToiThieu"));
                dkad.setSl(rs.getString("SoLuongSanPhamToiThieu"));
                dkad.setMakh(rs.getString("MaHangKhachHang"));
//                dkad.setMadt(rs.getString("MaDT"));
                list.add(dkad);
                
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list.get(0);
    }
    @Override
    public phieugiamgia1 select_id(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public List<phieugiamgia1> select_sql(String sql, Object... args) {
        List<phieugiamgia1> list = new ArrayList<>();
        try{
            ResultSet rs = jdbchelper1.query(sql, args);
            while(rs.next()){
                phieugiamgia1 pgg = new phieugiamgia1();
                pgg.setMakm(rs.getString("MaPhieuGiamGia"));
                pgg.setMaDieuKienGiamGia(rs.getString("MaDieuKienGiamGia"));
                pgg.setTenkhm(rs.getString("TenPhieu"));
                pgg.setNgaybatdau(rs.getString("NgayBatDau"));
                pgg.setNgaytao(rs.getString("Ngaytao"));
                pgg.setNgayketthuc(rs.getString("NgayHetHan"));
                pgg.setMota(rs.getString("MoTa"));
                pgg.setSolanSudung(Integer.parseInt(rs.getString("SoLanSuDung")));
                pgg.setGiatri(rs.getString("giatri"));
                pgg.setHang(rs.getString("hang"));
                list.add(pgg);
                
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
