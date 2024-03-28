package com.news_client.Controller;

import com.news_client.Service.ClientNewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/news")
public class ClientNewsController {
    private final ClientNewsService clientNewsService;

    public ClientNewsController(ClientNewsService clientNewsService) {
        this.clientNewsService = clientNewsService;
    }

    @GetMapping
    public ResponseEntity<List<String>> getNews() {
        return this.clientNewsService.getNews();
    }
}