package main.java.com.xml.officialbackend.service.implementation;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.namespace.QName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import main.java.com.xml.officialbackend.existdb.ExistDbManager;
import main.java.com.xml.officialbackend.model.izvestaj_o_imunizaciji.IzvestajOImunizaciji;
import main.java.com.xml.officialbackend.model.izvestaj_o_imunizaciji.IzvestajOImunizaciji.DatumIzdavanja;
import main.java.com.xml.officialbackend.model.izvestaj_o_imunizaciji.IzvestajOImunizaciji.Period;
import main.java.com.xml.officialbackend.model.izvestaj_o_imunizaciji.IzvestajOImunizaciji.Period.Do;
import main.java.com.xml.officialbackend.model.izvestaj_o_imunizaciji.IzvestajOImunizaciji.Period.Od;
import main.java.com.xml.officialbackend.model.izvestaj_o_imunizaciji.IzvestajOImunizaciji.RaspodelaPoDozama;
import main.java.com.xml.officialbackend.model.izvestaj_o_imunizaciji.IzvestajOImunizaciji.RaspodelaPoDozama.Doza;
import main.java.com.xml.officialbackend.model.izvestaj_o_imunizaciji.IzvestajOImunizaciji.RaspodelaProizvodjaca;
import main.java.com.xml.officialbackend.model.izvestaj_o_imunizaciji.IzvestajOImunizaciji.RaspodelaProizvodjaca.Vakcina;
import main.java.com.xml.officialbackend.model.potvrda_o_vakcinaciji.PotvrdaOVakcinaciji;
import main.java.com.xml.officialbackend.rdf.FusekiWriter;
import main.java.com.xml.officialbackend.rdf.MetadataExtractor;
import main.java.com.xml.officialbackend.repository.BaseRepository;
import main.java.com.xml.officialbackend.service.contract.IIzvestajService;
import main.java.com.xml.officialbackend.transformations.HtmlTransformer;
import main.java.com.xml.officialbackend.transformations.XSLFOTransformer;

@Service
public class IzvestajService implements IIzvestajService{

	private BaseRepository baseRepository;
	
	private MetadataExtractor metadataExctractor;
	
    private ExistDbManager existDbManager;

	private TerminService terminService;
   
    private HtmlTransformer htmlTransformer;
    
    private XSLFOTransformer xslfoTransformer;
	
	@Autowired
	public IzvestajService(BaseRepository baseRepository, ExistDbManager existDbManager, MetadataExtractor metadataExctractor,
			TerminService terminService, HtmlTransformer htmlTransformer, XSLFOTransformer xslfoTransformer) {
		this.baseRepository = baseRepository;
		this.metadataExctractor = metadataExctractor;
		this.existDbManager = existDbManager;
		this.terminService = terminService;
		this.htmlTransformer = htmlTransformer;
		this.xslfoTransformer = xslfoTransformer;
	}
	
	
	@Override
	public List<IzvestajOImunizaciji> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IzvestajOImunizaciji findById(String id) throws Exception {
		
		return baseRepository.findById("/db/izvestaj_o_imunizaciji", id, IzvestajOImunizaciji.class);
	}

	@Override
	public IzvestajOImunizaciji create(IzvestajOImunizaciji entity) throws Exception {
		String izvestajId = UUID.randomUUID().toString();
		entity.setAbout("http://www.ftn.uns.ac.rs/rdf/izvestaj_o_imunizaciji/" + izvestajId);
		entity.setTypeof("pred:IdentifikatorDokumenta");
		entity.getPeriod().getOd().setProperty("pred:PeriodOdDatuma");
		entity.getPeriod().getOd().setDatatype("xs:date");
		entity.getPeriod().getDo().setProperty("pred:PeriodDoDatuma");
		entity.getPeriod().getDo().setDatatype("xs:date");
		entity.getDatumIzdavanja().setProperty("pred:IzdatDatuma");
		entity.getDatumIzdavanja().setDatatype("xs:date");
		
		entity.getOtherAttributes().put(QName.valueOf("xmlns:pred"), "http://www.ftn.uns.ac.rs/rdf/izvestaj_o_imunizaciji/predicate/");
        entity.getOtherAttributes().put(QName.valueOf("xmlns:xs"), "http://www.w3.org/2001/XMLSchema#");
        
        baseRepository.save("/db/izvestaj_o_imunizaciji", izvestajId, entity, IzvestajOImunizaciji.class);
		
        XMLResource resource = existDbManager.load("/db/izvestaj_o_imunizaciji", izvestajId);
        byte[] out =  metadataExctractor.extractMetadataFromXmlContent(resource.getContent().toString());
        FusekiWriter.saveRDF(new ByteArrayInputStream(out), "izvestaj_o_imunizaciji");

        return entity;
	}

