package edu.fra.uas.middleware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.fra.uas.middleware.model.StudentDTO;

@Service
public class HisExtractionService {

    private static final Logger log = LoggerFactory.getLogger(HisExtractionService.class);

    @Autowired
    private CorrelationMap correlationMap;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "his.response.queue")
    public void handleHisResponse(StudentDTO student, Message message) {
        log.debug("Raw message payload: {}", new String(message.getBody()));
        log.debug("Message properties: {}", message.getMessageProperties());
        if (student == null) {
            log.debug("No HIS data found for the requested student.");
            return;
        }
        // Add debug logging for student object
        log.debug("Received student object: {}", student);
        log.debug("Student ID value: {}, type: {}",
                student.getStudentId(),
                student.getStudentId() != null ? student.getStudentId().getClass() : "null");
        log.info("Received HIS response for student ID: {}", student.getStudentId());
        log.info("HIS data found for student ID: {}, Name: {} {}", student.getStudentId(), student.getFirstName(),
                student.getLastName());
        String correlationId = message.getMessageProperties().getCorrelationId();
        String replyTo = correlationMap.get(correlationId);
        if (replyTo == null) {
            log.warn("No replyTo found for correlation ID: {}", correlationId);
        } else {
            log.info("Sending HIS data to client app with correlation ID: {}", correlationId);
            switch (replyTo) {
                case "peregos.response.queue":
                    sendToPeregos(student);
                    break;
                case "wyseflow.response.queue":
                    sendToWyseFlow(student);
                    break;
                default:
                    log.warn("Unknown replyTo queue: {}", replyTo);
            }
        }
    }

    public void sendToPeregos(StudentDTO student) {
        log.debug("Original student before conversion: {}", student);
        // Verify the student ID before creating new DTO
        if (student.getStudentId() == null) {
            log.error("Student ID is null in original DTO");
            return;
        }
        StudentDTO peregosStudent = new StudentDTO();
        peregosStudent.setStudentId(student.getStudentId());
        peregosStudent.setFirstName(student.getFirstName());
        peregosStudent.setLastName(student.getLastName());
        peregosStudent.setStudyPrograms(student.getStudyPrograms());
        try {
            rabbitTemplate.convertAndSend("peregos.exchange", "peregos.response", peregosStudent);
            log.debug("Message sent to RabbitMQ for student ID: {}", peregosStudent.getStudentId());
        } catch (Exception e) {
            log.error("Failed to send data. Original ID: {}, Error: {}",
                    student.getStudentId(), e.getMessage(), e);
        }
    }

    public void sendToWyseFlow(StudentDTO student) {
        log.info("Sending HIS data to WyseFlow for student ID: {}", student.getStudentId());
        StudentDTO wyseflowStudent = new StudentDTO();
        wyseflowStudent.setStudentId(student.getStudentId());
        wyseflowStudent.setFirstName(student.getFirstName());
        wyseflowStudent.setLastName(student.getLastName());
        wyseflowStudent.setEmail(student.getEmail());
        wyseflowStudent.setDateOfBirth(student.getDateOfBirth());
        wyseflowStudent.setStudyPrograms(student.getStudyPrograms());
        wyseflowStudent.setCurrentSemester(student.getCurrentSemester());
        try {
            rabbitTemplate.convertAndSend("wyseflow.exchange", "wyseflow.response", wyseflowStudent);
            log.info("HIS data sent to WyseFlow for student ID: {}", student.getStudentId());
        } catch (Exception e) {
            log.error("Failed to send HIS data to WyseFlow for student ID: {}", student.getStudentId(), e);
        }
    }

}
