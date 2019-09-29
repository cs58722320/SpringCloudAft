package com.springatf.memberservice;

import com.springatf.common.redis.RedisPoolHolder;
import com.springatf.common.exception.RedisPoolInitException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker // 开启断路器
@SpringBootApplication
public class MemberServiceApplication {

    public static void main(String[] args) throws RedisPoolInitException {
        RedisPoolHolder.initConfig("192.168.1.11", 6379);
        SpringApplication.run(MemberServiceApplication.class, args);
    }

}