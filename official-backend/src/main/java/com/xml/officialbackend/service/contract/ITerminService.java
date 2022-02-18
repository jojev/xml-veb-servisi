package main.java.com.xml.officialbackend.service.contract;

import main.java.com.xml.officialbackend.model.lista_cekanja.ListaCekanja;
import main.java.com.xml.officialbackend.model.termin.Termin;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

public interface ITerminService extends IService<Termin> {
    void assignToPatient() throws Exception;
    Termin findAvailableAppointment(String vaccineType, ListaCekanja.Stavka stavka) throws Exception;
    void addTerminOrAddToListaCekanja(String vaccineType, Integer numberOfVaccine, String usernameOfPatient, String emailOfPatient, XMLGregorianCalendar dateOfLast) throws Exception;
	void updateVaccineStatus() throws Exception;
	void processTermin(String jmbg, String tipVakcine, int doza) throws Exception;
}
