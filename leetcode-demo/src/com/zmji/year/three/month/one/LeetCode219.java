package com.zmji.year.three.month.one;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/contains-duplicate-ii/
 * 
 * @author : zhongmou.ji
 * @date : 2022/1/19 1:27 下午
 **/
public class LeetCode219 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, List<Integer>> numIndexsMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            numIndexsMap.putIfAbsent(nums[i], new ArrayList<>());
            numIndexsMap.get(nums[i]).add(i);
        }
        for (List<Integer> indexs : numIndexsMap.values()) {
            if (indexs.size() <= 1) {
                continue;
            }
            Collections.sort(indexs);
            int pre = indexs.get(0);
            for (int i = 1; i < indexs.size(); i++) {
                int value = indexs.get(i) - pre;
                if (value <= k) {
                    return true;
                }
                pre = indexs.get(i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LeetCode219 leetCode219 = new LeetCode219();
        leetCode219.containsNearbyDuplicate(new int[] {1, 2, 3, 1, 2, 3}, 2);
    }
}
