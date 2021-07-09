package com.jmd.cafe.rule;

import java.util.ArrayList;
import java.util.List;

public class RuleEngine {
    private static List<Rule> rules = new ArrayList<>();

    static {
        rules.add(new AddRule());
//        .다른 룰
//        .
//        .
//        .
    }

    public Result process(Expression expression) {
        Rule rule = getRule(expression);
        return rule.getResult();
    }

    public Rule getRule(Expression expression) {
        return rules
                .stream()
                .filter(r -> r.evaluate(expression))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Expression does not matches any Rule"));
    }


}
