package com.course.bff.books.filters;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class AuthFilter implements Filter {
    @Value("${auth-token}")
    private String authToken;

    private static final String ACCESS_TOKEN_HEADER = "Authorization";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

//        if (!authToken.equals(request.getHeader(ACCESS_TOKEN_HEADER))) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
        filterChain.doFilter(request, servletResponse);
    }
}
