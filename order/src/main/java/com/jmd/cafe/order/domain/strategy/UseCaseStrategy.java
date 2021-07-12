package com.jmd.cafe.order.domain.strategy;

import com.jmd.cafe.order.fiegn.dto.EventRequest;

import java.util.concurrent.CompletableFuture;

public interface UseCaseStrategy {
    CompletableFuture<String> manageProcess(EventRequest eventRequest);
    StrategyName getStrategyName();
}
