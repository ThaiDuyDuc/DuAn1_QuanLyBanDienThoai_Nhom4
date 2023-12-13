package Repository;

import Model.KetNoi;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class KetNoi_DAO extends ServiceDAO<KetNoi, String> {

    final String insert_sql = "INSERT INTO KetNoi (MaKetNoi, SIM, wifi, Bluetooth, CongSac)  VALUES (?,?,?,?,?);";
    final String update_sql = "UPDATE KetNoi SET  SIM = ?, wifi = ?, Bluetooth = ?, CongSac =? WHERE MaKetNoi = ? ";
    final String delete_sql = "BEGIN\n"
            + "	DECLARE @MaKetNoi1 NCHAR(15)\n"
            + "	SET @MaKetNoi1 = ? \n"
            + "	-- XÓA DỮ LIỆU BẢNG ĐIỆN THOẠI \n"
            + "	IF EXISTS (SELECT 1 FROM DienThoai WHERE MaKetNoi = @MaKetNoi1)\n"
            + "		BEGIN \n"
            + "			DECLARE @MaDT NCHAR(15),\n"
            + "					@TenDienThoai NVARCHAR(50),\n"
            + "					@ThuongHieu NVARCHAR(50),\n"
            + "					@MaCamera NCHAR(15),\n"
            + "					@MaKetNoi NCHAR(15),\n"
            + "					@MaPin_Sac NCHAR(15),\n"
            + "					@HeDieuHanh NVARCHAR(50),\n"
            + "					@MaCPU NCHAR(15),\n"
            + "					@MaTienIch NCHAR(15),\n"
            + "					@MaManHinh NCHAR(15),\n"
            + "					@MoTa NVARCHAR(1000)\n"
            + "\n"
            + "			SELECT \n"
            + "				 @MaDT = MaDT\n"
            + "				,@TenDienThoai=TenDienThoai\n"
            + "				,@ThuongHieu=ThuongHieu\n"
            + "				,@MaCamera = MaCamera\n"
            + "				,@MaKetNoi = MaKetNoi\n"
            + "				,@MaPin_Sac = MaPin_Sac\n"
            + "				,@HeDieuHanh = HeDieuHanh\n"
            + "				,@MaCPU = MaCPU\n"
            + "				,@MaTienIch = MaTienIch\n"
            + "				,@MaManHinh = MaManHinh\n"
            + "				,@MoTa = MoTa \n"
            + "			FROM DienThoai WHERE MaKetNoi = @MaKetNoi1\n"
            + "\n"
            + "			-- XÓA DỮ LIỆU BẢNG ĐIỆN THOẠI VÀ THỰC HIỆN CÁC HÀNH ĐỘNG NHƯ KHI XÓA ĐIỆN THOẠI LƯU VÀO THÙNG RÁC \n"
            + "			EXEC XoaDT_Save_LSDT @MaDT, @TenDienThoai, @ThuongHieu, @MaCamera, @MaKetNoi, @MaPin_Sac, @HeDieuHanh, @MaCPU, @MaTienIch, @MaManHinh, @MoTa\n"
            + "		END;\n"
            + "	-- XÓA DỮ LIỆU BẢNG  KẾT NỐI\n"
            + "	IF EXISTS (SELECT 1 FROM KetNoi WHERE MaKetNoi = @MaKetNoi1)\n"
            + "		BEGIN \n"
            + "			DELETE FROM KetNoi WHERE MaKetNoi = @MaKetNoi1\n"
            + "		END;\n"
            + "END";
    final String select_All = "SELECT * FROM KetNoi";
    final String select_Search = "SELECT * FROM KetNoi WHERE MaKetNoi = ? ";

    @Override
    public void insert(KetNoi x) {
        JDBC.JDBCHelPer.update(insert_sql, x.getMaKetNoi(), x.getSim(), x.getWifi(), x.getBluetooth(), x.getCongSac());
    }

    @Override
    public void update(KetNoi x) {
        JDBC.JDBCHelPer.update(insert_sql, x.getSim(), x.getWifi(), x.getBluetooth(), x.getCongSac(), x.getMaKetNoi());
    }

    @Override
    public void delete(String id) {
        JDBC.JDBCHelPer.update(delete_sql, id);
    }

    @Override
    public List<KetNoi> selectAll() {
        return selecBySQL(select_All);
    }

    @Override
    public List<KetNoi> select_Search(String key) {
        return selecBySQL(select_Search, "%" + key + "%");
    }

    @Override
    public List<KetNoi> selecBySQL(String sql, Object... args) {
        ArrayList<KetNoi> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.JDBCHelPer.query(sql, args);
            while (rs.next()) {
                String maketnoi = rs.getString(1);
                String sim = rs.getString(2);
                String wifi = rs.getString(3);
                String bluetooth = rs.getString(4);
                String congsac = rs.getString(5);

                KetNoi o = new KetNoi(maketnoi, sim, wifi, bluetooth, congsac);
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }

}
