/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JDBC;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;


public class jdbchelper1 {

    private static String url = "jdbc:sqlserver://localhost;database=QuanLyBanDienThoai;"
                +"encrypt=true;trustServerCertificate=true;sslProtocol=TLSv1.2";
    private static String user = "sa";
    private static String pass = "123456";
    
    public static  PreparedStatement getStmt(String sql,Object... args) throws SQLException{
        Connection con = DriverManager.getConnection(url, user, pass);
        PreparedStatement pr = null;
        if(sql.trim().startsWith("|")){
            pr = con.prepareCall(sql);
            
        }
        else{
            pr = con.prepareStatement(sql);
        }
        for(int i = 0 ;i<args.length; i++){
            pr.setObject(i+1, args[i]);
            
        }
        return pr;
    }
    public static int update(String sql, Object... agrs){
        try{
            PreparedStatement pr = getStmt(sql, agrs);
            try{
                return pr.executeUpdate();
            }finally{
                pr.getConnection().close();
            }
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    public static ResultSet query(String sql, Object... agrs){
        try{
            PreparedStatement pr = getStmt(sql, agrs);

            return pr.executeQuery();
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
    } 
    public static Object value(String sql, Object... agrs){
        try{
            ResultSet rs = query(sql, agrs);
            if(rs.next()){
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();;
        }
        catch(Exception e){
            throw  new RuntimeException(e);
        }
        return null;
    }
}
