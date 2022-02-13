package main.java.com.xml.officialbackend.service.contract;

import main.java.com.xml.officialbackend.model.stanjevakcine.StanjeVakcine;

import java.util.List;

public interface IVaccineStatusService extends IService<StanjeVakcine> {
    List<StanjeVakcine> findByHospitalAndVaccine(String hospital, String vaccine);
}
