package com.jmd.cafe.order.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmd.cafe.order.api.dto.OrderResponse;
import com.jmd.cafe.order.api.repository.OrderRepository;
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
    public OrderResponse order(OrderRequest orderRequest) {
        // 1. save the order
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OrderRequestEntity requestEntity = objectMapper.convertValue(orderRequest, OrderRequestEntity.class);
        orderRepository.save(requestEntity);
        // 2. push order event to event-Server
        EventRequest eventRequest = EventRequest.builder().id("id").build();
        eventService.event(eventRequest).thenApply(p->{
            return p;
        }).thenAccept(p->{
            // 3. stack the order to static-server

        });

        return OrderResponse.builder().result("Success").build();
    }
}
