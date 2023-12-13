package Model;

import java.awt.Component;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class LoadAnhLenBang extends DefaultTableCellRenderer {

    @Override
    protected void setValue(Object value) {
        if (value instanceof ImageIcon) {
            setIcon((ImageIcon) value);
        } else {
            super.setValue(value);
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Đảm bảo cách hiển thị đúng cho cột ảnh
        setHorizontalAlignment(JLabel.CENTER);
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }

    private static final int CELL_WIDTH = 50; // Độ rộng ô
    private static final int CELL_HEIGHT = 50; // Độ cao ô

    private ImageIcon resizeImage(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }
}
