package com.example.agro.service;

import com.example.agro.dto.VisitResponseDTO;
import com.example.agro.mapper.VisitMapper;
import com.example.agro.messaging.VisitMessageProducer;
import com.example.agro.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitService {
    private static final Logger logger = LoggerFactory.getLogger(VisitService.class);
    private static final String COMPANY_PAGINATION_CODE = "COMPANY_PAGINATION";

    private final VisitRepository visitRepository;
    private final VisitMessageProducer visitMessageProducer;
    private final VisitMapper visitMapper;

    /**
     * Records a visit for the company pagination feature.
     * This method is called when the pagination endpoint is accessed.
     * It only sends a message to the queue with the visit code.
     * The actual visit tracking is handled by the consumer.
     */
    @Transactional
    public void trackCompanyPagination() {
        // Just log and send the code to the message queue
        logger.info("Company pagination accessed. Sending message to queue");
        
        // Send message to queue with just the code
        visitMessageProducer.sendVisitCode(COMPANY_PAGINATION_CODE);
    }
    
    /**
     * Get all visits stored in the system.
     * 
     * @return List of visit DTOs
     */
    @Transactional(readOnly = true)
    public List<VisitResponseDTO> getAllVisits() {
        logger.info("Retrieving all visits");
        return visitMapper.toResponseDTOList(visitRepository.findAll());
    }
}