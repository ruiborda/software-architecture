package com.example.springkafka.mapper;

import com.example.springkafka.dto.UserDTO;
import com.example.springkafka.entity.User;

public class UserMapper {
    public static UserDTO UserEntitytoUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
