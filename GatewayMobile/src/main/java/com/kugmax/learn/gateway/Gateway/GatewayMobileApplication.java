package com.kugmax.learn.gateway.Gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class GatewayMobileApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayMobileApplication.class, args);
	}

	@Bean
	public KeyResolver apiV1KeyResolver() {
		return exchange -> Mono.just("/api/v1/");
	}
}
