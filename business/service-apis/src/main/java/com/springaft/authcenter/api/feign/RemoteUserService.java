package com.springaft.authcenter.api.feign;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */

import com.springaft.admin.api.dto.UserInfo;
import com.springaft.authcenter.api.feign.fallback.factory.RemoteUserServiceFallbackFactory;
import com.springaft.common.constant.ServiceNameConstants;
import com.springaft.common.respbase.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.ADMIN_SERVICE, fallbackFactory = RemoteUserServiceFallbackFactory.class)
public interface RemoteUserService {

    /**
     * 通过用户名查询用户、角色信息
     * feignCallBack调用需要将URLMap方在方法中
     * @param username 用户名
     * @return R
     */
    @RequestMapping(path = "/user/info/{username}", method = RequestMethod.GET)
    ResponseResult<UserInfo> info(@PathVariable("username") String username);

}
