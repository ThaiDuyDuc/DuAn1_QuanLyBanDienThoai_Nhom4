/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Repository.HoaDonDao;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class DonHang {
    private String maHD;
    private Date ngayLap;
    private KhachHang khachHang;
    private String maNV;
    private double tongTien;
    private String phieuGiamGia;
    private double tongTienSauGG;
    private String phuongThucThanhToan;
    private String ghiChu, trangThai;
    
    public DonHang() {
    }

    public DonHang(String maHD, Date ngayLap, KhachHang khachHang, String maNV, double tongTien, String phieuGiamGia, double tongTienSauGG, String phuongThucThanhToan, String ghiChu, String trangThai) {
        this.maHD = maHD;
        this.ngayLap = ngayLap;
        this.khachHang = khachHang;
        this.maNV = maNV;
        this.tongTien = tongTien;
        this.phieuGiamGia = phieuGiamGia;
        this.tongTienSauGG = tongTienSauGG;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public double getTongTienSauGG() {
        return tongTienSauGG;
    }

    public void setTongTienSauGG(double tongTienSauGG) {
        this.tongTienSauGG = tongTienSauGG;
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public String getPhieuGiamGia() {
        return phieuGiamGia;
    }

    public void setPhieuGiamGia(String phieuGiamGia) {
        this.phieuGiamGia = phieuGiamGia;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    
    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }  

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

}
