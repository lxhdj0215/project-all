package com.lxhdj.rocketMQ.RocketMQ;

/**
 * Created by wangguijun1 on 2018/6/7.
 */
public class RocketMQConsumerTest1 {

    public static void main(String[] args) {
        String mqNameServer = "192.168.99.100:9876";
        String mqTopics = "MQ-MSG-TOPICS-TEST";

        String consumerMqGroupName = "CONSUMER-MQ-GROUP1";
        RocketMQListener mqListener = new RocketMQListener(0);
        RocketMQConsumer mqConsumer = new RocketMQConsumer(mqListener, mqNameServer, consumerMqGroupName, mqTopics);
        mqConsumer.init();


        try {
            Thread.sleep(1000 * 60L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
