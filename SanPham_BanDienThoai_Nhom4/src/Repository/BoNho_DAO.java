package Repository;

import Model.BoNho;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class BoNho_DAO extends ServiceDAO<BoNho, String> {

    final String insert_sql = "INSERT INTO BoNho (MaBoNho, RAM, DungLuongLuuTru, DungLuongKhaDung, DanhBa)  VALUES (?,?,?,?,?);";
    final String update_sql = "UPDATE BoNho SET  RAM = ?, DungLuongLuuTru = ?, DungLuongKhaDung = ?, DanhBa = ?  WHERE MaBoNho = ? ";
    final String delete_sql = "DELETE FROM BoNho WHERE MaBoNho = ? ";
    final String select_All = "SELECT * FROM BoNho";
    final String select_Search = "SELECT * FROM BoNho WHERE MaBoNho like ? ";

    @Override
    public void insert(BoNho x) {
        JDBC.JDBCHelPer.update(insert_sql, x.getMaBoNho(), x.getRAM(), x.getDungLuongLuuTru(), x.getDungLuongKhaDung(), x.getDanhBa());
    }
    
    public BoNho getSingleLichSuDienThoai(String Xoa) {
        List<BoNho> danhSachKetQua = select_Search(Xoa);

        if (!danhSachKetQua.isEmpty()) {
            return danhSachKetQua.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void update(BoNho x) {
        JDBC.JDBCHelPer.update(update_sql, x.getRAM(), x.getDungLuongLuuTru(), x.getDungLuongKhaDung(), x.getDanhBa(), x.getMaBoNho());
    }

    @Override
    public void delete(String id) {
        JDBC.JDBCHelPer.update(delete_sql, id);
    }

    @Override
    public List<BoNho> selectAll() {
        return selecBySQL(select_All);
    }

    @Override
    public List<BoNho> select_Search(String key) {
        return selecBySQL(select_Search, "%" + key + "%");
    }

    @Override
    public List<BoNho> selecBySQL(String sql, Object... args) {
        ArrayList<BoNho> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.JDBCHelPer.query(sql, args);
            while (rs.next()) {
                String mabonho = rs.getString(1);
                int ram = rs.getInt(2);
                int luutru = rs.getInt(3);
                int khadung = rs.getInt(4);
                String danhba = rs.getString(5);

                BoNho o = new BoNho(mabonho, ram, luutru, khadung, danhba);
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

//    @Override
//    public String search_Ma_LoadTable(List<BoNho> list, String keyword) {
//        StringBuilder result = new StringBuilder();
//
//        for (BoNho o : list) {
//            // chuyển kiểu số về kiểu String 
//            int ram = o.getRAM();
//            String Sram = String.valueOf(ram);
//            String dllt = String.valueOf(o.getDungLuongLuuTru());
//            String dlkd = String.valueOf(o.getDungLuongKhaDung());
//
//            // Kiểm tra xem từ khoá có xuất hiện trong bất kỳ thuộc tính nào của Camera không
//            if (o.getMaBoNho().contains(keyword)
//                    || Sram.contains(keyword)
//                    || dllt.contains(keyword)
//                    || dlkd.contains(keyword)
//                    || o.getDanhBa().contains(keyword)) {
//
//                // Nếu có, thêm thông tin của Camera vào chuỗi kết quả
////                result.append("Camera: ").append(camera.getMaCamera()).append("\n");
//                result.append(o.getRAM());
////                result.append("Tn_camtruoc: ").append(camera.getTinhNang_CAMTruoc()).append("\n");
////                result.append("Camsau: ").append(camera.getCAM_Sau()).append("\n");
////                result.append("Tn_camsau: ").append(camera.getTinhNang_CAMSau()).append("\n");
////                result.append("\n");
//            }
//        }
//
//        // Kiểm tra xem có kết quả nào được tìm thấy không
////        if (result.length() == "Search results:\n".length()) {
////            result.append("No matching cameras found.");
////        }
//        return result.toString();
//    }

}
