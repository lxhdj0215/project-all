package com.lxhdj.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by wangguijun1 on 2018/6/6.
 */
public class RedisStringJava {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("192.168.99.100");
        System.out.println("连接成功");
        //设置 redis 字符串数据
        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        String value = jedis.get("runoobkey");
        System.out.println("redis 存储的字符串为: " + value);
    }
}

