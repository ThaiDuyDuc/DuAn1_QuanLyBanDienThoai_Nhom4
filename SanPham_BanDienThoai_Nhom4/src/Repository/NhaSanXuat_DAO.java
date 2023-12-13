package Repository;

import Model.NhaSanXuat;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhaSanXuat_DAO extends ServiceDAO<NhaSanXuat, String>{

    final String insert_sql = "INSERT INTO NhaSanXuat (MaNSX, TenNSX, QuocGia,DiaChi, Website, Email, SDT)  VALUES (?,?,?,?, ?, ?, ?);";
    final String update_sql = "UPDATE NhaSanXuat SET  TenNSX = ?, QuocGia = ? ,DiaChi = ?, Website = ?, Email = ?, SDT = ? WHERE MaNSX = ? ";
    final String delete_sql = "DELETE FROM NhaSanXuat WHERE MaNSX = ? ";
    final String select_All = "SELECT * FROM NhaSanXuat";
    final String select_Search = "SELECT * FROM NhaSanXuat WHERE MaNSX = ? ";
    
    @Override
    public void insert(NhaSanXuat x) {
        JDBC.JDBCHelPer.update(insert_sql, x.getMaNSX(), x.getTenNSX(), x.getQuocGia(), x.getDiaChi(), x.getWebsite(), x.getEmail(), x.getSDT());
    }

    @Override
    public void update(NhaSanXuat x) {
        JDBC.JDBCHelPer.update(insert_sql, x.getTenNSX(), x.getQuocGia(), x.getDiaChi(), x.getWebsite(), x.getEmail(), x.getSDT(), x.getMaNSX());
    }

    @Override
    public void delete(String id) {
        JDBC.JDBCHelPer.update(delete_sql, id);
    }

    @Override
    public List<NhaSanXuat> selectAll() {
        return selecBySQL(select_All);
    }

    @Override
    public List<NhaSanXuat> select_Search(String key) {
        return selecBySQL(select_Search, "%" + key + "%");
    }

    @Override
    public List<NhaSanXuat> selecBySQL(String sql, Object... args) {
        ArrayList<NhaSanXuat> list = new ArrayList<>();
        try {
             ResultSet rs = JDBC.JDBCHelPer.query(sql, args);
             while(rs.next()){
                 String mansx = rs.getString(1);
                 String ten = rs.getString(2);
                 String quocgia = rs.getString(3);
                 String diachi = rs.getString(4);
                 String web = rs.getString(5);
                 String email = rs.getString(6);
                 String sdt = rs.getString(7);
                 
                 NhaSanXuat o = new NhaSanXuat(mansx, ten, quocgia, diachi, web, email, sdt);
                 list.add(o);
             }
        }  catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }

   
    
}
