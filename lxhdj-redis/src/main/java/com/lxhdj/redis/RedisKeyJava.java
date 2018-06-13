package com.lxhdj.redis;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by wangguijun1 on 2018/6/6.
 */
public class RedisKeyJava {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("192.168.99.100");
        System.out.println("连接成功");

        // 获取数据并输出
        Set<String> keys = jedis.keys("*");
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key);
            jedis.del(key);
        }
    }
}
