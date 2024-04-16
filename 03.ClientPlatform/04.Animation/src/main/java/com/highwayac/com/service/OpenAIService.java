package com.highwayac.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class OpenAIService {

    @Autowired
    private WebClient webClient;

    public Mono<String> generateText(String prompt) {
        return webClient.post()
                        .uri("/v1/engines/davinci/completions")
                        .bodyValue(preparePayload(prompt))
                        .retrieve()
                        .bodyToMono(String.class);
    }

    private String preparePayload(String prompt) {
        return "{"
               + "\"prompt\": \"" + prompt + "\","
               + "\"max_tokens\": 150"
               + "}";
    }
}
