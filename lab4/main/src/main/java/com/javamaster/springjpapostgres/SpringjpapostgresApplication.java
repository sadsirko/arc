package com.javamaster.springjpapostgres;

import com.javamaster.springjpapostgres.business.GoodServiceFacade;
import com.javamaster.springjpapostgres.business.GoodServiceFacadeImpl;
import com.javamaster.springjpapostgres.business.service.CashLogService;
import com.javamaster.springjpapostgres.business.service.GoodCashService;
import com.javamaster.springjpapostgres.business.service.GoodService;
import com.javamaster.springjpapostgres.business.service.ProviderService;
import com.javamaster.springjpapostgres.persistence.entity.CashLog;
import com.javamaster.springjpapostgres.persistence.repository.CashLogRepository;
import com.javamaster.springjpapostgres.persistence.repository.GoodCashRepository;
import com.javamaster.springjpapostgres.persistence.repository.GoodRepository;
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