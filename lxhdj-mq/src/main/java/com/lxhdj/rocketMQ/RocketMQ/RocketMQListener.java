package com.lxhdj.rocketMQ.RocketMQ;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Created by wangguijun1 on 2018/6/7.
 */
public class RocketMQListener implements MessageListenerConcurrently {

    private int id;

    public RocketMQListener(int id) {
        this.id = id;
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        for (MessageExt message : msgs) {
            String msg = new String(message.getBody());
            System.out.println("id：" + id + ",messageId：" + message.getMsgId() + "，msg：" + msg);
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
