package com.example.springkafka.broker;

import com.example.springkafka.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaGenerateReceiptProducerService {

    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;
    private final String TOPIC = "topic-generate-receipt";

    @Autowired
    public KafkaGenerateReceiptProducerService(KafkaTemplate<String, OrderDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(OrderDTO orderDTO) {
        System.out.println("-------------------------------------------------------------------------------------");
        kafkaTemplate.send(TOPIC, orderDTO);
        System.out.println("Mensaje enviado: {orderId = " + orderDTO + "}");
        System.out.println("--------------------------------------------------------------------------------------");
    }
}
