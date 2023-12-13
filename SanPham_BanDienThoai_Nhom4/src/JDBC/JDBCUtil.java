/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JDBC;

import Repository.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class JDBCUtil {

    public static Connection getConnection() {
        Connection connect = null;
        try {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());

            String url = "jdbc:sqlserver://localhost;database=QuanLyBanDienThoai;"
                +"encrypt=true;trustServerCertificate=true;sslProtocol=TLSv1.2";
            String username = "sa";
            String password = "123456";

            connect = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connect;
    }

    public static void closeConnection(Connection connect) {
        if (connect != null) {
            try {
                connect.close();
            } catch (SQLException ex) {
                Logger.getLogger(JDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void printInfo(Connection connect) {
        if (connect != null) {
            DatabaseMetaData dtmt;
            try {
                dtmt = connect.getMetaData();
                System.out.println(dtmt.getDatabaseProductName());
                System.out.println(dtmt.getDatabaseProductVersion());
            } catch (SQLException ex) {
                Logger.getLogger(JDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    public static PreparedStatement getStmt(String sql, Object...args) throws SQLException{
        Connection cn = JDBCUtil.getConnection();
        PreparedStatement ps;
        if(sql.trim().startsWith("{")){
            ps = cn.prepareCall(sql);
        }else{
            ps = cn.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            ps.setObject(i+1, args[i]);
        }
        return ps;
    }
    
    public static ResultSet query(String sql, Object...args) throws SQLException{
        PreparedStatement ps = JDBCUtil.getStmt(sql, args);
        return ps.executeQuery();
    }
    
    public static Object[] value(String sql, Object...args) {
        try {
            ResultSet rs = JDBCUtil.query(sql, args);
            if(rs.next()){
                return (Object[]) rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static int update(String sql, Object...args) {
        try {
            PreparedStatement ps = JDBCUtil.getStmt(sql, args);
            try {
                return ps.executeUpdate();
            } finally{
                ps.getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    public static void main(String[] args) {
        printInfo(getConnection());
    }
}
