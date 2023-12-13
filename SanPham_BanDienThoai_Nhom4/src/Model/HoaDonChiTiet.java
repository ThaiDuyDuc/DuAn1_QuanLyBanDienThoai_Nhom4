package Model;

/**
 *
 * @author Administrator
 */
public class HoaDonChiTiet {
    private String maHoaDon, maDTCT;
    private int soLuong;
    private String donGia, thanhTien;
    private String ghiChu;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String maHoaDon, String maDTCT, int soLuong, String donGia, String thanhTien, String ghiChu) {
        this.maHoaDon = maHoaDon;
        this.maDTCT = maDTCT;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
        this.ghiChu = ghiChu;
    }


    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaDTCT() {
        return maDTCT;
    }

    public void setMaDTCT(String maDTCT) {
        this.maDTCT = maDTCT;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getDonGia() {
        return donGia;
    }

    public void setDonGia(String donGia) {
        this.donGia = donGia;
    }

    public String getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(String thanhTien) {
        this.thanhTien = thanhTien;
    }

   

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
}
