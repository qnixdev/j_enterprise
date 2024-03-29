package com.news_client.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class ClientNewsService {
    @Value("${proxy_server.news_url}")
    private String newsUrl;

    private final RestTemplate restTemplate;

    public ClientNewsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<List<String>> getNews() {
        ParameterizedTypeReference<List<String>> responseType = new ParameterizedTypeReference<>() {};

        return restTemplate.exchange(this.newsUrl, HttpMethod.GET, null, responseType);
    }
}