package com.zmji.year.three.month.four;

/**
 * @author : zhongmou.ji
 * @date : 2022/4/4 9:19 下午
 **/
public class LeetCode307 {

    class NumArray {
        private int[] nums;
        private int[] prev;

        public NumArray(int[] nums) {
            this.nums = new int[nums.length];
            System.arraycopy(nums, 0, this.nums, 0, nums.length);
            prev = new int[nums.length];
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                prev[i] = sum;
            }
        }

        public void update(int index, int val) {
            // 更新下标为index及之后的所有
            int distance = val - nums[index];
            for (int i = index; i < nums.length; i++) {
                prev[i] += distance;
            }
        }

        public int sumRange(int left, int right) {
            return prev[right] - (left == 0 ? 0 : prev[left - 1]);
        }
    }
}
