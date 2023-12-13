/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import JDBC.jdbchelper1;
import Model.HoaDonChiTiet;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class HoadonchotietDao extends EduSysDao<HoaDonChiTiet, Object>{
    String select_by_mahd = "select * from hoadonchitiet where mahoadon = ?";
    String insert  = "insert into hoadonchitiet(MaHoaDon,MaDTCT,SoLuong,DonGia,ThanhTien,GhiChu) values (?,?,?,?,?,?)";
    String update = "update HoaDonChiTiet set SoLuong = ? where MaDTCT = ?";
    String delete = "delete HoaDonChiTiet where madtct = ?";
    String select_by_madtct = "select * from hoadonchitiet where madtct = ?";
    @Override
    public void insert(HoaDonChiTiet entily) {
        jdbchelper1.update(insert, entily.getMaHoaDon(),entily.getMaDTCT(),entily.getSoLuong(),entily.getDonGia(),entily.getThanhTien(),entily.getGhiChu());
    }
    public List<HoaDonChiTiet> select_sldt(String madtct){
        return select_sql(select_by_madtct, madtct);
    }
    @Override
    public void update(HoaDonChiTiet entily) {
        jdbchelper1.update(update, entily.getSoLuong(),entily.getMaDTCT());
    }

    @Override
    public void delete(Object id) {
        jdbchelper1.update(delete, id);
    }
    public List<HoaDonChiTiet> select_hdct(String mahd){
        return select_sql(select_by_mahd, mahd);
    }
    @Override
    public List<HoaDonChiTiet> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoaDonChiTiet select_id(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HoaDonChiTiet> select_sql(String sql, Object... args) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        try{
            ResultSet rs = jdbchelper1.query(sql, args);
            while(rs.next()){
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setMaHoaDon(rs.getString("mahoadon"));
                hdct.setMaDTCT(rs.getString("madtct"));
                hdct.setSoLuong(Integer.parseInt(rs.getString("soluong")));
                hdct.setDonGia(rs.getString("dongia"));
                hdct.setThanhTien(rs.getString("thanhtien"));
                hdct.setGhiChu(rs.getString("ghichu"));
                list.add(hdct);
                
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
}
