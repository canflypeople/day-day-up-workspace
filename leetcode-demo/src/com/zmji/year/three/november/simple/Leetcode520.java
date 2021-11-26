package com.zmji.year.three.november.simple;

/**
 * 520. 检测大写字母 我们定义，在以下情况时，单词的大写用法是正确的：
 *
 * 全部字母都是大写，比如 "USA" 。 单词中所有字母都不是大写，比如 "leetcode" 。 如果单词不只含有一个字母，只有首字母大写， 比如 "Google" 。 给你一个字符串 word 。如果大写用法正确，返回 true
 * ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：word = "USA" 输出：true 示例 2：
 *
 * 输入：word = "FlaG" 输出：false
 * 
 * @author : zhongmou.ji
 * @date : 2021/11/13 上午11:32
 **/
public class Leetcode520 {
    public boolean detectCapitalUse(String word) {
        int upperCount = 0;
        int lowerCount = 0;
        for (char c : word.toCharArray()) {
            if (Character.isLowerCase(c)) {
                lowerCount++;
            } else if (Character.isUpperCase(c)) {
                upperCount++;
            } else {
                return false;
            }
        }
        int len = word.length();
        if (lowerCount == len || upperCount == len) {
            return true;
        }
        return Character.isUpperCase(word.charAt(0)) && upperCount == 1;
    }
}
