package Model;

public class TienIch {
    private String maTienIch, BaoMat, TinhNangDacBiet, KhangNuoc_Bui;

    public TienIch() {
    }

    public TienIch(String maTienIch, String BaoMat, String TinhNangDacBiet, String KhangNuoc_Bui) {
        this.maTienIch = maTienIch;
        this.BaoMat = BaoMat;
        this.TinhNangDacBiet = TinhNangDacBiet;
        this.KhangNuoc_Bui = KhangNuoc_Bui;
    }

    public String getMaTienIch() {
        return maTienIch;
    }

    public void setMaTienIch(String maTienIch) {
        this.maTienIch = maTienIch;
    }

    public String getBaoMat() {
        return BaoMat;
    }

    public void setBaoMat(String BaoMat) {
        this.BaoMat = BaoMat;
    }

    public String getTinhNangDacBiet() {
        return TinhNangDacBiet;
    }

    public void setTinhNangDacBiet(String TinhNangDacBiet) {
        this.TinhNangDacBiet = TinhNangDacBiet;
    }

    public String getKhangNuoc_Bui() {
        return KhangNuoc_Bui;
    }

    public void setKhangNuoc_Bui(String KhangNuoc_Bui) {
        this.KhangNuoc_Bui = KhangNuoc_Bui;
    }

    @Override
    public String toString() {
        return "TienIch{" + "KhangNuoc_Bui=" + KhangNuoc_Bui + '}';
    }
    
    
}
