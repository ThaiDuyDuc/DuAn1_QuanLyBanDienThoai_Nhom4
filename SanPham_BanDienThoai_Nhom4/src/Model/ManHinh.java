package Model;

public class ManHinh {
    private String maManHinh, CongNgheManHinh, DoPhanGiai, KichThuoc, DoSang, ManHinhCamUng;

    public ManHinh() {
    }

    public ManHinh(String maManHinh, String CongNgheManHinh, String DoPhanGiai, String KichThuoc, String DoSang, String ManHinhCamUng) {
        this.maManHinh = maManHinh;
        this.CongNgheManHinh = CongNgheManHinh;
        this.DoPhanGiai = DoPhanGiai;
        this.KichThuoc = KichThuoc;
        this.DoSang = DoSang;
        this.ManHinhCamUng = ManHinhCamUng;
    }

    public String getMaManHinh() {
        return maManHinh;
    }

    public void setMaManHinh(String maManHinh) {
        this.maManHinh = maManHinh;
    }

    public String getCongNgheManHinh() {
        return CongNgheManHinh;
    }

    public void setCongNgheManHinh(String CongNgheManHinh) {
        this.CongNgheManHinh = CongNgheManHinh;
    }

    public String getDoPhanGiai() {
        return DoPhanGiai;
    }

    public void setDoPhanGiai(String DoPhanGiai) {
        this.DoPhanGiai = DoPhanGiai;
    }

    public String getKichThuoc() {
        return KichThuoc;
    }

    public void setKichThuoc(String KichThuoc) {
        this.KichThuoc = KichThuoc;
    }

    public String getDoSang() {
        return DoSang;
    }

    public void setDoSang(String DoSang) {
        this.DoSang = DoSang;
    }

    public String getManHinhCamUng() {
        return ManHinhCamUng;
    }

    public void setManHinhCamUng(String ManHinhCamUng) {
        this.ManHinhCamUng = ManHinhCamUng;
    }

    @Override
    public String toString() {
        return "ManHinh{" + "CongNgheManHinh=" + CongNgheManHinh + '}';
    }

    
}
