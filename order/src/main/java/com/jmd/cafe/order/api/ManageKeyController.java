package com.jmd.cafe.order.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.jmd.cafe.order.api.dto.ManageKeyRequest;
import com.jmd.cafe.order.api.dto.ManagekeyResponse;
import com.jmd.cafe.order.service.ManageKeyService;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ManageKeyController {

    private final ManageKeyService manageKeyService;
    @PostMapping("/order")
    public ResponseEntity<ManagekeyResponse> order(@RequestBody ManageKeyRequest manageKeyRequest){
        log.debug(LocalDateTime.now()+" 1.order api start");
        long startTime=System.nanoTime();

        ManagekeyResponse response = manageKeyService.doSomething(manageKeyRequest);

        log.debug(LocalDateTime.now()+" 6.order api end");
        long takeTime= System.nanoTime()-startTime;
        log.debug("It takes "+takeTime/1000000000.0 +"ms");
        return ResponseEntity.ok(response);
    }
}
