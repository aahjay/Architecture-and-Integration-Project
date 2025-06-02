package edu.fra.uas.his;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtractionInterface {

    private static final Logger log = LoggerFactory.getLogger("ExtractionInterface");

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "his.request.queue")
    public void handleHisRequest(HisRequest hisRequest) {
        log.info("Received HIS request for student ID: {}", hisRequest.getStudentId());
        Student student = studentRepository.get(hisRequest.getStudentId());
        if (student != null) {
            String response = student.toString();
            log.info("Sending response for student ID {}: {}", student.getFirstName(), response);
            rabbitTemplate.convertAndSend("his.exchange", "his.response", response);
            log.info("Student object sent successfully to his.response.queue");
        } else {
            log.warn("No student found with ID: {}", hisRequest.getStudentId());
            ErrorResponse error = new ErrorResponse("Student not found", hisRequest.getStudentId());
            rabbitTemplate.convertAndSend(
                    "his.exchange",
                    "his.response",
                    error);
        }
    }

}
