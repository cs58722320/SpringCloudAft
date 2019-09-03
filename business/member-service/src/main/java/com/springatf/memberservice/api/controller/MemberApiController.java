package com.springatf.memberservice.api.controller;

import com.springatf.domain.MemberDto;
import com.springatf.memberservice.api.vo.MemberVo;
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
public class MemberApiController {

    @RequestMapping(path = "getMemberInfo", method = RequestMethod.GET)
    public MemberDto getMemberInfo(){
        MemberDto result = new MemberDto();
        result.setMemberName("JeffDu");
        return result;
    }

}
