package main.java.com.xml.officialbackend.service.contract;

import main.java.com.xml.officialbackend.model.lista_cekanja.ListaCekanja;

public interface IListaCekanjaService extends IService<ListaCekanja> {
	ListaCekanja addPatientToQueue(ListaCekanja.Stavka stavka);
}
