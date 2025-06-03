package edu.fra.uas.wyseflow;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import edu.fra.uas.wyseflow.model.WyseFlowStudent;

public class WyseFlowInterface {

    private static final Logger log = LoggerFactory.getLogger(WyseFlowInterface.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void requestStudentInfo(Integer studentId) {
        log.info("Requesting student info for Student ID: " + studentId);
        try {
            WyseFlowRequest request = new WyseFlowRequest();
            request.setStudentId(studentId);
            String correlationId = UUID.randomUUID().toString();
            MessageProperties props = new MessageProperties();
            props.setReplyTo("clientA.response.queue");
            props.setCorrelationId(correlationId);
            Message message = rabbitTemplate.getMessageConverter().toMessage(request, props);
            rabbitTemplate.send("student.exchange", "student.request", message);
            log.info("Request sent successfully for Student ID: " + studentId);
        } catch (Exception e) {
            log.debug("Failed to send request for Student ID: " + studentId + ". Error: " + e.getMessage());
        }
    }

    @RabbitListener(queues = "wyseflow.response.queue")
    public void recieveStudentInfo(WyseFlowStudent student) {
        if (student != null) {
            log.info("Received student info: " + student);
            WyseFlowStudent studentInfo = new WyseFlowStudent();
            studentInfo.setFirstName(student.getFirstName());
            studentInfo.setLastName(student.getLastName());
            studentInfo.setStudentID(student.getStudentID());
            studentInfo.setStudyProgram(student.getStudyPrograms());
            log.info("Student Info found!");
            studentInfo.toString();
        } else {
            log.debug("Something is wrong, student info not found!");
            System.out.println("Something is wrong, student info not found!");
        }
    }
}
