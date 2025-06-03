package edu.fra.uas.wyseflow.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class StudyProgramDTO {
    private String programName;
    private LocalDate startingDate;
    private int totalCreditPoints;
    private int cpSum = 0;
    private ArrayList<StudyModuleDTO> moduleList;

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public int getTotalCreditPoints() {
        return totalCreditPoints;
    }

    public ArrayList<StudyModuleDTO> getModuleList() {
        return moduleList;
    }

    public void setTotalCreditPoints(int totalCreditPoints) {
        this.totalCreditPoints = totalCreditPoints;
    }

    public int getCpSum() {
        return cpSum;
    }

    public void setModuleList(ArrayList<StudyModuleDTO> moduleList) {
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
