package View;

import Extensions.quyenDanhNhap;
import Model.BoNho;
import Model.CPU;
import Model.KiemKho;
import Model.Camera;
import Repository.DienThoai_DAO;
import Repository.ServiceDAO;
import javax.swing.table.DefaultTableModel;
import Model.DienThoai;
import Model.DienThoaiChiTiet;
import Model.KetNoi;
import Model.Kho;
import Model.ManHinh;
import Model.PhieuBaoHanh;
import Model.Pin_Sac;
import Model.TienIch;
import Model.NhaSanXuat;
import Model.LoadAnhLenBang;
import Model.DanhMucDienThoai;
import Model.LichSuDienThoai;
import Model.LichSuDienThoai_ChiTiet;
import Model.PhieuGiamGia;
import Repository.BoNho_DAO;
import Repository.Camera_DAO;
import Repository.Chip_DAO;
import Repository.DanhMucDienThoai_DAO;
import Repository.DienThoaiChiTiet_DAO;
import Repository.KetNoi_DAO;
import Repository.Kho_DAO;
import Repository.KiemKho_DAO;
import Repository.LichSuDienThoai_ChiTiet_DAO;
import Repository.LichSuDienThoai_DAO;
import Repository.ManHinh_DAO;
import Repository.NhaSanXuat_DAO;
import Repository.PhieuBaoHanh_DAO;
import Repository.PhieuGiamGia_DAO;
import Repository.Pin_Sac_DAO;
import Repository.TienIch_DAO;
import java.util.ArrayList;
import java.util.List;
import View.SanPham_JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.swing.table.TableModel;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;

public class SanPham_JPanel extends javax.swing.JPanel {

    DienThoai_DAO service_DT = new DienThoai_DAO();
    Camera_DAO service_CAM = new Camera_DAO();
    ServiceDAO service_KN = new KetNoi_DAO();
    ServiceDAO service_PS = new Pin_Sac_DAO();
    ServiceDAO service_Chip = new Chip_DAO();
    ServiceDAO service_TIch = new TienIch_DAO();
    ServiceDAO service_MH = new ManHinh_DAO();
    DienThoaiChiTiet_DAO service_DTCT = new DienThoaiChiTiet_DAO();
    ServiceDAO service_BN = new BoNho_DAO();
    ServiceDAO service_PBH = new PhieuBaoHanh_DAO();
    ServiceDAO service_NSX = new NhaSanXuat_DAO();
    ServiceDAO service_PGG = new PhieuGiamGia_DAO();
    ServiceDAO service_Kho = new Kho_DAO();
    ServiceDAO service_DMDT = new DanhMucDienThoai_DAO();
    LichSuDienThoai_DAO service_LSDT = new LichSuDienThoai_DAO();
    LichSuDienThoai_ChiTiet_DAO service_LSDT_CT = new LichSuDienThoai_ChiTiet_DAO();
    ServiceDAO service_DM = new DanhMucDienThoai_DAO();
    ServiceDAO service_KK = new KiemKho_DAO();

    String path = null;

    public SanPham_JPanel() {
        initComponents();
        LoadTableQuanLyDienThoai();
        LoadTableDienThoaiChiTiet();
        tblBangQuanLyDienThoai.setRowHeight(50);
        tblBangDTCT.setRowHeight(50);
        btnBaoCao.setEnabled(false);
        LoadCbbCamera();
        LoadCbbKetNoi();
        LoadCbbPinSac();
        LoadCbbChip();
        LoadCbbTienIch();
        LoadCbbManHinh();
        LoadCbbBoNho();
        LoadCbbPhieuBaoHanh();
        LoadCbbNhaSanXuat();
        LoadCbbKho();
        LoadCbbMaDT_DTCT();
        cbbLichSu_DTCT.setSelectedIndex(0);
    }

    public void LoadTableQuanLyDienThoai() {
        Object[] columnNames = {"Mã DT", "Tên DT", "Thương hiệu", "Camera", "Kết nối", "Pin sạc", "Hệ điều hành", "Chip", "Tiện ích", "Màn hình", "Mô tả"};
        tblBangQuanLyDienThoai.setModel(new DefaultTableModel(null, columnNames));
        DefaultTableModel model = (DefaultTableModel) this.tblBangQuanLyDienThoai.getModel();
        List<DienThoai> dienThoaiList = service_DT.selectAll();
        List<Camera> camList = service_CAM.selectAll();
        model.setRowCount(0);

        for (DienThoai o : dienThoaiList) {
            Object[] rowData = {
                o.getMaDT(),
                o.getTenDienThoai(),
                o.getThuongHieu(),
                o.getMaCamera(),
                o.getMaKetNoi(),
                o.getMaPinSac(),
                o.getHeDieuHanh(),
                o.getMaCPU(),
                o.getMaTienIch(),
                o.getManHinh(),
                o.getMoTa()
            };
            model.addRow(rowData);
        }
    }

    public void LoadTableQuanLyDienThoai_KiemKho() {
        Object[] columnNames = {"Mã DT", "Tên DT", "Thương hiệu", "Hệ điều hành", "Màu sắc", "Giá bán", "Chip", "Bộ nhớ", "Kho", "SL tồn"};
        tblBangQuanLyDienThoai.setModel(new DefaultTableModel(null, columnNames));

        // đổi màu bảng theo giá trị tồn kho
        tblBangQuanLyDienThoai.setColumnSelectionInterval(0, 0);
        for (int i = 0; i < tblBangQuanLyDienThoai.getColumnCount(); i++) {
            tblBangQuanLyDienThoai.getColumnModel().getColumn(i).setCellRenderer(headerRenderer);
        }
        DefaultTableModel model = (DefaultTableModel) this.tblBangQuanLyDienThoai.getModel();
        List<KiemKho> dienThoaiList = service_KK.selectAll();

        System.out.println("kiểm kho : " + dienThoaiList.toString());
        model.setRowCount(0);

        for (KiemKho o : dienThoaiList) {
            Object[] rowData = {
                o.getMadt(),
                o.getTendt(),
                o.getThuonghieu(),
                o.getHeDieuHanh(),
                o.getMauSac(),
                o.getGiaBan(),
                o.getChip(),
                o.getBonho(),
                o.getKho(),
                o.getSlton()

            };
            model.addRow(rowData);
        }
    }

    public void LoadTableDienThoaiChiTiet() {
        Object[] columnNames = {"Mã DTCT", "Mã DT", "Giá", "Ảnh", "Màu", "SL tồn", "Bộ nhớ", "Kho", "NSX", "Bảo hành", "Trạng thái", "Mô tả"};
        tblBangDTCT.setModel(new DefaultTableModel(null, columnNames));
        // Sử dụng LoadAnhLenBang làm renderer cho cột ảnh
        tblBangDTCT.getColumnModel().getColumn(3).setCellRenderer(new LoadAnhLenBang());
        DefaultTableModel model = (DefaultTableModel) this.tblBangDTCT.getModel();
        List<DienThoaiChiTiet> dienThoaichitietList = service_DTCT.selectAll();
        List<BoNho> bn_list = service_BN.selectAll();
        model.setRowCount(0);
        for (DienThoaiChiTiet o : dienThoaichitietList) {
            ImageIcon anh = new ImageIcon(o.getHinhAnh());
            Object[] rowData = {
                o.getMaDTCT(),
                o.getMaDT(),
                o.getGiaBan(),
                anh,
                o.getMauSac(),
                o.getSoLuongTonKho(),
                o.getMaBoNho(),
                o.getMaKho(),
                o.getMaNSX(),
                o.getMaPhieuBaoHanh(),
                o.isTrangThai() ? "Đang kinh doanh" : "Ngừng kinh doanh",
                o.getMoTa(),};
            model.addRow(rowData);
        }
    }

    public void LoadTableLichSuDienThoai() {
        Object[] columnNames = {"Mã DT", "Tên DT", "Thương hiệu", "Camera", "Kết nối", "Pin sạc", "Hệ điều hành", "Chip", "Tiện ích", "Màn hình", "Mô tả", "Ngày xóa"};
        tblBangQuanLyDienThoai.setModel(new DefaultTableModel(null, columnNames));
        DefaultTableModel model = (DefaultTableModel) this.tblBangQuanLyDienThoai.getModel();
        List<LichSuDienThoai> LSDTList = service_LSDT.selectAll();
        model.setRowCount(0);
        for (LichSuDienThoai o : LSDTList) {
            System.out.println(o.getThoigianxoa());
            Object[] rowData = {
                o.getMaDT(),
                o.getTenDienThoai(),
                o.getThuongHieu(),
                o.getMaCamera(),
                o.getMaKetNoi(),
                o.getMaPinSac(),
                o.getHeDieuHanh(),
                o.getMaCPU(),
                o.getMaTienIch(),
                o.getManHinh(),
                o.getMoTa(),
                o.getThoigianxoa()
            };
            model.addRow(rowData);
        }
    }

