package com.xzh.xspring.test.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("1", "aaa");
        hashMap.put("2", "bbb");
        hashMap.put("3", "ccc");
    }

    public String queryUserName(String id) {

        return hashMap.get(id);
    }
}
