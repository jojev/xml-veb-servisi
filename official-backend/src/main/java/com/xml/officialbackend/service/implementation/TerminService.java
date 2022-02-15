package main.java.com.xml.officialbackend.service.implementation;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;

import main.java.com.xml.officialbackend.existdb.ExistDbManager;
import main.java.com.xml.officialbackend.model.lista_cekanja.ListaCekanja;
import main.java.com.xml.officialbackend.model.poslednji_termin.PoslednjiTermin;
import main.java.com.xml.officialbackend.model.stanjevakcine.StanjeVakcine;
import main.java.com.xml.officialbackend.model.termin.Termin;
import main.java.com.xml.officialbackend.repository.BaseRepository;
import main.java.com.xml.officialbackend.service.contract.ITerminService;

@Service
public class TerminService implements ITerminService {
    private BaseRepository baseRepository;

    private ListaCekanjaService listaCekanjaService;

    private VaccineStatusService vaccineStatusService;

    private PoslednjiTerminService poslednjiTerminService;
    
    private ExistDbManager existDbManager;

    @Autowired
    public TerminService(BaseRepository baseRepository, ListaCekanjaService listaCekanjaService,
                         VaccineStatusService vaccineStatusService, PoslednjiTerminService poslednjiTerminService,
                         ExistDbManager existDbManager) {
        this.baseRepository = baseRepository;
        this.listaCekanjaService = listaCekanjaService;
        this.vaccineStatusService = vaccineStatusService;
        this.poslednjiTerminService = poslednjiTerminService;
        this.existDbManager = existDbManager;
    }

    @Override
    public List<Termin> findAll() {
        return null;
    }

    @Override
    public Termin findById(String id) throws Exception {
        return baseRepository.findById("/db/termini", id, Termin.class);
    }

    @Override
    public Termin create(Termin entity) throws Exception {
        String terminId = UUID.randomUUID().toString();
        entity.setAbout("http://www.ftn.uns.ac.rs/rdf/temini/" + terminId);

        baseRepository.save("/db/termini", terminId, entity, Termin.class);
        return findById(terminId);
    }

    @Override
    public Termin update(Termin entity, String id) throws Exception {
        return null;
    }

    @Override
    public void delete(String id) throws Exception {

    }

    @Override
    public void assignToPatient() throws Exception {
        ListaCekanja lista = listaCekanjaService.findById("ListaCekanja");
        List<String> vaccineTypes = new ArrayList<>();
        vaccineTypes.add("Pfizer-BioNTech");
        vaccineTypes.add("Moderna");
        vaccineTypes.add("Sputnik V");
        vaccineTypes.add("Sinopharm");
        vaccineTypes.add("AstraZeneca");

        for(int i = 0; i < lista.getStavka().size(); i++) {
            ListaCekanja.Stavka stavka = lista.getStavka().get(i);

            if(stavka.getTipVakcine().equalsIgnoreCase("Bilo koja") ||
                    stavka.getTipVakcine().equalsIgnoreCase("Било која")) {
                for(String vaccineType: vaccineTypes) {
                    Termin termin = findAvailableAppointment(vaccineType, stavka);
                    if (termin != null) {
                        listaCekanjaService.removePatientFromQueue(i+1);
                        break;
                    }
                }
            }
            else {
                Termin termin = findAvailableAppointment(stavka.getTipVakcine(), stavka);
                if (termin != null) {
                    listaCekanjaService.removePatientFromQueue(i+1);
                }
            }
        }
    }

    @Override
    public Termin findAvailableAppointment(String vaccineType, ListaCekanja.Stavka stavka) throws Exception {
        XMLGregorianCalendar date = stavka.getPeriodCekanja();
        
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());

        XMLGregorianCalendar now =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        int result = date.toGregorianCalendar().compareTo(now.toGregorianCalendar());
        
        if(result > 0) {
        	date = now;
        }
        
        StanjeVakcine stanje = vaccineStatusService.findById(vaccineType);
        if(stanje == null || stanje.getKolicina() < 1) {
        	return null;
        }
        
