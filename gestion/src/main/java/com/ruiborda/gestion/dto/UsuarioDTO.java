package com.ruiborda.gestion.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long userId;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
}
