package com.springatf.zuulgateway.filter.accessstrategy;

import com.springaft.common.constant.AccessConstant;

/**
 * 名称：接口访问控制上下文<br>
 * 描述：接口访问控制上下文<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public class AccessContext implements AccessStrategy {

    private AccessStrategy strategy;

    /**
     * 构造策略
     *
     * @param strategyMode 所使用策略
     */
    @SuppressWarnings("DuplicateBranchesInSwitch")
    public AccessContext(String strategyMode) {
        switch (strategyMode) {
            case AccessConstant.WHITE_MODE:
                this.strategy = new AccessWhite();
                break;
            case AccessConstant.BLACK_MODE:
                this.strategy = new AccessBlack();
                break;
            case AccessConstant.MIXED_MODE:
                this.strategy = new AccessMixed();
                break;
            default:
                this.strategy = new AccessWhite();
        }
    }


    @Override
    public boolean isAvailable(boolean isWhite, boolean isBlack) {
        return this.strategy.isAvailable(isWhite, isBlack);
    }

    /**
     * 获取访问控制策略
     * @return
     */
    public String getAccessMode() {
        if (this.strategy instanceof AccessWhite) {
           return "白名单访问模式";
        }
        if (this.strategy instanceof AccessBlack) {
            return "黑名单访问模式";
        }
        if (this.strategy instanceof AccessMixed) {
            return "混合访问模式";
        }
        return "";
    }
}
