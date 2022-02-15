package main.java.com.xml.officialbackend.service.contract;

import java.time.LocalDate;

import main.java.com.xml.officialbackend.model.izvestaj_o_imunizaciji.IzvestajOImunizaciji;

public interface IIzvestajService extends IService<IzvestajOImunizaciji> {

	IzvestajOImunizaciji createReport(LocalDate startDate, LocalDate endDate, int interesovanjeCnt, int zahtevCnt)
			throws Exception;

}
