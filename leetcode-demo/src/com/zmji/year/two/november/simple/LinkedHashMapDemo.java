package com.zmji.year.two.november.simple;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : zhongmou.ji
 * @date : 2021/11/12 上午10:12
 **/
public class LinkedHashMapDemo {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < 100000; i++) {
            map.put(i, i);
        }
        for (int i : map.values()) {
            System.out.println(i);
        }
    }
}
