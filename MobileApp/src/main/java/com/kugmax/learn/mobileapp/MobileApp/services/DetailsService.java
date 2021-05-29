package com.kugmax.learn.mobileapp.MobileApp.services;

import com.kugmax.learn.mobileapp.MobileApp.responses.BookDetailsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.Request;
import org.asynchttpclient.RequestBuilder;
import org.asynchttpclient.Response;
import org.asynchttpclient.util.HttpConstants;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class DetailsService {
    @Value("${details.url}")
    private String detailsUrl;

    @Value("${auth-token}")
    private String authToken;

    public Optional<BookDetailsResponse> getBookDetails(UUID bookId) {
        DefaultAsyncHttpClientConfig.Builder clientBuilder = Dsl.config().setConnectTimeout(500);
        AsyncHttpClient client = Dsl.asyncHttpClient(clientBuilder);
        Request socketRequest = new RequestBuilder(HttpConstants.Methods.GET)
                .setUrl(detailsUrl + "details/" + bookId.toString())
                .setHeader("Authorization", authToken)
                .build();

        ListenableFuture<Response> socketFuture = client.executeRequest(socketRequest);
        try {
            Response response = socketFuture.get();
            if (response.getStatusCode() != HttpStatus.OK.value()) {
                return Optional.empty();
            }

            BookDetailsResponse authorResponse = new Gson().fromJson(response.getResponseBody(), BookDetailsResponse.class);
            return Optional.of(authorResponse);
        } catch (InterruptedException | ExecutionException e) {
            return Optional.empty();
        }
    }
}
