package Model;

public class Pin_Sac {
    private String maPinSac;
    private int DungLuongPin;
    private String LoaiPin, Sac, CongNghePin;

    public Pin_Sac() {
    }

    public Pin_Sac(String maPinSac, int DungLuongPin, String LoaiPin, String Sac, String CongNghePin) {
        this.maPinSac = maPinSac;
        this.DungLuongPin = DungLuongPin;
        this.LoaiPin = LoaiPin;
        this.Sac = Sac;
        this.CongNghePin = CongNghePin;
    }

    public String getMaPinSac() {
        return maPinSac;
    }

    public void setMaPinSac(String maPinSac) {
        this.maPinSac = maPinSac;
    }

    public int getDungLuongPin() {
        return DungLuongPin;
    }

    public void setDungLuongPin(int DungLuongPin) {
        this.DungLuongPin = DungLuongPin;
    }

    public String getLoaiPin() {
        return LoaiPin;
    }

    public void setLoaiPin(String LoaiPin) {
        this.LoaiPin = LoaiPin;
    }

    public String getSac() {
        return Sac;
    }

    public void setSac(String Sac) {
        this.Sac = Sac;
    }

    public String getCongNghePin() {
        return CongNghePin;
    }

    public void setCongNghePin(String CongNghePin) {
        this.CongNghePin = CongNghePin;
    }

    @Override
    public String toString() {
        return "Pin_Sac{" + "DungLuongPin=" + DungLuongPin + '}';
    }
    
    
}
