/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import JDBC.JDBCUtil;
import JDBC.jdbchelper1;
import Model.HangKhachHang;
import Model.KhachHang;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Model.hoadon;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class qlhoadon extends EduSysDao<hoadon, Object>{

    String select_all = "select * from hoadon";
    String select_cc = "select top 1 * from hoadon ORDER BY mahoadon DESC";
    String insert_hd = "insert into HoaDon (MaHoaDon,NgayLap,MaKhachHang,MaNV,MaPhieuGiamGia,TrangThai) values (?,?,?,?,?,?)";
    String select_dtct = "select * from hoadon where mahoadon = ?";
    String delete = """
                    DECLARE @MaHoaDonToDelete NCHAR(20);
                    SET @MaHoaDonToDelete = ?;
                    DELETE FROM HoaDonChiTiet WHERE MaHoaDon = @MaHoaDonToDelete;
                    DELETE FROM HoaDon WHERE MaHoaDon = @MaHoaDonToDelete;""";
    String update = "update HoaDon set TongTien = ?,TongTienSauGG = ?,PhuongThucThanhToan = ?, ghichu = ?,trangthai = ? where MaHoaDon = ? ";
    String select_by_mahd = "select * from hoadon where mahoadon = ?";
    String select_kh = "select * from khachhang where makh = ?";
    @Override
    public void insert(hoadon entily) {
        jdbchelper1.update(insert_hd, entily.getMahd(),entily.getNgaylap(),entily.getMakhachhang(),entily.getManv(),entily.getMaphieugiam(),entily.getTrangthai());
    }
    public List<hoadon> select_macc(){
        return select_sql(select_cc);
    }
    public List<hoadon> get_khachhang( String mahd){
        return select_sql(select_by_mahd,mahd);
    }
    @Override
    public void update(hoadon entily) {
        jdbchelper1.update(update, entily.getTongtien(),entily.getTongtiensaugg(), entily.getPhuongthucthanhtoan(),entily.getGhichu(),entily.getTrangthai(),entily.getMahd());
    }

    @Override
    public void delete(Object id) {
        jdbchelper1.update(delete, id);
    }

    @Override
    public List<hoadon> selectAll() {
        return select_sql(select_all);
    }

    @Override
    public hoadon select_id(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public List<hoadon> select_mahdct(String mahoadon){
        return select_sql(select_dtct, mahoadon);
    }
    @Override
    public List<hoadon> select_sql(String sql, Object... args) {
        List<hoadon> list = new ArrayList<>();
        try{
            ResultSet rs = jdbchelper1.query(sql, args);
            while(rs.next()){
                hoadon hd = new hoadon();
                hd.setMahd(rs.getString("mahoadon"));
                hd.setMakhachhang(rs.getString("makhachhang"));
                hd.setManv(rs.getString("manv"));
                hd.setMaphieugiam(rs.getString("maphieugiamgia"));
                hd.setGhichu(rs.getString("ghichu"));
                hd.setNgaylap(rs.getString("ngaylap"));
                hd.setPhuongthucthanhtoan("phuongthucthanhtoan");
                try{
                    hd.setTongtien(Float.parseFloat(rs.getString("tongtien")));
                    hd.setTongtiensaugg(Float.parseFloat(rs.getString("tongtiensaugg")));
                }
                catch(Exception e){
                    
                }
                hd.setTrangthai(rs.getString("trangthai"));
                list.add(hd);
                
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    private ArrayList<KhachHang> getListKH(String sql,Object... args) {
        ArrayList<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = jdbchelper1.query(sql, args);
            while(rs.next()){
                //KhachHang
                String maKH = rs.getString("MaKH");
                String hoTen = rs.getString("HoTen");
                boolean gioiTinh = rs.getBoolean("GioiTinh");
                Date ngaySinh = rs.getDate("NgaySinh");
                String email = rs.getString("Email");
                String SDT = rs.getString("SDT");
                String anhDaiDien = rs.getString("AnhDaiDien");
                String ghiChu = rs.getString("GhiChu");
                boolean trangThai = rs.getBoolean("TrangThai");
                int maHang = rs.getInt("MaHang");

                HangKhachHang hangKhachHang = new HangKHDAO().getByID(maHang);
                KhachHang kh = new KhachHang(maKH, hoTen, gioiTinh, ngaySinh, email, SDT, anhDaiDien, 0,0, ghiChu, trangThai, hangKhachHang);
                list.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<KhachHang> get_kh_by_makh(String makh){
        return getListKH(select_kh, makh);
    }
}
