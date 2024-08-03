package io.microservices.demo.Payment.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class PaymentDTO implements Serializable {

    private Integer orderId;

    private String paymentMessage;
}
