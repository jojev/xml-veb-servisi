package main.java.com.xml.officialbackend.scheduler;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import main.java.com.xml.officialbackend.service.implementation.TerminService;

@Component
public class FindMissedTermins {

	private TerminService terminService;
	
	@Autowired
	public FindMissedTermins(TerminService terminService) {
		this.terminService = terminService;
	}
	
	@Scheduled(fixedDelay = 60, initialDelay = 5, timeUnit = TimeUnit.MINUTES)
	public void FindTermins() throws Exception {
		terminService.updateVaccineStatus();
	}
}
