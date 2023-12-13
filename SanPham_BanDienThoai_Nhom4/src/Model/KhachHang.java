/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author Administrator
 */
public class KhachHang {
    private String maKH, hoTen;
    private boolean gioiTinh;	
    private Date ngaySinh;
    private String email, SDT, anhDaiDien;	
    private int soDonHang;	
    private double giaTriChiTieu;
    private String ghiChu;
    private boolean trangThai;	
    private HangKhachHang hangKhachHang;
    
    public KhachHang() {
    }

    public KhachHang(String maKH, String hoTen, boolean gioiTinh, Date ngaySinh, String email, String SDT, String anhDaiDien, int soDonHang, double giaTriChiTieu, String ghiChu, boolean trangThai, HangKhachHang hangKhachHang) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.SDT = SDT;
        this.anhDaiDien = anhDaiDien;
        this.soDonHang = soDonHang;
        this.giaTriChiTieu = giaTriChiTieu;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
        this.hangKhachHang = hangKhachHang;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }

    public int getSoDonHang() {
        return soDonHang;
    }

    public void setSoDonHang(int soDonHang) {
        this.soDonHang = soDonHang;
    }

    public double getGiaTriChiTieu() {
        return giaTriChiTieu;
    }

    public void setGiaTriChiTieu(double giaTriChiTieu) {
        this.giaTriChiTieu = giaTriChiTieu;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public HangKhachHang getHangKhachHang() {
        return hangKhachHang;
    }

    public void setHangKhachHang(HangKhachHang hangKhachHang) {
        this.hangKhachHang = hangKhachHang;
    }

    @Override
    public String toString() {
        return hoTen;
    }

    
}
