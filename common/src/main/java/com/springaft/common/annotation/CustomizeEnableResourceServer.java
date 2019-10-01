package com.springaft.common.annotation;

import com.springaft.common.security.component.CustomizeSecurityBeanDefinitionRegistrar;
import com.springaft.common.security.component.ResourceServerAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.*;

/**
 * 名称：资源服务配置注解<br>
 * 描述：资源服务配置注解<br>
 *     包含引入了两个配置文件<p/>
 *     【ResourceServerAutoConfiguration】是用作扫描通用底下的资源服务相关包
 *     TODO：现在负载均衡组件暂时有点问题先启用
 *     【CustomizeSecurityBeanDefinitionRegistrar】是用作注册资源服务器适配<p/>
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Documented
@Inherited
@EnableResourceServer
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({ResourceServerAutoConfiguration.class, CustomizeSecurityBeanDefinitionRegistrar.class})
public @interface CustomizeEnableResourceServer {
}
