package com.lxhdj.kafka;

import com.alibaba.fastjson.JSONObject;
import com.lxhdj.entity.Message;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import java.util.Properties;

/**
 * Created by wangguijun1 on 2018/6/20.
 */
public class ProducerTest {

    @Test
    public void producerTest() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.99.100:9092");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = null;
        try {
            producer = new KafkaProducer<>(properties);
            for (int i = 0; i < 100; i++) {
                Message message = new Message();
                message.setMsgId(i);
                message.setMsg("msg-" + i);
                String msg = JSONObject.toJSONString(message);
                producer.send(new ProducerRecord<>("mykafka", msg));
                System.out.println("Sent:" + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}
