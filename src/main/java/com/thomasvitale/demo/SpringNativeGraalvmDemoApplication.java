package com.thomasvitale.demo;

import reactor.core.publisher.Mono;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
public class SpringNativeGraalvmDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringNativeGraalvmDemoApplication.class, args);
	}

	@Bean
	RouterFunction<ServerResponse> routes(BookRepository bookRepository) {
		return route()
			.GET("/", request -> ok().body(Mono.just("Spring Native and Beyond!"), String.class))
			.GET("/books", request -> ok().body(bookRepository.findAll(), Book.class))
			.build();
	}

	@Bean
	ApplicationRunner runner(DatabaseClient databaseClient, BookRepository bookRepository) {
		return args -> databaseClient.sql("CREATE TABLE IF NOT EXISTS book (id BIGSERIAL PRIMARY KEY, title varchar(255) NOT NULL)")
				.fetch()
				.rowsUpdated()
				.then(bookRepository.deleteAll())
				.then(bookRepository.save(new Book(null, "Northern Lights")))
				.then(bookRepository.save(new Book(null, "Polar Journey")))
				.subscribe();
	}
}
