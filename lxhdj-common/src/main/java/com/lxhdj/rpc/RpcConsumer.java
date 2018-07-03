package com.lxhdj.rpc;

import com.lxhdj.constant.Constants;
import org.junit.Test;

/**
 * Created by wangguijun1 on 2018/6/21.
 */
public class RpcConsumer {

    @Test
    public void rpcConsumerTest() {
        HelloService service = null;
        try {
            service = RpcFramework.refer(HelloService.class, "127.0.0.1", Constants.CONSTANT_1234);
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                String hello = service.hello("World" + i);
                System.out.println(hello);
                Thread.sleep(Constants.CONSTANT_1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
