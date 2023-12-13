package Model;

public class BoNho {
    private String MaBoNho;
    private int RAM, DungLuongLuuTru, DungLuongKhaDung;
    private String DanhBa;

    public BoNho() {
    }

    public BoNho(String MaBoNho, int RAM, int DungLuongLuuTru, int DungLuongKhaDung, String DanhBa) {
        this.MaBoNho = MaBoNho;
        this.RAM = RAM;
        this.DungLuongLuuTru = DungLuongLuuTru;
        this.DungLuongKhaDung = DungLuongKhaDung;
        this.DanhBa = DanhBa;
    }

    public String getMaBoNho() {
        return MaBoNho;
    }

    public void setMaBoNho(String MaBoNho) {
        this.MaBoNho = MaBoNho;
    }

    public int getRAM() {
        return RAM;
    }

    public void setRAM(int RAM) {
        this.RAM = RAM;
    }

    public int getDungLuongLuuTru() {
        return DungLuongLuuTru;
    }

    public void setDungLuongLuuTru(int DungLuongLuuTru) {
        this.DungLuongLuuTru = DungLuongLuuTru;
    }

    public int getDungLuongKhaDung() {
        return DungLuongKhaDung;
    }

    public void setDungLuongKhaDung(int DungLuongKhaDung) {
        this.DungLuongKhaDung = DungLuongKhaDung;
    }

    public String getDanhBa() {
        return DanhBa;
    }

    public void setDanhBa(String DanhBa) {
        this.DanhBa = DanhBa;
    }

    @Override
    public String toString() {
        return  RAM + "";
    }

    
    
    
}
