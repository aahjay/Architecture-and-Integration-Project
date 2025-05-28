package edu.fra.uas.his;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private StudentRepository studentRepository;

    public String authenticateUser(Integer studId, String password) {
        String message;
        Student authenticatedStudent = studentRepository.get(studId);
        if (password == authenticatedStudent.getPassword()) {
            message = "Welcome " + authenticatedStudent.getFirstName() + " " + authenticatedStudent.getLastName();
        } else {
            message = "A User with ID: " + studId + " and the entered password could not be found";
        }
        return message;
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
