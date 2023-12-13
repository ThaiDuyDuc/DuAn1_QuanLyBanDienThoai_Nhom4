/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import JDBC.JDBCUtil;
import Model.BoNho;
import Model.DiaChi;
import Model.DienThoai;
import Model.DienThoaiChiTiet;
import Model.DonHang;
import Model.GioHang;
import Model.HangKhachHang;
import Model.HoaDonChiTiet;
import Model.KhachHang;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class KhachHangDAO implements DAOInterface<KhachHang, String> {

    private ArrayList<KhachHang> getListKH(String sql) {
        ArrayList<KhachHang> list = new ArrayList<>();
        Connection cn = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //KhachHang
                String maKH = rs.getString("MaKH");
                String hoTen = rs.getString("HoTen");
                boolean gioiTinh = rs.getBoolean("GioiTinh");
                Date ngaySinh = rs.getDate("NgaySinh");
                String email = rs.getString("Email");
                String SDT = rs.getString("SDT");
                String anhDaiDien = rs.getString("AnhDaiDien");
                int soDonHang = rs.getInt("SoDonHang");
                double giaTriChiTieu = rs.getDouble("GiaTriChiTieu");
                String ghiChu = rs.getString("GhiChu");
                boolean trangThai = rs.getBoolean("TrangThai");
                int maHang = rs.getInt("MaHang");

                HangKhachHang hangKhachHang = new HangKHDAO().getByID(maHang);
                KhachHang kh = new KhachHang(maKH, hoTen, gioiTinh, ngaySinh, email, SDT, anhDaiDien, soDonHang, giaTriChiTieu, ghiChu, trangThai, hangKhachHang);
                list.add(kh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return list;
    }

    @Override
    public ArrayList<KhachHang> getAll() {
        String sql = "SELECT KH.*, COUNT(HD.MaHoaDon) 'SoDonHang', SUM(HD.TongTien) 'GiaTriChiTieu'\n"
                + "FROM KhachHang KH LEFT JOIN HoaDon HD ON KH.MaKH = HD.MaKhachHang\n"
                + "GROUP BY MaKH, HoTen, GioiTinh, NgaySinh, Email, SDT, AnhDaiDien, KH.GhiChu, KH.TrangThai, MaHang";
        ArrayList<KhachHang> list = getListKH(sql);
        return list;
    }

    @Override
    public KhachHang getByID(String k) {
        KhachHang kh = null;
        Connection cn = JDBCUtil.getConnection();
        String sql = "SELECT KH.*, COUNT(HD.MaHoaDon) 'SoDonHang', SUM(HD.TongTien) 'GiaTriChiTieu'\n"
                + "FROM KhachHang KH LEFT JOIN HoaDon HD ON KH.MaKH = HD.MaKhachHang\n"
                + "WHERE MaKH ='" + k + "'\n"
                + "GROUP BY MaKH, HoTen, GioiTinh, NgaySinh, Email, SDT, AnhDaiDien, KH.GhiChu, KH.TrangThai, MaHang";

        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //KhachHang
                String maKH = rs.getString("MaKH");
                String hoTen = rs.getString("HoTen");
                boolean gioiTinh = rs.getBoolean("GioiTinh");
                Date ngaySinh = rs.getDate("NgaySinh");
                String email = rs.getString("Email");
                String SDT = rs.getString("SDT");
                String anhDaiDien = rs.getString("AnhDaiDien");
                int soDonHang = rs.getInt("SoDonHang");
                double giaTriChiTieu = rs.getDouble("GiaTriChiTieu");
                String ghiChu = rs.getString("GhiChu");
                boolean trangThai = rs.getBoolean("TrangThai");
                int maHang = rs.getInt("MaHang");

                HangKhachHang hangKhachHang = new HangKHDAO().getByID(maHang);
                kh = new KhachHang(maKH, hoTen, gioiTinh, ngaySinh, email, SDT, anhDaiDien, soDonHang, giaTriChiTieu, ghiChu, trangThai, hangKhachHang);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return kh;
    }

    public ArrayList<KhachHang> search(String key) {
        String sql = "SELECT KH.*, COUNT(HD.MaHoaDon) 'SoDonHang', SUM(HD.TongTien) 'GiaTriChiTieu'\n"
                + "      FROM KhachHang KH LEFT JOIN HoaDon HD ON KH.MaKH = HD.MaKhachHang\n"
                + "      WHERE HoTen LIKE '%" + key + "%' "
                + "      OR SDT LIKE '%" + key + "%' "
                + "      OR Email LIKE '%" + key + "%'"
                + "      GROUP BY MaKH, HoTen, GioiTinh, NgaySinh, Email, SDT, AnhDaiDien, KH.GhiChu, KH.TrangThai, MaHang";
        ArrayList<KhachHang> list = getListKH(sql);
        return list;
    }

    public ArrayList<KhachHang> paginate(int pageNumber, int pageSize) {
        int start = (pageNumber - 1) * pageSize + 1;
        int end = pageNumber * pageSize;
        String sql = "SELECT * FROM"
                + "(SELECT ROW_NUMBER() OVER (ORDER BY MaKH) AS rownum,  *\n"
                + "FROM (SELECT KH.*, COUNT(HD.MaHoaDon) 'SoDonHang', SUM(HD.TongTien) 'GiaTriChiTieu'\n"
                + "      FROM KhachHang KH LEFT JOIN HoaDon HD ON KH.MaKH = HD.MaKhachHang\n"
                + "      GROUP BY MaKH, HoTen, GioiTinh, NgaySinh, Email, SDT, AnhDaiDien, KH.GhiChu, KH.TrangThai, MaHang) as tmp1) "
                + "AS temp "
                + "WHERE rownum BETWEEN " + start + " AND " + end;
        ArrayList<KhachHang> list = getListKH(sql);
        return list;
    }

    @Override
    public int insert(KhachHang e) {
        int row = 0;
        Connection cn = JDBCUtil.getConnection();
        String sql = "INSERT INTO KhachHang(MaKH, HoTen, GioiTinh, NgaySinh, Email, SDT, AnhDaiDien, "
                + "GhiChu, TrangThai, MaHang)  "
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, e.getMaKH());
            ps.setString(2, e.getHoTen());
            ps.setBoolean(3, e.isGioiTinh());
            ps.setDate(4, e.getNgaySinh());
            ps.setString(5, e.getEmail());
            ps.setString(6, e.getSDT());
            ps.setString(7, e.getAnhDaiDien());
            ps.setString(8, e.getGhiChu());
            ps.setBoolean(9, e.isTrangThai());
            ps.setInt(10, e.getHangKhachHang().getMaHang());

            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return row;
    }

    @Override
    public int update(KhachHang e) {
        int row = 0;
        Connection cn = JDBCUtil.getConnection();
        String sql = "UPDATE KhachHang SET HoTen=?, GioiTinh=?, NgaySinh=?, Email=?, SDT=?, AnhDaiDien=?, "
                + "GhiChu=?, TrangThai=?, MaHang=? WHERE MaKH = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(10, e.getMaKH());
            ps.setString(1, e.getHoTen());
            ps.setBoolean(2, e.isGioiTinh());
            ps.setDate(3, e.getNgaySinh());
            ps.setString(4, e.getEmail());
            ps.setString(5, e.getSDT());
            ps.setString(6, e.getAnhDaiDien());
            ps.setString(7, e.getGhiChu());
            ps.setBoolean(8, e.isTrangThai());
            ps.setInt(9, e.getHangKhachHang().getMaHang());
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return row;
    }

    public int updateKH(KhachHang e, DiaChi dc) {
        int row = 0;
        Connection cn = JDBCUtil.getConnection();
        String sql = "UPDATE KhachHang SET HoTen=?, GioiTinh=?, NgaySinh=?, Email=?, SDT=?, AnhDaiDien=?, "
                + "GhiChu=?, TrangThai=?, MaHang=? "
                + "WHERE MaKH = ? "
                + "UPDATE DiaChi SET ThanhPho = ?, Quan =?, Phuong = ?, Duong_ToaNha=? WHERE MaDC =?";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(10, e.getMaKH());
            ps.setString(1, e.getHoTen());
            ps.setBoolean(2, e.isGioiTinh());
            ps.setDate(3, e.getNgaySinh());
            ps.setString(4, e.getEmail());
            ps.setString(5, e.getSDT());
            ps.setString(6, e.getAnhDaiDien());
            ps.setString(7, e.getGhiChu());
            ps.setBoolean(8, e.isTrangThai());
            ps.setInt(9, e.getHangKhachHang().getMaHang());

            ps.setString(15, dc.getMaDC());
            ps.setString(11, dc.getThanhPho());
            ps.setString(12, dc.getQuan());
            ps.setString(13, dc.getPhuong());
            ps.setString(14, dc.getToaNha());
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return row;
    }

    public int getSoSP(String key) {
        int count = 0;
        String sql = "SELECT HD.MaKhachHang, COUNT(MaDTCT) 'SoSP' "
                + "FROM HoaDon HD JOIN HoaDonChiTiet CT ON HD.MaHoaDon = CT.MaHoaDon "
                + "WHERE HD.MaKhachHang = '" + key + "' "
                + "GROUP BY HD.MaKhachHang";
        Connection cn = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("SoSP");
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return count;
    }

    @Override
    public int delete(String k) {
        int row = 0;
        Connection cn = JDBCUtil.getConnection();
        String sql = "DELETE HoaDonChiTiet WHERE MaHoaDon IN (SELECT MaHoaDon FROM HoaDon WHERE MaKhachHang = '" + k + "')  "
                + "DELETE HoaDon WHERE MaKhachHang = '" + k + "' "
                + "DELETE DiaChiChiTiet WHERE MaKH = '" + k + "' "
                + "DELETE GioHangChiTiet WHERE MaGH = (SELECT MaGH FROM GioHang WHERE NguoiSoHuu = '" + k + "')  "
                + "DELETE GioHang WHERE NguoiSoHuu = '" + k + "' "
                + "DELETE PHIEUGIAM_KHACHHANG WHERE Ma_KhachHang = '" + k + "' "
                + "DELETE TheThanhVien WHERE NguoiSoHuu = '" + k + "' "
                + "DELETE KhachHang WHERE MaKH = '" + k + "' ";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);

            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return row;
    }

    public ArrayList<DiaChi> getListDCByMaKH(String key) {
        ArrayList<DiaChi> list = new ArrayList<>();
        String sql = "SELECT * FROM DiaChi DC JOIN DiaChiChiTiet CT ON DC.MaDC = CT.MaDC WHERE MaKH = '" + key + "'";
        Connection cn = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString(1);
                String thanhPho = rs.getString("ThanhPho");
                String quan = rs.getString("Quan");
                String phuong = rs.getString("Phuong");
                String toaNha = rs.getString("Duong_ToaNha");
                String ghiChu = rs.getString("GhiChu");
                DiaChi dc = new DiaChi(ma, thanhPho, quan, phuong, toaNha, ghiChu);
                list.add(dc);

            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return list;
    }

    public ArrayList<GioHang> getListGHByMaKH(String key) {
        ArrayList<GioHang> list = new ArrayList<>();
        String sql = "SELECT * FROM GioHang G JOIN GioHangChiTiet GH ON G.MaGH = GH.MaGH "
                + "JOIN DienThoaiChiTiet DT ON GH.MaDTCT = DT.MaDTCT "
                + "WHERE NguoiSoHuu = '" + key + "'";
        Connection cn = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maGH = rs.getString(1);
                Date ngayTao = rs.getDate("ThoiGianTao");
                Date ngayCapNhat = rs.getDate("ThoiGianCapNhat");
                String maKH = rs.getString("NguoiSoHuu");
                int soLuong = rs.getInt("SoLuong");
                String ghiChu = rs.getString("GhiChu");
                boolean trangThai = rs.getBoolean("TrangThai");
                String maDTCT = rs.getString("MaDTCT");

                KhachHang khachHang = getByID(maKH);
                DienThoaiChiTiet dtct = getDTCTById(maDTCT);
                GioHang gh = new GioHang(maGH, ngayTao, ngayCapNhat, khachHang, dtct, soLuong, ghiChu, trangThai);
                list.add(gh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return list;
    }

    public ArrayList<DonHang> getListDHByMaKH(String key) {
        ArrayList<DonHang> list = new ArrayList<>();
        String sql = "SELECT HD.*, SUM (CT.SoLuong * CT.DonGia) N'ThanhTien' "
                + "FROM HoaDon HD JOIN HoaDonChiTiet CT ON HD.MaHoaDon = CT.MaHoaDon "
                + "WHERE HD.MaKhachHang = '" + key + "'"
                + "GROUP BY HD.MaHoaDon, Ngaylap, MaKhachHang, MaNV, TongTien, MaPhieuGiamGia, TongTienSauGG, PhuongThucThanhToan, HD.GhiChu, HD.TrangThai";
        Connection cn = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString(1);
                Date ngayLap = rs.getDate("NgayLap");
//                String maKH = rs.getString("MaKhachHang");
                String maNV = rs.getString("MaNV");
//                String maDGG = rs.getString("MaDotGiamGia");
//                String maPGG = rs.getString("MaPhieuGiamGia");
//                String maPGH = rs.getString("MaPhieuGiaoHang");
//                String ghiChu = rs.getString("GhiChu");
                String trangThai = rs.getString("TrangThai");
                double thanhTien = rs.getDouble("ThanhTien");
                DonHang dh = new DonHang();
                dh.setMaHD(ma);
                dh.setMaNV(maNV);
                dh.setNgayLap(ngayLap);
                dh.setTrangThai(trangThai);
                dh.setTongTien(thanhTien);
                list.add(dh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return list;
    }

    public DonHang getByIDHoaDon(String k) {
        DonHang dh = null;
        Connection cn = JDBCUtil.getConnection();
        String sql = "SELECT * FROM HoaDon WHERE MaHoaDon = '" + k + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String ma = rs.getString(1);
                Date ngayLap = rs.getDate("NgayLap");
                String maKH = rs.getString("MaKhachHang");
                String maNV = rs.getString("MaNV");
                String maPGG = rs.getString("MaPhieuGiamGia");
                String ghiChu = rs.getString("GhiChu");
                String trangThai = rs.getString("TrangThai");
                String phuongThucThanhToan = rs.getString("PhuongThucThanhToan");
                double thanhTien = rs.getDouble("TongTien");
                double tongTienSauGG = rs.getDouble("TongTienSauGG");
                KhachHang khachHang = new KhachHangDAO().getByID(maKH);
                dh = new DonHang(ma, ngayLap, khachHang, maNV, thanhTien, maPGG, tongTienSauGG, phuongThucThanhToan, ghiChu, trangThai);

            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return dh;
    }

    public ArrayList<HoaDonChiTiet> getAllHoaDonChiTietByMa(String maHD) {
        String sql = "SELECT * FROM HoaDonChiTiet WHERE MaHoaDon = '" + maHD + "'";
        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
        Connection cn = JDBCUtil.getConnection();
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String ma = rs.getString(1);
                String maDTCT = rs.getString("MaDTCT");
                int soLuong = rs.getInt("SoLuong");
                double donGia = rs.getDouble("DonGia");
                double thanhTien = rs.getDouble("ThanhTien");
                String ghiChu = rs.getString("GhiChu");

                HoaDonChiTiet hdct = new HoaDonChiTiet(ma, maDTCT, soLuong, String.valueOf(donGia), String.valueOf(thanhTien), ghiChu);
                list.add(hdct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return list;
    }

    public DienThoai getDienThoaiByID(String key) {
        String sql = "SELECT * FROM DienThoai WHERE MaDT = '" + key + "'";
        Connection cn = JDBCUtil.getConnection();
        DienThoai dt = null;
        try (
                PreparedStatement ps = cn.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maDT = rs.getString(1);
                String TenDT = rs.getString(2);
                String ThuongHieu = rs.getString(3);
                String Camera = rs.getString(4);
                String KetNoi = rs.getString(5);
                String PinSac = rs.getString(6);
                String HeDieuHanh = rs.getString(7);
                String Chip = rs.getString(8);
                String TienIch = rs.getString(9);
                String ManHinh = rs.getString(10);
                String MoTa = rs.getString(11);

                dt = new DienThoai(maDT, TenDT, ThuongHieu, Camera, KetNoi,
                        PinSac, HeDieuHanh, Chip, TienIch, ManHinh, MoTa);
            }

        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return dt;
    }

    public DienThoaiChiTiet getDTCTById(String key) {
        String sql = "SELECT * FROM DienThoaiChiTiet WHERE MaDTCT = '" + key + "'";
        Connection cn = JDBCUtil.getConnection();
        DienThoaiChiTiet dtct = null;
        try (
                PreparedStatement ps = cn.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String madtct = rs.getString(1);
                String madt = rs.getString(2);
                BigDecimal giaban = rs.getBigDecimal(3);
                String hinhanh = rs.getString(4);
                String mau = rs.getString(5);
                int slton = rs.getInt(6);
                String bonho = rs.getString(7);
                String kho = rs.getString(8);
                String nsx = rs.getString(9);
                String pbh = rs.getString(10);
                Boolean trangThai = rs.getBoolean(11);
                String mota = rs.getString(12);

                dtct = new DienThoaiChiTiet(madtct, madt, giaban, hinhanh, mau, slton,
                        bonho, kho, nsx, pbh, trangThai, mota);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return dtct;
    }

    public BoNho getBoNhoByID(String key) {
        String sql = "SELECT * FROM BoNho WHERE MaBoNho = '" + key + "'";
        Connection cn = JDBCUtil.getConnection();
        BoNho bn = null;
        try (
                PreparedStatement ps = cn.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String mabonho = rs.getString(1);
                int ram = rs.getInt(2);
                int luutru = rs.getInt(3);
                int khadung = rs.getInt(4);
                String danhba = rs.getString(5);

                bn = new BoNho(mabonho, ram, luutru, khadung, danhba);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return bn;
    }

    public DiaChi getDC(DiaChi diaChi) {
        DiaChi dc = null;
        String sql = "SELECT * FROM DiaChi WHERE ThanhPho = ? AND Quan = ? AND Phuong = ? AND Duong_ToaNha = ?";
        Connection cn = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, diaChi.getThanhPho());
            ps.setString(2, diaChi.getQuan());
            ps.setString(3, diaChi.getPhuong());
            ps.setString(4, diaChi.getToaNha());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString(1);
                String thanhPho = rs.getString("ThanhPho");
                String quan = rs.getString("Quan");
                String phuong = rs.getString("Phuong");
                String toaNha = rs.getString("Duong_ToaNha");
                dc = new DiaChi(ma, thanhPho, quan, phuong, toaNha, null);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dc;
    }

    public int insertDC(DiaChi e) {
        int row = 0;
        Connection cn = JDBCUtil.getConnection();
        String sql = "INSERT INTO DiaChi(MaDC, ThanhPho, Quan, Phuong, Duong_ToaNha) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, e.getMaDC());
            ps.setString(2, e.getThanhPho());
            ps.setString(3, e.getQuan());
            ps.setString(4, e.getPhuong());
            ps.setString(5, e.getToaNha());

            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return row;
    }

    public int insertDCCT(String maKH, String maDC) {
        int row = 0;
        Connection cn = JDBCUtil.getConnection();
        String sql = "INSERT INTO DiaChiChiTiet VALUES (?,?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, maKH);
            ps.setString(2, maDC);
            ps.setString(3, null);

            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return row;
    }

    public int updateDC(DiaChi e) {
        int row = 0;
        Connection cn = JDBCUtil.getConnection();
        String sql = "UPDATE DiaChi SET ThanhPho = ?, Quan = ?, Phuong = ?, Duong_ToaNha = ? WHERE MaDC = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(5, e.getMaDC());
            ps.setString(1, e.getThanhPho());
            ps.setString(2, e.getQuan());
            ps.setString(3, e.getPhuong());
            ps.setString(4, e.getToaNha());

            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return row;
    }

    public int deleteDC(String key) {
        int row = 0;
        Connection cn = JDBCUtil.getConnection();
        String sql = "DELETE DiaChi WHERE MaDC = '" + key + "'";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);

            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return row;
    }

    public int deleteDCCT(String idKH, String idDC) {
        int row = 0;
        Connection cn = JDBCUtil.getConnection();
        String sql = "DELETE DiaChiChiTiet WHERE MaDC = '" + idDC + "' AND MaKH = '" + idKH + "'";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);

            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDBCUtil.closeConnection(cn);
        return row;
    }
}
