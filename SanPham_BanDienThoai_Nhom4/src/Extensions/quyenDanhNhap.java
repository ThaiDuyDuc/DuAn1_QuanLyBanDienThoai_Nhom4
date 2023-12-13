package Extensions;

import Model.NhanVien;

public class quyenDanhNhap {

    // đối tượng chứa thong tin đang nhập người dùng
    public static NhanVien user = null;

    // xóa thông tin của người dùng khi có yêu cầu đăng xuất
    public static void clear() {
        quyenDanhNhap.user = null;
    }

    // kiểm tra đăng nhập hay chưa
    // return đăng nhập hay chưa
    public static boolean isLogin() {
        return quyenDanhNhap.user != null;
    }

    // kiểm tra quyền
    public static boolean isManager(String tenCV) {
        return quyenDanhNhap.isLogin() && user.getChucVu().trim().equalsIgnoreCase(tenCV);
    }
}
