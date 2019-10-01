package com.springaft.common.exception;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public class RedisPoolInitException extends Exception {
    /**
     * 无参构造函数
     */
    public RedisPoolInitException(){
        super();
    }

    /**
     * 设定详细信息构造函数
     * @param message
     */
    public RedisPoolInitException(String message){
        super(message);
    }

    /**
     * 设定详细信息并且将异常传入构造函数
     * @param message
     * @param cause
     */
    public RedisPoolInitException(String message, Throwable cause){
        super(message,cause);
    }

    /**
     * 将异常传入构造函数
     * @param cause
     */
    public RedisPoolInitException(Throwable cause) {
        super(cause);
    }
}
