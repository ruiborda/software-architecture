package com.example.userservice.service;

import com.example.userservice.messaging.MessagePublisher;
import com.example.userservice.model.EmailNotification;
import com.example.userservice.model.Order;
import com.example.userservice.model.User;
import com.example.userservice.repository.OrderRepository;
import com.example.userservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    private final MessagePublisher messagePublisher;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, MessagePublisher messagePublisher) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.messagePublisher = messagePublisher;
    }

    @Transactional
    public Order createOrder(Order order) {
        // Save order to database
        Order savedOrder = orderRepository.save(order);
        logger.info("Order created successfully: {}", savedOrder.getId());

        try {
            User user = userRepository.findById(order.getCustomerId()).orElseThrow();
            EmailNotification notification = EmailNotification.forNewOrder(savedOrder, user);
            messagePublisher.publishEmailNotification(notification);
        } catch (Exception e) {
            // Log the error but don't fail the order creation
            logger.error("Failed to process order notification: {}", e.getMessage());
        }
        return savedOrder;
    }
}
