package com.jmd.cafe.rule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RuleEngineTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void process() {
        Expression expression = new Expression(5, 5, Operator.ADD);
        RuleEngine engine = new RuleEngine();
        Result result = engine.process(expression);

        assertNotNull(result);
        assertEquals(10, result.getValue());
    }
}