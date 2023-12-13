
package Repository;

import JDBC.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.HangKhachHang;
import java.math.BigDecimal;

/**
 *
 * @author Administrator
 */
public class HangKHDAO implements DAOInterface<HangKhachHang, Integer>{

    @Override
    public ArrayList<HangKhachHang> getAll() {
        ArrayList<HangKhachHang> list = new ArrayList<>();
        Connection cn = JDBCUtil.getConnection();
        String sql = "SELECT * FROM HangKhachHang";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                int maHang = rs.getInt("MaHang");
                String tenHang = rs.getString("TenHang");
                int soDH = rs.getInt("SoDonHang");
                double gtri = rs.getDouble("GiaTriChiTieu");
                
                HangKhachHang hang = new HangKhachHang(maHang, tenHang, soDH, gtri);
                list.add(hang);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HangKHDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return list;
    }

    @Override
    public int insert(HangKhachHang e) {
        int row = 0;
        Connection cn = JDBCUtil.getConnection();
        String sql = "INSERT INTO HangKhachHang (MaHang, TenHang, SoDonHang, GiaTriChiTieu)  VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, e.getMaHang());
            ps.setString(2, e.getTenHang());
            ps.setInt(3, e.getSoDonHang());
            ps.setDouble(4, e.getGiaTriChiTieu());
            
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HangKHDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return row;
    }

    @Override
    public int update(HangKhachHang e) {
        int row = 0;
        Connection cn = JDBCUtil.getConnection();
        String sql = "UPDATE HangKhachHang SET TenHang = ?, SoDonHang = ?, GiaTriChiTieu = ? WHERE MaHang = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(4, e.getMaHang());
            ps.setString(1, e.getTenHang());
            ps.setInt(2, e.getSoDonHang());
            ps.setDouble(3, e.getGiaTriChiTieu());
             
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HangKHDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return row;
    }

    @Override
    public int delete(Integer k) {
        int row = 0;
        Connection cn = JDBCUtil.getConnection();
        String sql = "DELETE HangKhachHang WHERE MaHang = "+k;
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
//            ps.setInt(4, k);
            
            
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HangKHDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return row;
    }

    @Override
    public HangKhachHang getByID(Integer k) {
        HangKhachHang hang = null;
        Connection cn = JDBCUtil.getConnection();
        String sql = "SELECT * FROM HangKhachHang Where MaHang = "+k;
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
//            ps.setInt(1, k);
            while(rs.next()){
                int maHang = rs.getInt("MaHang");
                String tenHang = rs.getString("TenHang");
                int soDH = rs.getInt("SoDonHang");
                double gtri = rs.getDouble("GiaTriChiTieu");
                
                hang = new HangKhachHang(maHang, tenHang, soDH, gtri);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HangKHDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return hang;
    }
    public HangKhachHang getByTen(String k) {
        HangKhachHang hang = null;
        Connection cn = JDBCUtil.getConnection();
        String sql = "SELECT * FROM HangKhachHang Where TenHang = N'"+k+"'";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
//            ps.setInt(1, k);
            while(rs.next()){
                int maHang = rs.getInt("MaHang");
                String tenHang = rs.getString("TenHang");
                int soDH = rs.getInt("SoDonHang");
                double gtri = rs.getDouble("GiaTriChiTieu");
                
                hang = new HangKhachHang(maHang, tenHang, soDH, gtri);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HangKHDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return hang;
    }
    public static void main(String[] args) {
        System.out.println(new HangKHDAO().getByID(1));
    }

   
}
