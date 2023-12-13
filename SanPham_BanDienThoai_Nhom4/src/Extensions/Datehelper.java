/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Extensions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Datehelper {
    static  SimpleDateFormat formater = new SimpleDateFormat();
    
    /*
    chuyển đổi String sang date
    date là Strig cần chuyển
    pattern là định dạng thời gian
    return là kết quả
    */
    public static Date toDate(String date, String pattern){
        try {
            formater.applyPattern(pattern);
            return formater.parse(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /* chuyển từ date sang string
    date là Date cần chuyển
    pattern là định dạng thời gian
    return là kết quả
    */
    
     public static String toString(String date, String pattern){
        
            formater.applyPattern(pattern);
            return formater.format(date);
       
    }
     
     // trả về thời gian hiện tại
     public static Date now(){
         return new Date();
     }
     
     /*
     bổ sung số ngày vào thời gian
     
     date thời gian hiện có
     days số ngày cần bổ sung vào date
     return kết quả
     */
     
     public static Date addDays(Date date, long days){
         date.setTime(date.getTime()+days*24*60*60*1000);
         return date;
     }
}
