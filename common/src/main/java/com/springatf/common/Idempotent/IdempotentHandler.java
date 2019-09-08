package com.springatf.common.Idempotent;

import com.springatf.common.redis.RedisPoolHolder;
import com.springatf.common.exception.RedisPoolInitException;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.UUID;

/**
 * 名称：幂等处理<br>
 * 描述：实现接口幂等的处理类<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public class IdempotentHandler {

    public final static int TOKEN_TIMEOUT = 60*60;

    /**
     * redis分布式锁key目录
     */
    private final static String REDIS_KEY_DIR = "idempotent:redisKey:";

    /**
     * 获取一个token令牌
     * @return
     */
    public static String getToken() {
        StringBuilder key = new StringBuilder(REDIS_KEY_DIR);
        String tokenKey = key.append(UUID.randomUUID()).toString();
        RedisPoolHolder.getPoolResource().setex(tokenKey, TOKEN_TIMEOUT, "1");
        return tokenKey;
    }

    /**
     * 查询令牌是否被占用
     * @param token 令牌
     * @return
     */
    public static boolean isDuplicate(String token) {
        Jedis conn = RedisPoolHolder.getPoolResource();
        if(StringUtils.isEmpty(conn.get(token))) {
            // 没有获取到令牌，不可以执行
            return true;
        }

        // 如果获取到了令牌，说明可以被执行
        conn.del(token);
        return false;
    }

    public static void main(String[] args) throws RedisPoolInitException {
        RedisPoolHolder.initConfig("192.168.1.11", 6379);
        System.out.println(isDuplicate("idempotent:redisKey:1e8532fb-e769-48f7-90b8-88bda7c8e73c"));

    }

}
