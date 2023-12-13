/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class phieugiamgia1 {

    private String makm, Tenkhm, MaNhanVien, MaDieuKienGiamGia, mota, giatri, loaigiamgia, hang;
    private String Ngaytao;
    private String Ngaybatdau;
    private String Ngayketthuc;
    private int SolanSudung;

    public phieugiamgia1() {
    }

    public phieugiamgia1(String makm, String Tenkhm, String MaNhanVien, String MaDieuKienGiamGia, String mota, String giatri, String loaigiamgia, String hang, String Ngaytao, String Ngaybatdau, String Ngayketthuc, int SolanSudung) {
        this.makm = makm;
        this.Tenkhm = Tenkhm;
        this.MaNhanVien = MaNhanVien;
        this.MaDieuKienGiamGia = MaDieuKienGiamGia;
        this.mota = mota;
        this.giatri = giatri;
        this.loaigiamgia = loaigiamgia;
        this.hang = hang;
        this.Ngaytao = Ngaytao;
        this.Ngaybatdau = Ngaybatdau;
        this.Ngayketthuc = Ngayketthuc;
        this.SolanSudung = SolanSudung;
    }

    public String getMakm() {
        return makm;
    }

    public void setMakm(String makm) {
        this.makm = makm;
    }

    public String getTenkhm() {
        return Tenkhm;
    }

    public void setTenkhm(String Tenkhm) {
        this.Tenkhm = Tenkhm;
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(String MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public String getMaDieuKienGiamGia() {
        return MaDieuKienGiamGia;
    }

    public void setMaDieuKienGiamGia(String MaDieuKienGiamGia) {
        this.MaDieuKienGiamGia = MaDieuKienGiamGia;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getGiatri() {
        return giatri;
    }

    public void setGiatri(String giatri) {
        this.giatri = giatri;
    }

    public String getLoaigiamgia() {
        return loaigiamgia;
    }

    public void setLoaigiamgia(String loaigiamgia) {
        this.loaigiamgia = loaigiamgia;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public String getNgaytao() {
        return Ngaytao;
    }

    public void setNgaytao(String Ngaytao) {
        this.Ngaytao = Ngaytao;
    }

    public String getNgaybatdau() {
        return Ngaybatdau;
    }

    public void setNgaybatdau(String Ngaybatdau) {
        this.Ngaybatdau = Ngaybatdau;
    }

    public String getNgayketthuc() {
        return Ngayketthuc;
    }

    public void setNgayketthuc(String Ngayketthuc) {
        this.Ngayketthuc = Ngayketthuc;
    }

    public int getSolanSudung() {
        return SolanSudung;
    }

    public void setSolanSudung(int SolanSudung) {
        this.SolanSudung = SolanSudung;
    }

    @Override
    public String toString() {
        return "phieugiamgia1{" + "makm=" + makm + ", Tenkhm=" + Tenkhm + ", MaNhanVien=" + MaNhanVien + ", MaDieuKienGiamGia=" + MaDieuKienGiamGia + ", mota=" + mota + ", giatri=" + giatri + ", loaigiamgia=" + loaigiamgia + ", hang=" + hang + ", Ngaytao=" + Ngaytao + ", Ngaybatdau=" + Ngaybatdau + ", Ngayketthuc=" + Ngayketthuc + ", SolanSudung=" + SolanSudung + '}';
    }

}
