package edu.fra.uas.peregos.model;

public class StudyModuleDTO {
    private int cp;
    private String moduleName;
    private double grade;

    public StudyModuleDTO(int cp, String moduleName, double grade) {
        this.cp = cp;
        this.moduleName = moduleName;
        this.grade = grade;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return moduleName + "\n grade : " + grade;
    }

}
