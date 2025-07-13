package com.example.ai_demo.configs;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Value("${default-system-voice}")
    private String defaultSystemPrompt;

    @Value("${default-text-reader-voice}")
    private String defaultTextReaderPrompt;

    @Bean
    ChatClient chatClient(ChatClient.Builder builder) {
        InMemoryChatMemory chatMemory = new InMemoryChatMemory();
        if (null != defaultSystemPrompt) {
            return builder.defaultSystem(defaultSystemPrompt)
                    .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory))
                    .build();
        } else {
            return builder
                    .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory))
                    .build();
        }
    }

    @Bean
    ChatClient textClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem(defaultTextReaderPrompt)
                .build();
    }
}
