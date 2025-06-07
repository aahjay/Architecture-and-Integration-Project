package edu.fra.uas.middleware.model;

import java.io.Serializable;

public class HisRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer studentId;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
