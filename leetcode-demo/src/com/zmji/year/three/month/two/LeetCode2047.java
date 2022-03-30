package com.zmji.year.three.month.two;

import java.util.Set;

/**
 * @author : zhongmou.ji
 * @date : 2022/2/8 8:57 上午
 **/
public class LeetCode2047 {

    private static final Set<Character> PUNCTUATION_MARK_SET = Set.of('!', '.', ',');

    public int countValidWords(String sentence) {
        int n = sentence.length();
        int l = 0, r = 0;
        int ret = 0;
        while (true) {
            while (l < n && sentence.charAt(l) == ' ') {
                l++;
            }
            if (l >= n) {
                break;
            }
            r = l + 1;
            while (r < n && sentence.charAt(r) != ' ') {
                r++;
            }
            if (isValid(sentence.substring(l, r))) {
                ret++;
            }
            l = r + 1;
        }
        return ret;
    }

    private boolean isValid(String word) {
        int n = word.length();
        boolean hasHyphens = false;
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(word.charAt(i))) {
                return false;
            } else if (word.charAt(i) == '-') {
                if (hasHyphens || i == 0 || i == n - 1 || !Character.isLetter(word.charAt(i - 1))
                    || !Character.isLetter(i + 1)) {
                    return false;
                }
                hasHyphens = true;
            } else if (word.charAt(i) == '!' || word.charAt(i) == '.' || word.charAt(i) == ',') {
                if (i != n - 1) {
                    return false;
                }
            }
        }
        return true;
    }

}
