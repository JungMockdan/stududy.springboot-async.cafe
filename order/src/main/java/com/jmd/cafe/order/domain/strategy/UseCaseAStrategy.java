package com.jmd.cafe.order.domain.strategy;

import com.jmd.cafe.order.fiegn.EventServerCallerFeign;
import com.jmd.cafe.order.fiegn.dto.EventRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Data
public class UseCaseAStrategy implements UseCaseStrategy {

    @Override
    public int getIntValue() {
        return 4500;
    }

    @Override
    public String getStringValue() {
        String product = "Americano!!";
        log.debug(product);
        return product;
    }


    @Async
    @Override
    public CompletableFuture<String> manageProcess(EventRequest eventRequest, EventServerCallerFeign feign) {
        log.debug("7. UseCase-A-Strategy> start");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return feign.event(eventRequest).getResult();
        }).thenCompose(s -> CompletableFuture.supplyAsync(()->feign.event2(eventRequest)))
                .thenCompose(s->CompletableFuture.supplyAsync(this::getStringValue));
        log.debug("10. UseCase-A-Strategy> end");
        return future;
    }

    @Override
    public boolean evaluate(Conditions expression) {
        return true;
    }
}
