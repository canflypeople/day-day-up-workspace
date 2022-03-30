package com.zmji.year.two.december;

/**
 * @author : zhongmou.ji
 * @date : 2021/12/7 2:59 下午
 **/
public class Main {

    public static int maxGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int len = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        if (min == max) {
            return 0;
        }
        // 不止一种数，min~max一定有range, len个数据，准备len+1个桶
        boolean[] hasNum = new boolean[len + 1]; // hasNum[i] i号桶是否进来过数字
        int[] maxs = new int[len + 1]; // maxs[i] i号桶收集的所有数字的最大值
        int[] mins = new int[len + 1]; // mins[i] i号桶收集的所有数字的最小值

        int bid = 0; // 桶号
        for (int i = 0; i < len; i++) {
            bid = bucket(nums[i], len, min, max);
            mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
            hasNum[bid] = true;
        }
        int res = 0;
        int lastMax = maxs[0]; // 上一个非空桶的最大值
        int i = 1;
        for (; i <= len; i++) {
            if (hasNum[i]) {
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    /**
     * 只考虑到数组中元素等于非负数的情况 若存在负数， 需考虑偏移....
     *
     * @param nums
     * @return
     */
    public static int findMaxDValue(int[] nums) {
        int maxNum = Integer.MIN_VALUE;
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }
        boolean[] count = new boolean[maxNum + 1];
        for (int num : nums) {
            count[num] = true;
        }
        int res = 0;
        int prev = -1;
        for (int i = 0; i < count.length; i++) {
            if (Boolean.TRUE.equals(count[i])) {
                if (prev != -1) {
                    res = Math.max(res, i - prev);
                }
                prev = i;
            }
        }
        return res;
    }

    public static int bucket(long num, long len, long min, long max) {
        return (int)((num - min) * len / (max - min));
    }

    public static void main(String[] args) {
        long nowTime = System.nanoTime();
        int res1 = maxGap(new int[] {3, 1, 7, 9});
        System.out.println(System.nanoTime() - nowTime);
        System.out.println(res1);
    }
}
