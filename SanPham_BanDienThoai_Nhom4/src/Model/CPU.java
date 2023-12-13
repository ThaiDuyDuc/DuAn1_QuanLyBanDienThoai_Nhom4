package Model;

public class CPU {
    private String maCPU, HeDieuHanh, CPU, GPU;

    public CPU() {
    }

    public CPU(String maCPU, String HeDieuHanh, String CPU, String GPU) {
        this.maCPU = maCPU;
        this.HeDieuHanh = HeDieuHanh;
        this.CPU = CPU;
        this.GPU = GPU;
    }

    public String getMaCPU() {
        return maCPU;
    }

    public void setMaCPU(String maCPU) {
        this.maCPU = maCPU;
    }

    public String getHeDieuHanh() {
        return HeDieuHanh;
    }

    public void setHeDieuHanh(String HeDieuHanh) {
        this.HeDieuHanh = HeDieuHanh;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getGPU() {
        return GPU;
    }

    public void setGPU(String GPU) {
        this.GPU = GPU;
    }

    @Override
    public String toString() {
        return  CPU ;
    }
    
    
}
