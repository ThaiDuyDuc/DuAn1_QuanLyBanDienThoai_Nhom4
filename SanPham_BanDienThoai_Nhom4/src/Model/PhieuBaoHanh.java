package Model;

import java.util.Date;

public class PhieuBaoHanh {
    private String maPhieuBaoHanh;
    private Date NgayMua, NgayBatDauBaoHanh, NgayKetThuc;
    private boolean TrangThai;
    private String GhiChu;

    public PhieuBaoHanh() {
    }

    public PhieuBaoHanh(String maPhieuBaoHanh, Date NgayMua, Date NgayBatDauBaoHanh, Date NgayKetThuc, boolean TrangThai, String GhiChu) {
        this.maPhieuBaoHanh = maPhieuBaoHanh;
        this.NgayMua = NgayMua;
        this.NgayBatDauBaoHanh = NgayBatDauBaoHanh;
        this.NgayKetThuc = NgayKetThuc;
        this.TrangThai = TrangThai;
        this.GhiChu = GhiChu;
    }

    public String getMaPhieuBaoHanh() {
        return maPhieuBaoHanh;
    }

    public void setMaPhieuBaoHanh(String maPhieuBaoHanh) {
        this.maPhieuBaoHanh = maPhieuBaoHanh;
    }

    public Date getNgayMua() {
        return NgayMua;
    }

    public void setNgayMua(Date NgayMua) {
        this.NgayMua = NgayMua;
    }

    public Date getNgayBatDauBaoHanh() {
        return NgayBatDauBaoHanh;
    }

    public void setNgayBatDauBaoHanh(Date NgayBatDauBaoHanh) {
        this.NgayBatDauBaoHanh = NgayBatDauBaoHanh;
    }

    public Date getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(Date NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    @Override
    public String toString() {
        return "PhieuBaoHanh{" + "GhiChu=" + GhiChu + '}';
    }
    
    
}
