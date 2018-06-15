package com.lxhdj.consumer;


import com.lxhdj.constant.Constants;
import com.lxhdj.dubbo.service.DubboService;
import com.lxhdj.util.CommonUtil;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerTest {
    @Test
    public void comsumerTest() {
        // 测试常规服务
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
        context.start();
        System.out.println("consumer start");
        while (true) {
            try {
                DubboService dubboService = context.getBean(DubboService.class);
                System.out.println(dubboService.getName("world"));
                dubboService.sayHello();
                Thread.sleep(Constants.CONSTANT_1000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                CommonUtil.sleep(Constants.CONSTANT_1000);
            }

        }
    }
}
