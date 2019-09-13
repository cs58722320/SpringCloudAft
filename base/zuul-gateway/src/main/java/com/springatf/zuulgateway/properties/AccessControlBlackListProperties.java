package com.springatf.zuulgateway.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 名称：第三方接口读取访问控制<br>
 * 描述：黑名单访问接口<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "zuul.blacklist")
public class AccessControlBlackListProperties {
    /**
     * 远程访问ip
     */
    List<String> hosts;
}
