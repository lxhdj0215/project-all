package com.lxhdj.rpc;

import com.lxhdj.constant.Constants;
import org.junit.Test;

/**
 * Created by wangguijun1 on 2018/6/21.
 */
public class RpcProvider {
    @Test
    public void rpcProviderTest() {
        HelloService service = new HelloServiceImpl();
        try {
            RpcFramework.export(service, Constants.CONSTANT_1234);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
