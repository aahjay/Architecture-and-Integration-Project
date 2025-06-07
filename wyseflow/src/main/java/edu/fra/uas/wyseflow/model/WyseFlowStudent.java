package edu.fra.uas.wyseflow.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class WyseFlowStudent {
    private String firstName;
    private String lastName;
    private String email;
    private int currentSemester;
    private LocalDate dateOfBirth;
    private Integer studentId;
    private ArrayList<StudyProgramDTO> studyPrograms;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(int currentSemester) {
        this.currentSemester = currentSemester;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentID(int studentId) {
        this.studentId = studentId;
    }

    public ArrayList<StudyProgramDTO> getStudyPrograms() {
        return studyPrograms;
    }

    public void setStudyProgram(ArrayList<StudyProgramDTO> studyPrograms) {
        this.studyPrograms = studyPrograms;
    }

    @Override
    public String toString() {
        return firstName + "\n lastName : " + lastName + "\n email : " + email + "\n currentSemester : "
                + currentSemester + "\n dateOfBirth : " + dateOfBirth + "\n studentID : " + studentId
                + "\n Study Programs : "
                + studyPrograms;
    }

}
