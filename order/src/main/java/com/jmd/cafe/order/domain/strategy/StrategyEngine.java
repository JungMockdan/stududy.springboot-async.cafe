package com.jmd.cafe.order.domain.strategy;

import java.util.ArrayList;
import java.util.List;

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

    public UseCaseStrategy getStrategy(Conditions expression) {
        return strategy
                .stream()
                .filter(r -> r.evaluate(expression))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Expression does not matches any Rule"));
    }


}
