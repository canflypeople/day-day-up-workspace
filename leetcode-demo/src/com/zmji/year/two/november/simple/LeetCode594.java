package com.zmji.year.two.november.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * 594. 最长和谐子序列 和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。
 *
 * 现在，给你一个整数数组 nums ，请你在所有可能的子序列中找到最长的和谐子序列的长度。
 *
 * 数组的子序列是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,2,2,5,2,3,7] 输出：5 解释：最长的和谐子序列是 [3,2,2,2,3] 示例 2：
 *
 * 输入：nums = [1,2,3,4] 输出：2 示例 3：
 *
 * 输入：nums = [1,1,1,1] 输出：0
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 104 -109 <= nums[i] <= 109
 * 
 * @author : zhongmou.ji
 * @date : 2021/11/20 下午2:27
 **/
public class LeetCode594 {

    /**
     * 要点， 统计数组中元素的个数
     * 
     * @param nums
     * @return
     */
    public int findLHS(int[] nums) {
        int res = 0;
        Map<Integer, Integer> numCountMap = new HashMap<>();
        for (int num : nums) {
            numCountMap.put(num, numCountMap.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : numCountMap.entrySet()) {
            if (numCountMap.containsKey(entry.getKey() - 1)) {
                res = Math.max(res, entry.getValue() + numCountMap.get(entry.getKey() - 1));

            }

            if (numCountMap.containsKey(entry.getKey() + 1)) {
                res = Math.max(res, entry.getValue() + numCountMap.get(entry.getKey() + 1));

            }
        }
        return res;
    }
}
