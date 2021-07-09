package com.jmd.cafe.rule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Expression {
    private Integer x;
    private Integer y;
    private Operator operator;
}
