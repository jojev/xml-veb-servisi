package main.java.com.xml.officialbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class OfficialBackendApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(OfficialBackendApplication.class);
		Map<String,Object> settings =  new HashMap<>();
		settings.put("server.port","8081");
		settings.put("logging.level.org.springframework.web","DEBUG");
		app.setDefaultProperties(settings);
		app.run(args);

	}

}
