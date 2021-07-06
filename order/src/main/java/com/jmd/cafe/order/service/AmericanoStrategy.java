package com.jmd.cafe.order.service;

import com.jmd.cafe.order.fiegn.EventServerCallerFeign;
import com.jmd.cafe.order.fiegn.dto.EventRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Data
@RequiredArgsConstructor
@Service
public class AmericanoStrategy implements CoffeStrategy {
    private final EventServerCallerFeign feign;

    @Override
    public int getPrice() {
        return 4500;
    }

    @Override
    public String getProduct() {
        String product = "Americano!!";
        log.debug(product);
        return product;
    }


    @Async
    @Override
    public CompletableFuture<String> orderProcess(EventRequest eventRequest) {
        log.debug("7. AmericanoStrategy> start");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return feign.event(eventRequest).getResult();
        }).thenCompose(s -> CompletableFuture.supplyAsync(()->feign.event2(eventRequest)))
                .thenCompose(s->CompletableFuture.supplyAsync(this::getProduct));
        log.debug("10. AmericanoStrategy> end");
        return future;
    }
}
