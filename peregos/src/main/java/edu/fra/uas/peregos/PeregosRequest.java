package edu.fra.uas.peregos;

import java.io.Serializable;

public class PeregosRequest implements Serializable {
    private static final Long correlationId = 1L;
    Integer studentId;

    public static Long getCorrelationid() {
        return correlationId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
