package edu.fra.uas.his;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HisApplication implements CommandLineRunner {

	@Autowired
	private AuthenticationService authService;
	public static final Logger log = LoggerFactory.getLogger("HisApplication");

	public static void main(String[] args) {
		SpringApplication.run(HisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("### Starting HIS Application ###");

		// Example: Display grades for a student
		int studentId = 1512699; // Example student ID (Salah's ID)
		String programName = "International Business Information Systems";

		String grades = authService.displayGrades(studentId, programName);
		log.info("Grades for student {}: \n{}", studentId, grades);
	}
}