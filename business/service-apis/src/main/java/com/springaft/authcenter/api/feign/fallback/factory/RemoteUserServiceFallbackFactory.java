package com.springaft.authcenter.api.feign.fallback.factory;

import com.springaft.authcenter.api.feign.RemoteUserService;
import com.springaft.authcenter.api.feign.fallback.RemoteUserServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 名称：系统用户查询降级处理<br>
 * 描述：系统用户查询降级处理<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Slf4j
@Component
public class RemoteUserServiceFallbackFactory implements FallbackFactory<RemoteUserService> {

    /**
     * 实例化UserFallBack对象
     * @param throwable
     * @return
     */
    @Override
    public RemoteUserService create(Throwable throwable) {
        RemoteUserServiceFallbackImpl remoteUserServiceFallback = new RemoteUserServiceFallbackImpl();
        remoteUserServiceFallback.setCause(throwable);
        return remoteUserServiceFallback;
    }
}
