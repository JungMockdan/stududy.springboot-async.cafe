package com.jmd.cafe.order.conf.feign;


import feign.Logger;
import feign.RequestInterceptor;
import feign.Retryer;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;

public class EventServerFiegnConfiguration {
    /**
     * feign 로그레벨설정
     * */
    @Bean
    Logger.Level feignLogLevel(){
        return Logger.Level.FULL;
    }

    /**
     * requestHeader 공통부분 세팅가능
     * */
    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate -> {
            requestTemplate.query("default-value","haha");
            requestTemplate.query("event-specific-value","hahaha");
        };
    }

    /**
    * Get요청 시 날짜에 대한 부분 인코딩관련 해결
    * */
    @Bean
    public FeignFormatterRegistrar localDataFormatterRegistrar(){
        return formatterRegistry -> {
            DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
            registrar.setUseIsoFormat(true);
            registrar.registerFormatters(formatterRegistry);
        };
    }

    /**
     * Retry 설정 : default 설정을 사용하게 됨.
     * */
    @Bean
    public Retryer retryer(){
        return new Retryer.Default();
    }
}
