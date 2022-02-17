package main.java.com.xml.officialbackend.service.contract;


import main.java.com.xml.officialbackend.model.izvestaj_o_imunizaciji.IzvestajOImunizaciji;

public interface IIzvestajService extends IService<IzvestajOImunizaciji> {

	IzvestajOImunizaciji createReport(String startDate, String endDate, int interesovanjeCnt, int zahtevCnt)
			throws Exception;

	byte[] generatePotvrdaToXHTML(String id) throws Exception;

}
