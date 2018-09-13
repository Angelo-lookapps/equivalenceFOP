package com.testHibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration
public class TestHibernateApplication {
	
	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(TestHibernateApplication.class, args);		
	}
}
