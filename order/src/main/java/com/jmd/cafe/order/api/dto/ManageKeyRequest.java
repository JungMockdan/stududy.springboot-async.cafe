package com.jmd.cafe.order.api.dto;

import lombok.Data;

@Data
public class ManageKeyRequest {
    private String id;
    private int totalAmount;
}
