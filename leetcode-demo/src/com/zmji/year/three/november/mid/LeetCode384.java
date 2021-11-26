package com.zmji.year.three.november.mid;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 
 * 384. 打乱数组 给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。
 *
 * 实现 Solution class:
 *
 * Solution(int[] nums) 使用整数数组 nums 初始化对象 int[] reset() 重设数组到它的初始状态并返回 int[] shuffle() 返回数组随机打乱后的结果
 *
 *
 * 示例：
 *
 * 输入 ["Solution", "shuffle", "reset", "shuffle"] [[[1, 2, 3]], [], [], []] 输出 [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
 *
 * 解释 Solution solution = new Solution([1, 2, 3]); solution.shuffle(); // 打乱数组 [1,2,3] 并返回结果。任何
 * [1,2,3]的排列返回的概率应该相同。例如，返回 [3, 1, 2] solution.reset(); // 重设数组到它的初始状态 [1, 2, 3] 。返回 [1, 2, 3] solution.shuffle(); //
 * 随机返回数组 [1, 2, 3] 打乱后的结果。例如，返回 [1, 3, 2]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 200 -106 <= nums[i] <= 106 nums 中的所有元素都是 唯一的 最多可以调用 5 * 104 次 reset 和 shuffle
 * 通过次数57,745提交次数98,855
 * 
 * @author : zhongmou.ji
 * @date : 2021/11/22 上午8:34
 **/
public class LeetCode384 {

    class Solution {
        int[] origin;
        int[] curr;

        private Random random;

        public Solution(int[] nums) {
            origin = new int[nums.length];
            curr = new int[nums.length];
            random = new Random();
            System.arraycopy(nums, 0, curr, 0, nums.length);
            System.arraycopy(nums, 0, origin, 0, nums.length);
        }

        /**
         * 将数组恢复至初始的状态
         * 
         * @return
         */
        public int[] reset() {
            curr = new int[origin.length];
            System.arraycopy(origin, 0, curr, 0, origin.length);
            return origin;
        }

        /**
         * 打乱数组
         * 
         * @return
         */
        public int[] shuffle() {
            for (int i = 0; i < curr.length; i++) {
                swapAt(curr, i, rangeRange(i, curr.length));
            }
            return curr;

        }

        private int rangeRange(int i, int length) {
            return i + random.nextInt(length - i);
        }

        private void swapAt(int[] arr, int index1, int index2) {
            int temp = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = temp;
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list.stream().filter(i -> Objects.equals(1, i)).collect(Collectors.toList()).size());
    }
}
