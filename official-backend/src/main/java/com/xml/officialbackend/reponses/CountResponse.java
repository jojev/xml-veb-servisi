package main.java.com.xml.officialbackend.reponses;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "count")
@XmlAccessorType(XmlAccessType.FIELD)
public class CountResponse {
	@XmlValue
    private int value;

	public CountResponse() {
		super();
	}

	public CountResponse(int value) {
		super();
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	

}