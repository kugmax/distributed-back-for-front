package com.kugmax.learn.frontend.Frontend.clients;

import com.kugmax.learn.frontend.Frontend.model.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class BookClient {
    private WebClient booksClient;

    public BookClient(@Value("${books.url}") String booksUrl) {
        booksClient = WebClient.builder().baseUrl(booksUrl).build();
    }

    public Mono<Book> getBook(String id) {
       return booksClient.get()
                .uri("/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Book.class);
    }
}
