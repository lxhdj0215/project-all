package com.lxhdj.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.Serializable;

/**
 * Created by wangguijun1 on 2018/6/20.
 */
public class CommonTest {
    @Test
    public void serializableTest() {
        User user = new User();
        user.setId("1");
        user.setName("wgj");

        String userString = JSONObject.toJSONString(user);
        JSONObject jsonObject = JSONObject.parseObject(userString);
        String name = (String) jsonObject.get("name");
        System.out.println(name);
        User user_ = JSONObject.toJavaObject(jsonObject, User.class);
        System.out.println(user_.getId());
    }

    static class User implements Serializable {
        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
