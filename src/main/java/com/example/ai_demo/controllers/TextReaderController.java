package com.example.ai_demo.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;

import org.springframework.ai.reader.TextReader;

import org.springframework.ai.transformer.splitter.TokenTextSplitter;

import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TextReaderController {

    private final VectorStore vectorStore;
    private final ChatClient textClient;

    @Value("classpath:dataset.txt")
    private Resource textResource;

    // @PostConstruct
    // public void init() {
    //     TextReader textReader = new TextReader(textResource);
    //     textReader.getCustomMetadata().put("filename", "dataset.txt");
    //     TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();
    //     vectorStore.accept(tokenTextSplitter.apply(textReader.get()));
    // }

    @GetMapping("/chat-with-text")
    public String chatWithText(
            @RequestParam(value = "message", defaultValue = "Summarize what this document is about") String message) {

        return textClient.prompt()
                .advisors(new QuestionAnswerAdvisor(vectorStore))
                .user(message)
                .call()
                .content();
    }

}
