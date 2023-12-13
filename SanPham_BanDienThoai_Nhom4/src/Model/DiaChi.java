/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Administrator
 */
public class DiaChi {
    private String maDC, thanhPho, quan, phuong, toaNha;
    private String ghiChu;

    public DiaChi() {
    }

    public DiaChi(String maDC, String thanhPho, String quan, String phuong, String toaNha, String ghiChu) {
        this.maDC = maDC;
        this.thanhPho = thanhPho;
        this.quan = quan;
        this.phuong = phuong;
        this.toaNha = toaNha;
        this.ghiChu = ghiChu;
    }

    public String getMaDC() {
        return maDC;
    }

    public void setMaDC(String maDC) {
        this.maDC = maDC;
    }

    public String getThanhPho() {
        return thanhPho;
    }

    public void setThanhPho(String thanhPho) {
        this.thanhPho = thanhPho;
    }

    public String getQuan() {
        return quan;
    }

    public void setQuan(String quan) {
        this.quan = quan;
    }

    public String getPhuong() {
        return phuong;
    }

    public void setPhuong(String phuong) {
        this.phuong = phuong;
    }

    public String getToaNha() {
        return toaNha;
    }

    public void setToaNha(String toaNha) {
        this.toaNha = toaNha;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return toaNha + ", " + phuong + ", " + quan + ", " + thanhPho;
    }

    

}
