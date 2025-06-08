package edu.fra.uas.peregos.controller;

import edu.fra.uas.peregos.model.HelpRequestDTO;
import edu.fra.uas.peregos.model.PeregosStudent;
import edu.fra.uas.peregos.PeregosInterface;
import edu.fra.uas.peregos.PeregosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/peregos")
public class PeregosController {

    @Autowired
    private PeregosInterface peregosInterface;

    @Autowired
    private PeregosRepository peregosRepository;

    @GetMapping("/form")
    public String showForm() {
        return "peregos-form";
    }

    @PostMapping("/student/{id}")
    @ResponseBody
    public ResponseEntity<PeregosStudent> getStudentInfo(@PathVariable Integer id) {
        peregosInterface.requestStudentInfo(id);
        PeregosStudent student = peregosRepository.get(id);
        return ResponseEntity.ok(student);
    }

    @PostMapping("/submit")
    @ResponseBody
    public ResponseEntity<String> submitRequest(@RequestBody HelpRequestDTO request) {
        return ResponseEntity.ok("Request submitted successfully");
    }
}