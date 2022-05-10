package edu.supavenir.spanimals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import edu.supavenir.spanimals.conf.EmailSenderServices;

@SpringBootApplication
public class SpaAnimalsApplication {

	@Autowired
	private EmailSenderServices service;
	
	public static void main(String[] args) {
		SpringApplication.run(SpaAnimalsApplication.class, args);
	}

	/*@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() {
		service.sendSimpleEmail("spanimals.project@gmail.com", "Test", "Contact pour adoption");
	}
	*/
}
