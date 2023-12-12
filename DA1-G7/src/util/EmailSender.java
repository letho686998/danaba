/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import java.net.Authenticator;
//import java.net.PasswordAuthentication;

/**
 *
 * @author Admin
 */
public class EmailSender {

    private static String sentCode; // Lưu trữ mã xác nhận đã gửi đi

    public static String sendEmail(String mailNhan, String email, String pass) {
        int code = (int) Math.floor(((Math.random() * 899999) + 100000));
        
        String text = "Phần mềm quản lí bán Áo Thun G7 gửi bạn mã xác minh"
                + "Mã xác minh để lấy lại tài khoản "+mailNhan +" là : ";
        sentCode = String.valueOf(code); // Cập nhật giá trị của sentCode với mã xác nhận mới
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props,
                new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, pass);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mailNhan));
            message.setSubject("Password confirmation code");
            message.setText(text+code + "");
            Transport.send(message);
            return code + "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sentCode;
    }


  public static String sendSupPort(String mailNhan, String email, String pass, String content) {
    try {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, pass);
                    }
                });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(email));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailNhan));
        message.setSubject("Hỗ trợ khách hàng");
        message.setText(content);

        Transport.send(message);
        return "Gửi email thành công!";
    } catch (MessagingException e) {
        return "Gửi email không thành công. Vui lòng kiểm tra lại thông tin email.";
    }
}

    public static String getSentCode() {
        return sentCode;
    }
}
