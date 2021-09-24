package com.zmji.util;

import java.util.Collection;

/**
 * @author zhongmou.ji
 */
public class ListNodeUtils {

    public static ListNode getListNode(int[] arr) {
        ListNode dummyNode = new ListNode();
        ListNode tempNode = dummyNode;
        for (int num : arr) {
            ListNode node = new ListNode(num);
            dummyNode.next = node;
            dummyNode = dummyNode.next;
        }
        return tempNode.next;
    }

    public static ListNode getListNode(Collection<Integer> collection){
        ListNode dummyNode = new ListNode();
        ListNode tempNode = dummyNode;
        for (int num : collection) {
            ListNode node = new ListNode(num);
            dummyNode.next = node;
            dummyNode = dummyNode.next;
        }
        return tempNode.next;
    }
}
