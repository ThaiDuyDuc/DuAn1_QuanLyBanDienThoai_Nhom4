package Model;

import java.util.Date;

public class DanhMucDienThoai {
    private int MaDM;
    private String maDT, tenDM, loaiDT;
    private Date ngayTao, ngayCapNhat;

    public DanhMucDienThoai() {
    }

    public DanhMucDienThoai(int MaDM, String maDT, String tenDM, String loaiDT, Date ngayTao, Date ngayCapNhat) {
        this.MaDM = MaDM;
        this.maDT = maDT;
        this.tenDM = tenDM;
        this.loaiDT = loaiDT;
        this.ngayTao = ngayTao;
        this.ngayCapNhat = ngayCapNhat;
    }

    public int getMaDM() {
        return MaDM;
    }

    public void setMaDM(int MaDM) {
        this.MaDM = MaDM;
    }

    public String getMaDT() {
        return maDT;
    }

    public void setMaDT(String maDT) {
        this.maDT = maDT;
    }

    public String getTenDM() {
        return tenDM;
    }

    public void setTenDM(String tenDM) {
        this.tenDM = tenDM;
    }

    public String getLoaiDT() {
        return loaiDT;
    }

    public void setLoaiDT(String loaiDT) {
        this.loaiDT = loaiDT;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(Date ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }
    
}
