package com.springaft.common.redis.distribute;

import com.springaft.common.redis.RedisPoolHolder;
import com.springaft.common.exception.DistributeInitException;
import com.springaft.common.exception.LockAcuireTimeOutException;
import com.springaft.common.exception.LockExpireTimeOutException;
import com.springaft.common.exception.RedisPoolInitException;
import com.springaft.common.util.DateUtil;
import com.springaft.common.util.DistributedLock;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Slf4j
public final class RedisDistributedLock implements DistributedLock {
    /**
     * redis线程池声明
     */
    private static JedisPool jedisPool;
    /**
     * redis分布式锁key目录
     */
    private String redisLockDir = "distribute:redisKey";
    /**
     * 获取锁之前的超时时间(ms)
     * default:6000ms
     */
    private static Long acquireTimeout = 30000L;
    /**
     * 获取锁之后的超时时间(ms)
     * default:6000ms
     */
    private static Long timeOut = 6000L;
    /**
     * 初始化配置标示
     */
    private static boolean hasConfig = false;
    /**
     * config锁
     */
    private static ReentrantLock configLock = new ReentrantLock(true);


    /**
     * 插入锁成功标志位
     */
    private static final int GET_LOCK_SUCCESS = 1;

    @Override
    @Deprecated
    public void getDistributedLock() {

    }

    @Override
    @Deprecated
    public void releaseDistributedLock() {

    }

    /**
     * 获取分布式锁redis实现
     * 1. 建立redis连接
     * 2. 定义redis对应key的value（uuid）
     * 3. 定义在获取锁之前的超时时间
     * 4. 在获取锁之后的超时时间
     * 5. 使用循环机制保证重复进行尝试获取锁（乐观锁）
     * 6. 使用setnx命令插入对应的redisLockKey，如果返回1则成功获取锁
     * 定义两个超时时间含义：
     * 1. 在获取锁之前的超时时间----在尝试获取锁的时间，如果在规定的时间内还没有获取锁，直接放弃<br/>
     * 2. 在获取锁之后的超时时间----当获取锁成功之后，对应的key有对应的有效期
     *
     * @param businessType 锁的业务类型
     * @param id           唯一识别号
     */
    public Long getReidsLock(String businessType, String id) throws LockAcuireTimeOutException {
        Jedis conn = null;
        Long isSuccess = null;

        // 建立redis连接
        conn = RedisPoolHolder.getPoolResource();
        // 定义在获取锁之后的超时时间(以秒为单位)
        Long lockExpireTime = null;
//        Long lockExpireTime = DateUtil.nowMilli() + timeOut;
        // 使用循环机制保证重复进行尝试获取锁（乐观锁）
        Long lockEndTime = DateUtil.nowMilli() + acquireTimeout;

        // 获取分布式锁
        try {
            lockExpireTime = tryGetDistributedLock(businessType, id, conn, lockEndTime);
        } catch (Exception e) {
            log.info("获取锁时发生异常");
            throw new LockAcuireTimeOutException(e);
        } finally {
            conn.close();
        }

        // 如果获取锁成功则返回锁
        if (lockExpireTime != null) {
            log.info("获取锁成功");
            System.out.println("获取锁----" + id);
            // 获取成功则返回过期时间
            return lockExpireTime;
        } else {
            log.error("获取锁超时");
            // 获取失败则抛出异常
            throw new LockAcuireTimeOutException("获取分布式锁超时：业务类型=" + businessType + "，唯一标识号=" + id);
        }

    }

