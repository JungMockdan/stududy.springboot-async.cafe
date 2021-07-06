package com.jmd.cafe.event.api;

import com.jmd.cafe.event.api.dto.EventRequest;
import com.jmd.cafe.event.api.dto.EventResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {
    @PostMapping("/event")
    public ResponseEntity<EventResponse> event(@RequestBody EventRequest orderRequest){
        EventResponse response = EventResponse.builder().result("success").build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/event2")
    public ResponseEntity<EventResponse> event2(@RequestBody EventRequest orderRequest){
        EventResponse response = EventResponse.builder().result("success").build();
        return ResponseEntity.ok(response);
    }

}
