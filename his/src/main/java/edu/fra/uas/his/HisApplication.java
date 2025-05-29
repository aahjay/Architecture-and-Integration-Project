package edu.fra.uas.his;

import java.util.ArrayList;
import java.util.Scanner;

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
	@Autowired
	private StudentRepository studentRepository;
	public static final Logger log = LoggerFactory.getLogger("HisApplication");

	public static void main(String[] args) {
		SpringApplication.run(HisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner input = new Scanner(System.in);
		log.info("### Starting HIS Application ###");
		boolean isValid;
		do {
			System.out.println("StudentID : ");
			Integer studentId = input.nextInt();
			System.out.println("Password : ");
			String password = input.next();
			isValid = authService.authenticateUser(studentId, password);
			if (isValid = false) {
				System.out.println("Invalid Password or StudentId. Please try again");
			} else {
				System.out.println("Welcome " + studentId + " !");
				System.out.println(displayGradesForProgram(studentId, input));
				isValid = false;
			}
		} while (isValid == true);
		input.close();
		// Example: Display grades for a student
		/*
		 * int studentId = 1512699; // Example student ID (Salah's ID)
		 * String programName = "International Business Information Systems";
		 * 
		 * String grades = authService.displayGrades(studentId, programName);
		 * log.info("Grades for student {}: \n{}", studentId, grades);
		 */
	}

	public String displayGradesForProgram(Integer studId, Scanner input) {
		System.out.println("Would you like to see ur grades? (y/N)");
		String answer = input.next();
		if (answer.contains("N") == true) {
			return "Ending HIS...";
		}
		ArrayList<StudyProgram> programs = studentRepository.get(studId).getStudyPrograms();
		System.out.println("\nYou are enrolled in the following programs:");
		for (int i = 0; i < programs.size(); i++) {
			System.out.println((i + 1) + ": " + programs.get(i).getProgramName());
		}
		System.out.println("\nEnter the number (1-" + programs.size() + ") of the program to view grades:");
		int choice = input.nextInt();

		if (choice > 0 && choice <= programs.size()) {
			StudyProgram selectedProgram = programs.get(choice - 1);
			return authService.displayGrades(studId, selectedProgram.getProgramName());
		} else {
			return "Invalid selection. Please try again.";
		}
	}
}