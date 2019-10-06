package com.springatf.zuulgateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.springaft.common.util.RequestUtil;
import com.springatf.zuulgateway.filter.accessstrategy.AccessContext;
import com.springatf.zuulgateway.properties.AccessControlBlackListProperties;
import com.springatf.zuulgateway.properties.AccessControlWhiteListProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 名称：第三方接口过滤去<br>
 * 描述：添加第三方接口黑白名单访问控制<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Slf4j
@Component
public class ThirdInterfaceFilter extends ZuulFilter{

    @Value("${zuul.filter.third.paths}")
    List<String> ignorePaths;

    @Value("${zuul.accessmode}")
    String accessMode;

    /**
     * 访问控制上下文
     */
    AccessContext accessContext;

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
     * 过滤器初始化
     * 初始化访问控制上下文
     */
    @PostConstruct
    public void filterInit() {
        accessContext = new AccessContext(accessMode);
    }


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
        List paths = ignorePaths.parallelStream().filter(path -> uri.startsWith(path)).collect(Collectors.toList());
        // 不拦截Swagger访问网址
        return paths.size() != 0 && !uri.contains("/v2/api-docs");
    }

    @Override
    public Object run() {
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
            isWhite = true;
        }
        // 当前ip是否包含在黑名单中
        if (accessControlBlackListProperties.getHosts().parallelStream()
                .filter(host -> currentIp.equals(host)).findFirst().isPresent()) {
            isBlack = true;
        }
        // 判断当前访问权限
        if (!accessContext.isAvailable(isWhite, isBlack)) {
            log.warn("{}，访问模式下获取访问权限失败", accessContext.getAccessMode());
            // 不会继续执行，不回去调用服务接口，网关服务直接相应给客户端
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(405);
            currentContext.setResponseBody("can not get access control");
        }

        return null;
    }


}
