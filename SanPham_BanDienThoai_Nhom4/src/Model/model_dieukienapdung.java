/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class model_dieukienapdung {
    private String madk;
    private String gtrsl,sl,loaidk;
    private String makh;
    private String madt;
    private String hang;

    public model_dieukienapdung() {
    }

    public model_dieukienapdung(String madk, String gtrsl, String sl, String loaidk, String makh, String madt, String hang) {
        this.madk = madk;
        this.gtrsl = gtrsl;
        this.sl = sl;
        this.loaidk = loaidk;
        this.makh = makh;
        this.madt = madt;
        this.hang = hang;
    }

    public String getMadk() {
        return madk;
    }

    public void setMadk(String madk) {
        this.madk = madk;
    }

    public String getGtrsl() {
        return gtrsl;
    }

    public void setGtrsl(String gtrsl) {
        this.gtrsl = gtrsl;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getLoaidk() {
        return loaidk;
    }

    public void setLoaidk(String loaidk) {
        this.loaidk = loaidk;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getMadt() {
        return madt;
    }

    public void setMadt(String madt) {
        this.madt = madt;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    
}
