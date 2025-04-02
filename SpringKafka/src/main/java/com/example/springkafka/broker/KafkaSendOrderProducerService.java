package com.example.springkafka.broker;

import com.example.springkafka.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaSendOrderProducerService {

    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;
    private final String TOPIC = "topic-send-orders";

    @Autowired
    public KafkaSendOrderProducerService(KafkaTemplate<String, OrderDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(OrderDTO orderDTO) {
        log.info("-------------------------------------------------------------------------------------");
        log.info("KafkaSendOrderProducerService - publish");
        kafkaTemplate.send(TOPIC, orderDTO);
        System.out.println("Mensaje enviado: {orderId = " + orderDTO.getId() + "}");
        log.info("--------------------------------------------------------------------------------------");
    }
}
