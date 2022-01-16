package com.javamaster.springjpapostgres;

import com.javamaster.springjpapostgres.business.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringjpapostgresApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringjpapostgresApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	private void ready(){
	System.out.println("ready");
	}
}