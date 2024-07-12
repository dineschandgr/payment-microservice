package io.microservices.demo.Integration.Model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    private Integer id;

    private Double orderTotal;

    @Enumerated(EnumType.STRING)
    private EOrderStatus orderStatus;

    private Double gstAmount;

    private Double totalAmountWithGST ;


}
