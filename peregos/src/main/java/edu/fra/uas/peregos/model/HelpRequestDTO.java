package edu.fra.uas.peregos.model;

public class HelpRequestDTO {
    private Integer studentId;
    private String programName;
    private String requestDetails;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getRequestDetails() {
        return requestDetails;
    }

    public void setRequestDetails(String requestDetails) {
        this.requestDetails = requestDetails;
    }

    @Override
    public String toString() {
        return "HelpRequestDTO [studentId=" + studentId + ", programName=" + programName + ", requestDetails="
                + requestDetails + "]";
    }

}