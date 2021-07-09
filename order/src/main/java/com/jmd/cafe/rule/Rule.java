package com.jmd.cafe.rule;

public interface Rule {
    boolean evaluate(Expression expression);
    Result getResult();
}
