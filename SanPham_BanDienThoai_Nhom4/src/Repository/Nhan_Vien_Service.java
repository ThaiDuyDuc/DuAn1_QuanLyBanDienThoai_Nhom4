package Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.Nhan_Vien;
import JDBC.DBConnect;

/**
 *
 * @author Acer
 */
public class Nhan_Vien_Service {

    private List<Nhan_Vien> ListNV;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public List<Nhan_Vien> paging(int page,int limit) {
        ListNV = new ArrayList<>();
        String sql = "select * from NhanVien order by MaNV "
                + "offset ? rows fetch next ? rows only";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, (page-1) * limit);
            ps.setInt(2, limit);
            rs = ps.executeQuery();
            while (rs.next()) {
                Nhan_Vien nv = new Nhan_Vien(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),rs.getString(12));
                ListNV.add(nv);

            }

            return ListNV;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
public List<Nhan_Vien> getall() {
        ListNV = new ArrayList<>();
        String sql = "select * from NhanVien";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Nhan_Vien nv = new Nhan_Vien(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),rs.getString(12));
                ListNV.add(nv);

            }

            return ListNV;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
//    public ArrayList<Nhan_Vien> search(String trangThai) {
//        String sql = "select * from NhanVien where  TrangThai like ? ";
//        try {
//            con = DBConnect.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setString(1, trangThai);
//            rs = ps.executeQuery();
//            ArrayList<Nhan_Vien> list = new ArrayList<>();
//            while (rs.next()) {
//                Nhan_Vien nv = new Nhan_Vien(rs.getString(1), rs.getString(2), rs.getString(3),
//                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
//                        rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),rs.getString(12));
//                list.add(nv);
//            }
//            return list;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public int add(Nhan_Vien sv) {
// trả về số bản ghi được thêm
        int kq = 0;
        String sql = "insert into NhanVien(MaNV,TenNV,DiaChi,NgaySinh,SDT,Anh,GioiTinh,MatKhau,NgayTao,TrangThai,Emaill,ChucVu) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, sv.getMaNV());
            ps.setString(2, sv.getTenNV());
            ps.setString(3, sv.getDiaChi());
            ps.setString(4, sv.getNgaySinh());
            ps.setString(5, sv.getSDT());
            ps.setString(6, sv.getAnh());
            ps.setString(7, sv.getGioiTinh());
            ps.setString(8, sv.getMatKhau());
            ps.setString(9, sv.getNgayTao());
            ps.setString(10, sv.getTrangThai());
            ps.setString(11, sv.getEmaill());
            ps.setString(12, sv.getChucVu());
            
            kq = ps.executeUpdate();//thêm, sửa, xóa: executeUpdate()
        } catch (Exception e) {
            e.printStackTrace();
            kq = 0;// không ai được thêm
        }
        return kq;
    }

    public int deleteSV(String ma) {
        String sql = "delete from NhanVien where MaNV like ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            return ps.executeUpdate();// thêm, sửa, xóa:executeUpdate()  
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateSV(String ma, Nhan_Vien sv) {
        String sql = "update NhanVien set TenNV=?,DiaChi=?,NgaySinh=?,SDT=?,GioiTinh=?,MatKhau=?,NgayTao=?,TrangThai=?,Emaill=?,ChucVu=?,Anh=? \n"
                + "                where MaNV like ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, sv.getTenNV());
            ps.setString(2, sv.getDiaChi());
            ps.setString(3, sv.getNgaySinh());
            ps.setString(4, sv.getSDT());
            ps.setString(5, sv.getGioiTinh());
            ps.setString(6, sv.getMatKhau());
            ps.setString(7, sv.getNgayTao());
            ps.setString(8, sv.getTrangThai());
            ps.setString(9, sv.getEmaill());
            ps.setString(10, sv.getChucVu());
            ps.setString(11, sv.getAnh());
            ps.setString(12, ma);
            return ps.executeUpdate();
        } catch (Exception e) {// k sửa được
            e.printStackTrace();
            return 0;

        }

    }

    public Nhan_Vien searchByid(String id) {
        String sql = "select * from NhanVien Where maNhanVien=?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, id);
            List<Nhan_Vien> list = new ArrayList<>();
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Nhan_Vien x = new Nhan_Vien();
                boolean gt = true;
                boolean vt = true;
                if (rs.getInt(7) == 1) {
                    gt = true;
                } else {
                    gt = false;
                }
                x.setMaNV(rs.getString(1));
                x.setTenNV(rs.getString(2));
                x.setDiaChi(rs.getString(3));
                x.setNgaySinh(rs.getString(4));
                x.setSDT(rs.getString(5));
                x.setGioiTinh(rs.getString(6));
                x.setMatKhau(rs.getString(7));
                x.setNgayTao(rs.getString(8));
                x.setTrangThai(rs.getString(9));
                x.setEmaill(rs.getString(10));
                x.setChucVu(rs.getString(11));
                list.add(x);
            }
            return list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Nhan_Vien_Service ss = new Nhan_Vien_Service();
        List<Nhan_Vien> listKQ = ss.paging(1, 2);
        for (Nhan_Vien x : listKQ) {
            System.out.println(x.toString());
        }
    }

}
