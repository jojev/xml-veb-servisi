package main.java.com.xml.officialbackend.service.contract;

import main.java.com.xml.officialbackend.model.lista_cekanja.ListaCekanja;
import main.java.com.xml.officialbackend.model.termin.Termin;

public interface ITerminService extends IService<Termin> {
    void assignToPatient() throws Exception;
    Termin findAvailableAppointment(String vaccineType, ListaCekanja.Stavka stavka) throws Exception;

    }
