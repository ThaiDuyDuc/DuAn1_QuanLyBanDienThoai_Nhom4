package Repository;

import Model.TienIch;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class TienIch_DAO extends ServiceDAO<TienIch, String>{

    final String insert_sql = "INSERT INTO TienIch (MaTienIch, BaoMat, TinhNangDacBiet, KhangNuoc_Bui)  VALUES (?,?,?,?);";
    final String update_sql = "UPDATE TienIch SET  BaoMat = ?, TinhNangDacBiet = ?, KhangNuoc_Bui = ?  WHERE MaTienIch = ? ";
    final String delete_sql = "DELETE FROM TienIch WHERE MaTienIch = ? ";
    final String select_All = "SELECT * FROM TienIch";
    final String select_Search = "SELECT * FROM TienIch WHERE MaTienIch = ? ";
    
    @Override
    public void insert(TienIch x) {
        JDBC.JDBCHelPer.update(insert_sql, x.getMaTienIch(), x.getBaoMat(), x.getTinhNangDacBiet(), x.getKhangNuoc_Bui());
    }

    @Override
    public void update(TienIch x) {
        JDBC.JDBCHelPer.update(insert_sql, x.getBaoMat(), x.getTinhNangDacBiet(), x.getKhangNuoc_Bui(), x.getMaTienIch());
    }

    @Override
    public void delete(String id) {
        JDBC.JDBCHelPer.update(delete_sql, id);
    }

    @Override
    public List<TienIch> selectAll() {
        return selecBySQL(select_All);
    }

    @Override
    public List<TienIch> select_Search(String key) {
        return selecBySQL(select_Search, "%" + key + "%");
    }

    @Override
    public List<TienIch> selecBySQL(String sql, Object... args) {
        ArrayList<TienIch> list = new ArrayList<>();
        try {
             ResultSet rs = JDBC.JDBCHelPer.query(sql, args);
             while(rs.next()){
                 String maTienIch = rs.getString(1);
                 String Baomat = rs.getString(2);
                 String tndacbiet = rs.getString(3);
                 String khangnuoc = rs.getString(4);
                 
                 TienIch o = new TienIch(maTienIch, Baomat, tndacbiet, khangnuoc);
                 list.add(o);
             }
        }  catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }


}
