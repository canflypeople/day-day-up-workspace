package com.zmji.year.three.month.four;

import java.util.*;

/**
 * @author : zhongmou.ji
 * @date : 2022/4/1 6:16 下午
 **/
public class LeetCode954 {

    public boolean canReorderDoubled(int[] arr) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : arr) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }
        if (counts.getOrDefault(0, 0) % 2 != 0) {
            return false;
        }

        List<Integer> values = new ArrayList<>();
        values.addAll(counts.keySet());
        values.sort(Comparator.comparingInt(Math::abs));
        for (int value : values) {
            if (counts.getOrDefault(2 * value, 0) < counts.get(value)) {
                return false;
            }
            counts.put(2 * value, counts.getOrDefault(2 * value, 0) - counts.get(value));
        }
        return true;
    }

}
