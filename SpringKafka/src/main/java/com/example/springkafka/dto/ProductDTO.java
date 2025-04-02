package com.example.springkafka.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductDTO {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime createdAt;
}