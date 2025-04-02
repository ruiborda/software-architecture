package com.example.springkafka.mapper;

import com.example.springkafka.dto.OrderDTO;
import com.example.springkafka.entity.Order;
import com.example.springkafka.entity.OrderItem;
import com.example.springkafka.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class OrderMapper {

    public static OrderDTO toDto(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .orderItems(OrderItemMapper.toOrderItemList(order.getOrderItems()))
                .build();
    }

    public static Order toEntity(OrderDTO dto) {
        if (dto == null) return null;
        log.info("___________________________________________________");
        log.info("dto.getOrderItems: {}", dto.getOrderItems());
        log.info("___________________________________________________");
        return Order.builder()
                .id(dto.getId())
                .user(User.builder().id(dto.getUserId()).build())
                .orderDate(dto.getOrderDate())
                .status(dto.getStatus())
                .orderItems(Objects.isNull(dto.getOrderItems())
                        ? null
                        : dto.getOrderItems().stream()
                        .map(OrderItemMapper::toEntity)
                        .toList())
                .build();
    }

}
