package com.zmji.year.three.month.one;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import com.zmji.year.two.december.mid.LeetCode1034;

/**
 * @author : zhongmou.ji
 * @date : 2022/1/14 8:00 上午
 **/
public class LeetCode373 {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // 双指针，优先队列
        PriorityQueue<Pair<Integer, Integer>> priorityQueue =
            new PriorityQueue<Pair<Integer, Integer>>((o1, o2) -> o1.first + o1.second > o2.first + o2.second ? 1 : -1);
        for (int i = 0; (i < nums1.length && i < k); i++) {
            for (int j = 0; (j < nums2.length && j < k); j++) {
                priorityQueue.add(new Pair<>(nums1[i], nums2[2]));
                // if (priorityQueue.size() > k) {
                // priorityQueue.poll();
                // }
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            Pair<Integer, Integer> pair = priorityQueue.poll();
            list.add(pair.first);
            list.add(pair.second);
            res.add(list);
        }
        return res;

    }

    public static class Pair<T, U> {

        public Pair(T first, U second) {
            this.second = second;
            this.first = first;
        }

        public final T first;
        public final U second;

        // Because 'pair()' is shorter than 'new Pair<>()'.
        // Sometimes this difference might be very significant (especially in a
        // 80-ish characters boundary). Sorry diamond operator.
        public static <T, U> LeetCode1034.Pair<T, U> pair(T first, U second) {
            return new LeetCode1034.Pair<>(first, second);
        }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }
}
