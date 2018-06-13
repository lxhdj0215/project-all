package com.lxhdj.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by wangguijun1 on 2018/6/6.
 */
public class RedisJava {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("192.168.99.100");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());
    }
}
