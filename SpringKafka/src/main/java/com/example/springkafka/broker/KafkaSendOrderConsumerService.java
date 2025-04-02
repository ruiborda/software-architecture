package com.example.springkafka.broker;

import com.example.springkafka.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaSendOrderConsumerService {

    @KafkaListener(topics = "topic-send-orders", groupId = "grupo-send-orders")
    public void consume(OrderDTO order) {
        log.info("------------------------------------------------------------------------------------");
        log.info("KafkaSendOrderConsumerService - consume");
        log.info("Received order to send with order ID: {}", order.getId());
        try {
            // Lógica para enviar el pedido. Por ejemplo:
            System.out.println("Enviando pedido para order ID: " + order.getId());
            // Aquí puedes implementar la integración con otros servicios o actualizar el estado del pedido.
            log.info("Order sent successfully for order ID: {}", order.getId());
        } catch (Exception e) {
            log.error("Error sending order ID {}: {}", order.getId(), e.getMessage(), e);
        }
        log.info("------------------------------------------------------------------------------------");
    }
}
