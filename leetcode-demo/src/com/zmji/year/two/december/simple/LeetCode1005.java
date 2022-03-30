package com.zmji.year.two.december.simple;

import java.util.PriorityQueue;

/**
 * 1005. K 次取反后最大化的数组和 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
 *
 * 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
 *
 * 以这种方式修改数组后，返回数组 可能的最大和 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,2,3], k = 1 输出：5 解释：选择下标 1 ，nums 变为 [4,-2,3] 。 示例 2：
 *
 * 输入：nums = [3,-1,0,2], k = 3 输出：6 解释：选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2] 。 示例 3：
 *
 * 输入：nums = [2,-3,-1,5,-4], k = 2 输出：13 解释：选择下标 (1, 4) ，nums 变为 [2,3,-1,5,4] 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 104 -100 <= nums[i] <= 100 1 <= k <= 104 通过次数31,793提交次数59,930
 * 
 * @author : zhongmou.ji
 * @date : 2021/12/3 8:14 上午
 **/
public class LeetCode1005 {

    public int largestSumAfterKNegations(int[] nums, int k) {
        /**
         * 如何将数组的元素和变为最大？ 数组元素和= Sum(arr[i]) i= 0 - len-1 元素和最大-> 正数尽可能多， 负数尽可能少 正数尽可能大， 负数尽可能大（负数的绝对值尽可能小） 最小堆
         */
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            heap.offer(num);
        }
        while (k-- > 0) {
            heap.offer(-heap.poll());
        }
        int ans = 0;
        while (!heap.isEmpty()) {
            ans += heap.poll();
        }
        return ans;

    }
}
