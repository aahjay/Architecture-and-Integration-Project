package edu.fra.uas.wyseflow;

import java.io.Serializable;

public class WyseFlowRequest implements Serializable {
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
