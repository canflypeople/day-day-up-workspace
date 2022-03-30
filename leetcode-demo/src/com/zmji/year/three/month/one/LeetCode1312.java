package com.zmji.year.three.month.one;

/**
 * @author : zhongmou.ji
 * @date : 2022/1/22 12:05 下午
 **/
public class LeetCode1312 {

    public int removePalindromeSub(String s) {
        int n = s.length();
        for (int i = 0; i < n / 2; ++i) {
            if (s.charAt(i) != s.charAt(n - 1 - i)) {
                return 2;
            }
        }
        return 1;
    }
}
