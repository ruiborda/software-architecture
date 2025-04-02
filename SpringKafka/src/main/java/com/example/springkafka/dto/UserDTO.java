package com.example.springkafka.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserDTO {
    private UUID id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
}