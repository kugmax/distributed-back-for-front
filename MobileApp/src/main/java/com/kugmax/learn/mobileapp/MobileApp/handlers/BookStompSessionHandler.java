package com.kugmax.learn.mobileapp.MobileApp.handlers;

import com.google.gson.Gson;
import com.kugmax.learn.mobileapp.MobileApp.responses.AuthorResponse;
import com.kugmax.learn.mobileapp.MobileApp.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.UUID;

public class BookStompSessionHandler implements StompSessionHandler {

    private AuthorService authorService;
    private Gson gson = new Gson();

    public BookStompSessionHandler(AuthorService authorService) {
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

        String title = message.get("title");
        String authorId = message.get("authorId");

        StringBuilder msgInfo = new StringBuilder().append("Book title: ").append(title);

        if (authorId == null) {
            msgInfo.append(", Author is unknown");
            System.out.println(msgInfo);
            return;
        }

        AuthorResponse author = authorService.getAuthor(UUID.fromString(authorId)).orElseGet(AuthorResponse::new);

        msgInfo.append(", Author name: ").append(author.getFirstName()).append(" ").append(author.getLastName());
        System.out.println(msgInfo.toString());
    }
}
