package com.zmji.year.two.december.simple;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : zhongmou.ji
 * @date : 2021/12/2 7:54 上午
 **/
public class LeetCode506 {

    public String[] findRelativeRanks(int[] score) {
        Map<Integer, Integer> scoreIndexMap = new HashMap<>();
        for (int i = 0; i < score.length; i++) {
            scoreIndexMap.put(score[i], i);
        }
        Arrays.sort(score);
        String[] res = new String[score.length];
        for (int i = score.length - 1; i >= 0; i--) {
            if (i == score.length - 1) {
                res[scoreIndexMap.get(score[i])] = "Gold Medal";
            } else if (i == score.length - 2) {
                res[scoreIndexMap.get(score[i])] = "Silver Medal";
            } else if (i == score.length - 3) {
                res[scoreIndexMap.get(score[i])] = "Bronze Medal";
            } else {
                res[scoreIndexMap.get(score[i])] = String.valueOf(score.length - i);
            }
        }

        return res;
    }

    /**
     * 题解： 类似表驱动法
     * 
     * @param score
     * @return
     */
    public String[] findRelativeRanks2(int[] score) {
        String[] desc = {"Gold Medal", "Silver Medal", "Bronze Medal"};
        Map<Integer, Integer> scoreIndexMap = new HashMap<>();
        for (int i = 0; i < score.length; i++) {
            scoreIndexMap.put(score[i], i);
        }
        Arrays.sort(score);
        String[] res = new String[score.length];
        for (int i = score.length - 1; i >= 0; i--) {
            if (i >= score.length - 3) {
                res[scoreIndexMap.get(score[i])] = desc[score.length - i - 1];
            } else {
                res[scoreIndexMap.get(score[i])] = String.valueOf(score.length - i);
            }
        }

        return res;
    }
}
