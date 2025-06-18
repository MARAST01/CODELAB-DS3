package com.example.productoserviceasync;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductorService {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void enviarProductos(List<ProductoDTO> productos) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, productos);
        System.out.println("Productos enviados: " + productos);
    }
}