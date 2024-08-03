package io.microservices.demo.Integration.Service.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long userId;

    private Double orderTotal;

    @Enumerated(EnumType.STRING)
    private EOrderStatus orderStatus;

    private Double gstAmount;

    private Double totalAmountWithGST ;

    @JsonIgnore
    @OneToMany(mappedBy = "order",cascade = { CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();


}
