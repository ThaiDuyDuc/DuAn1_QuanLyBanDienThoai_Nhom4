package Model;

import java.math.BigDecimal;

public class KiemKho {
    private String madt, tendt, thuonghieu, HeDieuHanh, MauSac;
    private BigDecimal GiaBan;
    private CPU chip;
    private BoNho bonho;
    private Kho kho;
    private int slton;

    public KiemKho() {
    }

    public KiemKho(String madt, String tendt, String thuonghieu, String HeDieuHanh, String MauSac, BigDecimal GiaBan, CPU chip, BoNho bonho, Kho kho, int slton) {
        this.madt = madt;
        this.tendt = tendt;
        this.thuonghieu = thuonghieu;
        this.HeDieuHanh = HeDieuHanh;
        this.MauSac = MauSac;
        this.GiaBan = GiaBan;
        this.chip = chip;
        this.bonho = bonho;
        this.kho = kho;
        this.slton = slton;
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

    public String getThuonghieu() {
        return thuonghieu;
    }

    public void setThuonghieu(String thuonghieu) {
        this.thuonghieu = thuonghieu;
    }

    public String getHeDieuHanh() {
        return HeDieuHanh;
    }

    public void setHeDieuHanh(String HeDieuHanh) {
        this.HeDieuHanh = HeDieuHanh;
    }

    public String getMauSac() {
        return MauSac;
    }

    public void setMauSac(String MauSac) {
        this.MauSac = MauSac;
    }

    public BigDecimal getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(BigDecimal GiaBan) {
        this.GiaBan = GiaBan;
    }

    public CPU getChip() {
        return chip;
    }

    public void setChip(CPU chip) {
        this.chip = chip;
    }

    public BoNho getBonho() {
        return bonho;
    }

    public void setBonho(BoNho bonho) {
        this.bonho = bonho;
    }

    public Kho getKho() {
        return kho;
    }

    public void setKho(Kho kho) {
        this.kho = kho;
    }

    public int getSlton() {
        return slton;
    }

    public void setSlton(int slton) {
        this.slton = slton;
    }

    @Override
    public String toString() {
        return "KiemKho{" + "madt=" + madt + ", tendt=" + tendt + ", thuonghieu=" + thuonghieu + ", HeDieuHanh=" + HeDieuHanh + ", MauSac=" + MauSac + ", GiaBan=" + GiaBan + ", chip=" + chip + ", bonho=" + bonho + ", kho=" + kho + ", slton=" + slton + '}';
    }
    
    
}
