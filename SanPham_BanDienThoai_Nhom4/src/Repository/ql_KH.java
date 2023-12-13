/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import JDBC.jdbchelper1;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Model.Khachhang_phieugg;
import Model.phieugiamgia1;

/**
 *
 * @author ADMIN
 */
public class ql_KH extends EduSysDao<Khachhang_phieugg, Object>{
    String select_all = """
                        select*from khachhang
                        inner join HangKhachHang
                        on KhachHang.MaHang = HangKhachHang.MaHang
                        """;
    String select_kh_rank = """
                            select*from khachhang
                            inner join HangKhachHang
                            on KhachHang.MaHang = HangKhachHang.MaHang
                            where HangKhachHang.TenHang = ?""";
    @Override
    public void insert(Khachhang_phieugg entily) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Khachhang_phieugg entily) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Khachhang_phieugg> selectAll() {
        return select_sql(select_all);
    }

    @Override
    public Khachhang_phieugg select_id(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public List<Khachhang_phieugg> select_KH_rank( String hang) {
        return select_sql(select_kh_rank, hang);
    }
    @Override
    public List<Khachhang_phieugg> select_sql(String sql, Object... args) {
        List<Khachhang_phieugg> list = new ArrayList<>();
        try{
            ResultSet rs = jdbchelper1.query(sql, args);
            while(rs.next()){
                Khachhang_phieugg pgg = new Khachhang_phieugg();
                pgg.setMakh(rs.getString("Makh"));
                pgg.setTenkh(rs.getString("hoten"));
                pgg.setEmail(rs.getString("Email"));
                pgg.setSdt(rs.getString("SDT"));
                list.add(pgg);
                
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
}
