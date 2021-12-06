package com.zmji.year.three.november.mid;

import java.util.HashMap;
import java.util.Map;

/**
 * 423. 从英文中重建数字 给你一个字符串 s ，其中包含字母顺序打乱的用英文单词表示的若干数字（0-9）。按 升序 返回原始的数字。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "owoztneoer" 输出："012" 示例 2：
 *
 * 输入：s = "fviefuro" 输出："45"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 105 s[i] 为 ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"] 这些字符之一 s 保证是一个符合题目要求的字符串
 * 
 * @author : zhongmou.ji
 * @date : 2021/11/24 上午8:43
 **/
public class LeetCode423 {

    /**
     * 模拟
     */
    public String originalDigits(String s) {
        Map<Character, Integer> charCountMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }
        int[] count = new int[10];
        count[0] = charCountMap.getOrDefault('z', 0);
        count[2] = charCountMap.getOrDefault('w', 0);
        count[4] = charCountMap.getOrDefault('u', 0);
        count[6] = charCountMap.getOrDefault('s', 0);
        count[8] = charCountMap.getOrDefault('g', 0);

        count[3] = charCountMap.getOrDefault('h', 0) - count[8];
        count[5] = charCountMap.getOrDefault('f', 0) - count[4];
        count[7] = charCountMap.getOrDefault('s', 0) - count[6];

        count[1] = charCountMap.getOrDefault('o', 0) - count[0] - count[2] - count[4];

        count[9] = charCountMap.getOrDefault('i', 0) - count[5] - count[6] - count[8];

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < count[i]; ++j) {
                ans.append((char)(i + '0'));
            }
        }
        return ans.toString();
    }
}
