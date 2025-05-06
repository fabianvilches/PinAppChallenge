package com.pinapp.clientes.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClienteMessaging {
    private final RabbitTemplate rabbitTemplate;

    public ClienteMessaging(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarMensaje(String mensaje) {
        rabbitTemplate.convertAndSend("clientesQueue", mensaje);
    }
}