package Extensions;

import java.util.Date;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;



public class sendEmail {

    public static void main(String[] args) {
        final String username = quyenDanhNhap.user.getEmaill().toString();
        final String password = "mwbs etao wdyw ypbe";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("thaiduc20022310@gmail.com"));
            message.setSubject("Hóa đơn");

            // Create the message body part
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("tkcham");

            // Create the attachment body part
            BodyPart attachmentBodyPart = new MimeBodyPart();
            String filename = "C:\\PhanMenBanDienThoai-Nhom4.pdf";
            DataSource source = new FileDataSource(filename);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName("hahah.pdf");

            // Create the multipart message
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentBodyPart);

            // Set the content of the message
            message.setContent(multipart);

            // Send the message
            Transport.send(message);

            System.out.println("Email with attachment sent successfully.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}