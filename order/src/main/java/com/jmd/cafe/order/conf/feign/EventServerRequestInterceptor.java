package com.jmd.cafe.order.conf.feign;

import feign.RequestTemplate;

public interface EventServerRequestInterceptor {
    void apply(RequestTemplate template);
}
