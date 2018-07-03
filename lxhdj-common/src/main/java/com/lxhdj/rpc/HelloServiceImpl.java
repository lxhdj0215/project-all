package com.lxhdj.rpc;

/**
 * Created by wangguijun1 on 2018/6/21.
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "Hello " + name;
    }
}
