package edu.fra.uas.wyseflow.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.fra.uas.wyseflow.WyseFlowInterface;
import edu.fra.uas.wyseflow.WyseFlowRepository;
import edu.fra.uas.wyseflow.model.WyseFlowStudent;

@Controller
@RequestMapping("/wyseflow")
public class WyseFlowController {

    private static final Logger log = LoggerFactory.getLogger(WyseFlowController.class);
    // private static final int MAX_RETRIES = 5;
    // private static final int RETRY_DELAY_MS = 2000;

    @Autowired
    private WyseFlowInterface wyseFlowInterface;

    @Autowired
    private WyseFlowRepository wyseFlowRepository;

    @GetMapping("/form")
    public String showForm() {
        return "wyseflow-form";
    }

    @PostMapping("/student/{id}")
    @ResponseBody
    public ResponseEntity<WyseFlowStudent> getStudentInfo(@PathVariable Integer id) {
        log.info("Checking data for student ID: {}", id);

        // First check if we already have the data
        WyseFlowStudent student = wyseFlowRepository.get(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        }

        // If no data, send new request
        wyseFlowInterface.requestStudentInfo(id);

        // Return 404 to indicate data is not yet available
        return ResponseEntity.notFound().build();
    }
}