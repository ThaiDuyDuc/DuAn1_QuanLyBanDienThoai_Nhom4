package Model;

public class NhaSanXuat {
    private String maNSX, TenNSX, QuocGia, DiaChi, Website, Email, SDT;

    public NhaSanXuat() {
    }

    public NhaSanXuat(String maNSX, String TenNSX, String QuocGia, String DiaChi, String Website, String Email, String SDT) {
        this.maNSX = maNSX;
        this.TenNSX = TenNSX;
        this.QuocGia = QuocGia;
        this.DiaChi = DiaChi;
        this.Website = Website;
        this.Email = Email;
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    

    public String getMaNSX() {
        return maNSX;
    }

    public void setMaNSX(String maNSX) {
        this.maNSX = maNSX;
    }

    public String getTenNSX() {
        return TenNSX;
    }

    public void setTenNSX(String TenNSX) {
        this.TenNSX = TenNSX;
    }

    public String getQuocGia() {
        return QuocGia;
    }

    public void setQuocGia(String QuocGia) {
        this.QuocGia = QuocGia;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String Website) {
        this.Website = Website;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    @Override
    public String toString() {
        return "NhaSanXuat{" + "TenNSX=" + TenNSX + '}';
    }
    
    
}
