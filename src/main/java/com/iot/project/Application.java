package com.iot.project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.iot.project.repository.AlbumRepository;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {

	final AlbumRepository rep;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		System.out.println(rep.findByNameStartingWith("h").toString());
		
	}

}
