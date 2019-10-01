package com.springaft.common.annotation;

import java.lang.annotation.*;

/**
 * 名称：服务调用不鉴权注解<br>
 * 描述：服务调用不鉴权注解<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Inner {
    /**
     * 是否AOP统一处理
     *
     * @return false, true
     */
    boolean value() default true;

    /**
     * 需要特殊判空的字段(预留)
     *
     * @return {}
     */
    String[] field() default {};
}
