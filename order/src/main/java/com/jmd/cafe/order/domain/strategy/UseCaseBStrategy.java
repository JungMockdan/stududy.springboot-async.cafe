package com.jmd.cafe.order.domain.strategy;

import com.jmd.cafe.order.fiegn.EventServerCallerFeign;
import com.jmd.cafe.order.fiegn.dto.EventRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Data
@NoArgsConstructor
@Service
public class UseCaseBStrategy implements UseCaseStrategy {

    @Override
    public int getIntValue() {
        return 5500;
    }

    @Override
    public String getStringValue() {
        String product = "B-Strategy!!";
        log.debug(product);
        return product;
    }

    public List<CompletableFuture<String>> test(EventRequest eventRequest) {
        List<EventRequest> s = new ArrayList<>();
        List<CompletableFuture<String>> futures
                = s.stream().map(
                        ech -> CompletableFuture.supplyAsync(
                                () -> String.format("%s price is %.2f", ech.getId(), ech.getId()))
        ).collect(Collectors.toList());
        return futures;
    }

    public List<String> test2(EventRequest eventRequest) {
        List<EventRequest> s = new ArrayList<>();
        List<CompletableFuture<String>> futures
                = s.stream().map(
                        ech -> CompletableFuture.supplyAsync(//CompletableFuture 각각 비동기로 계산함.
                                () -> String.format("%s price is %.2f", ech.getId(), ech.getId()))
        ).collect(Collectors.toList());

        return futures
                .stream()
                .map(CompletableFuture::join)//모든 비동기 동작이 끝나기를 기다림.
                .collect(Collectors.toList());
    }
    @Async
    @Override
    public <T extends EventRequest> CompletableFuture<String> manageProcess(EventRequest eventRequest,EventServerCallerFeign feign) {
        log.debug("UseCase-B-Strategy> start");
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
        ).thenCompose(s -> CompletableFuture.supplyAsync(this::getStringValue)
        )
                ;

        log.debug("UseCase-B-Strategy> end");
        return future;
    }

    @Override
    public boolean evaluate(Conditions expression) {
        return false;
    }
}
