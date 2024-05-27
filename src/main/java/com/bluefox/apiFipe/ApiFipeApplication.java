package com.bluefox.apiFipe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bluefox.apiFipe.view.Menu;

@SpringBootApplication
public class ApiFipeApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ApiFipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Menu.displayMenu();
	}
}
