package com.pinapp.clientes.dto;

import java.time.LocalDate;

public record ClienteDTO(
		String nombre, 
		String apellido, 
		Integer edad, 
		LocalDate fechaNacimiento) 
{}
