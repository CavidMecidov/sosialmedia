package com.sosialmedia.sosialmedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@SpringBootApplication
@EnableJpaAuditing
public class SosialmediaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SosialmediaApplication.class, args);
	}

}
