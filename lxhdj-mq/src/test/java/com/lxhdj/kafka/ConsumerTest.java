package com.lxhdj.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxhdj.entity.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.Test;

import java.util.Arrays;
import java.util.Properties;

/**
 * Created by wangguijun1 on 2018/6/20.
 */
public class ConsumerTest {
    
    @Test
    public void consumerTest() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.99.100:9092");
        properties.put("group.id", "group-1");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "earliest");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Arrays.asList("mykafka"));
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                String msg = record.value();
                JSONObject jsonObject = JSON.parseObject(msg);
                Message message = JSON.toJavaObject(jsonObject, Message.class);
                int id = message.getMsgId();
                String messageInfo = message.getMsg();
                long offset = record.offset();
                System.out.printf("offset = %d, id = %d, msg = %s\n", offset, id, messageInfo);
            }
        }
    }
}
