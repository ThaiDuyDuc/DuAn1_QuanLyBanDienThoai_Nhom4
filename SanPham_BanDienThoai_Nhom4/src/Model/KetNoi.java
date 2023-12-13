package Model;

public class KetNoi {
    private String maKetNoi, Sim, wifi, Bluetooth, CongSac;

    public KetNoi() {
    }

    public KetNoi(String maKetNoi, String Sim, String wifi, String Bluetooth, String CongSac) {
        this.maKetNoi = maKetNoi;
        this.Sim = Sim;
        this.wifi = wifi;
        this.Bluetooth = Bluetooth;
        this.CongSac = CongSac;
    }

    public String getMaKetNoi() {
        return maKetNoi;
    }

    public void setMaKetNoi(String maKetNoi) {
        this.maKetNoi = maKetNoi;
    }

    public String getSim() {
        return Sim;
    }

    public void setSim(String Sim) {
        this.Sim = Sim;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getBluetooth() {
        return Bluetooth;
    }

    public void setBluetooth(String Bluetooth) {
        this.Bluetooth = Bluetooth;
    }

    public String getCongSac() {
        return CongSac;
    }

    public void setCongSac(String CongSac) {
        this.CongSac = CongSac;
    }

    @Override
    public String toString() {
        return "KetNoi{" + "CongSac=" + CongSac + '}';
    }
    
}
