package View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class test {
    public static void main(String[] args) {
        
        String ngaykt = "Sun Apr 30 00:00:00 ICT 2023";
        SimpleDateFormat inputFormat = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy",Locale.US);
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = inputFormat.parse(ngaykt);
            ngaykt = outputFormat.format(date);
        } catch (ParseException e) {
        }
        System.out.println(ngaykt);
    }
}
