/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


/**
 *
 * @author Administrator
 */
public class HangKhachHang {
    private int maHang;	
    private String tenHang; 	
    private int soDonHang;	
    private double giaTriChiTieu;

    public HangKhachHang() {
    }

    public HangKhachHang(int maHang, String tenHang, int soDonHang, double giaTriChiTieu) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.soDonHang = soDonHang;
        this.giaTriChiTieu = giaTriChiTieu;
    }

    public int getMaHang() {
        return maHang;
    }

    public void setMaHang(int maHang) {
        this.maHang = maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
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

    @Override
    public String toString() {
        return tenHang;
    }
    
}
