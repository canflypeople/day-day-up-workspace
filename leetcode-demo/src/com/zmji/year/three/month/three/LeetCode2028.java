package com.zmji.year.three.month.three;

import java.util.Arrays;

/**
 * @author : zhongmou.ji
 * @date : 2022/3/27 8:51 上午
 **/
public class LeetCode2028 {

    public static void main(String[] args) {
        LeetCode2028 main = new LeetCode2028();
        main.missingRolls(new int[] {1, 5, 6}, 3, 4);
    }

    public int[] missingRolls(int[] rolls, int mean, int n) {
        int currSum = 0;
        int m = rolls.length;
        for (int roll : rolls) {
            currSum += roll;
        }
        int restSum = (mean + n) * (m + n) - currSum;
        if (restSum > n * 6) {
            return new int[0];
        }
        // 计算出 n个 1-6内的数字
        int[] res = new int[n];
        Arrays.fill(res, 1);
        restSum = restSum - n;
        while (restSum > 0) {
            if (restSum >= n) {
                restSum = restSum - n;
                addAllElement1(res);
            } else {
                addPartElement1(res, restSum);
                break;
            }
        }
        return res;

    }

    private void addAllElement1(int[] a) {
        addPartElement1(a, a.length);
    }

    private void addPartElement1(int[] a, int n) {
        for (int i = 0; i < n; i++) {
            a[i]++;
        }
    }
}
