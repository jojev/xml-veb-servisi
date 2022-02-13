package main.java.com.xml.officialbackend.service.contract;

import main.java.com.xml.officialbackend.model.lista_cekanja.ListaCekanja;
import org.xmldb.api.base.XMLDBException;

import java.io.IOException;

public interface IListaCekanjaService extends IService<ListaCekanja> {
	ListaCekanja addPatientToQueue(ListaCekanja.Stavka stavka) throws Exception;
	void removePatientFromQueue(Integer stavkaIndex) throws Exception;
}
