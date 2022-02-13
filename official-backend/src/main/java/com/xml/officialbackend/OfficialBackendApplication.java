package main.java.com.xml.officialbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class OfficialBackendApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(OfficialBackendApplication.class);
		app.setDefaultProperties(Collections
				.<String, Object>singletonMap("server.port", "8081"));
		app.run(args);
	}

}
