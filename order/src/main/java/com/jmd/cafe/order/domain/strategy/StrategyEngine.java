package com.jmd.cafe.order.domain.strategy;

import com.jmd.cafe.order.fiegn.EventServerCallerFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StrategyEngine {
    private static List<UseCaseStrategy> strategy = new ArrayList<>();

    static {
        strategy.add(new UseCaseAStrategy());
        strategy.add(new UseCaseBStrategy());
//        .다른 룰
//        .
//        .
//        .
    }
    /*private final EventServerCallerFeign feign;

    public StrategyEngine(EventServerCallerFeign feign) {
        this.feign = feign;
    }*/

    public UseCaseStrategy getStrategy(Conditions expression) {
        return strategy
                .stream()
                .filter(r -> r.evaluate(expression))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Expression does not matches any Rule"));
    }


}
