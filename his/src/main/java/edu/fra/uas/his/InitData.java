package edu.fra.uas.his;

import java.time.LocalDate;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class InitData {

        private static final Logger log = LoggerFactory.getLogger("InitData");
        @Autowired
        StudentRepository studentRepository;

        @PostConstruct
        public void init() {
                log.info("### Initializing Student Data ###");

                // Student data Salah
                ArrayList<StudyModule> ibisModules1 = new ArrayList<>();
                ibisModules1.add(new StudyModule(10, "OOP", 1.7));
                ibisModules1.add(new StudyModule(5, "Business Administratoin", 2.3));
                ibisModules1.add(new StudyModule(5, "Marketing", 1.7));
                ibisModules1.add(new StudyModule(5, "Accounting", 2.7));
                ibisModules1.add(new StudyModule(5, "Mathematics", 2.3));
                ibisModules1.add(new StudyModule(5, "Databases", 1.3));

                ArrayList<StudyProgram> SalahStudyProgram = new ArrayList<>();
                SalahStudyProgram.add(new StudyProgram("International Business Information Systems",
                                LocalDate.of(2023, 10, 15),
                                ibisModules1));
                Student Salah = new Student(
                                "Salah",
                                "PinkesBallett123",
                                "Ismael",
                                "salah.ismael@stud.fra-uas.de",
                                4,
                                LocalDate.of(2002, 11, 4),
                                1512699,
                                SalahStudyProgram);
                // ----------------------------------------------------------------------//
                /*
                 * log.info("Created student: {} {}", Salah.getFirstName(),
                 * Salah.getLastName());
                 * log.info("Student ID: {}", Salah.getStudentID());
                 * log.info("Email: {}", Salah.getEmail());
                 * log.info("Semester: {}", Salah.getCurrentSemester());
                 * log.info("Date of Birth: {}", Salah.getDateOfBirth());
                 * log.info("Study Programs:");
                 * for (StudyProgram program : Salah.getStudyProgram()) {
                 * log.info("- Program: {}", program.getProgramName());
                 * log.info("  Starting Date: {}", program.getStartingDate());
                 * log.info("  Modules:");
                 * for (StudyModule module : program.getModuleList()) {
                 * log.info("    * {} ({}CP) - Grade: {}",
                 * module.getModuleName(),
                 * module.getCp(),
                 * module.getGrade());
                 * }
                 * }
                 */

                // Student data Manraj
                ArrayList<StudyModule> ibisModules2 = new ArrayList<>();
                ibisModules2.add(new StudyModule(10, "OOP", 1.0));
                ibisModules2.add(new StudyModule(5, "Business Administratoin", 3.7));
                ibisModules2.add(new StudyModule(5, "Marketing", 1.7));
                ibisModules2.add(new StudyModule(5, "Accounting", 2.7));
                ibisModules2.add(new StudyModule(5, "Mathematics", 1.3));
                ibisModules2.add(new StudyModule(5, "Databases", 1.3));

                ArrayList<StudyModule> wingModules = new ArrayList<>();
                wingModules.add(new StudyModule(5, "Physics1", 2.3));
                wingModules.add(new StudyModule(5, "Accounting", 2.7));
                wingModules.add(new StudyModule(5, "Physics2", 3.7));
                wingModules.add(new StudyModule(5, "Mathematics1", 1.3));

                ArrayList<StudyProgram> manrajStudyPrograms = new ArrayList<>();
                manrajStudyPrograms
                                .add(new StudyProgram("International Business Information Systems",
                                                LocalDate.of(2023, 10, 15),
                                                ibisModules2));
                manrajStudyPrograms.add(new StudyProgram("Wirtschaftsingenieurswesen", LocalDate.of(2024, 10, 15),
                                wingModules));

                Student Manraj = new Student(
                                "Manraj Singh",
                                "SchwarzeTelecaster123",
                                "Badwal",
                                "manraj.badwal@stud.fra-uas.de",
                                4,
                                LocalDate.of(2003, 04, 6),
                                1508839,
                                manrajStudyPrograms);
                // -------------------------------------------------------------------//
                /*
                 * log.info("\nCreated student: {} {}", Manraj.getFirstName(),
                 * Manraj.getLastName());
                 * log.info("Student ID: {}", Manraj.getStudentID());
                 * log.info("Email: {}", Manraj.getEmail());
                 * log.info("Semester: {}", Manraj.getCurrentSemester());
                 * log.info("Date of Birth: {}", Manraj.getDateOfBirth());
                 * log.info("Study Programs:");
                 * for (StudyProgram program : Manraj.getStudyProgram()) {
                 * log.info("- Program: {}", program.getProgramName());
                 * log.info("  Starting Date: {}", program.getStartingDate());
                 * log.info("  Modules:");
                 * for (StudyModule module : program.getModuleList()) {
                 * log.info("    * {} ({}CP) - Grade: {}",
                 * module.getModuleName(),
                 * module.getCp(),
                 * module.getGrade());
                 * }
                 * }
                 */

                // Student data Nicolas
                ArrayList<StudyModule> ibisModules3 = new ArrayList<>();
                ibisModules3.add(new StudyModule(10, "OOP", 1.0));
                ibisModules3.add(new StudyModule(5, "Business Administratoin", 3.7));
                ibisModules3.add(new StudyModule(5, "Marketing", 1.7));
                ibisModules3.add(new StudyModule(5, "Accounting", 2.7));
                ibisModules3.add(new StudyModule(5, "Mathematics", 1.3));
                ibisModules3.add(new StudyModule(6, "Databases", 1.3));

                ArrayList<StudyProgram> nicoStudyProgram = new ArrayList<>();
                nicoStudyProgram.add(new StudyProgram("International Business Information Systems",
                                LocalDate.of(2023, 10, 15),
                                ibisModules3));
                Student Nico = new Student(
                                "Nicolas",
                                "AdidasPredator123",
                                "Popp",
                                "nicolas.popp@stud.fra-uas.de",
                                4,
                                LocalDate.of(2003, 03, 27),
                                1508839,
                                nicoStudyProgram);

                /*
                 * log.info("\nCreated student: {} {}", Nico.getFirstName(),
                 * Nico.getLastName());
                 * log.info("Student ID: {}", Nico.getStudentID());
                 * log.info("Email: {}", Nico.getEmail());
                 * log.info("Semester: {}", Nico.getCurrentSemester());
                 * log.info("Date of Birth: {}", Nico.getDateOfBirth());
                 * log.info("Study Programs:");
                 * for (StudyProgram program : Nico.getStudyProgram()) {
                 * log.info("- Program: {}", program.getProgramName());
                 * log.info("  Starting Date: {}", program.getStartingDate());
                 * log.info("  Modules:");
                 * for (StudyModule module : program.getModuleList()) {
                 * log.info("    * {} ({}CP) - Grade: {}",
                 * module.getModuleName(),
                 * module.getCp(),
                 * module.getGrade());
                 * }
                 * }
                 */
                studentRepository.put(Nico.getStudentID(), Nico);
                studentRepository.put(Salah.getStudentID(), Salah);
                studentRepository.put(Manraj.getStudentID(), Manraj);

                log.info("### Data initialized ###");
        }
}
