package com.lxhdj.lombok;

import com.lxhdj.entity.Message;
import org.junit.Test;

/**
 * Created by wangguijun1 on 2018/6/20.
 */
public class LombokTest {

    @Test
    public void lombokTest() {
        Message message = new Message();
        message.setMsg("msg-111");
        message.setMsgId(111);
        System.out.println(message.toString());
    }
}
