package Model;

import java.awt.Image;
import java.util.Date;

public class NhanVien {
    private String MaNV, TenNV, DiaChi, NgaySinh,Anh, SDT, GioiTinh, MatKhau, NgayTao, TrangThai, Emaill, ChucVu;

    public NhanVien() {
    }

    public NhanVien(String MaNV, String TenNV, String DiaChi, String NgaySinh, String Anh, String SDT, String GioiTinh, String MatKhau, String NgayTao, String TrangThai, String Emaill, String ChucVu) {
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.DiaChi = DiaChi;
        this.NgaySinh = NgaySinh;
        this.Anh = Anh;
        this.SDT = SDT;
        this.GioiTinh = GioiTinh;
        this.MatKhau = MatKhau;
        this.NgayTao = NgayTao;
        this.TrangThai = TrangThai;
        this.Emaill = Emaill;
        this.ChucVu = ChucVu;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String Anh) {
        this.Anh = Anh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String NgayTao) {
        this.NgayTao = NgayTao;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getEmaill() {
        return Emaill;
    }

    public void setEmaill(String Emaill) {
        this.Emaill = Emaill;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String ChucVu) {
        this.ChucVu = ChucVu;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "MaNV=" + MaNV + ", TenNV=" + TenNV + ", DiaChi=" + DiaChi + ", NgaySinh=" + NgaySinh + ", Anh=" + Anh + ", SDT=" + SDT + ", GioiTinh=" + GioiTinh + ", MatKhau=" + MatKhau + ", NgayTao=" + NgayTao + ", TrangThai=" + TrangThai + ", Emaill=" + Emaill + ", ChucVu=" + ChucVu + '}';
    }

    
    
}
