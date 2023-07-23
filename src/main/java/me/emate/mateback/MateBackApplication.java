package me.emate.mateback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MateBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(MateBackApplication.class, args);
	}

}
