package com.lxhdj.test;

import com.alibaba.fastjson.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wangguijun1 on 2018/8/8.
 */
public class Man extends Person {
    private String name;

    public static void main(String[] args) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("商品编号", "1");
        map.put("商品名称", "2");
        map.put("购买数量", "3");
        map.put("充值账户", "4");
        map.put("区服信息", "5");
        System.out.println(JSONObject.toJSONString(map));

    }
}
