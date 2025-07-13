package com.example.ai_demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.ai_demo.services.WikipediaService;
import com.example.ai_demo.tools.WikiTools;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@Slf4j
@RequiredArgsConstructor
public class WikipediaChatController {

    private final WikipediaService wikipediaService;
    private final ChatClient textClient;
    // private final WikiTools wikiTools;

    @GetMapping("/wiki-lookup")
    public JsonNode getMethodName(@RequestParam String param) {
        return wikipediaService.getInfoFromWiki(param);
    }

    @GetMapping("/wiki-chat-bot")
    public String generate(
            @RequestParam(value = "message", defaultValue = "what is \"internet\"?") String message) {
        return textClient.prompt(message)
                .tools(new WikiTools(wikipediaService))
                .call()
                .content();
    }
}
