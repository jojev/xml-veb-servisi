package main.java.com.xml.userbackend.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import main.java.com.xml.userbackend.dto.SearchDTO;
import main.java.com.xml.userbackend.service.contract.ISearchService;

@Service
public class SearchService implements ISearchService{

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
