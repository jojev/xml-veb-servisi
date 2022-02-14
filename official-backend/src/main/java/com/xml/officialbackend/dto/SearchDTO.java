package main.java.com.xml.officialbackend.dto;


import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "search",
})
@XmlRootElement(name = "searchdto")
public class SearchDTO {

    @XmlElement(required = true)
    private String search;

    public SearchDTO() {
    }

    public SearchDTO(String search) {
        this.search = search;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