        while(true) {
        	String findDate = date.getYear() + "-" + date.getMonth() + "-" + date.getDay();
            PoslednjiTermin termin =  poslednjiTerminService.findById(findDate);
            
            if(termin != null) {
                if(termin.getBrojTermina() < 128 ) {
                    Termin newTermin = new Termin();
                    newTermin.setTrajanje(BigInteger.valueOf(15));
                    newTermin.setPacijent(stavka.getPacijent());
                    newTermin.setIspostovan(false);
                    newTermin.setDoza(stavka.getDoza());
                    newTermin.setTipVakcine(stavka.getTipVakcine());
                    newTermin.setDatumVreme(makeAppointment(termin.getBrojTermina(), date));

                    Termin savedTermin = this.create(newTermin);
                    this.updateVaccineStatusAndLastAppointment(savedTermin, termin, stanje, date);
                    
                    return savedTermin;
                }
            }
            else {
                Termin newTermin = new Termin();
                newTermin.setTrajanje(BigInteger.valueOf(15));
                newTermin.setPacijent(stavka.getPacijent());
                newTermin.setIspostovan(false);
                newTermin.setDoza(stavka.getDoza());
                newTermin.setTipVakcine(stavka.getTipVakcine());
                newTermin.setDatumVreme(makeAppointment(0, date));

                Termin savedTermin = this.create(newTermin);
                this.updateVaccineStatusAndLastAppointment(savedTermin, null, stanje, date);
                
                return savedTermin;
            }

            Duration d = DatatypeFactory.newInstance().newDuration(86400000);
            date.add(d);
            
        }
    }

    private XMLGregorianCalendar makeAppointment(int appointmentsCnt, XMLGregorianCalendar appointmentDate) throws ParseException, DatatypeConfigurationException {
        //Racunala sam da imamo 4 radnika * 15 jer nam toliko termin traje dijelimo sa 60 da dobijemo sate, ali kako od 9 radi dodala sam jos 9 sati na to
        int hours = ((appointmentsCnt / 4) * 15 / 60) + 9;
        int minutes = ((appointmentsCnt / 4) * 15) % 60;
        
        String dateTime = appointmentDate.getYear() + "-" + appointmentDate.getMonth() + "-" + appointmentDate.getDay()
                + " " + hours + ":" + minutes + ":00";
        
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = format.parse(dateTime);

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);

        XMLGregorianCalendar xmlGregCal =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);

        return xmlGregCal;
    }
    
    private void updateVaccineStatusAndLastAppointment(Termin appointment, PoslednjiTermin lastAppointment, StanjeVakcine vaccineStatus, XMLGregorianCalendar date) throws Exception {
    	vaccineStatus.setKolicina(vaccineStatus.getKolicina() - 1);
       	vaccineStatusService.update(vaccineStatus, vaccineStatus.getVakcina());
    	
    	if(lastAppointment == null) {
    		PoslednjiTermin newLastAppointment = new PoslednjiTermin();
    		newLastAppointment.setBrojTermina(1);
    		newLastAppointment.setDatum(date);
    		
    		poslednjiTerminService.create(newLastAppointment);
    	}
    	else {
    		String findDate = date.getYear() + "-" + date.getMonth() + "-" + date.getDay();
    		lastAppointment.setBrojTermina(lastAppointment.getBrojTermina() + 1);
    		
    		poslednjiTerminService.update(lastAppointment, findDate);
    	}
    }
    
    public List<Resource> findVaccineByDosage(LocalDate startDay, LocalDate endDay) throws Exception {
    	String xqueryPath = "data/xquery/raspodela_po_dozama.xqy";
    	String xqueryExpression = readFile(xqueryPath, StandardCharsets.UTF_8);
    	
    	XMLGregorianCalendar startDate = 
    			  DatatypeFactory.newInstance().newXMLGregorianCalendar(startDay.toString());
    	XMLGregorianCalendar endDate = 
  			  DatatypeFactory.newInstance().newXMLGregorianCalendar(endDay.plusMonths(6).toString());

    	String formattedXQueryExpresion = String.format(xqueryExpression, startDate, endDate);
    	
    	return existDbManager.executeXquery("/db/termini", "", formattedXQueryExpresion);
    }
    
    public List<Resource> findVaccineByManufacturer(LocalDate startDay, LocalDate endDay) throws Exception {
    	String xqueryPath = "data/xquery/raspodela_po_proizvodjacu.xqy";
    	String xqueryExpression = readFile(xqueryPath, StandardCharsets.UTF_8);
    	
    	XMLGregorianCalendar startDate = 
    			  DatatypeFactory.newInstance().newXMLGregorianCalendar(startDay.toString());
    	XMLGregorianCalendar endDate = 
  			  DatatypeFactory.newInstance().newXMLGregorianCalendar(endDay.plusMonths(6).toString());

    	String formattedXQueryExpresion = String.format(xqueryExpression, startDate, endDate);
    	
    	return existDbManager.executeXquery("/db/termini", "", formattedXQueryExpresion);
    }
    
    public static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}
