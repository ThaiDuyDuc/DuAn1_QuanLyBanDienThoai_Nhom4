package ConTroller;

import Bean.DanhMuc;
import View.BanHang_JPanel;
import View.GiamGia_JPanel;
import View.HoaDon_JPanel;
import View.KhachHang_JPanel;
import View.NhanVien_JPanel;
import View.SanPham_JPanel;
import View.ThongKe_JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class ChuyenManHinh {

    private JPanel root;
    private String x = "";
    private List<DanhMuc> listItem = null;

    public ChuyenManHinh() {
    }

    public ChuyenManHinh(JPanel jpnroot) {
        this.root = jpnroot;
    }
    
    public void setViewNhanVien(JPanel jpnItem, JLabel jlblIem, JLabel jlblIem1) {
        x = "NhanVien";

        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new NhanVien_JPanel());
        root.validate();
        root.repaint();
    }
    
    public void NhanVien(JPanel jpnItem, JLabel jlblIem, JLabel jlblIem1) {
        x = "NhanVien";

        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new NhanVien_JPanel());
        root.validate();
        root.repaint();
    }

    public void setView(JPanel jpnItem, JLabel jlblIem, JLabel jlblIem1) {
        x = "BanHang";

        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new BanHang_JPanel());
        root.validate();
        root.repaint();
    }

    public void setEvent(List<DanhMuc> listItem) {
        this.listItem = listItem;

        for (DanhMuc item : listItem) {
            item.getLblAnh().addMouseListener(new LabelEvent(item.getText(), item.getPanel(), item.getLblAnh(), item.getLblText()));
            item.getLblText().addMouseListener(new LabelEvent(item.getText(), item.getPanel(), item.getLblAnh(), item.getLblText()));
        }
    }

    class LabelEvent implements MouseListener {

        private JPanel node;
        private String xx;

        private JPanel jpnItem;
        private JLabel jlblItem;
        private JLabel jlblItem1;

        public LabelEvent(String x, JPanel jpnItem, JLabel jlblItem, JLabel jlblItem1) {

            this.xx = x;
            this.jpnItem = jpnItem;
            this.jlblItem = jlblItem;
            this.jlblItem1 = jlblItem1;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            switch (x) {
                case "BanHang":
                    node = new BanHang_JPanel();
                    break;
                case "SanPham":
                    node = new SanPham_JPanel();
                    break;
                case "KhachHang":
                    node = new KhachHang_JPanel();
                    break;
                case "HoaDon":
                    node = new HoaDon_JPanel();
                    break;
                case "GiamGia":
                    node = new GiamGia_JPanel();
                    break;
                case "NhanVien":
                    node = new NhanVien_JPanel();
                    break;
                case "ThongKe": {
                    try {
                        node = new ThongKe_JPanel();
                    } catch (Exception ex) {
                        Logger.getLogger(ChuyenManHinh.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

                default:
                    node = new BanHang_JPanel();
                    break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChange(xx);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            x = xx;

//            jpnItem.setBackground(Color.ORANGE);
//            jlblItem.setBackground(new Color(96,100,191));
            setChange(x);
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        private void setChange(String xx) {
            for (DanhMuc item : listItem) {
                if (item.getText().equalsIgnoreCase(xx)) {
                    item.getPanel().setBorder(new MatteBorder(1, 5, 1, 1, Color.BLACK));
                } else {
                    item.getPanel().setBorder(null);
                }
            }
        }
    }
}
