package edu.fra.uas.peregos.model;

import java.util.ArrayList;

public class PeregosStudent {
    private String firstName;
    private String lastName;
    private int studentID;
    private ArrayList<StudyProgramDTO> studyPrograms;

    public PeregosStudent(String firstName, String lastName,
            int studentID, ArrayList<StudyProgramDTO> studyPrograms) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public ArrayList<StudyProgramDTO> getStudyPrograms() {
        return studyPrograms;
    }

    public void setStudyProgram(ArrayList<StudyProgramDTO> studyPrograms) {
        this.studyPrograms = studyPrograms;
    }

    @Override
    public String toString() {
        return firstName + "\n lastName : " + lastName + "\n studentID : " + studentID
                + "\n Study Programs : "
                + studyPrograms;
    }

}
