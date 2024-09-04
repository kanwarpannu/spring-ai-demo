package com.example.ai_demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@Slf4j
public class WikipediaChatController {

    private final RestClient restClient = RestClient
            .create("https://en.wikipedia.org/w");

    @GetMapping("/wiki-lookup")
    public JsonNode getMethodName(@RequestParam String param) {
        log.info("Received request for {}", param);
        JsonNode response = restClient.get()
                .uri("/api.php?action=opensearch&format=json&profile=classic&search={param}", param)
                .retrieve()
                .body(JsonNode.class);

        log.info("Received response: {}", response);
        String correctUrl = response.get(3).get(0).asText();

        try {
            Document doc = Jsoup.connect(correctUrl).get();
            List<Element> elementList = new ArrayList<>();
            elementList = doc.select("p").stream().collect(Collectors.toList());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        


        String trueResponse = RestClient.create(correctUrl)
                .get()
                .retrieve()
                .body(String.class);

        return response;
    }

}
