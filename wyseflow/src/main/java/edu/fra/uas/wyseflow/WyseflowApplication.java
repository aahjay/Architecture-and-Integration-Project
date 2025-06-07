package edu.fra.uas.wyseflow;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.fra.uas.wyseflow.model.StudyProgramDTO;
import edu.fra.uas.wyseflow.model.WyseFlowStudent;

@SpringBootApplication
public class WyseflowApplication implements CommandLineRunner {

	@Autowired
	private WyseFlowInterface wyseFlowInterface;

	@Autowired
	private WyseFlowRepository wyseFlowRepository;

	private static final Logger log = LoggerFactory.getLogger(WyseflowApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WyseflowApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner input = new Scanner(System.in);
		log.info("Wyseflow Application has started successfully.");
		System.out.println("To apply for your Bachelor Program please enter your Student ID");
		int studentId = input.nextInt();
		wyseFlowInterface.requestStudentInfo(studentId);
		System.out.println("Checking data...");
		Thread.sleep(5000);
		WyseFlowStudent student = wyseFlowRepository.get(studentId);
		System.out.println("Which one of xour study programs would you like to apply for?");
		for (int i = 0; i < student.getStudyPrograms().size(); i++) {
			System.out.println((i + 1) + " -" + student.getStudyPrograms().get(i));
		}
		System.out.println("Please enter the number of the study program you want to apply for:");
		int programIndex = input.nextInt() - 1;
		if (programIndex < 0 || programIndex >= student.getStudyPrograms().size()) {
			System.out.println("Invalid selection. Please try again.");
			StudyProgramDTO selectedProgram = student.getStudyPrograms().get(programIndex);
			if (selectedProgram.getTotalCreditPoints() < 180) {
				System.out.println("You do not have enough credit points to apply for this program.");
			} else {
				System.out.println(
						"You have successfully applied for the " + selectedProgram.getProgramName() + " program.");
				log.info("Student ID: {}, Program: {}", studentId, selectedProgram.getProgramName());
				log.info("Total Credit Points: {}", selectedProgram.getTotalCreditPoints());

			}
			input.close();
		}
	}
}
