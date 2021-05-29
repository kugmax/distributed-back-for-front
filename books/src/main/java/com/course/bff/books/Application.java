package com.course.bff.books;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public JedisConnectionFactory redisConnectionFactory(@Value("${spring.redis.host}") String host) {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(host, 6379);
        return new JedisConnectionFactory(config);
    }


}
