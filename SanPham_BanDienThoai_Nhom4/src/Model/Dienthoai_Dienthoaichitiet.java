/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class Dienthoai_Dienthoaichitiet {
    private String madt,tendt,dongia,mausac,madtct;
    private int soluongton;

    public Dienthoai_Dienthoaichitiet() {
    }

    public Dienthoai_Dienthoaichitiet(String madt, String tendt, String dongia, String mausac, String madtct, int soluongton) {
        this.madt = madt;
        this.tendt = tendt;
        this.dongia = dongia;
        this.mausac = mausac;
        this.madtct = madtct;
        this.soluongton = soluongton;
    }

    public String getMadt() {
        return madt;
    }

    public void setMadt(String madt) {
        this.madt = madt;
    }

    public String getTendt() {
        return tendt;
    }

    public void setTendt(String tendt) {
        this.tendt = tendt;
    }

    public String getDongia() {
        return dongia;
    }

    public void setDongia(String dongia) {
        this.dongia = dongia;
    }

    public String getMausac() {
        return mausac;
    }

    public void setMausac(String mausac) {
        this.mausac = mausac;
    }

    public String getMadtct() {
        return madtct;
    }

    public void setMadtct(String madtct) {
        this.madtct = madtct;
    }

    public int getSoluongton() {
        return soluongton;
    }

    public void setSoluongton(int soluongton) {
        this.soluongton = soluongton;
    }

    
    
}
