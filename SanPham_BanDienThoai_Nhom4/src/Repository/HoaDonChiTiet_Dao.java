package Repository;

import JDBC.jdbchelper1;
import Model.HoaDonChiTiet;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class HoaDonChiTiet_Dao extends EduSysDao<HoaDonChiTiet, Object>{
    String select_by_mahd = "select * from hoadonchitiet where mahoadon = ?";
    String insert  = "insert into hoadonchitiet(MaHoaDon,MaDTCT,SoLuong,DonGia,ThanhTien,GhiChu) values (?,?,?,?,?,?)";
    String select_All = "select * from HoaDonChiTiet";
    String select_search = "select * from HoaDonChiTiet where MaHoaDon = ?";
    
    @Override
    public void insert(HoaDonChiTiet entily) {
        jdbchelper1.update(insert, entily.getMaHoaDon(),entily.getMaDTCT(),entily.getSoLuong(),entily.getDonGia(),entily.getThanhTien(),entily.getGhiChu());
    }
    
    @Override
    public void update(HoaDonChiTiet entily) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public List<HoaDonChiTiet> select_hdct(String mahd){
        return select_sql(select_by_mahd, mahd);
    }
    @Override
    public List<HoaDonChiTiet> selectAll() {
        return select_sql(select_All);
    }

    @Override
    public HoaDonChiTiet select_id(Object id) {
        return null;
    }
    
    public List<HoaDonChiTiet> select_search(String key) {
        return select_sql(select_search, key);
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