    public void LoadTableLichSuDienThoai_ChiTiet() {
        Object[] columnNames = {"Mã DTCT", "Mã DT", "Giá", "Ảnh", "Màu", "SL tồn", "Bộ nhớ", "Kho", "NSX", "Bảo hành", "Trạng thái", "Mô tả", "Ngày xóa"};
        tblBangDTCT.setModel(new DefaultTableModel(null, columnNames));
        // Sử dụng LoadAnhLenBang làm renderer cho cột ảnh
        tblBangDTCT.getColumnModel().getColumn(3).setCellRenderer(new LoadAnhLenBang());
        DefaultTableModel model = (DefaultTableModel) this.tblBangDTCT.getModel();
        List<LichSuDienThoai_ChiTiet> LSDTList_CT = service_LSDT_CT.selectAll();
        model.setRowCount(0);
        for (LichSuDienThoai_ChiTiet o : LSDTList_CT) {
            ImageIcon anh = new ImageIcon(o.getHinhAnh());
            Object[] rowData = {
                o.getMaDTCT(),
                o.getMaDT(),
                o.getGiaBan(),
                anh,
                o.getMauSac(),
                o.getSoLuongTonKho(),
                o.getMaBoNho(),
                o.getMaKho(),
                o.getMaNSX(),
                o.getMaPhieuGiamGia(),
                o.getMaPhieuBaoHanh(),
                o.isTrangThai() ? "Đang kinh doanh" : "Ngừng kinh doanh",
                o.getMoTa(),
                o.getThoigianxoa()
            };
            model.addRow(rowData);
        }
    }

    Color maudo = new Color(255, 99, 71);
    Color mauvang = new Color(240, 230, 140);
    Color[] rowColors = {maudo, mauvang};
    DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus,
                int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
// Lấy giá trị của ô số lượng tồn
            Object quantityValue = table.getValueAt(row, 9); // Thay columnIndexForQuantity bằng chỉ số cột cho ô số lượng tồn

            // Kiểm tra giá trị và đặt màu nền tương ứng
            if (quantityValue instanceof Integer) {
                int quantity = (int) quantityValue;
                if (quantity < 5) {
                    c.setBackground(maudo);
                } else if (quantity < 10) {
                    c.setBackground(mauvang);
                } else {
                    // Màu nền mặc định nếu không thỏa mãn điều kiện nào
                    c.setBackground(table.getBackground());
                }
            } else {
                // Xử lý trường hợp giá trị không phải là Integer
                c.setBackground(table.getBackground());
            }

