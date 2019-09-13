package com.springatf.zuulgateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * 名称：全局异常处理过滤器<br>
 * 描述：接受全局的异常处理，并将捕捉的错误设置到context中<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Slf4j
@Component
public class GlobalErrorFilter extends ZuulFilter {

    /**
     * 过滤器类型：ERROR
     * @return
     */
    @Override
    public String filterType() {
        return "error";
    }


    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // 这里处理的核心就是将错误信息设置到RequestContext中
        RequestContext context = RequestContext.getCurrentContext();
        Throwable throwable = context.getThrowable();
        log.error("[ErrorFilter] error message: {}",
                throwable.getCause().getMessage());
        // 将捕捉的错误设置到context中
        context.set("error.status_code",
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        context.set("error.exception", throwable.getCause());
        return null;
    }
}
