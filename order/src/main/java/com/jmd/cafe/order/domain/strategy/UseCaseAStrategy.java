package com.jmd.cafe.order.domain.strategy;

import com.jmd.cafe.order.fiegn.EventServerCallerFeign;
import com.jmd.cafe.order.fiegn.dto.EventRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Component
public class UseCaseAStrategy implements UseCaseStrategy {

    final EventServerCallerFeign feign;
    @Async
    @Override
    public CompletableFuture<String> manageProcess(EventRequest eventRequest) {
        log.debug("7. UseCase-A-Strategy> start");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return feign.event(eventRequest).getResult();
        }).thenCompose(s -> CompletableFuture.supplyAsync(()->feign.event2(eventRequest)))
                .thenApply(s->"finished-A")
                ;
        log.debug("10. UseCase-A-Strategy> end");
        return future;
    }

    @Override
    public StrategyName getStrategyName() {
        return StrategyName.USE_CASE_A;
    }
}
