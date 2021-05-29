package com.course.bff.websockets;

import com.course.bff.websockets.listener.BffMessageListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@SpringBootApplication
public class WebSocketsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSocketsApplication.class, args);
    }

    @Bean
    public JedisConnectionFactory redisConnectionFactory(@Value("${spring.redis.host}") String host) {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(host, 6379);
        return new JedisConnectionFactory(config);
    }

    @Bean
    public RedisMessageListenerContainer redisContainer(@Value("${redis.channel}") String channel,
                                                           BffMessageListener listener, JedisConnectionFactory connectionFactory) {
        System.out.println("#### listener " + listener);
        System.out.println("#### channel " + channel);
        System.out.println("#### connectionFactory " + connectionFactory);

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listener, new ChannelTopic(channel));

        return container;
    }
}
