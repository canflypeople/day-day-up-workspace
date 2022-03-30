package com.zmji.year.three.month.one;

/**
 * @author : zhongmou.ji
 * @date : 2022/1/12 8:12 上午
 **/
public class LeetCode334 {
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 3; i++) {
            if (nums[i] < nums[i + 1] && nums[i + 1] < nums[i + 2]) {
                return true;
            }
        }
        return false;

    }
}
