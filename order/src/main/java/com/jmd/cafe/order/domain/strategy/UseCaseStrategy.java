package com.jmd.cafe.order.domain.strategy;

import com.jmd.cafe.order.fiegn.EventServerCallerFeign;
import com.jmd.cafe.order.fiegn.dto.EventRequest;

import java.util.concurrent.CompletableFuture;

public interface UseCaseStrategy {
    int getIntValue();
    String getStringValue();
    CompletableFuture<String> manageProcess(EventRequest eventRequest, EventServerCallerFeign feign);
    boolean evaluate(Conditions expression);
}
