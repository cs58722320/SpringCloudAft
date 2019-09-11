package com.springatf.zuulgateway.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.springatf.common.constant.ResultConstant;
import com.springatf.common.respbase.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.protocol.ResponseServer;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.ObjectUtils;
import sun.security.provider.certpath.OCSPResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Slf4j
@Component
public class SampleFilter extends ZuulFilter {
    /**
     * 过滤器的类型
     * @return
     * @throws ZuulException
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器执行顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断过滤器是否生效
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return false;
    }

    /**
     * 过滤器业务代码
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        // 获取上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        // 获取request
        HttpServletRequest request = currentContext.getRequest();
        // 获取token的时候从请求参数中获取
        String userToken = request.getParameter("userToken");
        if (ObjectUtils.isEmpty(userToken)) {
            // 不会继续执行，不回去调用服务接口，网关服务直接相应给客户端
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(405);
            currentContext.setResponseBody("userToken is null");
        }
        return null;
    }
}
