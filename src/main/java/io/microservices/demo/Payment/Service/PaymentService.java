package io.microservices.demo.Payment.Service;

import io.microservices.demo.Integration.Service.CommonService;
import io.microservices.demo.Integration.Service.PaymentMessageProducer;
import io.microservices.demo.Integration.Service.models.Order;
import io.microservices.demo.Payment.Repository.PaymentRepository;
import io.microservices.demo.Payment.model.Payment;
import io.microservices.demo.Payment.model.PaymentDTO;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional(rollbackOn = Exception.class)
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentMessageProducer paymentMessageProducer;

    @Autowired
    CommonService commonService;

    private final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

    public String completePayment(Long userId, Long orderId, boolean fail) throws Exception {

         Order order = commonService.findOrderById(orderId);

        LOGGER.info("inside completePayment {} ",order);

         Payment payments = Payment.builder()
            .paymentMethod("VISA")
            .transactionId(String.valueOf(Math.random()))
            .totalAmountWithGST(order.getTotalAmountWithGST()).userId(order.getUserId())
            .orderId(order.getId()).build();

        paymentRepository.save(payments);

        // todo : use RabbitMQ to send to Order Service
        String paymentMessage = "success";
        if(fail)
            paymentMessage = "failure";

        PaymentDTO paymentDTO = PaymentDTO.builder().orderId(orderId.intValue()).paymentMessage(paymentMessage).build();

        LOGGER.info("inside payment {} ",paymentDTO);

        paymentMessageProducer.sendJsonMessage(paymentDTO);

        return "Payment In Process";
    }

}
