package com.jmd.cafe.order.domain.strategy;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@FieldDefaults(level= AccessLevel.PRIVATE)
public class StrategyFactory {

    Map<StrategyName, UseCaseStrategy> strategies;

    public StrategyFactory(Set<UseCaseStrategy> strategySet) {
        createStrategy(strategySet);
    }

    public UseCaseStrategy findStrategy(StrategyName strategyName) {
        return strategies.get(strategyName);
    }

    private void createStrategy(Set<UseCaseStrategy> strategySet) {
        strategies = new HashMap<StrategyName, UseCaseStrategy>();
        strategySet.forEach(
                strategy ->strategies.put(strategy.getStrategyName(), strategy));
    }

}
