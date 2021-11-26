package com.zmji.dynamicplan;

import java.util.HashMap;
import java.util.Map;

/**
 * 斐波那契数列
 * 
 * @author : zhongmou.ji
 * @date : 2021/11/19 下午3:08
 **/
public class Fib {

    /**
     * 暴力破解法
     * 
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    Map<Integer, Integer> numValueMap = new HashMap<>();

    public int fib1(int n) {
        if (numValueMap.containsKey(n)) {
            return numValueMap.get(n);
        }
        int value;
        if (n == 1 || n == 2) {
            value = 1;
        } else {
            value = fib1(n - 1) + fib1(n - 2);
        }
        numValueMap.put(n, value);
        return value;
    }

    public int fib2(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int fib3(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        int prev = 1;
        int curr = 1;
        for (int i = 3; i <= n; i++) {
            int sum = prev + curr;
            prev = curr;
            curr = sum;
        }
        return curr;
    }

}
