package main.java.com.xml.officialbackend.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "izvestajId",
        "collection"
})

@XmlRootElement(name = "izvestajdto")
public class IzvestajDTO {
	@XmlElement(required = true)
    private String izvestajId;

    @XmlElement(required = true)
    private byte[] collection;

	public IzvestajDTO() {
		super();
	}

	public IzvestajDTO(String izvestajId, byte[] collection) {
		super();
		this.izvestajId = izvestajId;
		this.collection = collection;
	}

	public String getIzvestajId() {
		return izvestajId;
	}

	public void setIzvestajId(String izvestajId) {
		this.izvestajId = izvestajId;
	}

	public byte[] getCollection() {
		return collection;
	}

	public void setCollection(byte[] collection) {
		this.collection = collection;
	}

    
}
