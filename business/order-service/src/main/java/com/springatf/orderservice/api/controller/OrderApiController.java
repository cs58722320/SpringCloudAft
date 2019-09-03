package com.springatf.orderservice.api.controller;

import com.springatf.domain.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/order")
public class OrderApiController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(path = "/getMemberInfo", method = RequestMethod.GET)
    public void getMemberInfo(){
        MemberDto memberDto = restTemplate.getForObject("http://member-service/member/getMemberInfo", MemberDto.class);
        System.out.println(memberDto);
    }


}
