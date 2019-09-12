/**
 * Copyright (C), 2015-2019
 * FileName: RouteConfig
 * Author:   JeffDu
 * Date:     2019/9/11 20:12
 * Description: 网管路由配置
 * History:
 * <author>          <time>          <version>          <desc>
 */
package com.springaft.springcloudgate.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 网管路由配置<br>
 *
 * @author JeffDu
 * @create 2019/9/11
 * @since 1.0.0
 */
@Component
public class RouteConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("memberservice",
                        r -> r.path("/member-api/**").filters(f -> f.stripPrefix(1))
                                .uri("lb://member-service"))
                .route(p -> p.host("localhost").and().path("/member-api/member/getMemberInfo2")
                        .filters(f -> f.hystrix(config -> config.setName("mycmd")
                                .setFallbackUri("forward:/member-api/member/getMemberInfo")))
                                .uri("lb://member-service"))

//                .route("", )
                .build();
    }

}