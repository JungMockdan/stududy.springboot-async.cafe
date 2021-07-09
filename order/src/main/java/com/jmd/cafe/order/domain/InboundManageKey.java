package com.jmd.cafe.order.domain;

import com.jmd.cafe.order.fiegn.dto.EventRequest;
import com.jmd.cafe.order.domain.strategy.UseCaseStrategy;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.CompletableFuture;

@Getter
@Setter
@NoArgsConstructor
public class InboundManageKey {
    private UseCaseStrategy useCaseStrategy;

    public InboundManageKey(UseCaseStrategy useCaseStrategy) {
        this.useCaseStrategy = useCaseStrategy;
    }

    public int getIntValue(){
        return  useCaseStrategy.getIntValue();
    }
    public CompletableFuture<String> getJobDone(EventRequest request){
        return useCaseStrategy.manageProcess(request);
    }
}
