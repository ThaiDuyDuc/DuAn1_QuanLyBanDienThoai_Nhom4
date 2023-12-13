package Model;

import java.math.BigDecimal;

public class NhanVien_thongKe {
    private String manv, tennv;
    private BigDecimal doanhthu;

    public NhanVien_thongKe() {
    }

    public NhanVien_thongKe(String manv, String tennv, BigDecimal doanhthu) {
        this.manv = manv;
        this.tennv = tennv;
        this.doanhthu = doanhthu;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public BigDecimal getDoanhthu() {
        return doanhthu;
    }

    public void setDoanhthu(BigDecimal doanhthu) {
        this.doanhthu = doanhthu;
    }
    
    
}
