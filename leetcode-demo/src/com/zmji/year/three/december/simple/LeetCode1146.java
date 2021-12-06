package com.zmji.year.three.december.simple;

/**
 * @author : zhongmou.ji
 * @date : 2021/12/1 7:42 上午
 **/
public class LeetCode1146 {

    public int maxPower(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int res = 1;
        int count = 1;
        char prev = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (prev == s.charAt(i)) {
                count++;
            } else {
                res = Math.max(res, count);
                count = 1;
                prev = s.charAt(i);
            }
        }
        res = Math.max(res, count);
        return res;
    }

}
