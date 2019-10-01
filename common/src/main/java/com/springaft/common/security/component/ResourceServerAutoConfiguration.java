package com.springaft.common.security.component;

import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * 名称  初始化资源服务器的配置<br>
 * 描述：lbRestTemplate使用LoadBalanced注解示器支持微服务负载均衡，不走网关直接连接认证中心<br>
 *      <p>customizeTokenRemoteServices其作用是调用TokenCheck端点 @link CheckTokenEndpoint </p>
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Data
@Configuration
@ComponentScan("com.springaft.common.security")
@ConfigurationProperties(prefix = "spring.security.oauth2.resource")
public class ResourceServerAutoConfiguration {
    @Bean
    @Primary
    @LoadBalanced
    public RestTemplate lbRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            @SneakyThrows
            public void handleError(ClientHttpResponse response) {
                if (response.getRawStatusCode() != HttpStatus.BAD_REQUEST.value()) {
                    super.handleError(response);
                }
            }
        });
        return restTemplate;
    }

    /**
     * checkToken Endpoints Url
     * @link CheckTokenEndpoint
     */
    private String tokenInfoUrl;

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 客户端密钥
     */
    private String clientSecret;

    @Bean
    public RemoteTokenServices customizeTokenRemoteServices() {
        RemoteTokenServices customizeTokenRemoteServices = new RemoteTokenServices();
        customizeTokenRemoteServices.setCheckTokenEndpointUrl(tokenInfoUrl);
        customizeTokenRemoteServices.setClientId(clientId);
        customizeTokenRemoteServices.setClientSecret(clientSecret);
        return customizeTokenRemoteServices;
    }

}
