package com.zmji.year.three.month.one;

/**
 * @author : zhongmou.ji
 * @date : 2022/1/13 7:59 上午
 **/
public class LeetCode747 {

    public int dominantIndex(int[] nums) {
        // 找到最大和第二大的数
        int maxNumIndex = -1;
        int firstBigNum = Integer.MIN_VALUE;
        int secondBigNum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num > secondBigNum) {
                secondBigNum = num;
            }
            if (firstBigNum < secondBigNum) {
                int temp = firstBigNum;
                firstBigNum = secondBigNum;
                secondBigNum = temp;
                maxNumIndex = i;
            }
        }
        return firstBigNum >= secondBigNum * 2 ? maxNumIndex : -1;
    }
}
