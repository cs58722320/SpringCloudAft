package com.springatf.zuulgateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.springatf.common.util.RequestUtil;
import com.springatf.zuulgateway.properties.AccessControlBlackListProperties;
import com.springatf.zuulgateway.properties.AccessControlWhiteListProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Slf4j
public class ThirdInterfaceFilter extends ZuulFilter{

    @Value("${zuul.filter.third.paths}")
    List<String> ignorePaths = null;

    /**
     * 访问控制白名单
     * type：ArrayList
     */
    @Autowired
    private AccessControlWhiteListProperties accessControlWhiteListProperties;

    /**
     * 访问控制黑名单
     * type: ArrayList
     */
    @Autowired
    private AccessControlBlackListProperties accessControlBlackListProperties;

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
        return 1;
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
        ignorePaths.parallelStream().filter(path -> uri.startsWith(""));
        return ignorePaths.size() != 0;
    }

    @Override
    public Object run() throws ZuulException {
        // 获取用户ip
        RequestContext currentContext = RequestContext.getCurrentContext();
        String currentIp = RequestUtil.getIpAddress(currentContext.getRequest());

        if (ObjectUtils.isEmpty(currentIp)) {
            log.warn("ip地址获取为空，不能放行");
            // 不会继续执行，不回去调用服务接口，网关服务直接相应给客户端
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(405);
            currentContext.setResponseBody("Customer IP can not be empty");
        }

        boolean isWhite = false;
        boolean isBlack = false;
        // 当前ip是否包含在白名单中
        if (accessControlWhiteListProperties.getHosts().parallelStream()
                .filter(host -> currentIp.equals(host)).findFirst().isPresent()) {
            return isWhite = true;
        }
        // 当前ip是否包含在黑名单中
        if (accessControlBlackListProperties.getHosts().parallelStream()
                .filter(host -> currentIp.equals(host)).findFirst().isPresent()) {
            return isBlack = true;
        }



        return null;
    }


}
