package com.jmd.cafe.order.service;

import com.jmd.cafe.order.fiegn.EventServerCallerFeign;
import com.jmd.cafe.order.fiegn.dto.EventRequest;
import com.jmd.cafe.order.fiegn.dto.EventResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@RequiredArgsConstructor
@Service
public class EventService {
    private final EventServerCallerFeign eventServerCallerFeign;
    @Async
    public CompletableFuture<EventResponse> event(EventRequest eventRequest) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        CompletableFuture<EventResponse> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return eventServerCallerFeign.event(eventRequest);
        }, executor);
        return future;
    }
}
