/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

/**
 *
 * @author ADMIN
 */
public class My_thread extends Thread{
    Message messenger;
    @Override
    public void run(){
        try {
            Transport.send(messenger);
        } catch (MessagingException ex) {
            Logger.getLogger(My_thread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
