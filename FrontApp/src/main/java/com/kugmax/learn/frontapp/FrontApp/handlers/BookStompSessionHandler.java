package com.kugmax.learn.frontapp.FrontApp.handlers;

import com.google.gson.Gson;
import com.kugmax.learn.frontapp.FrontApp.responses.BookDetailsResponse;
import com.kugmax.learn.frontapp.FrontApp.services.DetailsService;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.UUID;

public class BookStompSessionHandler implements StompSessionHandler {

    private DetailsService detailsService;
    private Gson gson = new Gson();

    public BookStompSessionHandler(DetailsService authorService) {
        this.detailsService = authorService;
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders stompHeaders) {
        session.subscribe("/topic/messages", this);
    }

    @Override
    public void handleException(StompSession stompSession, StompCommand stompCommand, StompHeaders stompHeaders, byte[] bytes, Throwable throwable) {
        System.out.println(throwable.getMessage() + " " + throwable);
    }

    @Override
    public void handleTransportError(StompSession stompSession, Throwable throwable) {
        System.out.println(throwable.getMessage() + " " + throwable);
    }

    @Override
    public Type getPayloadType(StompHeaders stompHeaders) {
        return String.class;
    }

    @Override
    public void handleFrame(StompHeaders stompHeaders, Object payload) {
        if (payload == null) {
            return;
        }

        Map<String, String> message = gson.fromJson(payload.toString(), Map.class);

        String bookId = message.get("id");

        BookDetailsResponse author = detailsService.getBookDetails(UUID.fromString(bookId)).orElseGet(BookDetailsResponse::new);

        StringBuilder msgInfo = new StringBuilder("Book: ").append(author.getBook()).append(", Author: ").append(author.getAuthor());
        System.out.println(msgInfo.toString());
    }
}
