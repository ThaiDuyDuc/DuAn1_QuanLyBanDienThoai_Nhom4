package Model;

public class Kho {
    private String maKho, TenKho, DiaChi;

    public Kho() {
    }

    public Kho(String maKho, String TenKho, String DiaChi) {
        this.maKho = maKho;
        this.TenKho = TenKho;
        this.DiaChi = DiaChi;
    }

    public String getMaKho() {
        return maKho;
    }

    public void setMaKho(String maKho) {
        this.maKho = maKho;
    }

    public String getTenKho() {
        return TenKho;
    }

    public void setTenKho(String TenKho) {
        this.TenKho = TenKho;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    @Override
    public String toString() {
        return TenKho;
    }
    
}
