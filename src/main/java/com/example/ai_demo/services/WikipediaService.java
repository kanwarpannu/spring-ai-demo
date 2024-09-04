package com.example.ai_demo.services;

import org.springframework.cglib.core.internal.Function;
import org.springframework.web.client.RestClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WikipediaService implements Function<WikipediaService.Request, WikipediaService.Response> {

    private final RestClient restClient = RestClient.create();

    public record Request(String request) {}
    public record Response(String response) {}

    @Override
    public Response apply(Request key) {
        log.info("Received request for {}", key);
        Response response = restClient.get()
                .uri("")
                .retrieve()
                .body(Response.class);
        log.info("Received response: {}", response);
        return response;
    }
}
