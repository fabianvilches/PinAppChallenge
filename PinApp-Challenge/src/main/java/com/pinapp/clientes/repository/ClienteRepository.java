package com.pinapp.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pinapp.clientes.model.Cliente;

/**
 * Repositorio para operaciones CRUD sobre la entidad Cliente.
 * Extiende JpaRepository para aprovechar métodos estándar de Spring Data JPA.
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}