package com.jmd.cafe.order.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {
    private String result;
}
