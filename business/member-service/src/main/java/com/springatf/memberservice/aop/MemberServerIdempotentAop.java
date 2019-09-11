package com.springatf.memberservice.aop;

import com.alibaba.fastjson.JSON;
import com.springatf.common.Idempotent.aop.DefaultIdempotentAop;
import com.springatf.common.respbase.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Slf4j
@Aspect
@Component
public class MemberServerIdempotentAop extends DefaultIdempotentAop {
    @Override
    @Pointcut("execution(public * com.springatf.memberservice.api.controller.*.*(..))")
    public void idempotentAop() {
    }

    @Override
    public HttpServletRequest getRequest() {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request;
    }

    @Override
    public void response(ResponseResult result) throws IOException {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        response.setHeader("Content-type", MimeTypeUtils.APPLICATION_JSON_VALUE.concat(";charset=UTF-8"));
        PrintWriter writer = response.getWriter();
        try {
            JSON.toJSONString(result);
            writer.println(result);
        } catch (Exception e) {
            log.error("打印幂等信息失败", e);
        } finally {
            writer.close();
        }

    }
}
