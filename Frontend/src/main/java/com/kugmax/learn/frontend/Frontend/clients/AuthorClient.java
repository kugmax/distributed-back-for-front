package com.kugmax.learn.frontend.Frontend.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class AuthorClient {

    private WebClient client;

    public AuthorClient(@Value("$authors.url{}") String authorsUrl) {
        client = WebClient.builder().baseUrl(authorsUrl).build();
    }

    public Mono<String> getAuthor(String id) {
        return client.get()
                .uri("/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
    }
}
