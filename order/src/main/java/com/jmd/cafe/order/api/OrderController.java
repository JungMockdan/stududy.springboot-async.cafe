package com.jmd.cafe.order.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.jmd.cafe.order.api.dto.OrderRequest;
import com.jmd.cafe.order.api.dto.OrderResponse;
import com.jmd.cafe.order.service.OrderService;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;
    @PostMapping("/order")
    public ResponseEntity<OrderResponse> order(@RequestBody OrderRequest orderRequest){
        OrderResponse response = orderService.order(orderRequest);
        return ResponseEntity.ok(response);
    }
}
