package Repository;

import Model.Camera;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Camera_DAO extends ServiceDAO<Camera, String> {

    final String insert_sql = "INSERT INTO Camera (MaCamera, CAMTruoc, TinhNang_CAMTruoc, CAMSau, TinhNang_CAMSau) VALUES (?,?,?,?,?);";
    final String update_sql = "UPDATE Camera SET  CAMTruoc = ? , TinhNang_CAMTruoc = ? , CAMSau = ?, TinhNang_CAMSau = ?  WHERE MaCamera = ? ";
    final String delete_sql = "DELETE FROM Camera WHERE MaCamera = ? ";
    final String select_All = "SELECT * FROM Camera";
    final String select_Search = "SELECT * FROM Camera WHERE MaCamera like ? ";
    final String select_id = "SELECT CAMTruoc FROM Camera WHERE MaCamera = ? ";

    @Override
    public void insert(Camera x) {
        JDBC.JDBCHelPer.update(insert_sql, x.getMaCamera(), x.getCAM_truoc(), x.getTinhNang_CAMTruoc(), x.getCAM_Sau(), x.getTinhNang_CAMSau());
    }

    @Override
    public void update(Camera x) {
        JDBC.JDBCHelPer.update(update_sql, x.getCAM_truoc(), x.getTinhNang_CAMTruoc(), x.getCAM_Sau(), x.getTinhNang_CAMSau(), x.getMaCamera());
    }

    @Override
    public void delete(String id) {
        JDBC.JDBCHelPer.update(delete_sql, id);
    }

    @Override
    public List<Camera> selectAll() {
        return selecBySQL(select_All);
    }

    @Override
    public List<Camera> select_Search(String key) {
        return selecBySQL(select_Search, "%" + key + "%");
    }

    @Override
    public List<Camera> selecBySQL(String sql, Object... args) {
        ArrayList<Camera> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.JDBCHelPer.query(sql, args);
            while (rs.next()) {
                String macam = rs.getString(1);
                String camtruoc = rs.getString(2);
                String tn_camtruoc = rs.getString(3);
                String camsau = rs.getString(4);
                String tn_camsau = rs.getString(5);

                Camera o = new Camera(macam, camtruoc, tn_camtruoc, camsau, tn_camsau);
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }

//    @Override
//    public String search_Ma_LoadTable(List<Camera> list, String keyword) {
//        StringBuilder result = new StringBuilder();
//
//        for (Camera camera : list) {
//            // Kiểm tra xem từ khoá có xuất hiện trong bất kỳ thuộc tính nào của Camera không
//            if (camera.getMaCamera().contains(keyword)
//                    || camera.getCAM_truoc().contains(keyword)
//                    || camera.getTinhNang_CAMTruoc().contains(keyword)
//                    || camera.getCAM_Sau().contains(keyword)
//                    || camera.getTinhNang_CAMSau().contains(keyword)) {
//
//                // Nếu có, thêm thông tin của Camera vào chuỗi kết quả
////                result.append("Camera: ").append(camera.getMaCamera()).append("\n");
//                result.append(camera.getCAM_truoc());
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
//
//    //    public void searchAndPrintResults(String keyword) {
////        List<Camera> cameraList = selecBySQL("SELECT CAMTruoc FROM Camera");
////        String searchResult = searchCamera(cameraList, keyword);
////        // Bạn có thể thực hiện các hành động khác với kết quả tìm kiếm nếu cần
////    }
}
