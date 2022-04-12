package com.zmji.year.three.month.four;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author : zhongmou.ji
 * @date : 2022/4/8 8:32 上午
 **/
public class LeetCode429 {

    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> res = new ArrayList<>();
        List<Node> currNodes = new ArrayList<>();
        currNodes.add(root);
        while (!currNodes.isEmpty()) {
            List<Integer> currRes = new ArrayList<>();
            List<Node> nextNodes = new ArrayList<>();
            for (Node node : currNodes) {
                currRes.add(node.val);
                if (node.children != null && !node.children.isEmpty()) {
                    nextNodes.addAll(node.children);
                }
            }
            currNodes = nextNodes;
            res.add(currRes);
        }
        return res;
    }

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int val) {
            val = val;
        }

        public Node(int val, List<Node> children) {
            val = val;
            children = children;
        }
    };

}
