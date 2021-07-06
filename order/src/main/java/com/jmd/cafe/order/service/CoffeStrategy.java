package com.jmd.cafe.order.service;

import com.jmd.cafe.order.fiegn.EventServerCallerFeign;
import com.jmd.cafe.order.fiegn.dto.EventRequest;
import com.jmd.cafe.order.fiegn.dto.EventResponse;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.CompletableFuture;

public interface CoffeStrategy {
    int getPrice();
    String getProduct();
    CompletableFuture<String> orderProcess(EventRequest eventRequest);
}
