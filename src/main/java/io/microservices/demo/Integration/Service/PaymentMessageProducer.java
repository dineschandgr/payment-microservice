package io.microservices.demo.Integration.Service;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message)
    {
        rabbitTemplate.convertAndSend(
                "payment-exchange", "routing-key", message);
    }
}