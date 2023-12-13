package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JDBCHelPer {
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String dburl = "jdbc:sqlserver://localhost;database=QuanLyBanDienThoai;"
                +"encrypt=true;trustServerCertificate=true;sslProtocol=TLSv1.2";
    private static String user = "sa";
    private static String pass = "123456";
    
    static {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static Connection openConnection() throws Exception {
         Connection con = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=QuanLyBanDienThoai;"
                +"encrypt=true;trustServerCertificate=true;sslProtocol=TLSv1.2"; 
        String user = "sa";
        String password = "123456";
         con = DriverManager.getConnection(connectionUrl, user, password);
        //Statement stm =  con.createStatement();
        return con;
    }
    
    public static  PreparedStatement getstmt (String sql, Object...args) throws SQLException{
        Connection con = DriverManager.getConnection(dburl, user, pass);
        PreparedStatement ps = null;
        if(sql.trim().startsWith("{")){
            ps = con.prepareCall(sql);
        }else{
            ps = con.prepareStatement(sql);
        }
        
        for(int i = 0; i < args.length; i++){
            ps.setObject(i+1, args[i]);
        }
        return ps;
    }
    
    public static int update(String sql , Object... args){
        try {
            PreparedStatement stm = getstmt(sql, args);
            try {
                return stm.executeUpdate();
            } finally {
                stm.getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }
    
    public static ResultSet query(String sql, Object... args){
        try {
            PreparedStatement ps = getstmt(sql, args);
            return ps.executeQuery();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
