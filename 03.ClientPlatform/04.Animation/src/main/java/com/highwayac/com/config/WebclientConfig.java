package com.highwayac.com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebclientConfig {
    private static final String BASE_URL = "https://api.openai.com"; // 可根据需要修改
    private static final String API_KEY = "your_api_key_here"; // 替换成你的OpenAI API密钥

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                        .baseUrl(BASE_URL)
                        .defaultHeader("Authorization", "Bearer " + API_KEY)
                        .build();
    }
}
