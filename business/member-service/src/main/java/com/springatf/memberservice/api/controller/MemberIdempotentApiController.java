package com.springatf.memberservice.api.controller;

import com.springatf.common.Idempotent.IdempotentHandler;
import com.springatf.common.respbase.BaseApiService;
import com.springatf.common.respbase.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 名称：会员服务幂等令牌相关接口<br>
 * 描述：获取幂等令牌，用于幂等提交<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Api("会员幂等接口")
@RestController
@RequestMapping("/idempotent")
public class MemberIdempotentApiController extends BaseApiService {

    /**
     * 获取幂等令牌
     * @return
     */
    @ApiOperation("会员幂等令牌获取")
    @RequestMapping(path = "/getIdempotentToken", method = RequestMethod.GET)
    public ResponseResult<String> getIdempotentToken() {
        return buildSuccessWithData(IdempotentHandler.getToken());
    }

}
