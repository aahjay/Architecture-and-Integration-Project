package edu.fra.uas.his;

import java.time.LocalDate;
import java.util.ArrayList;

public class StudyProgram {
    private String programName;
    private LocalDate startingDate;
    private int totalCreditPoints;
    private static int cpSum = 0;
    private ArrayList<StudyModule> moduleList;

    public StudyProgram(String programName, LocalDate startingDate, ArrayList<StudyModule> moduleList) {
        this.programName = programName;
        this.startingDate = startingDate;
        this.moduleList = moduleList;
        for (StudyModule m : moduleList) {
            cpSum += m.getCp();
        }
        this.totalCreditPoints = cpSum;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public int getTotalCreditPoints() {
        return totalCreditPoints;
    }

    public ArrayList<StudyModule> getModuleList() {
        return moduleList;
    }

    public void setTotalCreditPoints(int totalCreditPoints) {
        this.totalCreditPoints = totalCreditPoints;
    }

    public static int getCpSum() {
        return cpSum;
    }

    public static void setCpSum(int cpSum) {
        StudyProgram.cpSum = cpSum;
    }

    public void setModuleList(ArrayList<StudyModule> moduleList) {
        this.moduleList = moduleList;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    @Override
    public String toString() {
        return programName + "\n Started at : " + startingDate + "\n Total CP : "
                + totalCreditPoints + "\n Modules passed : " + moduleList;
    }

}
