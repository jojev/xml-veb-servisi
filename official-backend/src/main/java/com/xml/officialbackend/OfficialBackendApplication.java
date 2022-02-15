package main.java.com.xml.officialbackend;

import main.java.com.xml.officialbackend.model.lista_cekanja.ListaCekanja;
import main.java.com.xml.officialbackend.model.poslednji_termin.PoslednjiTermin;
import main.java.com.xml.officialbackend.model.stanjevakcine.StanjeVakcine;
import main.java.com.xml.officialbackend.service.implementation.ListaCekanjaService;
import main.java.com.xml.officialbackend.service.implementation.PoslednjiTerminService;
import main.java.com.xml.officialbackend.service.implementation.TerminService;
import main.java.com.xml.officialbackend.service.implementation.VaccineStatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.datatype.DatatypeFactory;
import java.util.GregorianCalendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
public class OfficialBackendApplication{

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(OfficialBackendApplication.class);
		Map<String,Object> settings =  new HashMap<>();
		settings.put("server.port","8081");
		settings.put("logging.level.org.springframework.web","DEBUG");
		app.setDefaultProperties(settings);
		app.run(args);
	}
}
