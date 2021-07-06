package com.jmd.cafe.event.api;

import com.jmd.cafe.event.api.dto.EventRequest;
import com.jmd.cafe.event.api.dto.EventResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EventController {
    @PostMapping("/event")
    public ResponseEntity<EventResponse> event(@RequestBody EventRequest orderRequest){
        EventResponse response = EventResponse.builder().result("success").build();
        log.debug("event - "+response.toString());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/event2")
    public ResponseEntity<EventResponse> event2(@RequestBody EventRequest orderRequest){
        EventResponse response = EventResponse.builder().result("success").build();
        log.debug("event2 - "+response.toString());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/price")
    public ResponseEntity<EventResponse> price(@RequestBody EventRequest orderRequest){
        EventResponse response = EventResponse.builder().result("4000").build();
        log.debug("price - "+response.toString());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/price2")
    public ResponseEntity<EventResponse> price2(@RequestBody EventRequest orderRequest){
        EventResponse response = EventResponse.builder().result("5000").build();
        log.debug("price2 - "+response.toString());
        return ResponseEntity.ok(response);
    }

}
