package com.kugmax.learn.mobileapp.MobileApp;

import com.kugmax.learn.mobileapp.MobileApp.handlers.BookStompSessionHandler;
import com.kugmax.learn.mobileapp.MobileApp.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Scanner;

@SpringBootApplication
public class MobileAppApplication implements CommandLineRunner {

	@Value("${notification.url}")
	public String notificationUlr;

	@Autowired
	private ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(MobileAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		WebSocketClient client = new StandardWebSocketClient();

		WebSocketStompClient stompClient = new WebSocketStompClient(client);
		stompClient.setMessageConverter(new StringMessageConverter());

		stompClient.connect(notificationUlr, new BookStompSessionHandler(context.getBean(AuthorService.class)));
		stompClient.start();
	}
}
