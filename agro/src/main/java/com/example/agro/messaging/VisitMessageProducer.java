package com.example.agro.messaging;

import com.example.agro.dto.NotificationDTO;
import com.example.agro.model.Visit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class VisitMessageProducer {

    private static final Logger logger = LoggerFactory.getLogger(VisitMessageProducer.class);

    private final RabbitTemplate rabbitTemplate;
    private boolean rabbitMQAvailable = true;

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.routingkey}")
    private String routingKey;

    public VisitMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        
        // Check if RabbitMQ is available
        try {
            rabbitTemplate.execute(channel -> null);
            logger.info("RabbitMQ connection successful for Visit tracking");
        } catch (AmqpConnectException e) {
            logger.warn("RabbitMQ connection failed: {}. Visit tracking will be logged only.", e.getMessage());
            rabbitMQAvailable = false;
        }
    }

    /**
     * Publishes a visit notification to RabbitMQ
     */
    public void publishVisitNotification(NotificationDTO notification) {
        try {
            if (rabbitMQAvailable) {
                logger.info("Sending visit notification to queue: {}", notification.getEventType());
                rabbitTemplate.convertAndSend(exchange, routingKey, notification);
                logger.info("Visit notification sent successfully to queue");
            } else {
                // If RabbitMQ is not available, just log the notification
                logger.info("RabbitMQ not available. Would have sent notification: {}", notification.getEventType());
                logger.info("Notification message: {}", notification.getMessage());
            }
        } catch (Exception e) {
            logger.error("Error sending visit notification: {}", e.getMessage(), e);
            logger.info("Visit notification would have been sent: {}", notification.getMessage());
        }
    }

    /**
     * Sends just the visit code to the queue.
     * The consumer will handle looking up or creating the visit record.
     */
    public void sendVisitCode(String visitCode) {
        NotificationDTO notification = NotificationDTO.builder()
                .eventType("VISIT_CODE")
                .message("Visit code: " + visitCode)
                .entityType("VISIT_CODE")
                .entityId(visitCode)
                .data(visitCode)
                .build();

        publishVisitNotification(notification);
    }

    /**
     * Tracks a visit by sending a notification to RabbitMQ
     */
    public void trackVisit(Visit visit) {
        NotificationDTO notification = NotificationDTO.builder()
                .eventType("VISIT_TRACKED")
                .message("Visit tracking code: " + visit.getCode() + ", count: " + visit.getVisitCount())
                .entityType("VISIT")
                .entityId(visit.getId() != null ? visit.getId().toString() : "new")
                .data(visit)
                .build();

        publishVisitNotification(notification);
    }
}