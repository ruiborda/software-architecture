package com.example.agro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDTO {
    private String eventType;
    private String message;
    private String entityType;
    private String entityId;
    private String userEmail;
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
    private Object data;
}