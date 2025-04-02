package com.example.springkafka.mapper;

import com.example.springkafka.dto.OrderItemDTO;
import com.example.springkafka.entity.OrderItem;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors; // Añadir import si falta

public class OrderItemMapper {

    // --- MÉTODO toDto CORREGIDO ---
    public static OrderItemDTO toDto(OrderItem orderItem) {
        if (Objects.isNull(orderItem)) return null;
        return OrderItemDTO.builder()
                .id(orderItem.getId())
                // ***** LÍNEA PROBLEMÁTICA ELIMINADA/COMENTADA *****
                // Ya estás dentro de un OrderDTO, no necesitas mapear el padre de nuevo aquí.
                // .orderDTO(Objects.isNull(orderItem.getOrder()) ? null : OrderMapper.toDto(orderItem.getOrder()))
                .product(Objects.isNull(orderItem.getProduct()) ? null : ProductMapper.toDto(orderItem.getProduct()))
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .build();
    }

    // --- MÉTODO toEntity CORREGIDO ---
    // También es buena idea corregir el mapeo inverso para evitar problemas similares.
    // La relación 'order' se establecerá cuando añadas este OrderItem a la lista de un Order.
    public static OrderItem toEntity(OrderItemDTO dto) {
        if (Objects.isNull(dto)) return null;
        return OrderItem.builder()
                .id(dto.getId())
                // ***** LÍNEA PROBLEMÁTICA ELIMINADA/COMENTADA *****
                // La entidad Order se asignará desde fuera, no mapeándola desde el DTO hijo.
                // .order(Objects.isNull(dto.getOrderDTO()) ? null : OrderMapper.toEntity(dto.getOrderDTO()))
                .product(Objects.isNull(dto.getProduct()) ? null : ProductMapper.toEntity(dto.getProduct()))
                .quantity(dto.getQuantity())
                .price(dto.getPrice())
                .build();
    }

    // --- MÉTODO toOrderItemList (sin cambios, pero usa el toDto corregido) ---
    public static List<OrderItemDTO> toOrderItemList(List<OrderItem> orderItems) {
        if (Objects.isNull(orderItems)) return null;
        return orderItems.stream()
                .map(OrderItemMapper::toDto) // Ahora llama al toDto corregido
                .collect(Collectors.toList()); // Es más estándar usar collect
    }

    // --- MÉTODO toOrderItemEntityList (Opcional, si lo necesitas) ---
    // Si necesitas convertir una lista de DTOs a entidades:
    public static List<OrderItem> toOrderItemEntityList(List<OrderItemDTO> orderItemDTOs) {
        if (Objects.isNull(orderItemDTOs)) return null;
        return orderItemDTOs.stream()
                .map(OrderItemMapper::toEntity) // Llama al toEntity corregido
                .collect(Collectors.toList());
    }
}