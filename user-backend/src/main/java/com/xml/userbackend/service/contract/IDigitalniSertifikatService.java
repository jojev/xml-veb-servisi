package main.java.com.xml.userbackend.service.contract;

import main.java.com.xml.userbackend.model.digitalni_sertifikat.DigitalniZeleniSertifikat;

public interface IDigitalniSertifikatService extends IService<DigitalniZeleniSertifikat> {

    DigitalniZeleniSertifikat create(DigitalniZeleniSertifikat digitalniZeleniSertifikat) throws Exception;


}
