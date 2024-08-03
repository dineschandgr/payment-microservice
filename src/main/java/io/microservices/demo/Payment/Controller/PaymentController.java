package io.microservices.demo.Payment.Controller;

import io.microservices.demo.Configuration.UserContext;
import io.microservices.demo.Integration.Service.PaymentMessageProducer;
import io.microservices.demo.Payment.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public String createOrder(@RequestParam Long orderId, boolean fail) throws Exception {
        Long userId = UserContext.getUserId();
        return paymentService.completePayment(userId, orderId, fail);
    }

}

