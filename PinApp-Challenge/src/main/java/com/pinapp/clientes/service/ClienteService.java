package com.pinapp.clientes.service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

import com.pinapp.clientes.model.Cliente;
import com.pinapp.clientes.repository.ClienteRepository;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

    /**
     * Constructor que inyecta el repositorio de clientes.
     * @param clienteRepository Repositorio para manejar operaciones con la base de datos.
     */
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Registra un nuevo cliente en la base de datos.
     * @param cliente Objeto Cliente que será persistido.
     * @return Cliente registrado con ID generado.
     */
    public Cliente registrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    /**
     * Obtiene todos los clientes disponibles en la base de datos.
     * @return Lista de clientes.
     */
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    /**
     * Procesa clientes en segundo plano utilizando 'Virtual Threads'.
     * Este método ejecuta una tarea asíncrona que itera sobre la lista de clientes y
     * realiza alguna operación sobre ellos.
     */
    public void procesarClientesAsync() {
        executor.submit(() -> {
            List<Cliente> clientes = clienteRepository.findAll();
            clientes.forEach(cliente -> System.out.println("Procesando cliente: " + cliente.getNombre()));
        });
    }
}