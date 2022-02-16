package main.java.com.xml.officialbackend.dto;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "search",
        "collection"
})
@XmlRootElement(name = "metadatasearch")
public class MetadataSearchDTO {

    @XmlElement(required = true)
    private String search;

    @XmlElement(required = true)
    private String collection;


    public MetadataSearchDTO() {
    }

    public MetadataSearchDTO(String search, String collection) {
        this.search = search;
        this.collection = collection;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

}
