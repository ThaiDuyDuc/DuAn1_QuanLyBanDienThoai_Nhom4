package Model;

public class Camera {
    private String maCamera, CAM_truoc, TinhNang_CAMTruoc, CAM_Sau, TinhNang_CAMSau;

    public Camera() {
    }

    public Camera(String maCamera, String CAM_truoc, String TinhNang_CAMTruoc, String CAM_Sau, String TinhNang_CAMSau) {
        this.maCamera = maCamera;
        this.CAM_truoc = CAM_truoc;
        this.TinhNang_CAMTruoc = TinhNang_CAMTruoc;
        this.CAM_Sau = CAM_Sau;
        this.TinhNang_CAMSau = TinhNang_CAMSau;
    }

    public String getMaCamera() {
        return maCamera;
    }

    public void setMaCamera(String maCamera) {
        this.maCamera = maCamera;
    }

    public String getCAM_truoc() {
        return CAM_truoc;
    }

    public void setCAM_truoc(String CAM_truoc) {
        this.CAM_truoc = CAM_truoc;
    }

    public String getTinhNang_CAMTruoc() {
        return TinhNang_CAMTruoc;
    }

    public void setTinhNang_CAMTruoc(String TinhNang_CAMTruoc) {
        this.TinhNang_CAMTruoc = TinhNang_CAMTruoc;
    }

    public String getCAM_Sau() {
        return CAM_Sau;
    }

    public void setCAM_Sau(String CAM_Sau) {
        this.CAM_Sau = CAM_Sau;
    }

    public String getTinhNang_CAMSau() {
        return TinhNang_CAMSau;
    }

    public void setTinhNang_CAMSau(String TinhNang_CAMSau) {
        this.TinhNang_CAMSau = TinhNang_CAMSau;
    }

    @Override
    public String toString() {
        return  CAM_truoc ;
    }
    
    
}
