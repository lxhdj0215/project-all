package com.lxhdj.rocket;

import com.alibaba.rocketmq.common.message.Message;
import org.junit.Test;

/**
 * Created by wangguijun1 on 2018/6/7.
 */
public class RocketMQProducerTest {

    @Test
    public void producerTest() {
        String mqNameServer = "192.168.99.100:9876";
        String mqTopics = "MQ-MSG-TOPICS-TEST";

        String producerMqGroupName = "PRODUCER-MQ-GROUP";
        RocketMQProducer mqProducer = new RocketMQProducer(mqNameServer, producerMqGroupName, mqTopics);
        mqProducer.init();

        for (int i = 0; i < 500; i++) {
            Message message = new Message();
            message.setBody(("I send message to RocketMQ " + i).getBytes());
            mqProducer.send(message);
        }
    }
}
