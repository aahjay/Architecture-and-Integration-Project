package edu.fra.uas.middleware.model;

import java.io.Serializable;

public class StudentRequestDTO implements Serializable {
    private static final Long correlationId = 1L;
    private Integer studentId;

    public static Long getCorrelationId() {
        return correlationId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

}