	@Override
	public IzvestajOImunizaciji update(IzvestajOImunizaciji entity, String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public IzvestajOImunizaciji createReport(String startDate, String endDate, int interesovanjeCnt, int zahtevCnt) throws Exception {
		List<Resource> vaccinesByDosage = terminService.findVaccineByDosage(startDate, endDate);
		List<Resource> vaccinesByManufacturer = terminService.findVaccineByManufacturer(startDate, endDate);
		int totalVaccines = findTotalVaccines(vaccinesByDosage);
		
		IzvestajOImunizaciji izvestaj = new IzvestajOImunizaciji();
		izvestaj.setBrojInteresovanjaOImunizaciji(BigInteger.valueOf(interesovanjeCnt));
		izvestaj.setBrojZahtevaZaDigiatlniSertifikat(BigInteger.valueOf(zahtevCnt));
		izvestaj.setBrojIzdatihDigitalnihSertifikata(BigInteger.valueOf(0));
		izvestaj.setBrojPrimljenihDozaVakcina(BigInteger.valueOf(totalVaccines));
		Od odPeriod = new Od();
		odPeriod.setValue(DatatypeFactory.newInstance().newXMLGregorianCalendar(startDate.toString()));
		Do doPeriod = new Do();
		doPeriod.setValue(DatatypeFactory.newInstance().newXMLGregorianCalendar(endDate.toString()));
		Period period = new Period();
		period.setOd(odPeriod);
		period.setDo(doPeriod);
		izvestaj.setPeriod(period);
		
		DatumIzdavanja datum = new DatumIzdavanja();

		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(date);
		datum.setValue(DatatypeFactory.newInstance().newXMLGregorianCalendar(strDate));
		izvestaj.setDatumIzdavanja(datum);
		
		List<Doza> doze = findDosages(vaccinesByDosage);
		if(doze.size() < 1) {
			Doza doza = new Doza();
			doza.setKolicina(BigInteger.valueOf(0));
			doza.setRedniBroj(BigInteger.valueOf(1));
			doze.add(doza);
			Doza doza1 = new Doza();
			doza1.setKolicina(BigInteger.valueOf(0));
			doza1.setRedniBroj(BigInteger.valueOf(2));
			doze.add(doza1);
			Doza doza2 = new Doza();
			doza2.setKolicina(BigInteger.valueOf(0));
			doza2.setRedniBroj(BigInteger.valueOf(3));
			doze.add(doza2);
			
		}
		RaspodelaPoDozama raspodelaPoDozama = new RaspodelaPoDozama();
		raspodelaPoDozama.getDoza().addAll(doze);
		izvestaj.setRaspodelaPoDozama(raspodelaPoDozama);
		
		List<Vakcina> vakcine = findManufacturers(vaccinesByManufacturer);
		if(vakcine.size() < 1) {
			Vakcina vakcina1 = new Vakcina();
			vakcina1.setTip("Pfizer-BioNTech");
			vakcina1.setValue(BigInteger.valueOf(0));
			vakcine.add(vakcina1);
			Vakcina vakcina2 = new Vakcina();
			vakcina2.setTip("Sinopharm");
			vakcina2.setValue(BigInteger.valueOf(0));
			vakcine.add(vakcina2);
			Vakcina vakcina3 = new Vakcina();
			vakcina3.setTip("Moderna");
			vakcina3.setValue(BigInteger.valueOf(0));
			vakcine.add(vakcina3);
			Vakcina vakcina4 = new Vakcina();
			vakcina4.setTip("Sputnik V");
			vakcina4.setValue(BigInteger.valueOf(0));
			vakcine.add(vakcina4);
			Vakcina vakcina5 = new Vakcina();
			vakcina5.setTip("AstraZenec");
			vakcina5.setValue(BigInteger.valueOf(0));
			vakcine.add(vakcina5);
		}
		RaspodelaProizvodjaca raspodelaProizvodjaca = new RaspodelaProizvodjaca();
		raspodelaProizvodjaca.getVakcina().addAll(vakcine);
		izvestaj.setRaspodelaProizvodjaca(raspodelaProizvodjaca);
		
		return create(izvestaj);
		
	}
	
	private List<Doza> findDosages(List<Resource> resource) throws Exception {
		List<Doza> doze = new ArrayList<Doza>();
		
		for(Resource res: resource) {
			String[] tokens = res.getContent().toString().split("-");
			Doza doza = new Doza();
			doza.setRedniBroj(BigInteger.valueOf(Integer.parseInt(tokens[0])));
			doza.setKolicina(BigInteger.valueOf(Integer.parseInt(tokens[1])));
			
			doze.add(doza);
		}
		
		return doze;
	}
	
	private List<Vakcina> findManufacturers(List<Resource> resource) throws Exception {
		List<Vakcina> vakcine = new ArrayList<Vakcina>();
		
		for(Resource res: resource) {
			String[] tokens = res.getContent().toString().split("-");
			Vakcina vakcina = new Vakcina();
			vakcina.setTip(tokens[0]);
			vakcina.setValue(BigInteger.valueOf(Integer.parseInt(tokens[1])));
			
			vakcine.add(vakcina);
		}
		
		return vakcine;
	}
	
	private int findTotalVaccines(List<Resource> resources) throws XMLDBException {
		int totalVaccines = 0;
		
		for(Resource res : resources) {
			String[] tokens = res.getContent().toString().split("-");
			totalVaccines += Integer.parseInt(tokens[1]);
		}
		
		return totalVaccines;
	}
	
	 @Override
	 public byte[] generateIzvestajToXHTML(String id) throws Exception {
		 IzvestajOImunizaciji izvestaj = findById(id);
	     return htmlTransformer.generateHTMLtoByteArray(izvestaj);
	 }
	 
	 @Override
	 public byte[] generateIzestajToPDF(String id) throws Exception {
		 IzvestajOImunizaciji izvestaj = findById(id);
	     return xslfoTransformer.generatePDFtoByteArray(izvestaj);
	 }

}
