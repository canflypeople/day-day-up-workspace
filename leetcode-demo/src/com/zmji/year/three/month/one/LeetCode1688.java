package com.zmji.year.three.month.one;

/**
 * @author : zhongmou.ji
 * @date : 2022/1/25 8:26 上午
 **/
public class LeetCode1688 {

    public int numberOfMatches(int n) {
        // 模拟
        int res = 0;
        while (n > 1) {
            res += n / 2;
            n = n / 2 + n % 2;
        }
        return res;
    }
}
