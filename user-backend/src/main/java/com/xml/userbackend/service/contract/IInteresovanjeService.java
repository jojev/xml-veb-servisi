package main.java.com.xml.userbackend.service.contract;


import main.java.com.xml.userbackend.model.interesovanje.InteresovanjeZaVakcinisanje;

public interface IInteresovanjeService extends  IService<InteresovanjeZaVakcinisanje>{

    InteresovanjeZaVakcinisanje create(InteresovanjeZaVakcinisanje interesovanjeZaVakcinisanje) throws Exception;
}
