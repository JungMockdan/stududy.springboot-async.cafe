package com.jmd.cafe.order.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmd.cafe.order.api.dto.ManagekeyResponse;
import com.jmd.cafe.order.api.repository.ManageKeyRepository;
import com.jmd.cafe.order.domain.InboundManageKey;
import com.jmd.cafe.order.domain.strategy.Conditions;
import com.jmd.cafe.order.domain.strategy.StrategyEngine;
import com.jmd.cafe.order.fiegn.EventServerCallerFeign;
import com.jmd.cafe.order.fiegn.dto.EventRequest;
import com.jmd.cafe.order.jpa.entity.OrderRequestEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.jmd.cafe.order.api.dto.ManageKeyRequest;

@Slf4j
@RequiredArgsConstructor
@Service
public class ManageKeyService {
    private final ManageKeyRepository manageKeyRepository;
    private final EventService eventService;
    private final EventServerCallerFeign feign;
    private final StrategyEngine engine;
    public ManagekeyResponse doSomething(ManageKeyRequest manageKeyRequest) {
        log.debug("2. ManageKeyService>order start");
        // 1. save the order
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OrderRequestEntity requestEntity = objectMapper.convertValue(manageKeyRequest, OrderRequestEntity.class);
        manageKeyRepository.save(requestEntity);
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

        InboundManageKey inboundManageKey = new InboundManageKey();

//        CoffeStrategy americano = new AmericanoStrategy(feign);
//        coffee.setCoffeStrategy(americano);
//        log.debug("#. Americano : "+coffee.getPrice()+"원");
//        coffee.getJobDone(eventRequest);

        Conditions conditions = new Conditions("","","");

        inboundManageKey.setUseCaseStrategy(engine.getStrategy(conditions));

        log.debug("#. strategy : "+ inboundManageKey.getIntValue()+"원");
        inboundManageKey.getJobDone(eventRequest,feign);


        log.debug("5. ManageKeyService> end");
        return ManagekeyResponse.builder().result("Success").build();
    }
}
