package com.springatf.common.redis;

import com.springatf.common.exception.RedisPoolInitException;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 名称：redis连接池容器<br>
 * 描述：redis连接池容器<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Slf4j
public class RedisPoolHolder {

    /**
     * redisPort
     * redis的端口号
     * default:6379
     */
    private static int port = 6379;

    /**
     * redisIP
     * redis的访问主机地址
     * default:localhost
     */
    private static String ip = "127.0.0.1";

    /**
     * redis登陆密码
     */
    private static String authRequire = null;

    /**
     * redis线程池声明
     */
    private static JedisPool jedisPool;

    /**
     * 初始化配置标示
     */
    private static boolean hasConfig = false;

    /**
     * config锁
     */
    private static ReentrantLock configLock = new ReentrantLock(true);

    private static void init() {
        log.info("初始化Jedis池----start");
        JedisPoolConfig config = new JedisPoolConfig();
//        //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
//        //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
//        config.setMaxTotal(1000);
//        config.setMaxWaitMillis(1000);
//        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
//        config.setMaxIdle(50);
//        //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；单位毫秒
//        //小于零:阻塞不确定的时间,  默认-1
//        config.setMaxWaitMillis(1000 * 100);
//        //在borrow(引入)一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
//        config.setTestOnBorrow(true);
//        //return 一个jedis实例给pool时，是否检查连接可用性（ping()）
//        config.setTestOnReturn(true);

        config.setMaxTotal(500);
        config.setMaxIdle(200);
        config.setMinIdle(8);// 设置最小空闲数
        config.setMaxWaitMillis(60000);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        // Idle时进行连接扫描
        config.setTestWhileIdle(true);
        // 表示idle object evitor两次扫描之间要sleep的毫秒数
        config.setTimeBetweenEvictionRunsMillis(30000);
        // 表示idle object evitor每次扫描的最多的对象数
        config.setNumTestsPerEvictionRun(10);
        // 表示一个对象至少停留在idle状态的最短时间，然后才能被idle object
        // evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
        config.setMinEvictableIdleTimeMillis(60000);

        jedisPool = new JedisPool(config, ip, port, 2000, authRequire);
        log.info("初始化Jedis池----end");
    }

    /**
     * 初始化配置可以
     * 定制化redis访问地址以及端口
     * 只能用在启动项目是初始化
     */
    public static void initConfig()throws RedisPoolInitException {
        try {
            // 如果没有初始化过配置
            if (!hasConfig) {
                configLock.lock();
                hasConfig = true;
                init();
                return;
            }
            // 如果已经被初始化过就不能被初始化
            throw new RedisPoolInitException();
        } finally {
            configLock.unlock();
        }
    }

    /**
     * 初始化配置可以
     * 定制化redis访问地址以及端口
     * 只能用在启动项目是初始化
     *
     * @param ipM
     * @param portM
     */
    public static void initConfig(String ipM, int portM) throws RedisPoolInitException {
        try {
            // 如果没有初始化过配置
            if (!hasConfig) {
                configLock.lock();
                hasConfig = true;
                ip = ipM;
                port = portM;
                init();
                return;
            }
            // 如果已经被初始化过就不能被初始化
            throw new RedisPoolInitException();
        } finally {
            configLock.unlock();
        }
    }


    /**
     * 初始化配置可以
     * 定制化redis访问地址以及端口
     * 定制化redis访问用户名以及密码
     * 只能用在启动项目是初始化
     *
     * @param ipM
     * @param portM
     */
    public static void initConfig(String ipM, int portM
            , String authRequireM) throws RedisPoolInitException {
        try {
            // 如果没有初始化过配置
            if (!hasConfig) {
                configLock.lock();
                hasConfig = true;
                authRequire = authRequireM;
                ip = ipM;
                port = portM;
                init();
                return;
            }
            // 如果已经被初始化过就不能被初始化
            throw new RedisPoolInitException();
        } finally {
            configLock.unlock();
        }
    }

    public static Jedis getPoolResource() {
        return jedisPool.getResource();
    };

}
