/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author AcermaPGH
 MaDTCT
 MaKH
 MaNguoiVanChuyen
 MaBuuCuc
 MaDCGiaoHang
 MaDCLayHang
 GhiChu
 */
public class PhieuGiaoHang {
    private String MaPGH;
    
    private String MaKH;
    private String MaNguoiVanChuyen;
    private String MaBuuCuc;
    private String MaDCGiaoHang;
    private String MaDCLayHang;
    private String GhiChu;

    public PhieuGiaoHang() {
    }

    public PhieuGiaoHang(String MaPGH, String MaKH, String MaNguoiVanChuyen, String MaBuuCuc, String MaDCGiaoHang, String MaDCLayHang, String GhiChu) {
        this.MaPGH = MaPGH;
        this.MaKH = MaKH;
        this.MaNguoiVanChuyen = MaNguoiVanChuyen;
        this.MaBuuCuc = MaBuuCuc;
        this.MaDCGiaoHang = MaDCGiaoHang;
        this.MaDCLayHang = MaDCLayHang;
        this.GhiChu = GhiChu;
    }

    public String getMaPGH() {
        return MaPGH;
    }

    public void setMaPGH(String MaPGH) {
        this.MaPGH = MaPGH;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getMaNguoiVanChuyen() {
        return MaNguoiVanChuyen;
    }

    public void setMaNguoiVanChuyen(String MaNguoiVanChuyen) {
        this.MaNguoiVanChuyen = MaNguoiVanChuyen;
    }

    public String getMaBuuCuc() {
        return MaBuuCuc;
    }

    public void setMaBuuCuc(String MaBuuCuc) {
        this.MaBuuCuc = MaBuuCuc;
    }

    public String getMaDCGiaoHang() {
        return MaDCGiaoHang;
    }

    public void setMaDCGiaoHang(String MaDCGiaoHang) {
        this.MaDCGiaoHang = MaDCGiaoHang;
    }

    public String getMaDCLayHang() {
        return MaDCLayHang;
    }

    public void setMaDCLayHang(String MaDCLayHang) {
        this.MaDCLayHang = MaDCLayHang;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    @Override
    public String toString() {
        return "PhieuGiaoHang{" + "MaPGH=" + MaPGH + ", MaKH=" + MaKH + ", MaNguoiVanChuyen=" + MaNguoiVanChuyen + ", MaBuuCuc=" + MaBuuCuc + ", MaDCGiaoHang=" + MaDCGiaoHang + ", MaDCLayHang=" + MaDCLayHang + ", GhiChu=" + GhiChu + '}';
    }

    
 
            
        
    
    
}
