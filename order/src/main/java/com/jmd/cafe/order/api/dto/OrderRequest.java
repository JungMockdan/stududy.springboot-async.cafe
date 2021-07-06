package com.jmd.cafe.order.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private String id;
    private int totalAmount;
}
