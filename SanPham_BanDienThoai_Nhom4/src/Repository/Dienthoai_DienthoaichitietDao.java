/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import JDBC.jdbchelper1;
import Model.Dienthoai_Dienthoaichitiet;
import Model.model_dieukienapdung;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Dienthoai_DienthoaichitietDao extends EduSysDao<Dienthoai_Dienthoaichitiet, Object> {

    String select_all = """
                        select * from DienThoai
                        inner join DienThoaiChiTiet 
                        on DienThoai.MaDT = DienThoaiChiTiet.MaDT""";
    String select_madtct = """
                           select * from DienThoai
                           inner join DienThoaiChiTiet 
                           on DienThoai.MaDT = DienThoaiChiTiet.MaDT
                           where DienThoai.MaDT = ?""";
    String search = " select * from DienThoai\n"
            + "                           inner join DienThoaiChiTiet \n"
            + "                           on DienThoai.MaDT = DienThoaiChiTiet.MaDT\n"
            + "                           where DienThoai.TenDienThoai like ? or DienThoaiChiTiet.SLTonKho like ? or DienThoaiChiTiet.MauSac like ?";

    @Override
    public void insert(Dienthoai_Dienthoaichitiet entily) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Dienthoai_Dienthoaichitiet entily) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Dienthoai_Dienthoaichitiet> getmadtct(String madt) {
        return select_sql(select_madtct, madt);
    }

    @Override
    public void delete(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Dienthoai_Dienthoaichitiet> selectAll() {
        return select_sql(select_all);
    }

    public Dienthoai_Dienthoaichitiet getSingleLichSuDienThoai(String Xoa) {
        List<Dienthoai_Dienthoaichitiet> danhSachKetQua = select_search(Xoa);

        if (!danhSachKetQua.isEmpty()) {
            return danhSachKetQua.get(0);
        } else {
            return null;
        }
    }

    public List<Dienthoai_Dienthoaichitiet> select_search(String id) {
        return select_sql(search, "%"+id+"%", "%"+id+"%", "%"+id+"%");
    }

    @Override
    public Dienthoai_Dienthoaichitiet select_id(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Dienthoai_Dienthoaichitiet> select_sql(String sql, Object... args) {
        List<Dienthoai_Dienthoaichitiet> list = new ArrayList<>();
        try {
            ResultSet rs = jdbchelper1.query(sql, args);
            while (rs.next()) {
                Dienthoai_Dienthoaichitiet dtdtct = new Dienthoai_Dienthoaichitiet();
                dtdtct.setMadt(rs.getString("MaDT"));
                dtdtct.setMadtct(rs.getString("madtct"));
                dtdtct.setDongia(rs.getString("giaban"));
                dtdtct.setMausac(rs.getString("mausac"));
                dtdtct.setSoluongton(Integer.parseInt(rs.getString("sltonkho")));
                dtdtct.setTendt(rs.getString("tendienthoai"));
                list.add(dtdtct);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
