package main.java.com.xml.officialbackend.service.implementation;

import main.java.com.xml.officialbackend.dto.SearchDTO;
import main.java.com.xml.officialbackend.service.contract.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class SearchService implements ISearchService {


    @Autowired
    public SearchService() {
    }

    public HttpEntity<String> setEntity(SearchDTO searchDTO, String accessToken){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity<String> httpEntity = new HttpEntity<String>(String.format(
                "<searchdto><search>%s</search></searchdto>",
                searchDTO.getSearch()), headers);
        return httpEntity;
    }

}
