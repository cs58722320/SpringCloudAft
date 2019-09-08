package com.springatf.common.constant;

/**
 * 名称：返回结果常量<br>
 * 描述：返回结果常量<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public class ResultConstant {

    /**
     * 响应请求成功
     */
    public final static String HTTP_RES_CODE_200_VALUE = "success";
    /**
     * 系统错误
     */
    public final static String HTTP_RES_CODE_500_VALUE = "fail";

    /**
     * 响应请求成功Code
     */
    public final static Integer HTTP_RES_CODE_200 = 200;
    /**
     * 系统错误Code
     */
    public final static Integer HTTP_RES_CODE_500 = 500;
    /**
     * 接口幂等错误
     */
    public final static Integer HTTP_RES_CODE_IDEMPOTENT_ERROR = 40120;
}
