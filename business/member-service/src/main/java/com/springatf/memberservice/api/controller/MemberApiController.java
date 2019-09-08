package com.springatf.memberservice.api.controller;

import com.springatf.common.Idempotent.annotation.ExtApiIdempotent;
import com.springatf.common.constant.IdepotentConstant;
import com.springatf.common.domain.MemberDto;
import com.springatf.common.respbase.BaseApiService;
import com.springatf.common.respbase.ResponseResult;
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
@RestController
@RequestMapping("/member")
public class MemberApiController extends BaseApiService {

    @RequestMapping(path = "getMemberInfo", method = RequestMethod.GET)
    @ExtApiIdempotent(type= IdepotentConstant.EXT_API_HEAD)
    public ResponseResult getMemberInfo(){
        MemberDto result = new MemberDto();
        result.setMemberName("JeffDu");
        return buildSuccessWithData(result);
    }

}
