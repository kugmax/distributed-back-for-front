package com.kugmax.learn.frontend.Frontend.controllers;

import com.kugmax.learn.frontend.Frontend.responses.BookDetailsResponse;
import com.kugmax.learn.frontend.Frontend.services.BookAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/frontend/api/v1")
public class BookAuthorController {

    @Autowired
    BookAuthorService bookAuthorService;


    @ResponseBody()
    @GetMapping("/details/{bookId}")
    public Mono<BookDetailsResponse> details(@PathVariable("bookId") String bookId) {
        return bookAuthorService.details(bookId);
    }
}
