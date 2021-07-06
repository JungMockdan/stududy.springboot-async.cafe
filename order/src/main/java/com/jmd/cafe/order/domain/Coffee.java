package com.jmd.cafe.order.domain;

import com.jmd.cafe.order.fiegn.dto.EventRequest;
import com.jmd.cafe.order.service.CoffeStrategy;
import lombok.Data;

import java.util.concurrent.CompletableFuture;

@Data
public class Coffee {
    private CoffeStrategy coffeStrategy;
    public int getPrice(){
        return  coffeStrategy.getPrice();
    }
    public CompletableFuture<String> getJobDone(EventRequest request){
        return coffeStrategy.orderProcess(request);
    }
}
