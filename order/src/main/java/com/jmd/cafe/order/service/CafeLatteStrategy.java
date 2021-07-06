package com.jmd.cafe.order.service;

import com.jmd.cafe.order.fiegn.EventServerCallerFeign;
import com.jmd.cafe.order.fiegn.dto.EventRequest;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Data
@RequiredArgsConstructor
@Service
public class CafeLatteStrategy implements CoffeStrategy {
    private final EventServerCallerFeign feign;

    @Override
    public int getPrice() {
        return 5500;
    }

    @Override
    public String getProduct() {
        String product = "Cafe-Latte!!";
        log.debug(product);
        return product;
    }

    @Async
    @Override
    public CompletableFuture<String> orderProcess(EventRequest eventRequest) {
        log.debug("CafeLatteStrategy> start");
        CompletableFuture<String> future
                = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return feign.event(eventRequest).getResult();
        }).thenCompose(s ->
                CompletableFuture.supplyAsync(() -> feign.event2(eventRequest).getResult())
        ).thenCompose(s -> CompletableFuture.supplyAsync(this::getProduct)
        )
                ;

        log.debug("CafeLatteStrategy> end");
        return future;
    }
}
