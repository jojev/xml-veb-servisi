package main.java.com.xml.officialbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import main.java.com.xml.officialbackend.model.termin.Termin;

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

    public void sendTermin(Termin termin) {
    	try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            msg.setSubject("Dodjela termina");
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(termin.getEmailPacijenta());
            helper.setFrom("euprava");
            helper.setSubject("Termin");
           
            Multipart emailContent = new MimeMultipart();
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("Pacijent: " + termin.getJmbgPacijenta()+ "\nTermin Vam je dodeljen: " + termin.getDatumVreme().toString()
            		+ "\nDoza: " + termin.getDoza() + "\nTip vakcine: " + termin.getTipVakcine());
            emailContent.addBodyPart(textBodyPart);


            msg.setContent(emailContent);
           

            javaMailSender.send(msg);

        } catch (MessagingException ex) {
            System.out.println("Greška prilikom slanja!");
        }
    }
    
    public void sendResponse(String toEmail, String path, String htmlPath, String reason) {
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            msg.setSubject("Odgovor na zahtev za digitalni zeleni sertifikat");
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(toEmail);
            helper.setFrom("euprava");
            helper.setSubject("Odgovor na zahtev za digitalni zeleni sertifikat");
            if (!path.equals(" ")) {

                Multipart emailContent = new MimeMultipart();
                MimeBodyPart textBodyPart = new MimeBodyPart();
                textBodyPart.setText("U prilogu se nalazi Vaš digitalni zeleni sertfikat!");

                MimeBodyPart jpgBodyPart = new MimeBodyPart();
                jpgBodyPart.attachFile(path);

                MimeBodyPart jpgBodyPart2 = new MimeBodyPart();
                jpgBodyPart2.attachFile(htmlPath);


                emailContent.addBodyPart(textBodyPart);
                emailContent.addBodyPart(jpgBodyPart);
                emailContent.addBodyPart(jpgBodyPart2);

                msg.setContent(emailContent);

            }else{
                Multipart emailContent = new MimeMultipart();
                MimeBodyPart textBodyPart = new MimeBodyPart();
                textBodyPart.setText(reason);
                emailContent.addBodyPart(textBodyPart);


                msg.setContent(emailContent);
            }

            javaMailSender.send(msg);

        } catch (MessagingException | IOException ex) {
            System.out.println("Greška prilikom slanja!");
        }
    }

}
