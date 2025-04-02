package com.example.springkafka.dto;

import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderItemDTO {
    private UUID id;
    private OrderDTO orderDTO;
    private ProductDTO product;
    private Integer quantity;
    private BigDecimal price;
}