package com.example.springkafka.broker;

import com.example.springkafka.dto.OrderDTO;
import com.example.springkafka.entity.User;
import com.example.springkafka.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaGenerateReceiptConsumerService {

    private final UserRepository userRepository;
    private final TemplateEngine templateEngine; // <-- Inyectar TemplateEngine

    @Transactional
    @KafkaListener(topics = "topic-generate-receipt", groupId = "grupo-hello")
    public void consume(OrderDTO order) {
        log.info("------------------------------------------------------------------------------------");
        log.info("Received message to generate receipt HTML for order ID: {}", order.getId());
        try {
            User user = userRepository.findById(order.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + order.getUserId()));

            BigDecimal totalAmount = order.getOrderItems().stream()
                    .filter(item -> item.getPrice() != null && item.getQuantity() > 0)
                    .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            Context context = new Context();
            context.setVariable("order", order);
            context.setVariable("user", user);
            context.setVariable("totalAmount", totalAmount);

            String htmlReceipt = templateEngine.process("invoice", context);

            System.out.println("--- INICIO BOLETA HTML ---");
            System.out.println(htmlReceipt);
            System.out.println("--- FIN BOLETA HTML ---");

            log.info("HTML receipt generated successfully for order ID: {}", order.getId());

        } catch (IllegalArgumentException e) {
            log.error("Invalid argument processing order ID {}: {}", order.getId(), e.getMessage());
        } catch (Exception e) {
            log.error("Error processing message for order ID {}: {}", order.getId(), e.getMessage(), e);
        }
        log.info("------------------------------------------------------------------------------------");
    }
}