package com.jmd.cafe.order.domain;

import com.jmd.cafe.order.fiegn.EventServerCallerFeign;
import com.jmd.cafe.order.fiegn.dto.EventRequest;
import com.jmd.cafe.order.domain.strategy.UseCaseStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Data
public class InboundManageKey {
    private UseCaseStrategy useCaseStrategy;

    public int getIntValue() {
        return useCaseStrategy.getIntValue();
    }

    public CompletableFuture<String> getJobDone(EventRequest request, EventServerCallerFeign feign) {
        return useCaseStrategy.manageProcess(request, feign);
    }
}
