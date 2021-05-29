package com.kugmax.learn.frontend.Frontend.services;

import com.kugmax.learn.frontend.Frontend.clients.AuthorClient;
import com.kugmax.learn.frontend.Frontend.clients.BookClient;
import com.kugmax.learn.frontend.Frontend.responses.BookDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class BookAuthorService {

    @Autowired
    private BookClient bookClient;

    @Autowired
    private AuthorClient authorClient;

    public Mono<BookDetailsResponse> details(String bookId) {

        return bookClient.getBook(bookId)
                .map(b -> new Object[]{b.getAuthorId(), b.getTitle()} )
                .flatMap(arr -> authorClient.getAuthor((String)arr[0])
                        .map(a -> new BookDetailsResponse((String)arr[1], a.getFirstName() + " " + a.getLastName()))
                );
    }
}
