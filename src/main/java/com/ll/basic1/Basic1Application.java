package com.ll.basic1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //3
public class Basic1Application {

	public static void main(String[] args) {
		SpringApplication.run(Basic1Application.class, args);
	}

}
