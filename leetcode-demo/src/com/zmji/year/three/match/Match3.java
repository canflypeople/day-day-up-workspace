package com.zmji.year.three.match;

import java.util.*;

/**
 * @author : zhongmou.ji
 * @date : 2022/3/27 10:31 上午
 **/
public class Match3 {

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums2) {
            set2.add(num);
        }
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        res.add(new ArrayList<>());
        Set<Integer> sett1 = new HashSet<>();
        Set<Integer> sett2 = new HashSet<>();
        for (int num : nums1) {
            if (set2.contains(num) && sett1.contains(num)) {
                continue;
            }
            sett1.add(num);
            res.get(0).add(num);
        }

        for (int num : nums2) {
            if (set1.contains(num) && sett1.contains(num)) {
                continue;
            }
            sett2.add(num);
            res.get(1).add(num);
        }
        return res;
    }

    public int minDeletion(int[] nums) {
        int index = 0;
        int newIndex = -1;
        int n = nums.length;
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        while (index < n) {
            int num = nums[index];
            if (newIndex % 2 == 0) {
                if (!stack.isEmpty()) {
                    if (Objects.equals(stack.peek(), num)) {
                        res++;
                    } else {
                        stack.push(num);
                        newIndex++;
                    }
                }
            } else {
                stack.push(num);
                newIndex++;
            }
            index++;
        }
        if (stack.size() % 2 == 1) {
            res++;
        }

        return res;

    }

    public static void main(String[] args) {
        Match3 match3 = new Match3();
        match3.minDeletion(new int[] {1, 1, 2, 2, 3, 3});
    }
}
