package com.javamaster.springjpapostgres;

import com.javamaster.springjpapostgres.repository.GoodRepository;
import com.javamaster.springjpapostgres.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringjpapostgresApplication {

	@Autowired
	private GoodService goodService;


	public static void main(String[] args) {
		SpringApplication.run(SpringjpapostgresApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	private void testJpaMethods(){
	System.out.println("ready");
	}
}