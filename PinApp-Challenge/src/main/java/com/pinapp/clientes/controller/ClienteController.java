package com.pinapp.clientes.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinapp.clientes.model.Cliente;
import com.pinapp.clientes.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    /**
     * Constructor que inyecta el servicio de clientes.
     * @param clienteService Servicio que maneja la lógica de negocio.
     */
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    
    /**
     * Registra un nuevo cliente en la base de datos.
     * @param cliente Datos del cliente a registrar.
     * @return Cliente registrado.
     */
    @PostMapping
    public Cliente registrarCliente(@Valid @RequestBody Cliente cliente) {
        return clienteService.registrarCliente(cliente);
    }

    /**
     * Obtiene la lista de todos los clientes almacenados.
     * @return Lista de clientes.
     */
    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @PostMapping("/procesar")
    public void procesarClientesAsync() {
        clienteService.procesarClientesAsync();
    }
}