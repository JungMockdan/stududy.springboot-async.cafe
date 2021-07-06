package com.jmd.cafe.order.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmd.cafe.order.api.dto.OrderResponse;
import com.jmd.cafe.order.api.repository.OrderRepository;
import com.jmd.cafe.order.domain.Coffee;
import com.jmd.cafe.order.fiegn.EventServerCallerFeign;
import com.jmd.cafe.order.fiegn.dto.EventRequest;
import com.jmd.cafe.order.jpa.entity.OrderRequestEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.jmd.cafe.order.api.dto.OrderRequest;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final EventService eventService;
    private final EventServerCallerFeign feign;
    public OrderResponse order(OrderRequest orderRequest) {
        log.debug("2. OrderService>order start");
        // 1. save the order
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OrderRequestEntity requestEntity = objectMapper.convertValue(orderRequest, OrderRequestEntity.class);
        orderRepository.save(requestEntity);
        // 2. push order event to event-Server
        EventRequest eventRequest = EventRequest.builder().id("id").build();
//        eventService.event(eventRequest).thenApply(p->{
//            log.debug("thenApply"+p.getResult());
//            return p;
//        }).thenAccept(p->{
//            log.debug("thenAccept"+p.getResult());
//            // 3. stack the order to static-server
//
//        });

        Coffee coffee = new Coffee();

//        CoffeStrategy americano = new AmericanoStrategy(feign);
//        coffee.setCoffeStrategy(americano);
//        log.debug("#. Americano : "+coffee.getPrice()+"원");
//        coffee.getJobDone(eventRequest);

        coffee.setCoffeStrategy(new CafeLatteStrategy(feign));
        log.debug("#. Cafe-Latte : "+coffee.getPrice()+"원");
        coffee.getJobDone(eventRequest);


        log.debug("5. OrderService>order end");
        return OrderResponse.builder().result("Success").build();
    }
}
