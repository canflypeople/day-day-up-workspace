package com.zmji.year.three.october;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 187. 重复的DNA序列 所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
 *
 * 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT" 输出：["AAAAACCCCC","CCCCCAAAAA"] 示例 2：
 *
 * 输入：s = "AAAAAAAAAAAAA" 输出：["AAAAAAAAAA"]
 *
 *
 * 提示：
 *
 * 0 <= s.length <= 105 s[i] 为 'A'、'C'、'G' 或 'T'
 *
 * @author : zhongmou.ji
 * @date : 2021/10/8 9:54
 **/
public class LeetCode187RepeatDnaSequences {

    public List<String> findRepeatedDnaSequences(String s) {
        // 哈希表
        List<String> res = new ArrayList<>();
        Map<String, Integer> strCountMap = new HashMap<>();
        for (int i = 9; i <= s.length(); i++) {
            String subStr = s.substring(i - 9, i + 1);
            strCountMap.put(subStr, strCountMap.getOrDefault(subStr, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : strCountMap.entrySet()) {
            if (entry.getValue() > 1) {
                res.add(entry.getKey());
            }
        }
        return res;
    }

}
