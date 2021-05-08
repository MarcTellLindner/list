package com.github.marctelllindner.list;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ListApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListApplication.class, args);
	}

}
