package com.lxhdj.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by wangguijun1 on 2018/6/6.
 */
public class RedisTest {

    @Test
    public void connTest() {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("192.168.99.100");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());
    }

    @Test
    public void keysTest() {
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

    @Test
    public void stringTest() {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("192.168.99.100");
        System.out.println("连接成功");
        //设置 redis 字符串数据
        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        String value = jedis.get("runoobkey");
        System.out.println("redis 存储的字符串为: " + value);
    }

    @Test
    public void listTest() {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("192.168.99.100");
        System.out.println("连接成功");
        //存储数据到列表中
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0, 2);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("列表项为: " + list.get(i));
        }
    }
}
