package com.zmji.year.three.month.three;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author : zhongmou.ji
 * @date : 2022/3/17 5:18 下午
 **/
public class LeetCode432 {

    class AllOne {
        Node root;
        Map<String, Node> nodes;

        public AllOne() {
            root = new Node();
            root.next = root;
            root.prev = root;
            nodes = new HashMap<>();
        }

        public void inc(String key) {
            if (nodes.containsKey(key)) {
                // 判断是否要移除列表
                Node curr = nodes.get(key);
                Node next = curr.next;
                // 若当前node是空的则移除当前的key
                if (next == root || next.count > curr.count + 1) {
                    nodes.put(key, curr.insert(new Node(key, curr.count + 1)));
                } else {
                    next.keys.add(key);
                    nodes.put(key, next);
                }
                curr.keys.remove(key);
                if (curr.keys.isEmpty()) {
                    curr.remove();
                }
            } else {
                if (root.next == root || root.next.count > 1) {
                    nodes.put(key, root.insert(new Node(key, 1)));
                } else {
                    root.next.keys.add(key);
                    nodes.put(key, root.next);
                }
            }
        }

        public void dec(String key) {
            Node curr = nodes.get(key);
            if (curr.count == 1) {
                nodes.remove(key);
            } else {
                Node prev = curr.prev;
                if (prev == root || prev.count < curr.count - 1) {
                    nodes.put(key, new Node(key, curr.count - 1));
                } else {
                    prev.keys.add(key);
                    nodes.put(key, prev);
                }
            }

            curr.keys.remove(key);
            if (curr.keys.isEmpty()) {
                curr.remove();
            }
        }

        public String getMaxKey() {
            return root.prev != null ? root.prev.keys.iterator().next() : "";
        }

        public String getMinKey() {
            return root.next != null ? root.next.keys.iterator().next() : "";
        }
    }

    static class Node {
        Node prev;
        Node next;
        Set<String> keys;
        int count;

        public Node() {
            this("", 0);
        }

        public Node(String key, int count) {
            this.count = count;
            keys = new HashSet<>();
            keys.add(key);
        }

        public Node insert(Node node) {
            node.next = this.next;
            node.prev = this;
            this.next.prev = node;
            this.next = node;

            return node;
        }

        public void remove() {
            this.prev.next = this.next;
            this.next.prev = this.prev;
        }
    }

}
