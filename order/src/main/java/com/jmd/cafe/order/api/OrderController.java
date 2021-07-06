package com.jmd.cafe.order.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.jmd.cafe.order.api.dto.OrderRequest;
import com.jmd.cafe.order.api.dto.OrderResponse;
import com.jmd.cafe.order.service.OrderService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;
    @PostMapping("/order")
    public ResponseEntity<OrderResponse> order(@RequestBody OrderRequest orderRequest){
        log.debug("1.order api start");
        OrderResponse response = orderService.order(orderRequest);
        log.debug("6.order api end");
        return ResponseEntity.ok(response);
    }
}
