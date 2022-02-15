package main.java.com.xml.officialbackend.service.contract;

import main.java.com.xml.officialbackend.dto.SearchDTO;
import org.springframework.http.HttpEntity;

public interface ISearchService {

    HttpEntity<String> setEntity(SearchDTO searchDTO, String accessToken);
}
