package com.example.springkafka.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderDTO {
    private UUID id;
    private UUID userId;
    private LocalDateTime orderDate;
    private String status;
    private List<OrderItemDTO> orderItems;
}