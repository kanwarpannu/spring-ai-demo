package com.example.ai_demo.tools;

import org.springframework.ai.tool.annotation.Tool;

import com.example.ai_demo.services.WikipediaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WikiTools {

    private final WikipediaService wikipediaService;

    @Tool(description = "Get Information from internet")
    String getInformationFromNet(String subject) {
        return wikipediaService.getInfoFromWiki(subject).get("response").asText();
    }
}
