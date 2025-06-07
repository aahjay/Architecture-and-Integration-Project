package edu.fra.uas.peregos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.fra.uas.peregos.model.PeregosStudent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import java.util.Scanner;

@SpringBootApplication
public class PeregosApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(PeregosApplication.class);

	@Autowired
	private PeregosInterface peregosInterface;

	@Autowired
	private PeregosRepository peregosRepository;

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
		System.out.println("Checking data...");
		Thread.sleep(5000); // Simulate waiting for response
		PeregosStudent student = peregosRepository.get(studId);
		if (student != null) {
			System.out.println("Student Info found!");
			System.out.println("First Name: " + student.getFirstName());
			System.out.println("Last Name: " + student.getLastName());
			System.out.println("Student ID: " + student.getStudentID());
			System.out.println("Study Programs: " + student.getStudyPrograms());
		} else {
			System.out.println("Something is wrong, student info not found!");
		}
		input.close();
	}
}
