package Store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Service_Stores {
   // ArrayList<Stores> list = new ArrayList<>();
    
    public ArrayList<Stores> getAll(){
        ArrayList<Stores> list = new ArrayList<>();
        String sql = "SELECT * FROM Cuahang ";
        try
        (Connection con = Connect_SQL.connect.openConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                )
        {
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while(rs.next()){
                String id = rs.getString("id");
                String code = rs.getString("Ma");
                String name = rs.getString("Ten");
                String adress = rs.getString("DiaChi");
                String city = rs.getString("ThanhPho");
                String country = rs.getString("QuocGia");
                
                Stores store = new Stores(id, code, name, adress, city, country);
                list.add(store);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public Stores Add(Stores s){
        String sql = "INSERT INTO Cuahang "
                + "VALUES (NEWID(), ?, ? , ?, ?, ?)";
        
        try (Connection con = Connect_SQL.connect.openConnection();
                PreparedStatement ps = con.prepareStatement(sql);)
                 {
            
//            ps.setString(1, s.getCode());
//            ps.setString(2, s.getName());
//            ps.setString(3, s.getAdress());
//            ps.setString(4, s.getCity());
//            ps.setString(5, s.getCountry());
            
            JDBC.excuteUpdate(sql, s.getCode(), s.getName(), s.getAdress(), s.getCity(), s.getCountry());
            
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//     public int Add(Stores s){
//         int row = 0;
//        String sql = "INSERT INTO Cuahang "
//                + "VALUES (NEWID(), ?, ? , ?, ?, ?)";
//        
//        try (Connection con = Connect_SQL.connect.openConnection();
//                PreparedStatement ps = con.prepareStatement(sql);)
//                 {
//            
//            ps.setString(1, s.getCode());
//            ps.setString(2, s.getName());
//            ps.setString(3, s.getAdress());
//            ps.setString(4, s.getCity());
//            ps.setString(5, s.getCountry());
//            
//            row = ps.executeUpdate();
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return row;
//    }
    
    public Stores Update(Stores s){
        String sql = "UPDATE Cuahang "
                + "set Ma = ? , Ten = ?, DiaChi = ? , ThanhPho = ?, QuocGia = ? "
                + "WHERE ID = ? ";
        JDBC.excuteUpdate(sql, s.getCode(), s.getName(), s.getAdress(), s.getCity(), s.getCountry(), s.getId());
        return s;
    }
    
}
