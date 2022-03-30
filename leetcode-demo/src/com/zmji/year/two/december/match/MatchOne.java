package com.zmji.year.two.december.match;

import java.util.*;

/**
 * @author : zhongmou.ji
 * @date : 2021/12/5 10:56 上午
 **/
public class MatchOne {

    /**
     * 5942. 找出 3 位偶数 显示英文描述 User Accepted:1582 User Tried:1828 Total Accepted:1586 Total Submissions:2659
     * Difficulty:Easy 给你一个整数数组 digits ，其中每个元素是一个数字（0 - 9）。数组中可能存在重复元素。
     *
     * 你需要找出 所有 满足下述条件且 互不相同 的整数：
     *
     * 该整数由 digits 中的三个元素按 任意 顺序 依次连接 组成。 该整数不含 前导零 该整数是一个 偶数 例如，给定的 digits 是 [1, 2, 3] ，整数 132 和 312 满足上面列出的全部条件。
     *
     * 将找出的所有互不相同的整数按 递增顺序 排列，并以数组形式返回。
     * 
     * @param digits
     * @return
     */
    public int[] findEvenNumbers(int[] digits) {
        Set<Integer>[] setArr = new HashSet[3];
        for (int i = 0; i < 3; i++) {
            setArr[i] = new HashSet<>();
        }
        List<StringBuilder> sbs = new ArrayList<>();
        Map<Integer, Integer> digitCountMap = new HashMap<>();
        for (int num : digits) {
            digitCountMap.put(num, digitCountMap.getOrDefault(num, 0) + 1);
            if (num != 0) {
                setArr[0].add(num);
            }
            setArr[1].add(num);
            if (num % 2 == 0) {
                setArr[2].add(num);
            }
        }
        backtrack(sbs, setArr, new StringBuilder(), 0);
        Iterator<StringBuilder> iterator = sbs.iterator();
        while (iterator.hasNext()) {
            StringBuilder sb = iterator.next();
            Map<Integer, Integer> numCount = new HashMap<>();
            for (char c : sb.toString().toCharArray()) {
                int num = c - '0';
                numCount.put(num, numCount.getOrDefault(num, 0) + 1);
            }
            for (Map.Entry<Integer, Integer> entry : numCount.entrySet()) {
                if (digitCountMap.get(entry.getKey()) < entry.getValue()) {
                    iterator.remove();
                }
            }
        }
        int[] resArr = new int[sbs.size()];
        for (int i = 0; i < sbs.size(); i++) {
            resArr[i] = Integer.parseInt(sbs.get(i).toString());
        }
        Arrays.sort(resArr);
        return resArr;

    }

    private void backtrack(List<StringBuilder> res, Set<Integer>[] setArr, StringBuilder sb, int index) {
        if (sb.length() == 3) {
            res.add(new StringBuilder(sb));
            return;
        }
        for (int num : setArr[index]) {
            sb.append(num);
            backtrack(res, setArr, sb, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public ListNode deleteMiddle(ListNode head) {
        int len = 0;
        ListNode dummyNode = new ListNode(0, head);
        while (dummyNode.next != null) {
            len++;
            dummyNode = dummyNode.next;
        }
        int mid = (len + 2) / 2;
        dummyNode = new ListNode(0, head);
        for (int i = 1; i < mid; i++) {
            dummyNode = dummyNode.next;
        }
        dummyNode.next = dummyNode.next.next;
        if (mid == 1) {
            return null;
        }
        return head;
    }

    /**
     * Definition for singly-linked list.
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // public int[] findEvenNumbers(int[] digits) {
    // // backtrack
    // StringBuilder sb = new StringBuilder();
    // Set<Integer> res = new HashSet<>();
    //
    // backtrack(res, digits, sb, 0);
    // int[] resArr = new int[res.size()];
    // int index = 0;
    // for (Integer num : res) {
    // resArr[index++] = num;
    // }
    // Arrays.sort(resArr);
    // return resArr;
    //
    // }
    //
    // private void backtrack(Set<Integer> res, int[] digits, StringBuilder sb, int index) {
    // if (sb.length() == 3) {
    // if (Integer.valueOf(sb.charAt(2)) % 2 == 0) {
    // res.add(Integer.valueOf(sb.toString()));
    // }
    // return;
    // }
    // for (int i = index; i < digits.length; i++) {
    // if (sb.length() == 0 && digits[i] == 0) {
    // continue;
    // }
    // sb.append(digits[i]);
    // backtrack(res, digits, sb, i + 1);
    // sb.deleteCharAt(sb.length() - 1);
    // }
    // }

    public static void main(String[] args) {
        MatchOne matchOne = new MatchOne();
        // [1,3,4,7,1,2,6]
        ListNode node7 = new ListNode(6);
        ListNode node6 = new ListNode(2, node7);
        ListNode node5 = new ListNode(1, node6);
        ListNode node4 = new ListNode(7, node5);
        ListNode node3 = new ListNode(4, node4);
        ListNode node2 = new ListNode(3, node3);
        ListNode node1 = new ListNode(1, node2);
        matchOne.deleteMiddle(node1);

        matchOne.findEvenNumbers(new int[] {2, 1, 3, 0});
    }
}
