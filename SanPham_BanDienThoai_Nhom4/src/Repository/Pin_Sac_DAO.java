package Repository;

import Model.Pin_Sac;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Pin_Sac_DAO extends ServiceDAO<Pin_Sac, String> {

    final String insert_sql = "INSERT INTO Pin_Sac (MaPS, DungLuongPin, LoaiPin,Sac, CongNghePin) VALUES (?,?,?,?, ?);";
    final String update_sql = "UPDATE Pin_Sac SET DungLuongPin = ?, LoaiPin,Sac = ?, CongNghePin = ? WHERE MaPS = ? ";
    final String delete_sql = "DELETE FROM Pin_Sac WHERE MaPS = ? ";
    final String select_All = "SELECT * FROM Pin_Sac";
    final String select_Search = "SELECT * FROM Pin_Sac WHERE MaPS = ? ";

    @Override
    public void insert(Pin_Sac x) {
        JDBC.JDBCHelPer.update(insert_sql, x.getMaPinSac(), x.getDungLuongPin(), x.getLoaiPin(), x.getSac(), x.getCongNghePin());
    }

    @Override
    public void update(Pin_Sac x) {
        JDBC.JDBCHelPer.update(update_sql, x.getDungLuongPin(), x.getLoaiPin(), x.getSac(), x.getCongNghePin(), x.getMaPinSac());
    }

    @Override
    public void delete(String id) {
        JDBC.JDBCHelPer.query(delete_sql, id);
    }

    @Override
    public List<Pin_Sac> selectAll() {
        return selecBySQL(select_All);
    }

    @Override
    public List<Pin_Sac> select_Search(String key) {
        return selecBySQL(select_Search, "%" + key + "%");
    }

    @Override
    public List<Pin_Sac> selecBySQL(String sql, Object... args) {
         ArrayList<Pin_Sac> list = new ArrayList<>();
        try {
             ResultSet rs = JDBC.JDBCHelPer.query(sql, args);
             while(rs.next()){
                 String maps = rs.getString(1);
                 int dlpin = rs.getInt(2);
                 String laoipin = rs.getString(3);
                 String sac = rs.getString(4);
                 String congnghepin = rs.getString(5);
                 
                 Pin_Sac o = new Pin_Sac(maps, dlpin, laoipin, sac, congnghepin);
                 list.add(o);
             }
        }  catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }


}
