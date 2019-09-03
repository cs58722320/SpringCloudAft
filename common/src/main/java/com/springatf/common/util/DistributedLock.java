package com.springatf.common.util;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public interface DistributedLock {

    /**
     * 获取分布式锁
     */
    public void getDistributedLock();

    /**
     * 释放分布式锁
     */
    public void releaseDistributedLock();

}
