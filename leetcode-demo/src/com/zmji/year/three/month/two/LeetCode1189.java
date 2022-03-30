package com.zmji.year.three.month.two;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : zhongmou.ji
 * @date : 2022/2/13 7:04 PM
 **/
public class LeetCode1189 {

    private static final String word = "balloon";

    public int maxNumberOfBalloons(String text) {
        Map<Character, Integer> wordCharCountMapper = new HashMap<>();
        for (char c : word.toCharArray()) {
            wordCharCountMapper.put(c, wordCharCountMapper.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> textCharCountMapper = new HashMap<>();
        for (char c : text.toCharArray()) {
            if (!wordCharCountMapper.containsKey(c)) {
                continue;
            }
            textCharCountMapper.put(c, textCharCountMapper.getOrDefault(c, 0) + 1);
        }

        int res = Integer.MAX_VALUE;
        for (Map.Entry<Character, Integer> wordCharEntry : wordCharCountMapper.entrySet()) {
            char c = wordCharEntry.getKey();
            int wordCharCount = wordCharEntry.getValue();
            res = Math.min(res, textCharCountMapper.getOrDefault(c, 0) / wordCharCount);
            if (res == 0) {
                return 0;
            }
        }

        return res == Integer.MAX_VALUE ? 0 : res;

    }
}
