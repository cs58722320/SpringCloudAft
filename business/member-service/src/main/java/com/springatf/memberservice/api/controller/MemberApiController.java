package com.springatf.memberservice.api.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springaft.common.domain.MemberDto;
import com.springaft.common.domain.OrderDto;
import com.springaft.common.respbase.BaseApiService;
import com.springaft.common.respbase.ResponseResult;
import com.springatf.memberservice.rpc.OrderApiFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Autowired
    OrderApiFeign orderApiFeign;


    @ApiOperation("获取会员相关信息_需要调用幂等令牌")
    @PreAuthorize("@pms.hasPermission('sys_user_edit')")
    @RequestMapping(path = "/getMemberInfo", method = RequestMethod.GET)
//    @ExtApiIdempotent(type= IdepotentConstant.EXT_API_HEAD)
    public ResponseResult getMemberInfo() {
        MemberDto result = new MemberDto();
        result.setMemberName("JeffDu");
        return buildSuccessWithData(result);
    }

    @RequestMapping(path = "/getMemberOrderInfo")
    public ResponseResult<String> getMemberOrderInfo() {
        ResponseResult<String> result = orderApiFeign.getOrderInfo();
        return result;
    }

    /**
     * fallbackMethod 方法的作用：服务降级执行
     * @HystrixCommand 默认开启线程隔离方式，服务降级，服务熔断(默认是10个线程)
     * @return
     */
    @HystrixCommand(fallbackMethod="getMemberOrderInfoHystrixFallback")
    @RequestMapping(path = "/getMemberOrderInfoHystrix")
    public ResponseResult<String> getMemberOrderInfoHystrix() throws InterruptedException {
        System.out.println("getMemberOrderInfoHystrix:" + "线程池名称：" + Thread.currentThread().getName());
        ResponseResult<String> result = orderApiFeign.getOrderInfo();
        return result;
    }


    public ResponseResult<String> getMemberOrderInfoHystrixFallback() {
        return buildSuccessWithData("abc");
    }


    @RequestMapping(path = "/addMemberOrder", method = RequestMethod.POST)
    public ResponseResult<String> getMemberOrderInfo(@RequestBody OrderDto orderDto) {
        ResponseResult<String> result = orderApiFeign.addOrderInfo(orderDto);
        return result;
    }

}
