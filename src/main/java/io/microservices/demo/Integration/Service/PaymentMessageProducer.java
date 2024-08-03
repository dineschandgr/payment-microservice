package io.microservices.demo.Integration.Service;


import io.microservices.demo.Payment.model.PaymentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final Logger LOGGER = LoggerFactory.getLogger(PaymentMessageProducer.class);

    public void sendMessage(String message)
    {
        rabbitTemplate.convertAndSend(
                "payment-exchange", "routing-key", message);
    }

    public void sendJsonMessage(PaymentDTO paymentDTO){
        LOGGER.info(String.format("Json message sent -> %s", paymentDTO.toString()));
        rabbitTemplate.convertAndSend("payment-exchange", "routing-key", paymentDTO);
    }
}