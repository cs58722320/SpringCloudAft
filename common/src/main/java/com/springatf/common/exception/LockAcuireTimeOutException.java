package com.springatf.common.exception;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public class LockAcuireTimeOutException extends Exception {
    /**
     * 无参构造函数
     */
    public LockAcuireTimeOutException(){
        super("获取分布式锁超时");
    }

    /**
     * 设定详细信息构造函数
     * @param message
     */
    public LockAcuireTimeOutException(String message){
        super();
    }

    /**
     * 设定详细信息并且将异常传入构造函数
     * @param message
     * @param cause
     */
    public LockAcuireTimeOutException(String message, Throwable cause){
        super(message,cause);
    }

    /**
     * 将异常传入构造函数
     * @param cause
     */
    public LockAcuireTimeOutException(Throwable cause) {
        super(cause);
    }
}
