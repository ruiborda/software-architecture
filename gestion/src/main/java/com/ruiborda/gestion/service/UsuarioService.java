package com.ruiborda.gestion.service;

import com.ruiborda.gestion.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {
    List<UsuarioDTO> getAllUsuarios();
    UsuarioDTO getUsuarioById(Long id);
    UsuarioDTO createUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO updateUsuario(Long id, UsuarioDTO usuarioDTO);
    void deleteUsuario(Long id);
    List<UsuarioDTO> findByLastname(String query);
}
