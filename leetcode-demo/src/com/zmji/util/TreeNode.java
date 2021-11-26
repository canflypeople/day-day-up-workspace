package com.zmji.util;

/**
 * Definition for a binary tree node.
 * 
 * @author : zhongmou.ji
 * @date : 2021/11/18 上午7:57
 **/
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
