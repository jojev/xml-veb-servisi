package main.java.com.xml.userbackend.service.contract;


import java.io.IOException;
import java.time.LocalDate;

import main.java.com.xml.userbackend.model.interesovanje.InteresovanjeZaVakcinisanje;

public interface IInteresovanjeService extends  IService<InteresovanjeZaVakcinisanje>{

    InteresovanjeZaVakcinisanje create(InteresovanjeZaVakcinisanje interesovanjeZaVakcinisanje) throws Exception;

	int getNumberOfInterestedPatients(LocalDate startDate, LocalDate endDate) throws IOException;
}
