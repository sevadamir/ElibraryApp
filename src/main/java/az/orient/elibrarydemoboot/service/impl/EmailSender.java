package az.orient.elibrarydemoboot.service.impl;


import az.orient.elibrarydemoboot.service.EmailSenderService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import java.io.IOException;
import java.util.Properties;

import java.util.Date;

import javax.mail.internet.*;

@Service
public class EmailSender implements EmailSenderService {

    private final JavaMailSender mailSender;

    public EmailSender(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String to, String activationCode) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom("demirli2003@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("Activation link");
        simpleMailMessage.setText("Hello! \n Welcome to E-library. Please, visit next link: http://localhost:8085/customer/activate/"+activationCode);
        mailSender.send(simpleMailMessage);
    }

//    private static final Logger LOGGER = Logger.getLogger(EmailSender.class.getName());
//
//    public static void sendEmail(String to, String subject, String message, String username, String password, String host, int port, boolean useSsl) {
//        try {
//            Properties properties = new Properties();
//            properties.setProperty("mail.smtps.host", host);
//            properties.put("mail.smtp.starttls.enable", useSsl);
//            properties.put("mail.smtps.auth", "true");
//            properties.put("mail.smtp.port", port);
//
//            Session session = Session.getInstance(properties, new Authenticator() {
//                @Override
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(username, password);
//                }
//            });
//            Message msg = new MimeMessage(session);
//            msg.setFrom(new InternetAddress(username));
//            msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to, false));
//            msg.setSubject(subject);
//            msg.setContent(message, "text/html; charset=utf-8");
//            msg.setHeader("X-Mailer", "Pass link");
//            msg.setSentDate(new Date());
//            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
//            t.connect(host, username, password);
//            t.sendMessage(msg, msg.getAllRecipients());
//            t.close();
//
//        } catch (Exception ex) {
//            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

//    public void sendMail() throws AddressException, MessagingException, IOException {
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//
//        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("demirli2003@gmail.com", "nezabudparol");
//            }
//        });
//        Message msg = new MimeMessage(session);
//        msg.setFrom(new InternetAddress("demirli2003@gmail.com", false));
//
//        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("demirli2003@gmail.com"));
//        msg.setSubject("Register");
//        msg.setContent("Register to our library", "text/html");
//        msg.setSentDate(new Date());
//
//        MimeBodyPart messageBodyPart = new MimeBodyPart();
//        messageBodyPart.setContent("Register to our library", "text/html");
//
//        Multipart multipart = new MimeMultipart();
//        multipart.addBodyPart(messageBodyPart);
//        MimeBodyPart attachPart = new MimeBodyPart();
//
//        attachPart.attachFile("/var/tmp/image19.png");
//        multipart.addBodyPart(attachPart);
//        msg.setContent(multipart);
//        Transport.send(msg);
//    }


}
