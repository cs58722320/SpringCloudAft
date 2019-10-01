package com.springaft.common.Idempotent.aop;

import com.springaft.common.Idempotent.IdempotentHandler;
import com.springaft.common.Idempotent.annotation.ExtApiIdempotent;
import com.springaft.common.Idempotent.annotation.ExtApiToken;

import com.springaft.common.constant.IdepotentConstant;
import com.springaft.common.constant.ResultConstant;
import com.springaft.common.respbase.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public abstract class DefaultIdempotentAop {

    /**
     * 令牌获取点
     */
    private final static String EXTAPI_HEAD = "head";


    /**
     * 环绕切点定义：此处需要定义切点表达式
     */
    public abstract void idempotentAop();

    /**
     * 前置通知：
     * 用于表单获取token
     * 从redis中生成token，并存放于request的属性中
     * @param point
     */
    @Before("idempotentAop()")
    public void before(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        ExtApiToken extApiToken = signature.getMethod()
                .getDeclaredAnnotation(ExtApiToken.class);
        if (extApiToken != null) {
            getRequest().setAttribute("token", IdempotentHandler.getToken());
        }
    }

    /**
     * 环绕通知：
     * 如果是接口幂等则从请求头获取令牌
     * 如果是表单幂等则从请求参数中获取令牌
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("idempotentAop()")
    public Object doBefore(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 判断方法上是否有加ExtApiIdempotent
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        ExtApiIdempotent declaredAnnotation = methodSignature.getMethod().getDeclaredAnnotation(ExtApiIdempotent.class);
        // 如何方法上有加上ExtApiIdempotent
        if (declaredAnnotation != null) {
            String type = declaredAnnotation.type();
            // 如何使用Token 解决幂等性
            // 步骤：
            String token = null;
            HttpServletRequest request = getRequest();
            if (type.equals(IdepotentConstant.EXT_API_HEAD)) {
                token = request.getHeader("token");
            } else if(type.equals(IdepotentConstant.EXT_API_PARAM)){
                token = request.getParameter("token");
                if(StringUtils.isEmpty(token)){
                    request.getAttribute("token");
                }
            }

            if (StringUtils.isEmpty(token)) {
                response(ResponseResult.builder().msg("幂等参数错误，token为赋值！").rtnCode(ResultConstant.HTTP_RES_CODE_IDEMPOTENT_ERROR).build());
                return null;
            }
            // 接口获取对应的令牌,
            // 如果能够获取该(从redis获取令牌)令牌(将当前令牌删除掉)
            // 就直接执行该访问的业务逻辑
            boolean isDup = IdempotentHandler.isDuplicate(token);
            // 4.接口获取对应的令牌,如果获取不到该令牌 直接返回请勿重复提交
            if (isDup) {
                response(ResponseResult.builder().msg("请勿重复提交！").rtnCode(ResultConstant.HTTP_RES_CODE_IDEMPOTENT_ERROR).build());
                // 后面方法不在继续执行
                return null;
            }
        }
        // 放行
        Object proceed = proceedingJoinPoint.proceed();
        return proceed;
    }

    public abstract HttpServletRequest getRequest();

    public abstract void response(ResponseResult resp) throws IOException;
}
