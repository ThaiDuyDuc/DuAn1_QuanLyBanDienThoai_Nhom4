package Bean;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DanhMuc {
    private String text;
    
    private JPanel panel;
    private JLabel lblAnh, lblText;

    public DanhMuc() {
    }

    public DanhMuc(String text, JPanel panel, JLabel lblAnh, JLabel lblText) {
        this.text = text;
        this.panel = panel;
        this.lblAnh = lblAnh;
        this.lblText = lblText;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public JLabel getLblAnh() {
        return lblAnh;
    }

    public void setLblAnh(JLabel lblAnh) {
        this.lblAnh = lblAnh;
    }

    public JLabel getLblText() {
        return lblText;
    }

    public void setLblText(JLabel lblText) {
        this.lblText = lblText;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
    
    
}
