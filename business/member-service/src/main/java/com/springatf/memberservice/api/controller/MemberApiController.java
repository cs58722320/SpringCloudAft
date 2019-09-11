package com.springatf.memberservice.api.controller;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpMethods;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.springatf.common.Idempotent.annotation.ExtApiIdempotent;
import com.springatf.common.constant.IdepotentConstant;
import com.springatf.common.domain.MemberDto;
import com.springatf.common.respbase.BaseApiService;
import com.springatf.common.respbase.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Api("会员服务接口")
@RestController
@RequestMapping("/member")
public class MemberApiController extends BaseApiService {

    @ApiOperation("获取会员相关信息_需要调用幂等令牌")
    @RequestMapping(path = "getMemberInfo", method = RequestMethod.GET)
    @ExtApiIdempotent(type= IdepotentConstant.EXT_API_HEAD)
    public ResponseResult getMemberInfo(){
        MemberDto result = new MemberDto();
        result.setMemberName("JeffDu");
        return buildSuccessWithData(result);
    }
}
