package com.course.bff.websockets.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class BffMessageListener implements MessageListener {

    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println("### onMessage");

        System.out.println("### onMessage body " + new String(message.getBody()) );
        System.out.println("### onMessage channel " + new String(message.getChannel()));
        System.out.println("### onMessage bytes " + new String(bytes));

        this.template.convertAndSend("/topic/messages", String.format("%s", new String(message.getBody())));
    }
}
