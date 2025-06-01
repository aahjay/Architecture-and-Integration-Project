package edu.fra.uas.his;

import java.io.Serializable;

public class ErrorResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String message;
    private Integer studentId;

    public ErrorResponse(String message, Integer studentId) {
        this.message = message;
        this.studentId = studentId;
    }

    // Getters and setters
    public String getMessage() {
        return message;
    }

    public Integer getStudentId() {
        return studentId;
    }
}
