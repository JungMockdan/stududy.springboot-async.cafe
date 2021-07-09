package com.jmd.cafe.order.domain.strategy;

import com.jmd.cafe.order.fiegn.EventServerCallerFeign;
import com.jmd.cafe.order.fiegn.dto.EventRequest;
import com.jmd.cafe.order.fiegn.dto.EventResponse;

import java.util.concurrent.CompletableFuture;

public interface UseCaseStrategy {
    int getIntValue();
    String getStringValue();
    <T extends EventRequest> CompletableFuture<String> manageProcess(EventRequest eventRequest,EventServerCallerFeign feign);
    boolean evaluate(Conditions expression);
}
