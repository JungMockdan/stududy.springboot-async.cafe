package com.jmd.cafe.order.fiegn;

import com.jmd.cafe.order.conf.feign.EventServerFiegnConfiguration;
import com.jmd.cafe.order.fiegn.dto.EventRequest;
import com.jmd.cafe.order.fiegn.dto.EventResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "${feign.server.event.name}", url = "${feign.server.event.url}")
public interface EventServerCallerFeign {

    @PostMapping("/event")
    EventResponse event(@RequestBody EventRequest eventRequest);
}
