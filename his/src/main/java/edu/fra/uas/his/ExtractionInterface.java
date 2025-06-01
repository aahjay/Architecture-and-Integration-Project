package edu.fra.uas.his;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class ExtractionInterface {

    private static final Logger log = LoggerFactory.getLogger("ExtractionInterface");

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "his.request.queue")
    public void handleHisRequest(Integer studentId) {
        log.info("Received HIS request for student ID: {}", studentId);
        Student student = studentRepository.get(studentId);
        if (student != null) {
            String response = student.toString();
            log.info("Sending response for student ID {}: {}", studentId, response);
            rabbitTemplate.convertAndSend("his.exchange", "his.response", response);
            log.info("Student object sent successfully to his.response.queue");
        } else {
            log.warn("No student found with ID: {}", studentId);
            ErrorResponse error = new ErrorResponse("Student not found", studentId);
            rabbitTemplate.convertAndSend(
                    "his.exchange",
                    "his.response",
                    error);
        }
    }
}
