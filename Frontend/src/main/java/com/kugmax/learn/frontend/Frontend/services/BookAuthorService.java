package com.kugmax.learn.frontend.Frontend.services;

import com.google.gson.Gson;
import com.kugmax.learn.frontend.Frontend.clients.AuthorClient;
import com.kugmax.learn.frontend.Frontend.clients.BookClient;
import com.kugmax.learn.frontend.Frontend.responses.BookDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class BookAuthorService {

    @Autowired
    private BookClient bookClient;

    @Autowired
    private AuthorClient authorClient;

    private static final Gson gson = new Gson();

    public Mono<BookDetailsResponse> details(String bookId) {

        return bookClient.getBook(bookId)
                .map(b -> new String[]{extractAuthorId(b), b} )
                .flatMap(arr -> authorClient.getAuthor(arr[0])
                        .map(a -> new BookDetailsResponse(arr[1], a))
                );
    }

    private String extractAuthorId(String bookPayload) {
        Map<String, String> message = gson.fromJson(bookPayload, Map.class);

        return message.get("authorId");
    }

}
