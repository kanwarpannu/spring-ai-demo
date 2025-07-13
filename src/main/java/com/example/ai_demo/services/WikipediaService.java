package com.example.ai_demo.services;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WikipediaService {

    private final RestClient restClient = RestClient
            .create("https://en.wikipedia.org/w");

    private ObjectMapper objectMapper = new ObjectMapper();

    public JsonNode getInfoFromWiki(@NonNull String param) {
        String paragraph;
        log.info("Received request for {}", param);
        JsonNode response = restClient.get()
                .uri("/api.php?action=opensearch&format=json&profile=classic&search={param}", param)
                .retrieve()
                .body(JsonNode.class);

        log.info("Received response: {}", response);
        String correctUrl = response.get(3).get(0).asText();
        log.info("correct url is {}", correctUrl);

        try {
            Document doc = Jsoup.connect(correctUrl).get();
            paragraph = doc.select("p").text();
            log.info(paragraph.translateEscapes().substring(0, 1000));
            return objectMapper.createObjectNode().put("response", paragraph.translateEscapes());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}