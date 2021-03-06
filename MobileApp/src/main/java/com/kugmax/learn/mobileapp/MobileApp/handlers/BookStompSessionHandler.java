package com.kugmax.learn.mobileapp.MobileApp.handlers;

import com.google.gson.Gson;
import com.kugmax.learn.mobileapp.MobileApp.responses.AuthorResponse;
import com.kugmax.learn.mobileapp.MobileApp.responses.BookDetailsResponse;
import com.kugmax.learn.mobileapp.MobileApp.services.DetailsService;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.UUID;

public class BookStompSessionHandler implements StompSessionHandler {

    private DetailsService authorService;
    private Gson gson = new Gson();

    public BookStompSessionHandler(DetailsService authorService) {
        this.authorService = authorService;
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

        BookDetailsResponse detailsResponse = authorService.getBookDetails(UUID.fromString(bookId)).orElseGet(BookDetailsResponse::new);

        StringBuilder msgInfo = new StringBuilder().append("Book title: ").append(detailsResponse.getBookTitle());

        msgInfo.append(", Author name: ").append(detailsResponse.getAuthorName());
        System.out.println(msgInfo.toString());
    }
}
