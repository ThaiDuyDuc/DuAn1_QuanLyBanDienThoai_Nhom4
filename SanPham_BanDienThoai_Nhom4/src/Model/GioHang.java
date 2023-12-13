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
public class GioHang {
    private String maGH;
    private Date thoiGianTaoDate, thoiGianCapNhat;
    private KhachHang khachHang;
    private DienThoaiChiTiet dienThoai;
    private int soLuong;
    private String ghiChu;
    private boolean trangThai;
    
    public GioHang() {
    }

    public GioHang(String maGH, Date thoiGianTaoDate, Date thoiGianCapNhat, KhachHang khachHang, DienThoaiChiTiet dienThoai, int soLuong, String ghiChu, boolean trangThai) {
        this.maGH = maGH;
        this.thoiGianTaoDate = thoiGianTaoDate;
        this.thoiGianCapNhat = thoiGianCapNhat;
        this.khachHang = khachHang;
        this.dienThoai = dienThoai;
        this.soLuong = soLuong;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }

    

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public String getMaGH() {
        return maGH;
    }

    public void setMaGH(String maGH) {
        this.maGH = maGH;
    }

    public Date getThoiGianTaoDate() {
        return thoiGianTaoDate;
    }

    public void setThoiGianTaoDate(Date thoiGianTaoDate) {
        this.thoiGianTaoDate = thoiGianTaoDate;
    }

    public Date getThoiGianCapNhat() {
        return thoiGianCapNhat;
    }

    public void setThoiGianCapNhat(Date thoiGianCapNhat) {
        this.thoiGianCapNhat = thoiGianCapNhat;
    }

    
    public DienThoaiChiTiet getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(DienThoaiChiTiet dienThoai) {
        this.dienThoai = dienThoai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
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
    
}
