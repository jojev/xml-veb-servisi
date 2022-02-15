package main.java.com.xml.userbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;


    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String toEmail, String path) {
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            msg.setSubject("Interesovanje");
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(toEmail);
            helper.setFrom("euprava");
            helper.setSubject("Interesovanje");


            Multipart emailContent = new MimeMultipart();
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("Uspešno iskazano interesovanje!" +
                    "Dobićete prvi slobodan termin čim bude dostupan." +
                    "U prilogu se nalazi Vaše interesovanje.");

            MimeBodyPart jpgBodyPart = new MimeBodyPart();
            jpgBodyPart.attachFile(path);

            emailContent.addBodyPart(textBodyPart);
            emailContent.addBodyPart(jpgBodyPart);

            msg.setContent(emailContent);
            javaMailSender.send(msg);


        } catch (MessagingException | IOException ex) {
            System.out.println("Greška prilikom slanja!");
        }
    }


}
