package com.jmd.cafe.order.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmd.cafe.order.api.dto.ManagekeyResponse;
import com.jmd.cafe.order.domain.strategy.*;
import com.jmd.cafe.order.jpa.repository.ManageKeyRepository;
import com.jmd.cafe.order.fiegn.dto.EventRequest;
import com.jmd.cafe.order.jpa.entity.OrderRequestEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.jmd.cafe.order.api.dto.ManageKeyRequest;

@Slf4j
@FieldDefaults(level= AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class ManageKeyService {
    private final ManageKeyRepository manageKeyRepository;
    private final StrategyFactory strategyFactory;
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

        Conditions conditions = new Conditions("","","");
        UseCaseStrategy strategy =
                strategyFactory.findStrategy(StrategyName.evaluateConditionOfStrategy(conditions));

        strategy.manageProcess(eventRequest);

        log.debug("5. ManageKeyService> end");
        return ManagekeyResponse.builder().result("Success").build();
    }
}
