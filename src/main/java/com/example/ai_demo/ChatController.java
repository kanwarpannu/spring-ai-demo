package com.example.ai_demo;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/joke")
    public String joke() {
        return chatClient.prompt()
                .user("Tell me a joke")
                .call()
                .content();
    }

    @GetMapping("/ask-me-anything")
    public String generate(
            @RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    @GetMapping("/ask-me-anything-with-logging")
    public String generateWithLogging(
            @RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return chatClient.prompt()
                .advisors(new SimpleLoggerAdvisor())
                .user(message)
                .call()
                .content();
    }

}