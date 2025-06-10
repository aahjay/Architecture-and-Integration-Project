package edu.fra.uas.his;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;

@Service
public class ExtractionInterface {

    private static final Logger log = LoggerFactory.getLogger("ExtractionInterface");

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "his.request.queue")
    public void handleHisRequest(HisRequest hisRequest, Message message, Channel channel) {
        log.info("Received HIS request for student ID: {}", hisRequest.getStudentId());
        Student student = studentRepository.get(hisRequest.getStudentId());
        if (student != null) {
            log.info("Sending response for student ID {}: {}", student.getFirstName(), student.getStudentId());
            try {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                log.debug("Acknowledged message for student ID: {}", hisRequest.getStudentId());
                MessageProperties properties = new MessageProperties();
                properties.setCorrelationId(message.getMessageProperties().getCorrelationId());
                Message responseMessage = rabbitTemplate.getMessageConverter().toMessage(student, properties);
                rabbitTemplate.send("his.exchange", "his.response", responseMessage);
                log.info("Student object sent successfully to his.response.queue");
            } catch (Exception e) {
                log.error("Failed to send student object for student ID: {}", hisRequest.getStudentId(), e);
                ErrorResponse error = new ErrorResponse("Failed to send student object", hisRequest.getStudentId());
                rabbitTemplate.convertAndSend(
                        "his.exchange",
                        "his.response",
                        error);
            }
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
