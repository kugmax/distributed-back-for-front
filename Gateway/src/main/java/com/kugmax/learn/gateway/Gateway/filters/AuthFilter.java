package com.kugmax.learn.gateway.Gateway.filters;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Order(1)
public class AuthFilter implements GatewayFilter {
    @Value("${auth-token}")
    String authToken;

    private static final String ACCESS_TOKEN_HEADER = "Authorization";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (!authToken.equals(getAuthHeader(exchange.getRequest()))) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.FORBIDDEN);
            return response.setComplete();
        }

        return chain.filter(exchange);
    }

    private String getAuthHeader(ServerHttpRequest request) {
        List<String> headers = request.getHeaders().getOrEmpty(ACCESS_TOKEN_HEADER);
        return headers.isEmpty() ? "" : headers.get(0);
    }
}
