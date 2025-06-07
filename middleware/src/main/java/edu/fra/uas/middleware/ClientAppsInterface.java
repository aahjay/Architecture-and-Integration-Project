package edu.fra.uas.middleware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.fra.uas.middleware.model.HisRequestDTO;
import edu.fra.uas.middleware.model.StudentRequestDTO;

@Service
public class ClientAppsInterface {

    private static final Logger log = LoggerFactory.getLogger(ClientAppsInterface.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private CorrelationMap correlationMap;

    @RabbitListener(queues = { "peregos.request.queue", "wyseflow.request.queue" })
    public void handleClientRequest(StudentRequestDTO studentRequest, Message message) {
        log.info("Handling request for student ID: {}", studentRequest.getStudentId());
        // Store the correlation ID for later use
        String correlationId = message.getMessageProperties().getCorrelationId();
        String replyTo = message.getMessageProperties().getReplyTo();
        if (correlationId != null) {
            correlationMap.put(correlationId, replyTo);
        }
        try {
            HisRequestDTO hisRequest = new HisRequestDTO();
            hisRequest.setStudentId(studentRequest.getStudentId());
            MessageProperties properties = new MessageProperties();
            properties.setCorrelationId(correlationId);
            Message hisMessage = rabbitTemplate.getMessageConverter().toMessage(hisRequest, properties);
            rabbitTemplate.send("his.exchange", "his.request", hisMessage);
            log.info("Sent HIS data request for student ID: {}", studentRequest.getStudentId());
        } catch (Exception e) {
            log.error("Failed to send HIS data request for student ID: {}", studentRequest.getStudentId(), e);
            return;
        }
    }

}
