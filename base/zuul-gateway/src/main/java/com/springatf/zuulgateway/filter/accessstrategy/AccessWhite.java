package com.springatf.zuulgateway.filter.accessstrategy;

/**
 * 名称：白名单模式<br>
 * 描述：白名单模式值匹配请求IP是否存在于白名单列表中，如果有则放行，如果没有则拦截<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public class AccessWhite implements AccessStrategy {
    @Override
    public boolean isAvailable(boolean isWhite, boolean isBlack) {
        return isWhite;
    }
}
