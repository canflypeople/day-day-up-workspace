package com.zmji.year.three.month.four;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : zhongmou.ji
 * @date : 2022/4/10 9:39 上午
 **/
public class LeetCode804 {

    private static final String[] letterPasswords =
        new String[] {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.",
            "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

    public int uniqueMorseRepresentations(String[] words) {
        Set<String> pass = new HashSet<>();
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (char c : word.toCharArray()) {
                sb.append(letterPasswords[c - 'a']);
            }
            pass.add(String.valueOf(sb));
        }
        return pass.size();
    }
}
