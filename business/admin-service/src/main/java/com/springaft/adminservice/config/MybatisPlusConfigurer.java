package com.springaft.adminservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Configuration
@MapperScan("com.springaft.adminservice.mapper")
public class MybatisPlusConfigurer {
}
