package com.alibaba.dubbo.consumer;


import com.lxhdj.dubbo.service.DubboService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Consumer {
	public static void main(String[] args) throws IOException, InterruptedException {
		// 测试常规服务
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
		context.start();
		System.out.println("consumer start");
		while (true) {
			try {
				DubboService dubboService = context.getBean(DubboService.class);
				System.out.println(dubboService.getName("world"));
				dubboService.sayHello();
				Thread.sleep(1000 * 5);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				Thread.sleep(1000 * 5);
			}

		}
	}
}
