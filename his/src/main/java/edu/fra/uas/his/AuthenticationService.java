package edu.fra.uas.his;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private StudentRepository studentRepository;
    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);

    public boolean authenticateUser(Integer studId, String password) {
        try {
            Student authenticatedStudent = studentRepository.get(studId);
            if (authenticatedStudent == null) {
                log.warn("No student found with ID: {}", studId);
                return false;
            }
            boolean isValid = password.equals(authenticatedStudent.getPassword());
            if (isValid) {
                log.info("User {} authenticated successfully", authenticatedStudent.getFirstName());
            } else {
                log.debug("Authentication failed for student {}", authenticatedStudent.getFirstName());
            }
            return isValid;
        } catch (Exception e) {
            log.error("Authentication error for student ID {}: {}", studId, e.getMessage());
            return false;
        }
    }

    public String displayGrades(Integer studId, String programName) {
        try {
            ArrayList<StudyProgram> programList = studentRepository.get(studId).getStudyPrograms();
            return programList.stream()
                    .filter(program -> program.getProgramName().equals(programName))
                    .findFirst()
                    .map(StudyProgram::toString)
                    .orElse("Program not found");
        } catch (Exception e) {
            log.error("Error displaying grades for student {}: {}", studId, e.getMessage());
            return "Error retrieving grades";
        }
    }
}
