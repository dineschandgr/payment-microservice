package io.microservices.demo.Payment.Service;

import io.microservices.demo.Integration.Model.Order;
import io.microservices.demo.Integration.Service.CommonService;
import io.microservices.demo.Payment.Repository.PaymentRepository;
import io.microservices.demo.Payment.model.Payment;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional(rollbackOn = Exception.class)
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CommonService commonService;

    public String completePayment(Long userId, Integer orderId, boolean fail) throws Exception {

        Order order = commonService.getOrder(userId,orderId);

        Payment payments = Payment.builder()
                .paymentMethod("VISA")
                .transactionId(String.valueOf(Math.random()))
                .totalAmountWithGST(order.getTotalAmountWithGST()).userId(userId)
                .orderId(order.getId()).build();

        paymentRepository.save(payments);

        // todo : use RabbitMQ to send to Order Service
        //orderService.completeOrder(order);
        return "Payment In Process";
    }

}