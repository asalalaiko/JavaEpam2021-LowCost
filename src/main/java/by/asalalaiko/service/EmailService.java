package by.asalalaiko.service;

import by.asalalaiko.config.Configuration;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

public class EmailService {

    private static EmailService instance;
    private Properties props;

    private String UNAME ;
    private  String PASS ;

    private EmailService() {

        Properties applicationProps = Configuration.getApplicationProps();
        Properties prop = new Properties();
        prop.put("mail.smtp.host", applicationProps.getProperty("mail.smtp.host"));
        prop.put("mail.smtp.port", applicationProps.getProperty("mail.smtp.port"));
        prop.put("mail.smtp.auth", applicationProps.getProperty("mail.smtp.auth"));
        prop.put("mail.smtp.starttls.enable", applicationProps.getProperty("mail.smtp.starttls.enable"));
        props = prop;

        UNAME = applicationProps.getProperty("mail.username");
        PASS =  applicationProps.getProperty("mail.password");
        instance = this;
    }

    public static EmailService getInstance() {
        if (instance == null) {
            EmailService.instance = new EmailService();
        }
        return instance;
    }
    public void notifyUserRegistered(String email, Long id, String code) throws MessagingException {

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(UNAME, PASS);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("LowcostJava@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject("You were successfully registered");

        String msg = "To activate the user follow the link " +
                "<a href='http://localhost:8080/JavaEpam2021_LowCost_war/activate?code=" + code +
                "'>ACTIVATE</a>";


        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }
}
