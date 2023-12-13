package Store;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class JDBC {
    public static Integer excuteUpdate(String sql, Object ...args){
        Connection con = null;
        
        int row = 0;
        PreparedStatement ps = null;
        
        try {
            con = Connect_SQL.connect.openConnection();
            ps = con.prepareStatement(sql);
            // gán các giá trị dấu ? trong câu lệnh sql
            
            for(int i = 0; i < args.length; i++){
                ps.setObject(i+1, args[i]);
            }
            row = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
}
