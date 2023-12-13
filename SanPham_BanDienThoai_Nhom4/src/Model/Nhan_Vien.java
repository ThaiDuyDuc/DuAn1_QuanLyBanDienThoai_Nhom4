/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author Acer
 */
public class Nhan_Vien {
    private String MaNV, TenNV, DiaChi, NgaySinh, SDT,Anh, GioiTinh, MatKhau, NgayTao, TrangThai, Emaill, ChucVu;

    public Nhan_Vien() {
    }

    public Nhan_Vien(String MaNV, String TenNV, String DiaChi, String NgaySinh, String SDT, String Anh, String GioiTinh, String MatKhau, String NgayTao, String TrangThai, String Emaill, String ChucVu) {
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.DiaChi = DiaChi;
        this.NgaySinh = NgaySinh;
        this.SDT = SDT;
        this.Anh = Anh;
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

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String Anh) {
        this.Anh = Anh;
    }
    
public Object todatarow(){
    return new Object[]{
        this.MaNV,
        this.TenNV,
        this.DiaChi,
        this.NgaySinh,
        this.SDT,
        this.Anh,
        this.GioiTinh,
        this.MatKhau,
        this.NgayTao,
        this.TrangThai,
        this.Emaill,
        this.ChucVu
    };
}
}