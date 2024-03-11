package com.mini.gymnavi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GymNaviApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymNaviApplication.class, args);
	}

}
