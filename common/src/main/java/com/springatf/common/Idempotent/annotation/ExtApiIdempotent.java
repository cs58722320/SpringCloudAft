package com.springatf.common.Idempotent.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 名称：接口幂等注释<br>
 * 描述：在需要实现接口幂等的api上，如果标注此注解则可以<br>
 * 通过aop的方式实现aop的幂等功能<br>
 * 具体方式由服务内部自己实现<br>
 * 可以继承：DefaultIdempotentAop抽象类<br>
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */

@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtApiIdempotent {
    String type();
}
