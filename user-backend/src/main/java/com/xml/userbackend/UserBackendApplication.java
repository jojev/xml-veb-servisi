package main.java.com.xml.userbackend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.userdetails.User;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import java.util.Properties;

@SpringBootApplication
@Configuration
public class UserBackendApplication{

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(UserBackendApplication.class);
		app.setDefaultProperties(Collections
				.<String, Object>singletonMap("server.port", "8080"));
		app.run(args);
	}

	
	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.mailtrap.io");
		mailSender.setPort(2525);

		mailSender.setUsername("df2937cc739ea9");
		mailSender.setPassword("b3cb1c2c60096a");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.starttls.required", "true");
		props.put("mail.debug", "true");
		props.put("management.health.mail.enabled", "false");
		return mailSender;
	}


}
