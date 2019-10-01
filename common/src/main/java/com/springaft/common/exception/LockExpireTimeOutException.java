package com.springaft.common.exception;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public class LockExpireTimeOutException extends Exception{
    /**
     * 无参构造函数
     */
    public LockExpireTimeOutException(){
        super("获取分布式锁超时");
    }

    /**
     * 设定详细信息构造函数
     * @param message
     */
    public LockExpireTimeOutException(String message){
        super();
    }

    /**
     * 设定详细信息并且将异常传入构造函数
     * @param message
     * @param cause
     */
    public LockExpireTimeOutException(String message, Throwable cause){
        super(message,cause);
    }

    /**
     * 将异常传入构造函数
     * @param cause
     */
    public LockExpireTimeOutException(Throwable cause) {
        super(cause);
    }
}
