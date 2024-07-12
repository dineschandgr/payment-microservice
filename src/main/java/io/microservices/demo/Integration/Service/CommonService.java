package io.microservices.demo.Integration.Service;

import io.microservices.demo.Integration.Model.Order;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    public Order getOrder(Long orderId, Integer userId){
        return new Order();
    }
}
