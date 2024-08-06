package com.example.ai_demo;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final OllamaChatModel chatModel;

    @GetMapping("/ai/generate")
    public Map<String, String> generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return Map.of("generation", chatModel.call(message));
    }

    @GetMapping("/ai/generateStream")
    public ChatResponse generateStream(
            @RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        ChatResponse response = chatModel.call(
                new Prompt("Generate the names of 5 famous pirates."));

        return response;
    }

}