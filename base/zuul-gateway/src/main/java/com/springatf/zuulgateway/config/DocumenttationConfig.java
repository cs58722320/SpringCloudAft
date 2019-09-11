package com.springatf.zuulgateway.config;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Primary
@Component
public class DocumenttationConfig implements SwaggerResourcesProvider {
    /**
     * 开启Swagger2文档
     * @return
     */
    @Override
    public List<SwaggerResource> get() {
        List resources= new ArrayList<>();
        resources.add(swaggerResource("会员服务接口", "/member-api/v2/api-docs", "v1.0"));
        return resources;
    }

    /**
     * 添加swagger文档资源
     * @param name 文档名称
     * @param location 文档地址
     * @param version 文档版本
     * @return
     */
    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
