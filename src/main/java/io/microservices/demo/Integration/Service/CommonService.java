package io.microservices.demo.Integration.Service;

import io.microservices.demo.Integration.Service.models.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CommonService {

    @Autowired
    RestTemplate restTemplate;

    private final Logger LOGGER = LoggerFactory.getLogger(CommonService.class);

    public Order findOrderById(Long orderId) {

        LOGGER.info("inside findUserById id : {} ",orderId);

        ResponseEntity<Order> response =
                restTemplate.exchange(
                        "http://order-service/order/" + orderId,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {});
        return response.getBody();
    }
}
