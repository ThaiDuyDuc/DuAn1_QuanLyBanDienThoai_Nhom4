package Model;
import java.sql.Timestamp;
public class LichSuDienThoai {
    private String maDT, TenDienThoai, ThuongHieu, MaCamera, MaKetNoi, MaPinSac, HeDieuHanh, MaCPU, MaTienIch, ManHinh, MoTa;
    private Timestamp thoigianxoa;

    public LichSuDienThoai() {
    }

    public LichSuDienThoai(String maDT, String TenDienThoai, String ThuongHieu, String MaCamera, String MaKetNoi, String MaPinSac, String HeDieuHanh, String MaCPU, String MaTienIch, String ManHinh, String MoTa, Timestamp thoigianxoa) {
        this.maDT = maDT;
        this.TenDienThoai = TenDienThoai;
        this.ThuongHieu = ThuongHieu;
        this.MaCamera = MaCamera;
        this.MaKetNoi = MaKetNoi;
        this.MaPinSac = MaPinSac;
        this.HeDieuHanh = HeDieuHanh;
        this.MaCPU = MaCPU;
        this.MaTienIch = MaTienIch;
        this.ManHinh = ManHinh;
        this.MoTa = MoTa;
        this.thoigianxoa = thoigianxoa;
    }

    public String getMaDT() {
        return maDT;
    }

    public void setMaDT(String maDT) {
        this.maDT = maDT;
    }

    public String getTenDienThoai() {
        return TenDienThoai;
    }

    public void setTenDienThoai(String TenDienThoai) {
        this.TenDienThoai = TenDienThoai;
    }

    public String getThuongHieu() {
        return ThuongHieu;
    }

    public void setThuongHieu(String ThuongHieu) {
        this.ThuongHieu = ThuongHieu;
    }

    public String getMaCamera() {
        return MaCamera;
    }

    public void setMaCamera(String MaCamera) {
        this.MaCamera = MaCamera;
    }

    public String getMaKetNoi() {
        return MaKetNoi;
    }

    public void setMaKetNoi(String MaKetNoi) {
        this.MaKetNoi = MaKetNoi;
    }

    public String getMaPinSac() {
        return MaPinSac;
    }

    public void setMaPinSac(String MaPinSac) {
        this.MaPinSac = MaPinSac;
    }

    public String getHeDieuHanh() {
        return HeDieuHanh;
    }

    public void setHeDieuHanh(String HeDieuHanh) {
        this.HeDieuHanh = HeDieuHanh;
    }

    public String getMaCPU() {
        return MaCPU;
    }

    public void setMaCPU(String MaCPU) {
        this.MaCPU = MaCPU;
    }

    public String getMaTienIch() {
        return MaTienIch;
    }

    public void setMaTienIch(String MaTienIch) {
        this.MaTienIch = MaTienIch;
    }

    public String getManHinh() {
        return ManHinh;
    }

    public void setManHinh(String ManHinh) {
        this.ManHinh = ManHinh;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public Timestamp getThoigianxoa() {
        return thoigianxoa;
    }

    public void setThoigianxoa(Timestamp thoigianxoa) {
        this.thoigianxoa = thoigianxoa;
    }

    @Override
    public String toString() {
        return "DienThoai{" + "TenDienThoai=" + TenDienThoai + '}';
    }
    
}
