package com.ruiborda.gestion.service;

import com.ruiborda.gestion.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {
    List<ClienteDTO> getAllClientes();
    ClienteDTO getClienteById(Long id);
    ClienteDTO createCliente(ClienteDTO clienteDTO);
    ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO);
    void deleteCliente(Long id);
    List<ClienteDTO> searchClientes(String query);
}