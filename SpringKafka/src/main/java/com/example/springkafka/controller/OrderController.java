package com.example.springkafka.controller;


import com.example.springkafka.dto.OrderDTO;
import com.example.springkafka.entity.Order;
import com.example.springkafka.mapper.OrderMapper;
import com.example.springkafka.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        log.debug("getAllOrders");
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable UUID id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDTO orderDTO) { // Recibe DTO
        log.info("___________________________________________________");
        log.info("Received create order request: {}", orderDTO);
        log.info("___________________________________________________");
        try {
            // Llama directamente al servicio con el DTO
            Order createdOrder = orderService.createOrder(orderDTO);
            // Opcional: Podrías devolver un OrderDTO si prefieres no exponer la entidad directamente
            // OrderDTO responseDTO = OrderMapper.toDto(createdOrder);
            // return ResponseEntity.ok(responseDTO);
            return ResponseEntity.ok(createdOrder); // Devuelve la entidad por ahora
        } catch (EntityNotFoundException e) {
            log.error("Error creating order: {}", e.getMessage());
            // Devuelve 404 o 400 si una entidad relacionada no se encuentra
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("Error creating order: {}", e.getMessage());
            // Devuelve 400 por datos inválidos (ej: no hay items)
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error creating order", e);
            // Error genérico del servidor
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An internal error occurred.");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable UUID id, @RequestBody Order order) {
        try {
            Order updatedOrder = orderService.updateOrder(id, order);
            return ResponseEntity.ok(updatedOrder);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
