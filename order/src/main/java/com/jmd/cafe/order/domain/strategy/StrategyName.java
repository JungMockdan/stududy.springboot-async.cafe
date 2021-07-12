package com.jmd.cafe.order.domain.strategy;

public enum StrategyName {
    USE_CASE_A, USE_CASE_B;

    public static StrategyName evaluateConditionOfStrategy(Conditions conditions) {
        // condition 에 따른 evaluations 일단 default로 USE_CASE_A를 리턴한다.
        return StrategyName.USE_CASE_A;
    }
}
