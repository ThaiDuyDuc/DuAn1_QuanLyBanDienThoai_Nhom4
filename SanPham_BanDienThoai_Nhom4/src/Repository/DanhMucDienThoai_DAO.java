package Repository;

import Model.DanhMucDienThoai;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class DanhMucDienThoai_DAO extends ServiceDAO<DanhMucDienThoai, String> {

    final String insert_sql = "INSERT INTO DanhMucDienThoai (MaDanhMuc, MaDienThoai, TenDanhMuc, LoaiSanPham, NgayTao, NgayCapNhat) VALUES(?,?,?,?,?,?);";
    final String update_sql = "UPDATE DienThoai SET MaDienThoai = ?, TenDanhMuc = ?, LoaiSanPham = ?, NgayTao = ?, NgayCapNhat = ? where MaDanhMuc = ? ";
    final String delete_sql = "DELETE FROM DanhMucDienThoai WHERE MaDanhMuc = ? ";
    final String select_All = "SELECT * FROM DanhMucDienThoai";
    final String select_Search = "SELECT * FROM DanhMucDienThoai WHERE MaDanhMuc like ? ";

    @Override
    public void insert(DanhMucDienThoai x) {
        JDBC.JDBCHelPer.update(insert_sql, x.getMaDM(), x.getMaDT(), x.getTenDM(), x.getLoaiDT(), x.getNgayTao(), x.getNgayCapNhat());
    }

    @Override
    public void update(DanhMucDienThoai x) {
        JDBC.JDBCHelPer.update(insert_sql, x.getMaDT(), x.getTenDM(), x.getLoaiDT(), x.getNgayTao(), x.getNgayCapNhat(), x.getMaDM());
    }

    @Override
    public void delete(String id) {
        JDBC.JDBCHelPer.update(delete_sql, id);
    }

    @Override
    public List<DanhMucDienThoai> selectAll() {
        return selecBySQL(select_All);
    }

    @Override
    public List<DanhMucDienThoai> select_Search(String key) {
        return selecBySQL(select_Search, "%" + key + "%");
    }

    @Override
    public List<DanhMucDienThoai> selecBySQL(String sql, Object... args) {
         ArrayList<DanhMucDienThoai> list = new ArrayList<>();

        try {
            ResultSet rs = JDBC.JDBCHelPer.query(sql, args);
            while (rs.next()) {
               int madm = rs.getInt(1);
               String madt = rs.getString(2);
               String tendm = rs.getString(3);
               String loaidt = rs.getString(4);
               Date ngaytao = rs.getDate(5);
               Date ngaycapnhat = rs.getDate(6);

               DanhMucDienThoai o = new DanhMucDienThoai(madm, madt, tendm, loaidt, ngaytao, ngaycapnhat);
                list.add(o);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }


}
