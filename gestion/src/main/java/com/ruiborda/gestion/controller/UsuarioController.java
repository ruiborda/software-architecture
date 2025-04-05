package com.ruiborda.gestion.controller;

import com.ruiborda.gestion.dto.UsuarioDTO;
import com.ruiborda.gestion.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.getAllUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id) {
        UsuarioDTO usuario = usuarioService.getUsuarioById(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> createUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO newUsuario = usuarioService.createUsuario(usuarioDTO);
        return new ResponseEntity<>(newUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO updatedUsuario = usuarioService.updateUsuario(id, usuarioDTO);
        return new ResponseEntity<>(updatedUsuario, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/buscar-por-apellido")
    public ResponseEntity<List<UsuarioDTO>> buscarPorApellido(@RequestParam String apellido) {
        List<UsuarioDTO> usuarios = usuarioService.findByLastname(apellido);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
}
