package com.jmd.cafe.order.domain.strategy;

import com.jmd.cafe.rule.Operator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Conditions {
    private String condition1;
    private String condition2;
    private String condition3;
}
