package com.kugmax.learn.frontapp.FrontApp;

import com.kugmax.learn.frontapp.FrontApp.handlers.BookStompSessionHandler;
import com.kugmax.learn.frontapp.FrontApp.services.DetailsService;
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

@SpringBootApplication
public class FrontAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FrontAppApplication.class, args);
	}

	@Value("${notification.url}")
	public String notificationUlr;

	@Autowired
	private ApplicationContext context;

	@Override
	public void run(String... args) throws Exception {

		WebSocketClient client = new StandardWebSocketClient();

		WebSocketStompClient stompClient = new WebSocketStompClient(client);
		stompClient.setMessageConverter(new StringMessageConverter());

		stompClient.connect(notificationUlr, new BookStompSessionHandler(context.getBean(DetailsService.class)));
		stompClient.start();
	}

}
