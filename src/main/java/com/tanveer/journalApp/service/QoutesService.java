package com.tanveer.journalApp.service;

import com.tanveer.journalApp.response.QoutesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class QoutesService {

    @Autowired
    private RestTemplate restTemplate;

    public QoutesResponse getQuotes(){
       ResponseEntity<QoutesResponse> response = restTemplate.exchange("https://www.mockachino.com/bb4afdf7-30ab-45/qoutes", HttpMethod.GET, null, QoutesResponse.class);
        return response.getBody();
    }
}
