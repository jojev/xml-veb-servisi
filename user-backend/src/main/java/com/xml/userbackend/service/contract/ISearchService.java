package main.java.com.xml.userbackend.service.contract;

import org.springframework.http.HttpEntity;

import main.java.com.xml.userbackend.dto.SearchDTO;

public interface ISearchService {

	HttpEntity<String> setEntity(SearchDTO searchDTO, String accessToken);
}
