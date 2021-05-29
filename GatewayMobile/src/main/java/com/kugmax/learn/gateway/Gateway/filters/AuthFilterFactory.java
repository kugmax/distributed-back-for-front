package com.kugmax.learn.gateway.Gateway.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthFilterFactory extends AbstractGatewayFilterFactory<Object> {

    @Autowired
    AuthFilter filter;

    @Override
    public GatewayFilter apply(Object config) {
        return filter;
    }

    @Override
    public String name() {
        return "AuthFilter";
    }
}
