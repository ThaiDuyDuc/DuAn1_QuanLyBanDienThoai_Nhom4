package Repository;

import Model.Camera;
import Model.DienThoai;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class DienThoai_DAO extends ServiceDAO<DienThoai, String> {

    final String insert_sql = "INSERT INTO DienThoai (MaDT, TenDienThoai, ThuongHieu, MaCamera, MaKetNoi, MaPin_Sac, HeDieuHanh, MaCPU, MaTienIch, MaManHinh, MoTa )  VALUES (?,?,?,?,?,?,?,?,?,?,?);";
    final String update_sql = "UPDATE DienThoai SET TenDienThoai = ?, ThuongHieu = ?, MaCamera = ?, MaKetNoi = ?, MaPin_Sac = ?, HeDieuHanh = ?, MaCPU = ?, MaTienIch = ?, MaManHinh = ?, MoTa = ? where MaDT = ? ";
    final String delete_sql_insert = "{call XoaDT_Save_LSDT(?,?,?,?,?,?,?,?,?,?,?)}";
    final String select_All = "SELECT * FROM DienThoai";
    final String select_Search = "SELECT * FROM DienThoai WHERE MaDT like ? or ThuongHieu like ? or TenDienThoai like ? or HeDieuHanh like ? ";
    final String sql = "SELECT TOP 1 b.CAMTruoc FROM DienThoai a JOIN Camera  b on a.MaCamera = b.MaCamera WHERE a.MaCamera = ?";
    final String Sap_Xep_Ten_Tang= " SELECT * FROM DienThoai ORDER BY TenDienThoai ASC;";
    final String Sap_Xep_Ten_Giam= " SELECT * FROM DienThoai ORDER BY TenDienThoai DESC;";
    final String Sap_Xep_Ma_Giam= " SELECT * FROM DienThoai ORDER BY MaDT  DESC;";
    final String Sap_Xep_Ma_Tang= " SELECT * FROM DienThoai ORDER BY MaDT  ASC;";
    final String Kiem_Kho= " SELECT a.MaDT, a.TenDienThoai, a.ThuongHieu, a.HeDieuHanh, b.MauSac, b.GiaBan, a.MaCPU, b.MaBonho, b.MaKho, b.SLTonKho  FROM DienThoai a JOIN DienThoaiChiTiet b on a.MaDT= b.MaDT ";
    final String select_tendt = "select * from dienthoai where madt = ?";
    @Override
    public void insert(DienThoai x) {
        JDBC.JDBCHelPer.update(insert_sql, x.getMaDT(), x.getTenDienThoai(), x.getThuongHieu(), x.getMaCamera(), x.getMaKetNoi(), x.getMaPinSac(), x.getHeDieuHanh(), x.getMaCPU(), x.getMaTienIch(), x.getManHinh(), x.getMoTa());
    }

    @Override
    public void update(DienThoai x) {
        JDBC.JDBCHelPer.update(update_sql, x.getTenDienThoai(), x.getThuongHieu(), x.getMaCamera(), x.getMaKetNoi(), x.getMaPinSac(), x.getHeDieuHanh(), x.getMaCPU(), x.getMaTienIch(), x.getManHinh(), x.getMoTa(), x.getMaDT());
    }

    public DienThoai getSingleLichSuDienThoai(String Xoa) {
        List<DienThoai> danhSachKetQua = select_Search(Xoa);

        if (!danhSachKetQua.isEmpty()) {
            return danhSachKetQua.get(0);
        } else {
            return null;
        }
    }

    public List<DienThoai> gettendt(String madt){
        return selecBySQL(select_tendt, madt);
    }
    
    public void delete(DienThoai x) {
        JDBC.JDBCHelPer.update(delete_sql_insert, x.getMaDT(), x.getTenDienThoai(), x.getThuongHieu(), x.getMaCamera(), x.getMaKetNoi(), x.getMaPinSac(), x.getHeDieuHanh(), x.getMaCPU(), x.getMaTienIch(), x.getManHinh(), x.getMoTa());
    }

    @Override
    public void delete(String id) {
        //JDBC.JDBCHelPer.update(delete_sql, id);
    }

    @Override
    public List<DienThoai> selectAll() {
        return selecBySQL(select_All);
    }

    @Override
    public List<DienThoai> select_Search(String key) {
        return selecBySQL(select_Search, "%" + key + "%", "%" + key + "%", "%" + key + "%", "%" + key + "%");
    }

    @Override
    public List<DienThoai> selecBySQL(String sql, Object... args) {

        ArrayList<DienThoai> list = new ArrayList<>();

        try {
            ResultSet rs = JDBC.JDBCHelPer.query(sql, args);
            while (rs.next()) {
                DienThoai o = new DienThoai();
                o.setMaDT(rs.getString("MaDT"));
                o.setTenDienThoai(rs.getString("TenDienThoai"));
                o.setThuongHieu(rs.getString("ThuongHieu"));
                o.setMaCamera(rs.getString("MaCamera"));
                o.setMaKetNoi(rs.getString("MaKetNoi"));
                o.setMaPinSac(rs.getString("MaPin_Sac"));
                o.setHeDieuHanh(rs.getString("HeDieuHanh"));
                o.setMaCPU(rs.getString("MaCPU"));
                o.setMaTienIch(rs.getString("MaTienIch"));
                o.setManHinh(rs.getString("MaManHinh"));
                o.setMoTa(rs.getString("MoTa"));

                list.add(o);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }
    
    public List<DienThoai> Ten_Tang() {
        return selecBySQL(Sap_Xep_Ten_Tang);
    }
    
    public List<DienThoai> Ten_Giam() {
        return selecBySQL(Sap_Xep_Ten_Giam);
    }
    
    public List<DienThoai> Ma_Tang() {
        return selecBySQL(Sap_Xep_Ma_Tang);
    }
    
    public List<DienThoai> Ma_Giam() {
        return selecBySQL(Sap_Xep_Ma_Giam);
    }

    public List<DienThoai> select_Kiem_Kho() {
        return selecBySQL(Kiem_Kho);
    }

    
}
