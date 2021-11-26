package com.zmji.dynamicplan;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : zhongmou.ji
 * @date : 2021/11/14 下午4:19
 **/
public class Coin {

    /**
     * 暴力破解
     * 
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subRes = coinChange(coins, amount - coin);
            if (subRes == -1) {
                continue;
            }
            res = Math.min(res, 1 + subRes);
        }
        return res != Integer.MAX_VALUE ? res : -1;
    }

    Map<Integer, Integer> sumCoinCountMap = new HashMap<>();

    /**
     * 带备忘录的递归
     * 
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange1(int[] coins, int amount) {
        if (sumCoinCountMap.containsKey(amount)) {
            return sumCoinCountMap.get(amount);
        }
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subRes = coinChange1(coins, amount - coin);
            if (subRes == -1) {
                continue;
            }
            res = Math.min(res, 1 + subRes);
        }
        res = res != Integer.MAX_VALUE ? res : -1;
        sumCoinCountMap.put(amount, res);
        return res;
    }

    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];

    }
}
