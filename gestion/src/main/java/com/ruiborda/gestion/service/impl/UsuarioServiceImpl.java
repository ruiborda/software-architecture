package com.ruiborda.gestion.service.impl;

import com.ruiborda.gestion.dto.UsuarioDTO;
import com.ruiborda.gestion.exception.ResourceNotFoundException;
import com.ruiborda.gestion.model.Usuario;
import com.ruiborda.gestion.repository.UsuarioRepository;
import com.ruiborda.gestion.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO getUsuarioById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
        return convertToDTO(usuario);
    }

    @Override
    public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = convertToEntity(usuarioDTO);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return convertToDTO(savedUsuario);
    }

    @Override
    public UsuarioDTO updateUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario existingUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));

        existingUsuario.setUsername(usuarioDTO.getUsername());
        existingUsuario.setPassword(usuarioDTO.getPassword());
        existingUsuario.setEmail(usuarioDTO.getEmail());
        existingUsuario.setFirstName(usuarioDTO.getFirstName());
        existingUsuario.setLastName(usuarioDTO.getLastName());

        Usuario updatedUsuario = usuarioRepository.save(existingUsuario);
        return convertToDTO(updatedUsuario);
    }

    @Override
    public void deleteUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
        usuarioRepository.delete(usuario);
    }

    @Override
    public List<UsuarioDTO> findByLastname(String lastName) {
        var users= usuarioRepository.findByLastNameContaining(lastName);
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

    }

    private UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setUserId(usuario.getUserId());
        dto.setUsername(usuario.getUsername());
        dto.setPassword(usuario.getPassword());
        dto.setEmail(usuario.getEmail());
        dto.setFirstName(usuario.getFirstName());
        dto.setLastName(usuario.getLastName());
        return dto;
    }

    private Usuario convertToEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setUserId(dto.getUserId());
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());
        usuario.setEmail(dto.getEmail());
        usuario.setFirstName(dto.getFirstName());
        usuario.setLastName(dto.getLastName());
        return usuario;
    }
}
