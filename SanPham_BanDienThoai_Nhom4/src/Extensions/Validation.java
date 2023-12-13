/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Extensions;

import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Administrator
 */
public class Validation {

    public int checkNull(JTextField txt, JLabel error) {
        int count = 0;
        String inp = txt.getText();
        if (inp.trim().isEmpty()) {
            count++;
            error.setText("Vui lòng nhập dữ liệu!");
        } else {
            error.setText("   ");
        }
        return count;
    }

    public int checkRegex(JTextField txt, JLabel error, String regex, String message) {
        int count = 0;
        String inp = txt.getText();
        if (checkNull(txt, error) == 0) {
            if (inp.matches(regex)) {
                error.setText("   ");
            } else {
                count++;
                error.setText(message);
            }
        }else{
            count++;
        }
        return count;
    }

    public int checkNumber(JTextField txt, JLabel error, String message) {
        int count = 0;
        int integer = 0;
        String inp = txt.getText();
        if (checkNull(txt, error) == 0) {
            try {
                integer = Integer.parseInt(inp);
                if (integer > 0) {
                    error.setText("  ");
                } else {
                    count++;
                    error.setText(message);
                }
            } catch (Exception e) {
                count++;
                error.setText("Vui lòng nhập số nguyên!");
            }
        }
        return count;
    }
}
