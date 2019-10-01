package com.springatf.memberservice.rpc;

import com.springaft.common.domain.OrderDto;
import com.springaft.common.respbase.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 名称：调用订单服务<br>
 * 描述：调用订单服务<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@FeignClient("order-service")
@RequestMapping("/order")
public interface OrderApiFeign {

    @RequestMapping(path = "/getOrderInfo", method = RequestMethod.GET)
    ResponseResult<String> getOrderInfo();

    @RequestMapping(path = "/addOrderInfo", method = RequestMethod.GET)
    ResponseResult<String> addOrderInfo(OrderDto orderDto);
}
