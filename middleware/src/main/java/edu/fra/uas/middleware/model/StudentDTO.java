package edu.fra.uas.middleware.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unknown properties during deserialization
public class StudentDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonProperty("studentId")
    private Integer studentId;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private int currentSemester;
    private ArrayList<StudyProgramDTO> studyPrograms;

    public StudentDTO() {

    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public ArrayList<StudyProgramDTO> getStudyPrograms() {
        return studyPrograms;
    }

    public void setStudyPrograms(ArrayList<StudyProgramDTO> studyPrograms) {
        this.studyPrograms = studyPrograms;
    }

    public int getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(int currentSemester) {
        this.currentSemester = currentSemester;
    }
}
