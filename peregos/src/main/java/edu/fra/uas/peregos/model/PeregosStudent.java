package edu.fra.uas.peregos.model;

import java.util.ArrayList;

public class PeregosStudent {
    private String firstName;
    private String lastName;
    private Integer studentId;
    private ArrayList<StudyProgramDTO> studyPrograms;

    public PeregosStudent() {
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

    public Integer getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Integer studentId) {
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
        return firstName + "\n lastName : " + lastName + "\n studentID : " + studentId
                + "\n Study Programs : "
                + studyPrograms;
    }

}
