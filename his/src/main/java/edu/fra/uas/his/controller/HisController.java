package edu.fra.uas.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import edu.fra.uas.his.AuthenticationService;
import edu.fra.uas.his.Student;
import edu.fra.uas.his.StudentRepository;

@Controller
@RequestMapping("/his")
public class HisController {
    @Autowired
    private AuthenticationService authService;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/form")
    public String showForm() {
        return "his-form";
    }

    @PostMapping("/authenticate")
    @ResponseBody
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest request) {
        boolean isAuthenticated = authService.authenticateUser(request.getStudentId(), request.getPassword());

        if (!isAuthenticated) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }

        Student student = studentRepository.get(request.getStudentId());
        return ResponseEntity.ok(student);
    }
}

class LoginRequest {
    private Integer studentId;
    private String password;

    // Getters and setters
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
