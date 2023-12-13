/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

/**
 *
 * @author ADMIN
 */

import Model.model_dieukienapdung;
import JDBC.jdbchelper1;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class qldieukienapdung extends EduSysDao<model_dieukienapdung, String>{
    String Insert_sql ="INSERT INTO DieuKienApDung (MaDieuKienGiamGia,GiaTriDonHangToiThieu,MaHangKhachHang,soluongtoithieu,loaidieukien) VALUES (?, ?, ?,?,?)";
    String update_sql ="update DieuKienApDung set GiaTriDonHangToiThieu = ?,soluongtoithieu = ?,loaidieukien = ?,MaHangKhachHang = ? where MaDieuKienGiamGia = ?";
    String delete_sql = """
                        DECLARE @madk VARCHAR(255);
                        SET @madk = ?
                        delete PHIEUGIAM_KHACHHANG where Ma_PhieuGiamGia in (
                        select MaPhieuGiamGia from PhieuGiamGia
                        where MaDieuKienGiamGia = @madk
                        )
                        DELETE FROM HoaDonChiTiet WHERE MaHoaDon IN (SELECT MaHoaDon FROM HoaDon WHERE MaPhieuGiamGia IN (SELECT MaPhieuGiamGia FROM PhieuGiamGia WHERE MaDieuKienGiamGia = @madk));
                        DELETE FROM HoaDon WHERE MaPhieuGiamGia IN (SELECT MaPhieuGiamGia FROM PhieuGiamGia WHERE MaDieuKienGiamGia = @madk);
                        DELETE FROM PhieuGiamGia WHERE MaDieuKienGiamGia = @madk;
                        DELETE FROM DieuKienApDung WHERE MaDieuKienGiamGia = @madk;
                        """;
    String select_all = """
                        select * from DieuKienApDung
                        inner join HangKhachHang 
                        on DieuKienApDung.MaHangKhachHang = HangKhachHang.MaHang
                        """;
    String select_mahang = "select MaHang from HangKhachHang";
    String select_madt = "select MaDT from DienThoai";
    String select_count = """
                          select top 1 * from DieuKienApDung
                          inner join HangKhachHang 
                          on DieuKienApDung.MaHangKhachHang = HangKhachHang.MaHang
                          ORDER BY DieuKienApDung.MaDieuKienGiamGia DESC
                          """;
    String select_giatri = """
                            select * from DieuKienApDung
                            inner join HangKhachHang 
                            on DieuKienApDung.MaHangKhachHang = HangKhachHang.MaHang where DieuKienApDung.MaDieuKienGiamGia = ?
                           """;
    @Override
    public void insert(model_dieukienapdung entily) {
        jdbchelper1.update(Insert_sql, entily.getMadk(),entily.getGtrsl(),entily.getMakh(),entily.getSl(),entily.getLoaidk());
    }

    @Override
    public void update(model_dieukienapdung entily) {
        jdbchelper1.update(update_sql,entily.getGtrsl(),entily.getSl(),entily.getLoaidk(),entily.getMakh(), entily.getMadk());
    }

    @Override
    public void delete(String id) {
        jdbchelper1.update(delete_sql, id);
    }

    @Override
    public List<model_dieukienapdung> selectAll() {
        return select_sql(select_all);
    }
    public List<model_dieukienapdung> select_id() {
        return select_sql(select_count);
    }
    public List<model_dieukienapdung> select_gtr(String madkgiam) {
        return select_sql(select_giatri,madkgiam);
    }
    public List<String> select_tenhang(){
        List<String> list = new ArrayList<>();
        ResultSet rs = jdbchelper1.query(select_all );
        try{
            while(rs.next()){
                list.add(rs.getString("tenhang"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public List<String> select_mahang() {
        List<String> list = new ArrayList<>();
        ResultSet rs = jdbchelper1.query(select_mahang );
        try{
            while(rs.next()){
                list.add(rs.getString("MaHang"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public List<String> select_madt() {
        List<String> list = new ArrayList<>();
        ResultSet rs = jdbchelper1.query(select_madt );
        try{
            while(rs.next()){
                list.add(rs.getString("madt"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public model_dieukienapdung select_id(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<model_dieukienapdung> select_sql(String sql, Object... args) {
        List<model_dieukienapdung> list = new ArrayList<>();
        try{
            ResultSet rs = jdbchelper1.query(sql, args);
            while(rs.next()){
                model_dieukienapdung dkad = new model_dieukienapdung();
                dkad.setMadk(rs.getString("MaDieuKienGiamGia"));
                dkad.setGtrsl(rs.getString("GiaTriDonHangToiThieu"));
                dkad.setMakh(rs.getString("MaHangKhachHang"));
                dkad.setSl(rs.getString("soluongtoithieu"));
                dkad.setLoaidk(rs.getString("loaidieukien"));
//                dkad.setMadt(rs.getString("MaDT"));
                dkad.setHang(rs.getString("tenhang"));
                list.add(dkad);
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    
}
