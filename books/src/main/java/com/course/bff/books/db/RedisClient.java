package com.course.bff.books.db;

import com.course.bff.books.responses.BookResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisClient {

    @Autowired
    private StringRedisTemplate template;

    @Value("${redis.channel}")
    private String channel;

    private Gson gson = new Gson();

    public void sendNotification(BookResponse book) {
        template.convertAndSend(channel, gson.toJson(book));
    }
}
