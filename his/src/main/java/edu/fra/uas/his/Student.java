package edu.fra.uas.his;

import java.time.LocalDate;
import java.util.ArrayList;

public class Student {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private int currentSemester;
    private LocalDate dateOfBirth;
    private int studentID;
    private ArrayList<StudyProgram> studyPrograms;

    public Student(String firstName, String password, String lastName, String email, int currentSemester,
            LocalDate dateOfBirth,
            int studentID, ArrayList<StudyProgram> studyPrograms) {
        this.firstName = firstName;
        this.password = password;
        this.lastName = lastName;
        this.email = email;
        this.currentSemester = currentSemester;
        this.dateOfBirth = dateOfBirth;
        this.studentID = studentID;
        this.studyPrograms = studyPrograms;
    }

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

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public ArrayList<StudyProgram> getStudyPrograms() {
        return studyPrograms;
    }

    public void setStudyProgram(ArrayList<StudyProgram> studyPrograms) {
        this.studyPrograms = studyPrograms;
    }

    @Override
    public String toString() {
        return "Student [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", currentSemester="
                + currentSemester + ", dateOfBirth=" + dateOfBirth + ", studentID=" + studentID + ", studyProgram="
                + studyPrograms + "]";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
