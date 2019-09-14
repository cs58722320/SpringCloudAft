package com.springatf.zuulgateway.filter.accessstrategy;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public class AccessMixed implements AccessStrategy {
    @Override
    public boolean isAvailable(boolean isWhite, boolean isBlack) {
        if (isWhite) {
            return true;
        } else if (!isBlack) {
            return true;
        }


        return false;
    }
}
