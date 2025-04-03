package com.example.springkafka.service;

import com.example.springkafka.broker.KafkaGenerateReceiptProducerService;
import com.example.springkafka.broker.KafkaSendOrderProducerService;
import com.example.springkafka.dto.OrderDTO; // Importar DTO
import com.example.springkafka.dto.OrderItemDTO; // Importar DTO
import com.example.springkafka.entity.Order;
import com.example.springkafka.entity.OrderItem;
import com.example.springkafka.entity.Product; // Importar Product
import com.example.springkafka.entity.User; // Importar User
import com.example.springkafka.mapper.OrderMapper;
import com.example.springkafka.repository.OrderRepository;
import com.example.springkafka.repository.ProductRepository; // Importar Repo
import com.example.springkafka.repository.UserRepository; // Importar Repo
import jakarta.persistence.EntityNotFoundException; // Importar excepción
import jakarta.transaction.Transactional; // Importar para transacción
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j // Añadir para logging si es necesario
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository; // <--- Inyectar UserRepository
    private final ProductRepository productRepository; // <--- Inyectar ProductRepository
    private final KafkaGenerateReceiptProducerService kafkaGenerateReceiptProducerService;
    private final KafkaSendOrderProducerService kafkaSendOrderProducerService;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(UUID id) {
        return orderRepository.findById(id);
    }

    // *** MÉTODO CORREGIDO ***
    @Transactional // Es importante que la operación sea transaccional
    public Order createOrder(OrderDTO orderDTO) { // Recibe el DTO
        log.info("Attempting to create order for user ID: {}", orderDTO.getUserId());

        // 1. Validar y obtener el Usuario
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + orderDTO.getUserId()));
        log.debug("User found: {}", user.getId());

        // 2. Crear la entidad Order principal
        Order order = new Order();
        order.setUser(user);
        order.setStatus(orderDTO.getStatus());
        order.setOrderDate(LocalDateTime.now()); // Establecer la fecha aquí
        order.setOrderItems(new ArrayList<>()); // Inicializar la lista

        // 3. Procesar y validar OrderItems
        if (orderDTO.getOrderItems() == null || orderDTO.getOrderItems().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item.");
        }

        for (OrderItemDTO itemDTO : orderDTO.getOrderItems()) {
            log.debug("Processing item for product ID: {}", itemDTO.getProduct().getId());
            // 3.1 Validar y obtener el Producto
            Product product = productRepository.findById(itemDTO.getProduct().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + itemDTO.getProduct().getId()));
            log.debug("Product found: {}", product.getId());

            // 3.2 Crear la entidad OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDTO.getQuantity());
            // Usa el precio del DTO o el precio del producto encontrado? Usaremos el del DTO como en el request original.
            orderItem.setPrice(itemDTO.getPrice());
            // 3.3 Establecer la relación bidireccional
            orderItem.setOrder(order); // <- Enlazar item con la orden
            order.getOrderItems().add(orderItem); // <- Añadir item a la lista de la orden
            log.debug("OrderItem created for product {}", product.getId());
        }

        // 4. Guardar la Order (CascadeType.PERSIST guardará los OrderItems)
        Order orderSaved = orderRepository.save(order);
        log.info("Order created successfully with ID: {}", orderSaved.getId());

        // 5. Publicar en Kafka
        OrderDTO orderDto = OrderMapper.toDto(orderSaved);
        kafkaGenerateReceiptProducerService.publish(orderDto);
        kafkaSendOrderProducerService.publish(orderDto);
        return orderSaved;
    }


    // Mantén los otros métodos (updateOrder, deleteOrder) como estaban,
    // aunque updateOrder también podría necesitar validaciones similares si se permite cambiar el user o los items.
    @Transactional // Añadir @Transactional si modifica la entidad
    public Order updateOrder(UUID id, Order orderDetails) {
        // NOTA: Este método es problemático. Recibe una entidad 'Order' completa.
        // Sería mejor recibir un DTO y aplicar los cambios selectivamente.
        // Además, ¿se debería poder cambiar el usuario de una orden existente?
        // Por ahora, sólo actualiza la fecha como en el original, pero ten cuidado.
        return orderRepository.findById(id)
                .map(order -> {
                    // Validar si orderDetails tiene datos relevantes para actualizar
                    // Por ejemplo, si se actualiza el estado:
                    // if (orderDetails.getStatus() != null) {
                    //    order.setStatus(orderDetails.getStatus());
                    // }
                    // Aquí sólo actualiza la fecha como en el código original:
                    if (orderDetails.getOrderDate() != null) {
                        order.setOrderDate(orderDetails.getOrderDate());
                    }
                    // ¡No actualices los items o el usuario aquí sin lógica de validación!
                    return orderRepository.save(order);
                }).orElseThrow(() -> new EntityNotFoundException("Order not found with id " + id)); // Usar EntityNotFoundException
    }

    @Transactional
    public void deleteOrder(UUID id) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("Order not found with id " + id);
        }
        orderRepository.deleteById(id);
        log.info("Order deleted successfully with ID: {}", id);
    }
}