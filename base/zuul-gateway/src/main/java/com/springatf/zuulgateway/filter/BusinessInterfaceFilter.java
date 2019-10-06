package com.springatf.zuulgateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.springatf.zuulgateway.properties.AccessControlBlackListProperties;
import com.springatf.zuulgateway.properties.AccessControlWhiteListProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

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
public class BusinessInterfaceFilter extends ZuulFilter {

    /**
     * 第三方接口列表
     */
    @Value("${zuul.filter.third.paths}")
    List<String> ignorePaths = null;

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
        // 获取上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        String uri = currentContext.getRequest().getRequestURI();
        List paths = ignorePaths.parallelStream().filter(path -> uri.startsWith(path)).collect(Collectors.toList());
        // 不拦截Swagger访问网址
        return paths.size() == 0 && !uri.contains("/v2/api-docs");
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
        System.out.println("----" + request.getServletPath());
        System.out.println("----" + request.getContextPath());
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
