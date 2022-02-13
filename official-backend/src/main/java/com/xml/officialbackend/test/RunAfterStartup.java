package main.java.com.xml.officialbackend.test;

import main.java.com.xml.officialbackend.existdb.ExistDbManager;
import main.java.com.xml.officialbackend.model.lista_cekanja.ListaCekanja;
import main.java.com.xml.officialbackend.service.implementation.ListaCekanjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.GregorianCalendar;

@Component
public class RunAfterStartup {
    @Autowired
    Test test;

    @Autowired
    ListaCekanjaService lcs;

    @Autowired
    ExistDbManager manager;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() throws Exception {
//        test.testWriteToExistDb();
//        test.testReadFromExistDb();
//        test.testUpdateDocumentFromExistDb();
//        test.testWriteToRdf();
//        test.testReadFromRdf();

        ListaCekanja lsc = new ListaCekanja();
        ListaCekanja.Stavka stavka = new ListaCekanja.Stavka();
        stavka.setPacijent("Dragana");
        stavka.setTipVakcine("fajzer");
        GregorianCalendar c = new GregorianCalendar();
        XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        stavka.setPeriodCekanja(date2);

        lcs.create(lsc);

        lcs.addPatientToQueue(stavka);
        lcs.removePatientFromQueue(1);

    }
}
