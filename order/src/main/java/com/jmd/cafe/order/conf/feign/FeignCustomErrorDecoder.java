package com.jmd.cafe.order.conf.feign;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

/**
 * feign client 응답 오류 공통 처리 및 Exception handling
 */
@Slf4j
public class FeignCustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        log.info("%s 요청이 성고항지 못했습니다. status : %s, body : %s",methodKey,response.status(),response.body());
        switch ((response.status())){
            case 400:
                return new Exception("400Error");// TODO: 2021-07-01 디테일한 커스텀 익셉션 정의
            case 404:
                return new Exception("404Error");
            default:
                return new Exception("Error");
        }
    }

    @Bean
    public ErrorDecoder errorDecoder(){
        return new FeignCustomErrorDecoder();
    }
}
