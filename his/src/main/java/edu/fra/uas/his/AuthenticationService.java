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
    private static final Logger log = LoggerFactory.getLogger("AuthenticationService");

    public boolean authenticateUser(Integer studId, String password) {
        boolean isValid = false;
        Student authenticatedStudent = studentRepository.get(studId);
        if (password == authenticatedStudent.getPassword()) {
            isValid = true;
            log.debug("--> User " + authenticatedStudent.getFirstName() + " has been authenticated successfully");
        } else {
            log.debug("--> Something went wrong with the authentication for the student "
                    + authenticatedStudent.getFirstName());
        }
        return isValid;
    }

    public String displayGrades(Integer studId, String programName) {
        String gradeData = "";
        ArrayList<StudyProgram> programList = studentRepository.get(studId).getStudyPrograms();
        for (StudyProgram studyProgram : programList) {
            if (studyProgram.getProgramName() == programName) {
                gradeData = studyProgram.toString();
            }
        }
        return gradeData;
    }
}
