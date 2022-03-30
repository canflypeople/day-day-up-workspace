package com.zmji.year.three.month.one;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.zmji.util.ListNode;

/**
 * @author : zhongmou.ji
 * @date : 2022/1/16 8:37 下午
 **/
public class LeetCode383 {

    class Solution {

        private List<ListNode> listNodes = new ArrayList<>();

        public Solution(ListNode head) {
            ListNode dummyNode = new ListNode(0, head);
            while (dummyNode.next != null) {
                listNodes.add(dummyNode.next);
                dummyNode = dummyNode.next;
            }

        }

        public int getRandom() {
            Random random = new Random();
            int index = random.nextInt(listNodes.size());
            return listNodes.get(index).val;
        }
    }
}
