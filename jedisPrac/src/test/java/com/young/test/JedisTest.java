package com.young.test;

import com.young.redis.util.JedisConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class JedisTest {
    private Jedis jedis;

    @BeforeEach
    void setUp() {
        // 1.建立链接（创建jedis对象的方式）
//        jedis = new Jedis("39.106.198.163", 6379);
        // 1.1 使用连接池的方式获取jedis对象的方式
        jedis = JedisConnectionFactory.getJedis();
        // 2.设置密码
        jedis.auth("123456");
        // 3.选择库
        jedis.select(0);
    }

    @Test
    void testString() {
        String res;
        res = jedis.set("name", "young");
        System.out.println("result: " + res);
        res = jedis.set("age", "32");
        System.out.println("result: " + res);

        Map<String, String> get_res = jedis.hgetAll("person:user:young");
        System.out.println("get_res: " + get_res);
    }

    @AfterEach
    void tearDown() {
        if(jedis != null){
            jedis.close();
        }
    }
}
