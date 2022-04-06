package com.young.redis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

public class JedisConnectionFactory {
    private static final JedisPool jedisPool;
    
    static {
        // 配置连接池
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 配置连接池中最大的连接数
        poolConfig.setMaxTotal(8);
        // 配置连接池的最大空闲链接数：即空闲时连接池中有多少个链接
        poolConfig.setMaxIdle(8);
        // 配置连接池的最小空闲连接数：即过一段空闲时间后连接池中的链接会被销毁，直到设置的最小链接数。
        poolConfig.setMinIdle(0);
        // 设置链接池的满后的等待时间
        poolConfig.setMaxWait(Duration.ofSeconds(1));

        jedisPool = new JedisPool(poolConfig, "******", 6379, 1000, "123456");
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
