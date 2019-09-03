package com.springatf.common.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 名称：时间操作类<br>
 * 描述：提供了各种常用的常用的时间类型获取<br>
 *      以及常用的时间转换处理<br>
 *      该工具类全部采用JDK1.8新特性LocalDate派生类进行编写
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public class DateUtil {

    public static Long nowMilli() {
        return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public static void main(String[] args) {
        System.out.println(nowMilli());
    }
}