    /**
     * 尝试获取分布式锁
     *
     * @param id
     * @param conn
     * @param lockEndTime
     * @return
     * @throws InterruptedException
     */
    private Long tryGetDistributedLock(String businessType, String id, Jedis conn, Long lockEndTime) throws InterruptedException {
        // 重试次数
        int tryTime = 1;
        // 锁过期时间
        Long lockExpireTime;
        do {
            String key = redisLockDir.concat(":").concat(businessType).concat(":").concat(id);
            // 检索查看有没有客户端已经获取到锁
            String expireTimeOut = conn.get(key);

            // 如果没有获取到锁则直接获取锁
            if (expireTimeOut == null || expireTimeOut.equals("")) {
                log.info("通过key没有查到值，直接获取锁");
                // 同时有很多进程进来如果插入不成功则说明该锁已被其他进程得到
                lockExpireTime = DateUtil.nowMilli() + timeOut;
                if(conn.setnx(key, lockExpireTime.toString()) == GET_LOCK_SUCCESS){
                    return lockExpireTime;
                }
                continue;
            }

            // 如果已经有客户端拿到锁，但是经判断已超时则直接覆盖锁
            if (Long.valueOf(expireTimeOut) < DateUtil.nowMilli()) {
//                log.info("通过key查到的过期时间小于当前时间，覆盖锁。新过期时间{}----旧过期时间{}", expireTimeOut, lockExpireTime);
                if(conn.setnx(key+ ":dupflag", "1") != 1) continue;
                conn.expire(key+ ":dupflag", 1);
                lockExpireTime = DateUtil.nowMilli() + timeOut;
                conn.set(key, lockExpireTime.toString());
                return lockExpireTime;
            }

            // 如果客户端拿到所且没有超时则继续等待
            if (Long.valueOf(expireTimeOut) >= DateUtil.nowMilli()) {
//                log.info("通过key查到的过期时间大于当前时间，等待其他客户端释放锁。新过期时间{}----旧过期时间{}"
//                        , expireTimeOut, lockExpireTime);
                log.info("重试次数：{}", tryTime++);
//                System.out.println("重试次数："+tryTime++);
                Thread.sleep(500);
                continue;
            }
        } while (DateUtil.nowMilli() < lockEndTime);
        // 超时则返回失败
        log.info("获取锁超时，返回失败。。。");
        return null;
    }

    /**
     * 释放锁资源
     *
     * @param businessType
     * @param id
     * @param lockExpireTime
     */
    public void releaseDistributedLock(String businessType, String id, Long lockExpireTime)
            throws LockExpireTimeOutException {

        if(lockExpireTime == null) {
            return;
        }

        Jedis conn = null;

        // 建立redis连接
        conn = RedisPoolHolder.getPoolResource();
        String key = redisLockDir.concat(":").concat(businessType).concat(":").concat(id);
        String expireTimeOut = conn.get(key);
        // 为了防止当客户端A释放锁的时候，正好另外一个客户端B进入且此时时间判断已经超时得到了锁，
        // 但是此时因为在零界点判断没有过期，
        // 客户端A删除了key的这种场景。
        if (expireTimeOut == null || expireTimeOut.equals("")) {
            log.info("key已经被别的客户端删除，直接放行。");
            conn.close();
            return;
        }
        Long expireTimeOutL = Long.valueOf(expireTimeOut);
        // 如果通过key获取到的过期时间等于传入的过期时间则删除该锁
        if (expireTimeOutL.equals(lockExpireTime)) {
            log.info("业务执行完成释放该锁，业务类型：{}，唯一标识：{}", businessType, id);
            conn.del(key);
        } else {
            log.info("如果通过key查询到的过期时间和传入的过期时间不等，则判断为过期");
            conn.close();
            // 如果通过key查询到的过期时间和传入的过期时间不等，则判断为过期，抛出异常
            throw new LockExpireTimeOutException(
                    "获取分布式锁等待超时：业务类型=" + businessType + "，唯一标识号=" + id);
        }
        conn.close();
    }

    /**
     * 单例模式
     */
    public static class SingletonHolder {
        private static final RedisDistributedLock REDIS_LOCK = new RedisDistributedLock();
    }

    public static RedisDistributedLock getInstance() {
        return SingletonHolder.REDIS_LOCK;
    }

    /**
     * 初始化配置可以定制化超时时间
     * 只能用在启动项目是初始化
     *
     * @param acquireTimeoutM
     * @param timeOutM
     */
    public static void initConfig(Long acquireTimeoutM, Long timeOutM) throws DistributeInitException {
        try {
            // 如果没有初始化过配置
            if (!hasConfig) {
                configLock.lock();
                hasConfig = true;
                acquireTimeout = acquireTimeoutM;
                timeOut = timeOutM;
                return;
            }
            // 如果已经被初始化过就不能被初始化
            throw new DistributeInitException();
        } finally {
            configLock.unlock();
        }
    }

    public static void main(String[] args) throws RedisPoolInitException {
        RedisPoolHolder.initConfig("192.168.1.11", 6379);
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                RedisDistributedLock redisDistributedLock = RedisDistributedLock.getInstance();
                String id = null;
                Long expireTime = null;
                try {

                    expireTime = redisDistributedLock.getReidsLock("order", (id = "20190813" + UUID.randomUUID()));
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (LockAcuireTimeOutException e) {
                    e.printStackTrace();

                } finally {
                    try {
                        redisDistributedLock.releaseDistributedLock("order", id, expireTime);
                    } catch (LockExpireTimeOutException e) {
                        e.printStackTrace();
                    }
                }

            }).start();
        }


    }
}
