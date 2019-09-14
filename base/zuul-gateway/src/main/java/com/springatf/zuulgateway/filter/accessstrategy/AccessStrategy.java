package com.springatf.zuulgateway.filter.accessstrategy;

/**
 * 名称：访问控制接口<br>
 * 描述：访问控制接口<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public interface AccessStrategy {

    /**
     * 判断是可访问
     * @return boolean true为可访问
     */
    public boolean isAvailable(boolean isWhite, boolean isBlack);

}
