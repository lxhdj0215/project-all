package com.lxhdj.dubbo.dubboservice;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.lxhdj.dubbo.service.DubboService;

public class DubboServiceImpl implements DubboService {

    @Override
    public String getName(String name) {
        return "hello " + name;
    }

    @Override
    public void sayHello() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        System.out.println(sdf.format(date));
    }

}
