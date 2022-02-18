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
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import main.java.com.xml.officialbackend.existdb.ExistDbManager;
import main.java.com.xml.officialbackend.jaxb.JaxBParser;
import main.java.com.xml.officialbackend.model.izvestaj_o_imunizaciji.IzvestajOImunizaciji.RaspodelaProizvodjaca.Vakcina;
import main.java.com.xml.officialbackend.model.lista_cekanja.ListaCekanja;
import main.java.com.xml.officialbackend.model.poslednji_termin.PoslednjiTermin;
import main.java.com.xml.officialbackend.model.stanjevakcine.StanjeVakcine;
import main.java.com.xml.officialbackend.model.termin.Termin;
import main.java.com.xml.officialbackend.repository.BaseRepository;
import main.java.com.xml.officialbackend.service.EmailService;
import main.java.com.xml.officialbackend.service.contract.ITerminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class TerminService implements ITerminService {
    private BaseRepository baseRepository;

    private JaxBParser jaxBParser;
    
    private ListaCekanjaService listaCekanjaService;

    private VaccineStatusService vaccineStatusService;

    private PoslednjiTerminService poslednjiTerminService;
    
    private ExistDbManager existDbManager;

    private EmailService emailService;
    
    @Autowired
    public TerminService(BaseRepository baseRepository, ListaCekanjaService listaCekanjaService,
                         VaccineStatusService vaccineStatusService, PoslednjiTerminService poslednjiTerminService,
                         ExistDbManager existDbManager, EmailService emailService, JaxBParser jaxBParser) {
        this.baseRepository = baseRepository;
        this.listaCekanjaService = listaCekanjaService;
        this.vaccineStatusService = vaccineStatusService;
        this.poslednjiTerminService = poslednjiTerminService;
        this.existDbManager = existDbManager;
        this.emailService = emailService;
        this.jaxBParser = jaxBParser;
        
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
    	Termin termin = this.findById(id);
    	termin.setIspostovan(entity.isIspostovan());
    	termin.setObradjen(entity.isObradjen());
    	
    	baseRepository.update("/db/termini", id, "//*:termin//*:ispostovan", String.valueOf(entity.isIspostovan()), "http://www.ftn.uns.ac.rs/termin");
    	baseRepository.update("/db/termini", id, "//*:termin/*:obradjen", String.valueOf(entity.isObradjen()), "http://www.ftn.uns.ac.rs/termin");

    	return findById(id);
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
        vaccineTypes.add("AstraZeneca-Oxford");

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
        
        if(result <= 0) {
        	date = now;
        }
        
        StanjeVakcine stanje = null;
        try
        {
        	stanje = vaccineStatusService.findById(vaccineType);
        }
        catch(Exception e) {
        	return null;
        }
        
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
                    newTermin.setEmailPacijenta(stavka.getEmailPacijenta());
                    newTermin.setJmbgPacijenta(stavka.getJmbgPacijenta());
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
                newTermin.setEmailPacijenta(stavka.getEmailPacijenta());
                newTermin.setJmbgPacijenta(stavka.getJmbgPacijenta());
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

    @Override
    public void addTerminOrAddToListaCekanja(String vaccineType, Integer numberOfVaccine, String usernameOfPatient, String emailOFPatient, XMLGregorianCalendar dateOfLastVaccine) throws Exception {
        ListaCekanja.Stavka stavka = new ListaCekanja.Stavka();
        stavka.setJmbgPacijenta(usernameOfPatient);
        stavka.setEmailPacijenta(emailOFPatient);
        stavka.setTipVakcine(vaccineType);
        stavka.setDoza(numberOfVaccine);
        GregorianCalendar calendar = dateOfLastVaccine.toGregorianCalendar();
        if(numberOfVaccine == 2) {
            if(vaccineType.equalsIgnoreCase("AstraZeneca" )) {
                calendar.add(Calendar.MONTH, 2);
            }
            else {
                calendar.add(Calendar.DAY_OF_YEAR, 21);
            }
        }
        else {
            calendar.add(Calendar.MONTH, 6);
        }

        XMLGregorianCalendar xmlGregorianCalendar =
                DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        stavka.setPeriodCekanja(xmlGregorianCalendar);
        
        Termin termin = findAvailableAppointment(vaccineType, stavka);
        
        if(termin == null) {
            listaCekanjaService.addPatientToQueue(stavka);
        }
        else {
        	emailService.sendTermin(termin);
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
    
    public List<Resource> findVaccineByDosage(String startDay, String endDay) throws Exception {
    	String xqueryPath = "data/xquery/raspodela_po_dozama.xqy";
    	String xqueryExpression = readFile(xqueryPath, StandardCharsets.UTF_8);
    	
    	XMLGregorianCalendar startDate =
    			  DatatypeFactory.newInstance().newXMLGregorianCalendar(startDay.toString());
    	XMLGregorianCalendar endDate =
  			  DatatypeFactory.newInstance().newXMLGregorianCalendar(endDay);

    	String formattedXQueryExpresion = String.format(xqueryExpression, startDate, endDate);
        System.out.println(formattedXQueryExpresion);
    	return existDbManager.executeXquery("/db/termini", "", formattedXQueryExpresion);
    }
    
    public List<Resource> findVaccineByManufacturer(String startDay, String endDay) throws Exception {
    	String xqueryPath = "data/xquery/raspodela_po_proizvodjacu.xqy";
    	String xqueryExpression = readFile(xqueryPath, StandardCharsets.UTF_8);
    	
    	XMLGregorianCalendar startDate = 
    			  DatatypeFactory.newInstance().newXMLGregorianCalendar(startDay.toString());
    	XMLGregorianCalendar endDate = 
  			  DatatypeFactory.newInstance().newXMLGregorianCalendar(endDay);

    	String formattedXQueryExpresion = String.format(xqueryExpression, startDate, endDate);
    	
    	return existDbManager.executeXquery("/db/termini", "", formattedXQueryExpresion);
    }
    
    public static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

    @Override
    public void processTermin(String jmbg,  String tipVakcine, int doza) throws Exception {
    	List<Resource> resource = this.executeTerminIspostovan(jmbg, tipVakcine, doza);
    	
    	for(Resource res : resource) {
    		Termin termin = jaxBParser.unmarshall(res.getContent().toString(), Termin.class);
    		termin.setIspostovan(true);
    		termin.setObradjen(true);
    		String tokens[] = termin.getAbout().split("/");
    		String terminId = tokens[tokens.length - 1];
    		this.update(termin, terminId);
    		
    	}
    }
    
    public List<Resource> executeTerminIspostovan(String jmbg, String tipVakcine, int doza) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
    	String xqueryPath = "data/xquery/pretraga_termina_po_jmbg_vakcini_dozi.xqy";
    	String xqueryExpression = readFile(xqueryPath, StandardCharsets.UTF_8);
    	
    	String formattedXQueryExpresion = String.format(xqueryExpression, doza, jmbg, tipVakcine.strip());
    	System.out.println(formattedXQueryExpresion);
    	return existDbManager.executeXquery("/db/termini", "", formattedXQueryExpresion);
    }
    
    public List<Resource> executeMissedTerminQuery() throws Exception {
    	String xqueryPath = "data/xquery/pretraga_po_neispostovanim_terminima.xqy";
    	String xqueryExpression = readFile(xqueryPath, StandardCharsets.UTF_8);
    	
    	GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());

        XMLGregorianCalendar startDate =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
    	
    	String formattedXQueryExpresion = String.format(xqueryExpression, startDate);
    	System.out.println(formattedXQueryExpresion);
    	return existDbManager.executeXquery("/db/termini", "", formattedXQueryExpresion);
    }
    
    @Override
	public void updateVaccineStatus() throws Exception {
    	List<Termin> termini = this.findNeobradjeniTermini();
    	
    	for(Termin termin : termini) {
    		try {
    			StanjeVakcine stanje = vaccineStatusService.findById(termin.getTipVakcine());
        		stanje.setKolicina(stanje.getKolicina() + 1);
        		vaccineStatusService.update(stanje, termin.getTipVakcine());
    		}
       		catch(Exception e) {
    			StanjeVakcine vakcine = new StanjeVakcine();
    			vakcine.setKolicina(1);
    			vakcine.setVakcina(termin.getTipVakcine());
        		vaccineStatusService.create(vakcine);
    		}
    	}
	}
    
    private List<Termin> findNeobradjeniTermini() throws Exception {
    	List<Resource> resource = executeMissedTerminQuery();
    	List<Termin> termini = new ArrayList<Termin>();
    	
    	for(Resource res : resource) {
    		Termin termin = jaxBParser.unmarshall(res.getContent().toString(), Termin.class);
    		termin.setObradjen(true);
    		String tokens[] = termin.getAbout().split("/");
    		String terminId = tokens[tokens.length - 1];
    		this.update(termin, terminId);
    	}
		
		return termini;
    }
}
