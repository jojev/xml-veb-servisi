package main.java.com.xml.officialbackend.service.implementation;

import main.java.com.xml.officialbackend.existdb.ExistDbManager;
import main.java.com.xml.officialbackend.model.lista_cekanja.ListaCekanja;
import main.java.com.xml.officialbackend.repository.BaseRepository;
import main.java.com.xml.officialbackend.service.contract.IListaCekanjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.modules.XMLResource;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class ListaCekanjaService implements IListaCekanjaService {
    private BaseRepository baseRepository;

    private ExistDbManager existDbManager;


    @Autowired
    public ListaCekanjaService(BaseRepository baseRepository, ExistDbManager existDbManager) {
        this.baseRepository = baseRepository;
        this.existDbManager = existDbManager;
    }

    @Override
    public List<ListaCekanja> findAll() {
        return null;
    }

    @Override
    public ListaCekanja findById(String id) throws Exception {
        return baseRepository.findById("/db/cekanje", id, ListaCekanja.class);
    }

    @Override
    public ListaCekanja create(ListaCekanja entity) throws Exception {
        String queueName = "ListaCekanja";
        
        baseRepository.save("/db/cekanje", queueName, entity, ListaCekanja.class);
        XMLResource resource = existDbManager.load("/db/cekanje", queueName);
        System.out.println(resource.getContent().toString());
        return entity;
    }

    @Override
    public ListaCekanja update(ListaCekanja entity, String id) throws Exception {
        return null;
    }

    @Override
    public void delete(String id) throws Exception {

    }

    @Override
    public ListaCekanja addPatientToQueue(ListaCekanja.Stavka stavka) throws Exception {
        Calendar calendar = stavka.getPeriodCekanja().toGregorianCalendar();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(calendar.getTimeZone());
        String dateString = formatter.format(calendar.getTime());

        String newStavka = String.format("<stavka><period_cekanja>%1$s</period_cekanja><pacijent>%2$s</pacijent><tip_vakcine>%3$s</tip_vakcine><doza>%4$d</doza></stavka>", dateString, stavka.getPacijent(), stavka.getTipVakcine(), stavka.getDoza());
        baseRepository.insertAsLastNode("/db/cekanje","ListaCekanja", "//lista_cekanja", newStavka, "http://www.ftn.uns.ac.rs/lista_cekanja");
        return findById("ListaCekanja") ;
    }

    @Override
    public void removePatientFromQueue(Integer stavkaIndex) throws Exception {
        baseRepository.removeNode("/db/cekanje", "ListaCekanja", String.format("//lista_cekanja/stavka[%s]", stavkaIndex.toString()), "http://www.ftn.uns.ac.rs/lista_cekanja");
    }
}
