package com.testHibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration
public class TestHibernateApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TestHibernateApplication.class, args);
	}
}
