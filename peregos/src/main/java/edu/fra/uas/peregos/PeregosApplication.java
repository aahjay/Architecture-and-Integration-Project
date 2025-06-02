package edu.fra.uas.peregos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import java.util.Scanner;

@SpringBootApplication
public class PeregosApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(PeregosApplication.class);

	private PeregosInterface peregosInterface;

	public static void main(String[] args) {
		SpringApplication.run(PeregosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.println("To make a request, please enter your Student ID");
		Integer studId = input.nextInt();
		peregosInterface.requestStudentInfo(studId);
		log.info("Request sent for Student ID: {}", studId);
		input.close();
	}
}
