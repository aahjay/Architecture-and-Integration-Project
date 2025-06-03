package edu.fra.uas.wyseflow.model;

public class StudyModuleDTO {
    private int cp;
    private String moduleName;
    private double grade;

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
