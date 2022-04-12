package com.zmji.year.three.month.four;

import java.util.Objects;

/**
 * @author : zhongmou.ji
 * @date : 2022/4/7 8:30 上午
 **/
public class LeetCode796 {

    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        // 双指针
        int n = goal.length();
        for (int i = 0; i < n; i++) {
            String reserveGoal = goal.substring(n - i, n) + goal.substring(0, n - i);
            if (Objects.equals(reserveGoal, s)) {
                return true;
            }
        }
        return false;

    }
}
