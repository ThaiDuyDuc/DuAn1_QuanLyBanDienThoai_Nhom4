package Model;

import java.sql.Timestamp;

public class PhieuGiamGia {

    private String maPhieuGiamGia, MaNV, MaDieuKienGiamGia,TenPhieu, LoaiGiamGia, Giatri;
    private Timestamp NgayTao, NgayBatDau, NgayHetHan;
    private String MoTa;
    private int SoLanSuDung;
    private boolean  TrangThaiPhieu;

    public PhieuGiamGia() {
    }

    public PhieuGiamGia(String maPhieuGiamGia, String MaNV, String MaDieuKienGiamGia, String TenPhieu, String LoaiGiamGia, String Giatri, Timestamp NgayTao, Timestamp NgayBatDau, Timestamp NgayHetHan, String MoTa, int SoLanSuDung, boolean TrangThaiPhieu) {
        this.maPhieuGiamGia = maPhieuGiamGia;
        this.MaNV = MaNV;
        this.MaDieuKienGiamGia = MaDieuKienGiamGia;
        this.TenPhieu = TenPhieu;
        this.LoaiGiamGia = LoaiGiamGia;
        this.Giatri = Giatri;
        this.NgayTao = NgayTao;
        this.NgayBatDau = NgayBatDau;
        this.NgayHetHan = NgayHetHan;
        this.MoTa = MoTa;
        this.SoLanSuDung = SoLanSuDung;
        this.TrangThaiPhieu = TrangThaiPhieu;
    }

    public String getMaPhieuGiamGia() {
        return maPhieuGiamGia;
    }

    public void setMaPhieuGiamGia(String maPhieuGiamGia) {
        this.maPhieuGiamGia = maPhieuGiamGia;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getMaDieuKienGiamGia() {
        return MaDieuKienGiamGia;
    }

    public void setMaDieuKienGiamGia(String MaDieuKienGiamGia) {
        this.MaDieuKienGiamGia = MaDieuKienGiamGia;
    }

    public String getTenPhieu() {
        return TenPhieu;
    }

    public void setTenPhieu(String TenPhieu) {
        this.TenPhieu = TenPhieu;
    }

    public String getLoaiGiamGia() {
        return LoaiGiamGia;
    }

    public void setLoaiGiamGia(String LoaiGiamGia) {
        this.LoaiGiamGia = LoaiGiamGia;
    }

    public String getGiatri() {
        return Giatri;
    }

    public void setGiatri(String Giatri) {
        this.Giatri = Giatri;
    }

    public Timestamp getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Timestamp NgayTao) {
        this.NgayTao = NgayTao;
    }

    public Timestamp getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(Timestamp NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    public Timestamp getNgayHetHan() {
        return NgayHetHan;
    }

    public void setNgayHetHan(Timestamp NgayHetHan) {
        this.NgayHetHan = NgayHetHan;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public int getSoLanSuDung() {
        return SoLanSuDung;
    }

    public void setSoLanSuDung(int SoLanSuDung) {
        this.SoLanSuDung = SoLanSuDung;
    }

    public boolean isTrangThaiPhieu() {
        return TrangThaiPhieu;
    }

    public void setTrangThaiPhieu(boolean TrangThaiPhieu) {
        this.TrangThaiPhieu = TrangThaiPhieu;
    }

    
}
