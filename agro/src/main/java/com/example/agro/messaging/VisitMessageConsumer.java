package com.example.agro.messaging;

import com.example.agro.dto.NotificationDTO;
import com.example.agro.model.Visit;
import com.example.agro.repository.VisitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Consumer class that listens to RabbitMQ for visit tracking messages
 * and processes them accordingly.
 */
@Component
public class VisitMessageConsumer {
    private static final Logger logger = LoggerFactory.getLogger(VisitMessageConsumer.class);
    
    private final VisitRepository visitRepository;
    
    public VisitMessageConsumer(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @RabbitListener(queues = "${rabbitmq.queue.notification.name}")
    @Transactional
    public void consumeVisitNotification(NotificationDTO notification) {
        logger.info("Received visit notification: {}", notification);
        
        // Process notification based on event type
        switch (notification.getEventType()) {
            case "VISIT_CODE":
                processVisitCode(notification);
                break;
            case "VISIT_TRACKED":
                processVisitTracked(notification);
                break;
            case "PAGINATION_ACCESSED":
                processPaginationAccessed(notification);
                break;
            default:
                logger.warn("Unknown notification event type: {}", notification.getEventType());
        }
    }
    
    /**
     * Process a VISIT_CODE notification.
     * This handles looking up the visit by code, creating it if it doesn't exist,
     * incrementing the counter, and saving it.
     */
    private void processVisitCode(NotificationDTO notification) {
        try {
            // Extract the visit code from the notification data
            String visitCode = (String) notification.getData();
            logger.info("Processing VISIT_CODE notification for code: {}", visitCode);
            
            // Look up the visit by code
            Optional<Visit> existingVisit = visitRepository.findByCode(visitCode);
            
            Visit visit;
            if (existingVisit.isPresent()) {
                // If it exists, increment the counter
                visit = existingVisit.get();
                visit.setVisitCount(visit.getVisitCount() + 1);
                logger.info("Incrementing visit count for code {}: new count = {}", 
                        visitCode, visit.getVisitCount());
            } else {
                // If it doesn't exist, create a new one
                visit = new Visit();
                visit.setCode(visitCode);
                visit.setVisitCount(1);
                logger.info("Creating new visit record for code: {}", visitCode);
            }
            
            // Save the visit
            visitRepository.save(visit);
            logger.info("Visit record saved successfully for code: {}", visitCode);
            
        } catch (Exception e) {
            logger.error("Error processing VISIT_CODE notification: {}", e.getMessage(), e);
        }
    }
    
    private void processVisitTracked(NotificationDTO notification) {
        // In a real application, this might update analytics or send an email
        logger.info("Processing VISIT_TRACKED notification: {}", notification.getMessage());
        
        // Could also store in a database for reporting
        logger.info("Visit tracking data processed: {}", notification.getData());
    }
    
    private void processPaginationAccessed(NotificationDTO notification) {
        // Process pagination access event
        logger.info("Processing PAGINATION_ACCESSED notification: {}", notification.getMessage());
        logger.info("Pagination data: {}", notification.getData());
        
        // In a real application, this might update usage analytics
        logger.info("Company pagination access recorded for analytics");
    }
}