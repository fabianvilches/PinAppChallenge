package com.pinapp.clientes.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Clase que escucha mensajes en la cola 'clientesQueue' y los procesa en segundo plano.
 * Los mensajes pueden contener información sobre clientes que requieren validaciones
 * o generación de reportes.
 */
@Component
public class ClienteListener {
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Método que se ejecuta automáticamente cuando llega un mensaje a la cola `clientesQueue`.
     * @param mensaje JSON con datos del cliente.
     */
    @RabbitListener(queues = "clientesQueue")
    public void procesarMensaje(String mensaje) {
        System.out.println("Procesando tarea asíncrona: " + mensaje);

        try {
            JsonNode jsonNode = objectMapper.readTree(mensaje);

            // Validación de estructura mínima del mensaje antes de procesarlo
            if (!jsonNode.has("nombre") || !jsonNode.has("edad")) {
                System.err.println("Error: El mensaje no contiene datos válidos.");
                return;
            }

            System.out.println("Cliente válido: " + jsonNode.get("nombre").asText());
        } catch (Exception e) {
            System.err.println("Error al procesar datos: " + e.getMessage());
        }
    }
}