            return c;
        }
    };

    //------------------------------------------------------------------ CBB ---------------------------------------------------------
    public void LoadCbbCamera() {
        List<Camera> list = service_CAM.selectAll();
        for (Camera o : list) {
            cbbCamera.addItem(o.getMaCamera());
        }
    }

    public void LoadCbbKetNoi() {
        List<KetNoi> list = service_KN.selectAll();
        for (KetNoi o : list) {
            cbbKetNoi.addItem(o.getMaKetNoi());
        }
    }

    public void LoadCbbPinSac() {
        List<Pin_Sac> list = service_PS.selectAll();
        for (Pin_Sac o : list) {
            cbbPinSac.addItem(o.getMaPinSac());
        }
    }

    public void LoadCbbChip() {
        List<CPU> list = service_Chip.selectAll();
        for (CPU o : list) {
            cbbChip.addItem(o.getMaCPU());
        }
    }

    public void LoadCbbTienIch() {
        List<TienIch> list = service_TIch.selectAll();
        for (TienIch o : list) {
            cbbTienIch.addItem(o.getMaTienIch());
        }
    }

    public void LoadCbbManHinh() {
        List<ManHinh> list = service_MH.selectAll();
        for (ManHinh o : list) {
            cbbManHinh.addItem(o.getMaManHinh());
        }
    }

    public void LoadCbbBoNho() {
        List<BoNho> list = service_BN.selectAll();
        for (BoNho o : list) {
            cbbBoNho.addItem(o.getMaBoNho());
        }
    }

    public void LoadCbbPhieuBaoHanh() {
        List<PhieuBaoHanh> list = service_PBH.selectAll();
        cbbPhieuBaoHanh.removeAllItems();
        for (PhieuBaoHanh o : list) {
            cbbPhieuBaoHanh.addItem(o.getMaPhieuBaoHanh());
        }
    }

    public void LoadCbbNhaSanXuat() {
        List<NhaSanXuat> list = service_NSX.selectAll();
        cbbNhaSanXuat.removeAllItems();
        for (NhaSanXuat o : list) {
            cbbNhaSanXuat.addItem(o.getMaNSX());
        }
    }

    public void LoadCbbKho() {
        List<Kho> list = service_Kho.selectAll();
        for (Kho o : list) {
            cbbKhoHang.addItem(o.getMaKho());
        }
    }

    public void LoadCbbMaDT_DTCT() {
        List<DienThoai> list = service_DT.selectAll();
        cbbMaDT_DTCT.removeAllItems();
        for (DienThoai o : list) {
            cbbMaDT_DTCT.addItem(o.getMaDT());
        }
    }

    // hàm trang điện thoại khi chuyển sang lịch sử
    public void KhoiPhuc_DT() {
        int row = this.tblBangQuanLyDienThoai.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn đối tượng để khôi phục");
            return;
        }

        String khoiphuc = tblBangQuanLyDienThoai.getValueAt(row, 0).toString();
        LichSuDienThoai thongtinkhoiphuc = service_LSDT.getSingleLichSuDienThoai(khoiphuc);

        // Hiển thị hộp thoại xác nhận
        try {
            service_LSDT.insert(thongtinkhoiphuc);
            LoadTableQuanLyDienThoai();
            service_LSDT.delete(khoiphuc);
            LoadTableLichSuDienThoai();
            JOptionPane.showMessageDialog(this, "Khôi phục thành công");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi hệ thống khôi phục dữ liệu" + e.getMessage());
        }

    }

    public void XoaKhoiHeThong_DT() {
        int row = this.tblBangQuanLyDienThoai.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn đối tượng để Xóa");
            return;
        }
        int x = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa khỏi hệ thống không.", "Thông báo", JOptionPane.YES_NO_OPTION);
        try {
            if (x == 0) {
                String macanxoa = tblBangQuanLyDienThoai.getValueAt(row, 0).toString();
                service_LSDT.delete(macanxoa);
                LoadTableLichSuDienThoai();
                JOptionPane.showMessageDialog(this, "Xóa thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Xóa không thành công");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }

    public void KhoiPhuc_DTCT() {
        int row = tblBangDTCT.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn đối tượng để khôi phục");
        }
        String khoiphuc = tblBangDTCT.getValueAt(row, 0).toString();
        System.out.println("khoiphuc_ lsdtct  " + khoiphuc);
        LichSuDienThoai_ChiTiet thongtinkhoiphuc = service_LSDT_CT.getSingleLichSuDienThoai(khoiphuc);

        // Hiển thị hộp thoại xác nhận
        try {
            service_LSDT_CT.insert(thongtinkhoiphuc);
            LoadTableDienThoaiChiTiet();
            service_LSDT_CT.delete(khoiphuc);
            LoadTableLichSuDienThoai_ChiTiet();
            JOptionPane.showMessageDialog(this, "Khôi phục thành công");
            LamMoi_DTCT();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi hệ thống khôi phục dữ liệu" + e.getMessage());
        }
    }

    public void XoaKhoiHeThong_DTCT() {
        int row = tblBangDTCT.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn đối tượng để xóa");
        }
        int x = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không.", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (x == 0) {
            String macanxoa = tblBangDTCT.getValueAt(row, 0).toString();
            service_LSDT_CT.delete(macanxoa);
            LoadTableLichSuDienThoai_ChiTiet();
            JOptionPane.showMessageDialog(this, "Xóa thành công");
        }
    }

    //------------------------------------------------------------------- ĐIỆN THOẠI ------------------------------------------------------------------
    DienThoai dataform_DienThoai() {
        String madt = txtMaDT.getText().trim();
        String tendt = txtTenDT.getText().trim();
        String thuonghieu = txtThuongHieu.getText().trim();
        String camera = cbbCamera.getSelectedItem().toString();
        String KetNoi = cbbKetNoi.getSelectedItem().toString();
        String Pin_Sac = cbbPinSac.getSelectedItem().toString();
        String HDH = txtHeDieuHanh.getText().trim();
        String Chip = cbbChip.getSelectedItem().toString();
        String TienIch = cbbTienIch.getSelectedItem().toString();
        String ManHinh = cbbManHinh.getSelectedItem().toString();
        String mota = txtMoTa.getText().trim();

        System.out.println(Chip);

        try {
            if (madt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã không được rỗng");
                txtMaDT.requestFocus();
                return null;
            }

            if (tendt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên điện thoại không được rỗng");
                txtTenDT.requestFocus();
                return null;
            }

            if (thuonghieu.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Thương hiệu không được rỗng");
                txtThuongHieu.requestFocus();
                return null;
            }

            if (HDH.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Hệ điều hành không được rỗng");
                txtHeDieuHanh.requestFocus();
                return null;
            }

            DienThoai dt = new DienThoai(madt, tendt, thuonghieu, camera, KetNoi, Pin_Sac, HDH, Chip, TienIch, ManHinh, mota);
            return dt;
        } catch (Exception e) {
            return null;
        }
    }

    public void search_DienThoai() {
        String title = txtTimKiemQLDT.getText().trim();

        try {
            List<DienThoai> dtList = service_DT.select_Search(title);

            if (dtList == null) {
                JOptionPane.showMessageDialog(this, "Dữ liệu trả về từ service_DT.select_Search(title) là null");
                return;
            }
            DefaultTableModel model = (DefaultTableModel) tblBangQuanLyDienThoai.getModel();
            model.setRowCount(0);

            for (DienThoai o : dtList) {
                Object[] rowData = {
                    o.getMaDT(),
                    o.getTenDienThoai(),
                    o.getThuongHieu(),
                    o.getMaCamera(),
                    o.getMaKetNoi(),
                    o.getMaPinSac(),
                    o.getHeDieuHanh(),
                    o.getMaCPU(),
                    o.getMaTienIch(),
                    o.getManHinh(),
                    o.getMoTa()
                };

                // In dữ liệu để kiểm tra
                for (Object value : rowData) {
                    System.out.print(value + " ");
                }
                System.out.println();

                model.addRow(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lammoi_QLDT() {
        LoadTableQuanLyDienThoai();
        cbbSapXepTheoQLDT.setSelectedIndex(0);
        txtMaDT.setText("");
        txtTenDT.setText("");
        txtThuongHieu.setText("");
        cbbCamera.setSelectedIndex(0);
        cbbKetNoi.setSelectedIndex(0);
        cbbPinSac.setSelectedIndex(0);
        txtHeDieuHanh.setText("");
        cbbChip.setSelectedIndex(0);
        cbbTienIch.setSelectedIndex(0);
        cbbManHinh.setSelectedIndex(0);
        txtMoTa.setText("");
        cbbLichSuDT.setSelectedIndex(0);
    }

    public void Them_QLDT() {
        //          cần đăng nhập để thêm

//        if (!quyenDanhNhap.isLogin()) {
//            JOptionPane.showMessageDialog(this, "Vui lòng đăng nhập để thêm sản phẩm.");
//            return;
//        }
        if (btnThem.getText().equalsIgnoreCase("Thêm")) {
            DienThoai dt = dataform_DienThoai();
            if (dt == null) {
                return;
            }
            try {
                service_DT.insert(dt);
                LoadTableQuanLyDienThoai();
                LoadCbbMaDT_DTCT();
                JOptionPane.showMessageDialog(this, "Thêm thành công");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
                e.printStackTrace();
            }
        } else {
            KhoiPhuc_DT();
        }
    }

    public void Sua_QLDT() {
        //          cần đăng nhập để sửa

//        if (!quyenDanhNhap.isLogin()) {
//            JOptionPane.showMessageDialog(this, "Vui lòng đăng nhập để sửa sản phẩm.");
//            return;
//        }
        int row = this.tblBangQuanLyDienThoai.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn hàng để sửa");
            return;
        }

        DienThoai dt = dataform_DienThoai();
        if (dt == null) {
            return;
        }
        try {
            service_DT.update(dt);
            LoadTableQuanLyDienThoai();
            JOptionPane.showMessageDialog(this, "Sửa thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
            e.printStackTrace();
        }
    }

    public void Xoa_QLDT() {
        // Kiểm tra quyền quản lý trước khi xóa sản phẩm
        if (!quyenDanhNhap.isManager("Quản lý")) {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền Xóa.");
            return;
        }

        if (btnXoa.getText().equalsIgnoreCase("Xóa")) {
            int row = this.tblBangQuanLyDienThoai.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng click chọn hàng để xóa.");
                return;
            }
            String macanxoa = tblBangQuanLyDienThoai.getValueAt(row, 0).toString();

            // lấy thông tin cần xóa Chuyển về kiểu điện thoại chi tiết
            DienThoai thongtinluuvaothungrac = service_DT.getSingleLichSuDienThoai(macanxoa);

            try {
                int index = JOptionPane.showConfirmDialog(this, "Xóa thông tin sản phẩm này có thể ảnh hưởng \n đến các dữ liệu khác", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

                System.out.println(index);
                if (index == 0) {
                    if (thongtinluuvaothungrac == null) {
                        JOptionPane.showMessageDialog(this, "Không thấy thông tin để lưu vào thùng rác");
                    }
                    service_DT.delete(thongtinluuvaothungrac);
                    LoadTableLichSuDienThoai();
                    LoadTableQuanLyDienThoai();
                    service_DT.delete(macanxoa);
                    LoadTableQuanLyDienThoai();
                    LoadTableDienThoaiChiTiet();
                    LoadTableLichSuDienThoai_ChiTiet();
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    lammoi_QLDT();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Xóa thất bại, Lỗi hệ thống!");
                e.printStackTrace();
            }
        } else {
            XoaKhoiHeThong_DT();
        }
    }

    // ------------------------------------------------------------- ĐIỆN THOẠI CHI TIẾT ------------------------------------------------------------
    private String getCellValue_DTCT(int row, int column) {
        Object cellValue = tblBangDTCT.getValueAt(row, column);
        return (cellValue != null) ? cellValue.toString().trim() : "";
    }

    public String imgs() {
        JFileChooser filechoose = new JFileChooser();
        filechoose.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int values = filechoose.showOpenDialog(this);

        if (values == 0) {
            File file = filechoose.getSelectedFile();
            System.out.println("File: " + file);
            // Lấy đường dẫn của tập tin ảnh đã chọn
            path = file.getAbsolutePath();

            try {
                BufferedImage b = ImageIO.read(file);

                if (b != null) {
                    lblIMGS.setText("");
                    lblIMGS.setIcon(new ImageIcon(b));

                    // Trả về đường dẫn của tập tin ảnh
                    return path;
                } else {
                    lblIMGS.setText("Chọn ảnh");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    DienThoaiChiTiet dataformDTCT() {
        String madtct = txtMaDTCT.getText().trim();
        String madt = cbbMaDT_DTCT.getSelectedItem().toString();
        String gia = txtGiaBan.getText().trim();
        String hinhanh = path;
        String mau = txtMauSac.getText().trim();
        String tonko = txtSLTonKho.getText().trim();
        String bonho = cbbBoNho.getSelectedItem().toString();
        String kho = cbbKhoHang.getSelectedItem().toString();
        String NSX = cbbNhaSanXuat.getSelectedItem().toString();
        String PhieuBaoHanh = cbbPhieuBaoHanh.getSelectedItem().toString();
        boolean trangthai = rdoDangKinhDoanh.isSelected() == true ? true : false;
        String mota = txtMoTaDTCT.getText().trim();
        System.out.println(hinhanh);

        try {
            if (madtct.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã không được rỗng");
                txtMaDTCT.requestFocus();
                return null;
            }

            if (gia.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Giá không được rỗng");
                txtGiaBan.requestFocus();
                return null;
            }

            if (mau.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nàu sắc không được rỗng");
                txtMauSac.requestFocus();
                return null;
            }

            if (madt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã không được rỗng");
                txtMaDT.requestFocus();
                return null;
            }

            if (tonko.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Số lượng tồn kho không được rỗng");
                txtSLTonKho.requestFocus();
                return null;
            }

            try {
                BigDecimal giaban = new BigDecimal(gia);
                try {
                    int slTonKho = Integer.parseInt(tonko);
                    DienThoaiChiTiet o = new DienThoaiChiTiet(madtct, madt, giaban, hinhanh, mau, slTonKho, bonho, kho, NSX, PhieuBaoHanh, trangthai, mota);
                    return o;
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Số lượng tồn kho phải viết số");
                    return null;
                }
            } catch (HeadlessException headlessException) {
                headlessException.printStackTrace();
                JOptionPane.showMessageDialog(this, "Giá phải viết số");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Giá bán phải nhập số.");
            return null;
        }
    }

    public void Them_DTCT() {
        //          kiểm tra đã dăng nhập chưa trước khi thêm

//        if (!quyenDanhNhap.isLogin()) {
//            JOptionPane.showMessageDialog(this, "Vui lòng đăng nhập để sửa sản phẩm.");
//            return;
//        }
        if (btnThemDTCT.getText().equalsIgnoreCase("Thêm") && cbbLichSu_DTCT.getSelectedIndex() == 0) {
            DienThoaiChiTiet dt = dataformDTCT();
            System.out.println(dt);
            if (dt == null) {
                return;
            }
            try {
                service_DTCT.insert(dt);
                LoadTableDienThoaiChiTiet();
                JOptionPane.showMessageDialog(this, "Thêm thành công");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
                e.printStackTrace();
            }
        } else {
            KhoiPhuc_DTCT();
        }
    }

    public void Sua_DTCT() {
        //          kiểm tra đã dăng nhập chưa trước khi sửa

//        if (!quyenDanhNhap.isLogin()) {
//            JOptionPane.showMessageDialog(this, "Vui lòng đăng nhập để sửa sản phẩm.");
//            return;
//        }
        int row = this.tblBangDTCT.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn hàng muốn sửa");
            return;
        }

        DienThoaiChiTiet dt = dataformDTCT();
        System.out.println(dt);
        if (dt == null) {
            return;
        }

        try {
            service_DTCT.update(dt);
            LoadTableDienThoaiChiTiet();
            LoadTableQuanLyDienThoai_KiemKho();
            LoadTableQuanLyDienThoai();
            LamMoi_DTCT();
            JOptionPane.showMessageDialog(this, "Sửa thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
            e.printStackTrace();
        }
    }

    public void Xoa_DTCT() {
        // Kiểm tra quyền quản lý trước khi xóa sản phẩm
        if (!quyenDanhNhap.isManager("Quản lý")) {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền Xóa.");
            return;
        }
        if (cbbLichSu_DTCT.getSelectedIndex() == 0) {
            int row = this.tblBangDTCT.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng click chọn hàng để xóa.");
                return;
            }
            String macanxoa = tblBangDTCT.getValueAt(row, 0).toString();
            // lấy thông tin cần xóa
            DienThoaiChiTiet thongtinluuvaothungrac = service_DTCT.getSingleLichSuDienThoai(macanxoa);
            try {

                int index = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

                if (index == 0) {
                    service_DTCT.delete(thongtinluuvaothungrac);
                    LoadTableDienThoaiChiTiet();
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    LamMoi_DTCT();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Xóa thất bại, Lỗi hệ thống!");
                e.printStackTrace();
            }
        }

        if (cbbLichSu_DTCT.getSelectedIndex() == 1 && btnThemDTCT.getText().equalsIgnoreCase("Khôi phục")) {
            XoaKhoiHeThong_DTCT();
        }

    }

    public void LamMoi_DTCT() {
        LoadTableDienThoaiChiTiet();
        txtMauSac.setText("");
        txtSLTonKho.setText("");
        txtMaDTCT.setText("");
        txtGiaBan.setText("");
        txtMoTaDTCT.setText("");
        cbbSapXepDTCT.setSelectedIndex(0);
        cbbMaDT_DTCT.setSelectedIndex(0);
        cbbBoNho.setSelectedIndex(0);
        cbbKhoHang.setSelectedIndex(0);
        cbbPhieuBaoHanh.setSelectedIndex(0);
        cbbNhaSanXuat.setSelectedIndex(0);
        lblIMGS.setText("Chọn ảnh");
        lblIMGS.setIcon(null);
        cbbLichSu_DTCT.setSelectedIndex(0);
    }

    public void Search_DTCT() {
        if (txtTimKiemDTCT.getText().isEmpty()) {
            LoadTableDienThoaiChiTiet();
        } else {
            String title = txtTimKiemDTCT.getText().trim();
            try {
                List<DienThoaiChiTiet> dtList = service_DTCT.select_Search(title);

                if (dtList == null) {
                    JOptionPane.showMessageDialog(this, "Dữ liệu trả về từ service_DT.select_Search(title) là null");
                    return;
                }
                DefaultTableModel model = (DefaultTableModel) tblBangDTCT.getModel();
                model.setRowCount(0);

                for (DienThoaiChiTiet o : dtList) {
                    Object[] rowData = {
                        o.getMaDTCT(),
                        o.getMaDT(),
                        o.getGiaBan(),
                        o.getHinhAnh(),
                        o.getMauSac(),
                        o.getSoLuongTonKho(),
                        //                service_BN.search_Ma_LoadTable(bn_list, o.getMaBoNho()),
                        o.getMaBoNho(),
                        o.getMaKho(),
                        o.getMaNSX(),
                        o.getMaPhieuBaoHanh(),
                        o.isTrangThai() ? "Đang kinh doanh" : "Ngừng kinh doanh",
                        o.getMoTa()
                    };

                    // In dữ liệu để kiểm tra
                    for (Object value : rowData) {
                        System.out.print(value + " ");
                    }
                    System.out.println();

                    model.addRow(rowData);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void SapXep_QLDT() {
        if (cbbSapXepTheoQLDT.getSelectedIndex() == 1) {
            DefaultTableModel model = (DefaultTableModel) this.tblBangQuanLyDienThoai.getModel();
            List<DienThoai> dienThoaiList = service_DT.Ten_Tang();
            List<Camera> camList = service_CAM.selectAll();
            model.setRowCount(0);

            for (DienThoai o : dienThoaiList) {
                Object[] rowData = {
                    o.getMaDT(),
                    o.getTenDienThoai(),
                    o.getThuongHieu(),
                    //                service_CAM.search_Ma_LoadTable(camList, o.getMaCamera()),
                    o.getMaCamera(),
                    o.getMaKetNoi(),
                    o.getMaPinSac(),
                    o.getHeDieuHanh(),
                    o.getMaCPU(),
                    o.getMaTienIch(),
                    o.getManHinh(),
                    o.getMoTa()
                };
                model.addRow(rowData);

            }
        } else if (cbbSapXepTheoQLDT.getSelectedIndex() == 2) {
            DefaultTableModel model = (DefaultTableModel) this.tblBangQuanLyDienThoai.getModel();
            List<DienThoai> dienThoaiList = service_DT.Ten_Giam();
            List<Camera> camList = service_CAM.selectAll();
            model.setRowCount(0);

            for (DienThoai o : dienThoaiList) {
                Object[] rowData = {
                    o.getMaDT(),
                    o.getTenDienThoai(),
                    o.getThuongHieu(),
                    o.getMaCamera(),
                    o.getMaKetNoi(),
                    o.getMaPinSac(),
                    o.getHeDieuHanh(),
                    o.getMaCPU(),
                    o.getMaTienIch(),
                    o.getManHinh(),
                    o.getMoTa()
                };
                model.addRow(rowData);
            }
        } else if (cbbSapXepTheoQLDT.getSelectedIndex() == 3) {
            DefaultTableModel model = (DefaultTableModel) this.tblBangQuanLyDienThoai.getModel();
            List<DienThoai> dienThoaiList = service_DT.Ma_Tang();;
            List<Camera> camList = service_CAM.selectAll();
            model.setRowCount(0);

            for (DienThoai o : dienThoaiList) {
                Object[] rowData = {
                    o.getMaDT(),
                    o.getTenDienThoai(),
                    o.getThuongHieu(),
                    //                service_CAM.search_Ma_LoadTable(camList, o.getMaCamera()),
                    o.getMaCamera(),
                    o.getMaKetNoi(),
                    o.getMaPinSac(),
                    o.getHeDieuHanh(),
                    o.getMaCPU(),
                    o.getMaTienIch(),
                    o.getManHinh(),
                    o.getMoTa()
                };
                model.addRow(rowData);
            }
        } else if (cbbSapXepTheoQLDT.getSelectedIndex() == 4) {
            DefaultTableModel model = (DefaultTableModel) this.tblBangQuanLyDienThoai.getModel();
            List<DienThoai> dienThoaiList = service_DT.Ma_Giam();;
            List<Camera> camList = service_CAM.selectAll();
            model.setRowCount(0);

            for (DienThoai o : dienThoaiList) {
                Object[] rowData = {
                    o.getMaDT(),
                    o.getTenDienThoai(),
                    o.getThuongHieu(),
                    //                service_CAM.search_Ma_LoadTable(camList, o.getMaCamera()),
                    o.getMaCamera(),
                    o.getMaKetNoi(),
                    o.getMaPinSac(),
                    o.getHeDieuHanh(),
                    o.getMaCPU(),
                    o.getMaTienIch(),
                    o.getManHinh(),
                    o.getMoTa()
                };
                model.addRow(rowData);
            }
        } else {
            LoadTableQuanLyDienThoai();
        }

    }

    public void SapXep_DTCT() {
        if (cbbSapXepDTCT.getSelectedIndex() == 1) {
            Object[] columnNames = {"Mã DTCT", "Mã DT", "Giá", "Ảnh", "Màu", "SL tồn", "Bộ nhớ", "Kho", "NSX", "Bảo hành", "Trạng thái", "Mô tả"};
            tblBangDTCT.setModel(new DefaultTableModel(null, columnNames));
            // Sử dụng LoadAnhLenBang làm renderer cho cột ảnh
            tblBangDTCT.getColumnModel().getColumn(3).setCellRenderer(new LoadAnhLenBang());
            DefaultTableModel model = (DefaultTableModel) this.tblBangDTCT.getModel();
            List<DienThoaiChiTiet> dienThoaichitietList = service_DTCT.select_TonKho_Tang();
            List<BoNho> bn_list = service_BN.selectAll();
            model.setRowCount(0);
            for (DienThoaiChiTiet o : dienThoaichitietList) {
                ImageIcon anh = new ImageIcon(o.getHinhAnh());
                Object[] rowData = {
                    o.getMaDTCT(),
                    o.getMaDT(),
                    o.getGiaBan(),
                    anh,
                    o.getMauSac(),
                    o.getSoLuongTonKho(),
                    o.getMaBoNho(),
                    o.getMaKho(),
                    o.getMaNSX(),
                    o.getMaPhieuBaoHanh(),
                    o.isTrangThai() ? "Đang kinh doanh" : "Ngừng kinh doanh",
                    o.getMoTa(),};
                model.addRow(rowData);
            }
        } else if (cbbSapXepDTCT.getSelectedIndex() == 2) {
            Object[] columnNames = {"Mã DTCT", "Mã DT", "Giá", "Ảnh", "Màu", "SL tồn", "Bộ nhớ", "Kho", "NSX", "Bảo hành", "Trạng thái", "Mô tả"};
            tblBangDTCT.setModel(new DefaultTableModel(null, columnNames));
            // Sử dụng LoadAnhLenBang làm renderer cho cột ảnh
            tblBangDTCT.getColumnModel().getColumn(3).setCellRenderer(new LoadAnhLenBang());
            DefaultTableModel model = (DefaultTableModel) this.tblBangDTCT.getModel();
            List<DienThoaiChiTiet> dienThoaichitietList = service_DTCT.select_TonKho_Giam();
            List<BoNho> bn_list = service_BN.selectAll();
            model.setRowCount(0);
            for (DienThoaiChiTiet o : dienThoaichitietList) {
                ImageIcon anh = new ImageIcon(o.getHinhAnh());
                Object[] rowData = {
                    o.getMaDTCT(),
                    o.getMaDT(),
                    o.getGiaBan(),
                    anh,
                    o.getMauSac(),
                    o.getSoLuongTonKho(),
                    o.getMaBoNho(),
                    o.getMaKho(),
                    o.getMaNSX(),
                    o.getMaPhieuBaoHanh(),
                    o.isTrangThai() ? "Đang kinh doanh" : "Ngừng kinh doanh",
                    o.getMoTa(),};
                model.addRow(rowData);
            }
        } else {
            LoadTableDienThoaiChiTiet();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cbbCamera = new javax.swing.JComboBox<>();
        btnAddCamera = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cbbKetNoi = new javax.swing.JComboBox<>();
        btnAddKetNoi = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cbbPinSac = new javax.swing.JComboBox<>();
        btnAddPinSac = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cbbChip = new javax.swing.JComboBox<>();
        btnAddChip = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        cbbTienIch = new javax.swing.JComboBox<>();
        btnAddTienIch = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        cbbManHinh = new javax.swing.JComboBox<>();
        btnAddManHinh = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        txtTenDT = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtThuongHieu = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtHeDieuHanh = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtMaDT = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBangQuanLyDienThoai = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbbSapXepTheoQLDT = new javax.swing.JComboBox<>();
        txtTimKiemQLDT = new javax.swing.JTextField();
        cbbLichSuDT = new javax.swing.JComboBox<>();
        btnBaoCao = new javax.swing.JButton();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBangDTCT = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiemDTCT = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbbSapXepDTCT = new javax.swing.JComboBox<>();
        cbbLichSu_DTCT = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        lblIMGS = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtMaDTCT = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        cbbMaDT_DTCT = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtMoTaDTCT = new javax.swing.JTextArea();
        btnThemDTCT = new javax.swing.JButton();
        btnSuaDTCT = new javax.swing.JButton();
        btnXoaDTCT = new javax.swing.JButton();
        btnLamMoiDTCT = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txtSLTonKho = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        cbbBoNho = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        cbbKhoHang = new javax.swing.JComboBox<>();
        btnKhoHang = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        AddBoNho = new javax.swing.JButton();
        txtMauSac = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        rdoDangKinhDoanh = new javax.swing.JRadioButton();
        rdoNgungKinhDoanh = new javax.swing.JRadioButton();
        jLabel18 = new javax.swing.JLabel();
        cbbPhieuBaoHanh = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        cbbNhaSanXuat = new javax.swing.JComboBox<>();
        btnAddPhieuBaoHanh = new javax.swing.JButton();
        btnAddNhaSanXuat = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông số kỹ thuật", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel7.setText("Camera");

        btnAddCamera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/add (2).png"))); // NOI18N
        btnAddCamera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCameraActionPerformed(evt);
            }
        });

        jLabel8.setText("Kết nối");

        btnAddKetNoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/add (2).png"))); // NOI18N
        btnAddKetNoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddKetNoiActionPerformed(evt);
            }
        });

        jLabel9.setText("Pin & sạc");

        btnAddPinSac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/add (2).png"))); // NOI18N
        btnAddPinSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPinSacActionPerformed(evt);
            }
        });

        jLabel10.setText("Chip");

        btnAddChip.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/add (2).png"))); // NOI18N
        btnAddChip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddChipActionPerformed(evt);
            }
        });

        jLabel11.setText("Tiện ích");

        btnAddTienIch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/add (2).png"))); // NOI18N
        btnAddTienIch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTienIchActionPerformed(evt);
            }
        });

        jLabel12.setText("Màn Hình");

        btnAddManHinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/add (2).png"))); // NOI18N
        btnAddManHinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddManHinhActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel6.setText("Tên điện thoại");

        jLabel13.setText("Thương hiệu");

        jLabel14.setText("Hệ điều hành");

        jLabel15.setText("Mã điện thoại");

        jLabel16.setText("Mô tả");

        txtMoTa.setColumns(15);
        txtMoTa.setRows(5);
        jScrollPane3.setViewportView(txtMoTa);

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel10)
                        .addComponent(cbbChip, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddChip, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7)
                        .addComponent(cbbCamera, 0, 127, Short.MAX_VALUE)
                        .addComponent(btnAddCamera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8)
                            .addComponent(cbbKetNoi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAddKetNoi, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9)
                            .addComponent(cbbPinSac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAddPinSac, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11)
                            .addComponent(cbbTienIch, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAddTienIch, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12)
                            .addComponent(cbbManHinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAddManHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel15)
                        .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenDT, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtThuongHieu, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtHeDieuHanh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(txtMaDT, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbPinSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAddPinSac))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbKetNoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAddKetNoi))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbCamera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAddCamera)))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbChip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAddChip))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbTienIch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAddTienIch))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbManHinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAddManHinh))))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMaDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15)
                                .addComponent(jLabel16)
                                .addComponent(btnThem))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addGap(3, 3, 3))
                                            .addComponent(txtTenDT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel13)
                                            .addComponent(txtThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel14)
                                            .addComponent(txtHeDieuHanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jScrollPane3))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(btnSua)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnXoa)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnLamMoi))))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách điện thoại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblBangQuanLyDienThoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã DT", "Tên", "Thương hiệu", "Camera", "Kêt nối", "Pin & Sạc", "Hệ điều hành", "Chip", "Tiện ích", "Màn hình", "Mô tả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBangQuanLyDienThoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangQuanLyDienThoaiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblBangQuanLyDienThoaiMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tblBangQuanLyDienThoai);

        jLabel2.setText("Tìm kiếm");

        jLabel3.setText("Sắp xếp theo");

        cbbSapXepTheoQLDT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sắp xếp", "Tên tăng dần", "Tên giảm dần", "Mã tăng dần", "Mã giảm dần" }));
        cbbSapXepTheoQLDT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbSapXepTheoQLDTItemStateChanged(evt);
            }
        });

        txtTimKiemQLDT.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtTimKiemQLDT.setForeground(new java.awt.Color(153, 153, 153));
        txtTimKiemQLDT.setText("Tìm kiếm theo mã, tên, thương hiệu, hệ điều hành");
        txtTimKiemQLDT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimKiemQLDTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTimKiemQLDTFocusLost(evt);
            }
        });
        txtTimKiemQLDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemQLDTKeyPressed(evt);
            }
        });

        cbbLichSuDT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Điện thoại", "Lịch sử điện thoại", "Kiểm tra tồn kho" }));
        cbbLichSuDT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLichSuDTItemStateChanged(evt);
            }
        });

        btnBaoCao.setText("Báo cáo");
        btnBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaoCaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiemQLDT, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(cbbSapXepTheoQLDT, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(cbbLichSuDT, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnBaoCao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(cbbSapXepTheoQLDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbLichSuDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBaoCao))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtTimKiemQLDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane2.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lý điện thoại", jLayeredPane2);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblBangDTCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã DTCT", "Mã DT", "Giá", "Hình", "Màu", "Tồn kho", "Bộ nhớ", "Kho", "NSX", "Bảo hành", "Trạng thái", "Mô tả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBangDTCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangDTCTMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblBangDTCT);
        if (tblBangDTCT.getColumnModel().getColumnCount() > 0) {
            tblBangDTCT.getColumnModel().getColumn(5).setPreferredWidth(50);
        }

        jLabel1.setText("Tìm kiếm");

        txtTimKiemDTCT.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtTimKiemDTCT.setForeground(new java.awt.Color(153, 153, 153));
        txtTimKiemDTCT.setText("MaDTCT, MaDT, Giá, Màu, Bộ nhớ, kho, NSX");
        txtTimKiemDTCT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimKiemDTCTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTimKiemDTCTFocusLost(evt);
            }
        });
        txtTimKiemDTCT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemDTCTKeyPressed(evt);
            }
        });

        jLabel4.setText("Sắp xếp");

        cbbSapXepDTCT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sắp xếp", "SL tồn tăng dần", "SL tồn giảm dần" }));
        cbbSapXepDTCT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbSapXepDTCTItemStateChanged(evt);
            }
        });

        cbbLichSu_DTCT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Điện thoại chi tiết", "Lịch sử điện thoại chi tiết" }));
        cbbLichSu_DTCT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLichSu_DTCTItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiemDTCT, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbSapXepDTCT, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(cbbLichSu_DTCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 21, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTimKiemDTCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cbbSapXepDTCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbLichSu_DTCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        lblIMGS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIMGS.setText("Chọn ảnh");
        lblIMGS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIMGSMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIMGS, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIMGS, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
        );

        jLabel25.setText("Mã điện thoại chi tiết");

        jLabel26.setText("Mã điện thoại");

        cbbMaDT_DTCT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel27.setText("Giá bán");

        jLabel28.setText("Mô tả");

        txtMoTaDTCT.setColumns(15);
        txtMoTaDTCT.setRows(5);
        jScrollPane4.setViewportView(txtMoTaDTCT);

        btnThemDTCT.setText("Thêm");
        btnThemDTCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDTCTActionPerformed(evt);
            }
        });

        btnSuaDTCT.setText("Sửa");
        btnSuaDTCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaDTCTActionPerformed(evt);
            }
        });

        btnXoaDTCT.setText("Xóa");
        btnXoaDTCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDTCTActionPerformed(evt);
            }
        });

        btnLamMoiDTCT.setText("Làm mới");
        btnLamMoiDTCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiDTCTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel25)
                            .addComponent(txtMaDTCT)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                            .addComponent(jLabel28)
                            .addComponent(cbbMaDT_DTCT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnThemDTCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnXoaDTCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnSuaDTCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnLamMoiDTCT, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaDTCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbMaDT_DTCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemDTCT)
                    .addComponent(btnSuaDTCT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaDTCT)
                    .addComponent(btnLamMoiDTCT))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel22.setText("Số lượng tồn kho");

        jLabel23.setText("Bộ nhớ");

        jLabel24.setText("Kho hàng");

        btnKhoHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/add (2).png"))); // NOI18N
        btnKhoHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoHangActionPerformed(evt);
            }
        });

        jLabel21.setText("Màu sắc");

        AddBoNho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/add (2).png"))); // NOI18N
        AddBoNho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBoNhoActionPerformed(evt);
            }
        });

        jLabel17.setText("Trạng thái");

        buttonGroup1.add(rdoDangKinhDoanh);
        rdoDangKinhDoanh.setSelected(true);
        rdoDangKinhDoanh.setText("Đang kinh doanh");

        buttonGroup1.add(rdoNgungKinhDoanh);
        rdoNgungKinhDoanh.setText("Ngừng kinh doanh");

        jLabel18.setText("Phiếu bảo hành");

        cbbPhieuBaoHanh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel20.setText("Nhà sản xuất");

        cbbNhaSanXuat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAddPhieuBaoHanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/add (2).png"))); // NOI18N
        btnAddPhieuBaoHanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPhieuBaoHanhActionPerformed(evt);
            }
        });

        btnAddNhaSanXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/add (2).png"))); // NOI18N
        btnAddNhaSanXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNhaSanXuatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel23)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(cbbBoNho, 0, 177, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(AddBoNho, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtSLTonKho, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMauSac, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17)
                            .addComponent(jLabel20))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(cbbNhaSanXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAddNhaSanXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(rdoDangKinhDoanh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                .addComponent(rdoNgungKinhDoanh))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(cbbPhieuBaoHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAddPhieuBaoHanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(24, 24, 24))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(cbbKhoHang, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnKhoHang, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(rdoDangKinhDoanh)
                    .addComponent(rdoNgungKinhDoanh))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(txtSLTonKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18)
                        .addComponent(cbbPhieuBaoHanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAddPhieuBaoHanh))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(cbbNhaSanXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnAddNhaSanXuat))
                    .addComponent(AddBoNho)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23)
                        .addComponent(cbbBoNho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24)
                        .addComponent(cbbKhoHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnKhoHang))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane3.setLayer(jPanel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane3Layout = new javax.swing.GroupLayout(jLayeredPane3);
        jLayeredPane3.setLayout(jLayeredPane3Layout);
        jLayeredPane3Layout.setHorizontalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Điện thoại chi tiết", jLayeredPane3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 625, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        Them_QLDT();
    }//GEN-LAST:event_btnThemActionPerformed

    private void AddBoNhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBoNhoActionPerformed
        JDialog_BoNho bonho = new JDialog_BoNho(null, true);
        bonho.setVisible(true);
    }//GEN-LAST:event_AddBoNhoActionPerformed

    private void btnAddPhieuBaoHanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPhieuBaoHanhActionPerformed
        JDialog_PhieuBaoHanh baohanh = new JDialog_PhieuBaoHanh(null, true);
        baohanh.setVisible(true);
    }//GEN-LAST:event_btnAddPhieuBaoHanhActionPerformed

    private void btnAddNhaSanXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNhaSanXuatActionPerformed
        JDialog_NhaSanXuat nsx = new JDialog_NhaSanXuat(null, true);
        nsx.setVisible(true);
    }//GEN-LAST:event_btnAddNhaSanXuatActionPerformed

    private void btnAddCameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCameraActionPerformed
        Frame SanPham_JPanel = null;
        JDialog_Camera cam = new JDialog_Camera(SanPham_JPanel, true);
        cam.setVisible(true);
    }//GEN-LAST:event_btnAddCameraActionPerformed

    private void btnAddKetNoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddKetNoiActionPerformed
        Frame SanPham_JPanel = null;
        JDialog_KetNoi o = new JDialog_KetNoi(SanPham_JPanel, true);
        o.setVisible(true);
    }//GEN-LAST:event_btnAddKetNoiActionPerformed

    private void btnAddPinSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPinSacActionPerformed
        Frame SanPham_JPanel = null;
        JDialog_PinSac o = new JDialog_PinSac(SanPham_JPanel, true);
        o.setVisible(true);
    }//GEN-LAST:event_btnAddPinSacActionPerformed

    private void btnAddChipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddChipActionPerformed
        Frame SanPham_JPanel = null;
        JDialog_Chip o = new JDialog_Chip(SanPham_JPanel, true);
        o.setVisible(true);
    }//GEN-LAST:event_btnAddChipActionPerformed

    private void btnAddTienIchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTienIchActionPerformed
        Frame SanPham_JPanel = null;
        JDialog_TienIch o = new JDialog_TienIch(SanPham_JPanel, true);
        o.setVisible(true);
    }//GEN-LAST:event_btnAddTienIchActionPerformed

    private void btnAddManHinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddManHinhActionPerformed
        Frame SanPham_JPanel = null;
        JDialog_Manhinh o = new JDialog_Manhinh(SanPham_JPanel, true);
        o.setVisible(true);
    }//GEN-LAST:event_btnAddManHinhActionPerformed

    private void txtTimKiemQLDTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemQLDTKeyPressed
        search_DienThoai();
    }//GEN-LAST:event_txtTimKiemQLDTKeyPressed

    private void txtTimKiemQLDTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemQLDTFocusGained
        txtTimKiemQLDT.setText("");
        Font font1 = new Font("Segoe UI", Font.PLAIN, 12);
        txtTimKiemQLDT.setFont(font1);
        txtTimKiemQLDT.setForeground(Color.black);
    }//GEN-LAST:event_txtTimKiemQLDTFocusGained

    private void txtTimKiemQLDTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemQLDTFocusLost
        Font font2 = new Font("Segoe UI", Font.ITALIC, 12);
        txtTimKiemQLDT.setText("Tìm kiếm theo mã, tên, thương hiệu, hệ điều hành");
        txtTimKiemQLDT.setFont(font2);
        txtTimKiemQLDT.setForeground(Color.gray);
    }//GEN-LAST:event_txtTimKiemQLDTFocusLost

    private void tblBangQuanLyDienThoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangQuanLyDienThoaiMouseClicked
        int row = this.tblBangQuanLyDienThoai.getSelectedRow();
        if (row == -1) {
            return;
        }
        txtMaDT.setText(tblBangQuanLyDienThoai.getValueAt(row, 0).toString().trim());
        txtTenDT.setText(tblBangQuanLyDienThoai.getValueAt(row, 1).toString().trim());
        txtThuongHieu.setText(tblBangQuanLyDienThoai.getValueAt(row, 2).toString().trim());
        cbbCamera.setSelectedItem(tblBangQuanLyDienThoai.getValueAt(row, 3).toString());
        cbbKetNoi.setSelectedItem(tblBangQuanLyDienThoai.getValueAt(row, 4).toString());
        cbbPinSac.setSelectedItem(tblBangQuanLyDienThoai.getValueAt(row, 5).toString());
        txtHeDieuHanh.setText(tblBangQuanLyDienThoai.getValueAt(row, 6).toString().trim());
        cbbChip.setSelectedItem(tblBangQuanLyDienThoai.getValueAt(row, 7).toString());
        cbbTienIch.setSelectedItem(tblBangQuanLyDienThoai.getValueAt(row, 8).toString());
        cbbManHinh.setSelectedItem(tblBangQuanLyDienThoai.getValueAt(row, 9).toString());
        txtMoTa.setText(tblBangQuanLyDienThoai.getValueAt(row, 10).toString().trim());
    }//GEN-LAST:event_tblBangQuanLyDienThoaiMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        lammoi_QLDT();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        Sua_QLDT();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        Xoa_QLDT();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblBangDTCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangDTCTMouseClicked
        int row = this.tblBangDTCT.getSelectedRow();
        if (row == -1) {
            return;
        }

        txtMaDTCT.setText(tblBangDTCT.getValueAt(row, 0).toString().trim());
        cbbMaDT_DTCT.setSelectedItem(tblBangDTCT.getValueAt(row, 1).toString());
        txtGiaBan.setText(tblBangDTCT.getValueAt(row, 2).toString().trim());

        // đọc hình ảnh lên lbl
        Object cellValue = tblBangDTCT.getValueAt(row, 3);

        String hinhanh;
        if (cellValue != null && cellValue instanceof ImageIcon) {
            ImageIcon icon = (ImageIcon) cellValue;

            // Lấy đường dẫn ban đầu khi tạo ImageIcon
            hinhanh = icon.getDescription();

            if (hinhanh != null && !hinhanh.isEmpty()) {
                path = hinhanh;
                System.out.println("Cell value: " + hinhanh);
                lblIMGS.setIcon(icon);
                lblIMGS.setText(""); // Đảm bảo là văn bản được xóa
            } else {
                lblIMGS.setText("Không có hình ảnh");
                lblIMGS.setIcon(null);
            }
        } else {
            lblIMGS.setText("Không có hình ảnh");
            lblIMGS.setIcon(null);
        }
        int maxWidth = lblIMGS.getWidth();
        int maxHeight = lblIMGS.getHeight();

        txtMauSac.setText(tblBangDTCT.getValueAt(row, 4).toString().trim());
        txtSLTonKho.setText(tblBangDTCT.getValueAt(row, 5).toString().trim());
        cbbBoNho.setSelectedItem(tblBangDTCT.getValueAt(row, 6).toString());
        cbbKhoHang.setSelectedItem(tblBangDTCT.getValueAt(row, 7).toString());
        cbbNhaSanXuat.setSelectedItem(tblBangDTCT.getValueAt(row, 8).toString());

        Object baohanhObject = tblBangDTCT.getValueAt(row, 9);
        String baohanh = baohanhObject != null ? baohanhObject.toString().trim() : "";
        cbbPhieuBaoHanh.setSelectedItem(baohanh);

        String trangThai = tblBangDTCT.getValueAt(row, 10).toString();
        if ("Đang kinh doanh".equalsIgnoreCase(trangThai)) {
            rdoDangKinhDoanh.setSelected(true);
        } else {
            rdoNgungKinhDoanh.setSelected(true);
        }

        txtMoTaDTCT.setText(tblBangDTCT.getValueAt(row, 11).toString().trim());
    }//GEN-LAST:event_tblBangDTCTMouseClicked

    private void btnThemDTCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDTCTActionPerformed
        Them_DTCT();
    }//GEN-LAST:event_btnThemDTCTActionPerformed

    private void btnSuaDTCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaDTCTActionPerformed
        Sua_DTCT();
    }//GEN-LAST:event_btnSuaDTCTActionPerformed

    private void btnXoaDTCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDTCTActionPerformed
        Xoa_DTCT();
    }//GEN-LAST:event_btnXoaDTCTActionPerformed

    private void lblIMGSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIMGSMouseClicked
        if (evt.getClickCount() == 2) {
            imgs();
        }
    }//GEN-LAST:event_lblIMGSMouseClicked

    private void btnLamMoiDTCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiDTCTActionPerformed
        LamMoi_DTCT();
    }//GEN-LAST:event_btnLamMoiDTCTActionPerformed

    private void txtTimKiemDTCTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemDTCTFocusGained
        txtTimKiemDTCT.setText("");
        Font font1 = new Font("Segoe UI", Font.PLAIN, 12);
        txtTimKiemDTCT.setFont(font1);
        txtTimKiemDTCT.setForeground(Color.black);
    }//GEN-LAST:event_txtTimKiemDTCTFocusGained

    private void txtTimKiemDTCTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemDTCTFocusLost
        Font font2 = new Font("Segoe UI", Font.ITALIC, 12);
        txtTimKiemDTCT.setText("Tìm MaDTCT, MaDT, Giá, Màu, Bộ nhớ, kho, NSX");
        txtTimKiemDTCT.setFont(font2);
        txtTimKiemDTCT.setForeground(Color.gray);
    }//GEN-LAST:event_txtTimKiemDTCTFocusLost

    private void txtTimKiemDTCTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemDTCTKeyPressed
        Search_DTCT();
    }//GEN-LAST:event_txtTimKiemDTCTKeyPressed

    private void btnKhoHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoHangActionPerformed
        JDialog_Kho kho = new JDialog_Kho(null, true);
        kho.setVisible(true);
    }//GEN-LAST:event_btnKhoHangActionPerformed

    private void cbbSapXepTheoQLDTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbSapXepTheoQLDTItemStateChanged
        SapXep_QLDT();
    }//GEN-LAST:event_cbbSapXepTheoQLDTItemStateChanged

    private void cbbLichSuDTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLichSuDTItemStateChanged
        if (cbbLichSuDT.getSelectedItem().toString().equalsIgnoreCase("Điện thoại")) {
            LoadTableQuanLyDienThoai();
            tblBangQuanLyDienThoai.setEnabled(true);
            btnThem.setText("Thêm");
            btnXoa.setText("Xóa");
            btnSua.setEnabled(true);
            cbbSapXepTheoQLDT.setEnabled(true);
            btnThem.setEnabled(true);
            btnSua.setEnabled(true);
            btnXoa.setEnabled(true);
            btnLamMoi.setEnabled(true);
            btnBaoCao.setEnabled(false);
        } else if (cbbLichSuDT.getSelectedItem().toString().equalsIgnoreCase("Lịch sử điện thoại")) {
            LoadTableLichSuDienThoai();
            btnSua.setEnabled(false);
            tblBangQuanLyDienThoai.setEnabled(true);
            btnThem.setText("Khôi phục DL");
            btnXoa.setText("Xóa khỏi HT");
            cbbSapXepTheoQLDT.setEnabled(false);
            btnThem.setEnabled(true);
            btnSua.setEnabled(true);
            btnXoa.setEnabled(true);
            btnLamMoi.setEnabled(true);
            btnBaoCao.setEnabled(false);
        } else {
            LoadTableQuanLyDienThoai_KiemKho();
            tblBangQuanLyDienThoai.setEnabled(false);
            btnBaoCao.setEnabled(true);
            cbbSapXepTheoQLDT.setEnabled(false);
            btnThem.setEnabled(false);
            btnSua.setEnabled(false);
            btnXoa.setEnabled(false);
            btnLamMoi.setEnabled(false);
        }
    }//GEN-LAST:event_cbbLichSuDTItemStateChanged

    private void cbbLichSu_DTCTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLichSu_DTCTItemStateChanged
        if (cbbLichSu_DTCT.getSelectedIndex() == 0) {
            LoadTableDienThoaiChiTiet();
            btnThemDTCT.setText("Thêm");
            btnXoaDTCT.setText("Xóa");
            btnSuaDTCT.setEnabled(true);
        } else if (cbbLichSu_DTCT.getSelectedIndex() == 1) {
            LoadTableLichSuDienThoai_ChiTiet();
            btnThemDTCT.setText("Khôi phục");
            btnSuaDTCT.setEnabled(false);
        }
    }//GEN-LAST:event_cbbLichSu_DTCTItemStateChanged

    private void cbbSapXepDTCTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbSapXepDTCTItemStateChanged
        SapXep_DTCT();
    }//GEN-LAST:event_cbbSapXepDTCTItemStateChanged

    public static void sendEmail(JTable tblBangQuanLyDienThoai) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedDate = dateFormat.format(currentDate);
        // Hiển thị JDialog để nhập mật khẩu và địa chỉ email
        BaoCaoKho_JDialog bc = new BaoCaoKho_JDialog(null, true);
        bc.setVisible(true);

        // Kiểm tra xem người dùng đã nhập thông tin đúng chưa
        if (bc.isConfirmed()) {

            // Thông tin tài khoản email của bạn
            final String fromEmail = bc.getNgươiGui();
            // Lấy thông tin email và mật khẩu từ JDialog
            final String password = bc.getPassWord();

            // Địa chỉ email người nhận
            final String toEmail = bc.getNgươiNhan().toString();
            System.out.println(fromEmail);
            System.out.println(password);
            System.out.println(toEmail);
            // Cấu hình properties cho session
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true"); // TLS

            // Tạo đối tượng Session
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            try {
                // Tạo đối tượng MimeMessage
                MimeMessage message = new MimeMessage(session);

                // Đặt thông tin người gửi, người nhận và tiêu đề
                message.setFrom(new InternetAddress(fromEmail));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
                message.setSubject("Cửa hàng bán điện thoại");

                // Lấy dữ liệu từ bảng và tạo chuỗi HTML
                StringBuilder htmlContent = new StringBuilder();
                htmlContent.append("<html><body><h2>Nhân viên " + quyenDanhNhap.user.getMaNV() + " Báo cáo số lượng tồn kho " + formattedDate + "</h2><br/><br/><table border='1'>");
                htmlContent.append("<tr>");

                // Thêm các tiêu đề cột
                for (int i = 0; i < tblBangQuanLyDienThoai.getColumnCount(); i++) {
                    htmlContent.append("<th>").append(tblBangQuanLyDienThoai.getColumnName(i)).append("</th>");
                }

                htmlContent.append("</tr>");

                // Thêm dữ liệu từ bảng
                for (int row = 0; row < tblBangQuanLyDienThoai.getRowCount(); row++) {
                    htmlContent.append("<tr>");

                    for (int col = 0; col < tblBangQuanLyDienThoai.getColumnCount(); col++) {
                        htmlContent.append("<td>").append(tblBangQuanLyDienThoai.getValueAt(row, col)).append("</td>");
                    }

                    htmlContent.append("</tr>");
                }

                htmlContent.append("</table></body></html>");

                // Đặt nội dung của email
                message.setContent(htmlContent.toString(), "text/html; charset=utf-8");

                // Gửi email
               
//                    Transport.send(message);
                    My_thread tr = new My_thread();
                    tr.messenger = message;
                    tr.start();

                    // Sử dụng JFrame để hiển thị JOptionPane
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "Gửi thành công");
                    System.out.println("Email sent successfully!");
               
            } catch (MessagingException e) {
                e.printStackTrace();
                // Sử dụng JFrame để hiển thị JOptionPane
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "Gửi thất bại");
            }
        }
    }

    private void btnBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaoCaoActionPerformed
        sendEmail(tblBangQuanLyDienThoai);
    }//GEN-LAST:event_btnBaoCaoActionPerformed

    private void tblBangQuanLyDienThoaiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangQuanLyDienThoaiMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblBangQuanLyDienThoaiMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddBoNho;
    private javax.swing.JButton btnAddCamera;
    private javax.swing.JButton btnAddChip;
    private javax.swing.JButton btnAddKetNoi;
    private javax.swing.JButton btnAddManHinh;
    private javax.swing.JButton btnAddNhaSanXuat;
    private javax.swing.JButton btnAddPhieuBaoHanh;
    private javax.swing.JButton btnAddPinSac;
    private javax.swing.JButton btnAddTienIch;
    private javax.swing.JButton btnBaoCao;
    private javax.swing.JButton btnKhoHang;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLamMoiDTCT;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaDTCT;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemDTCT;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaDTCT;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbBoNho;
    private javax.swing.JComboBox<String> cbbCamera;
    private javax.swing.JComboBox<String> cbbChip;
    private javax.swing.JComboBox<String> cbbKetNoi;
    private javax.swing.JComboBox<String> cbbKhoHang;
    private javax.swing.JComboBox<String> cbbLichSuDT;
    private javax.swing.JComboBox<String> cbbLichSu_DTCT;
    private javax.swing.JComboBox<String> cbbMaDT_DTCT;
    private javax.swing.JComboBox<String> cbbManHinh;
    private javax.swing.JComboBox<String> cbbNhaSanXuat;
    private javax.swing.JComboBox<String> cbbPhieuBaoHanh;
    private javax.swing.JComboBox<String> cbbPinSac;
    private javax.swing.JComboBox<String> cbbSapXepDTCT;
    private javax.swing.JComboBox<String> cbbSapXepTheoQLDT;
    private javax.swing.JComboBox<String> cbbTienIch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblIMGS;
    private javax.swing.JRadioButton rdoDangKinhDoanh;
    private javax.swing.JRadioButton rdoNgungKinhDoanh;
    private javax.swing.JTable tblBangDTCT;
    private javax.swing.JTable tblBangQuanLyDienThoai;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtHeDieuHanh;
    private javax.swing.JTextField txtMaDT;
    private javax.swing.JTextField txtMaDTCT;
    private javax.swing.JTextField txtMauSac;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextArea txtMoTaDTCT;
    private javax.swing.JTextField txtSLTonKho;
    private javax.swing.JTextField txtTenDT;
    private javax.swing.JTextField txtThuongHieu;
    private javax.swing.JTextField txtTimKiemDTCT;
    private javax.swing.JTextField txtTimKiemQLDT;
    // End of variables declaration//GEN-END:variables
}
