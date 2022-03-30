package com.zmji.year.two.november.simple;

import com.zmji.util.TreeNode;

/**
 * 700. 二叉搜索树中的搜索 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
 *
 * 例如，
 *
 * 给定二叉搜索树:
 *
 * 4 / \ 2 7 / \ 1 3
 *
 * 和值: 2 你应该返回如下子树:
 *
 * 2 / \ 1 3 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。
 * 
 * @author : zhongmou.ji
 * @date : 2021/11/26 8:05 上午
 **/
public class LeetCode700 {

    public TreeNode searchBST(TreeNode root, int val) {
        // 广度优先遍历
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }
        TreeNode leftRes = searchBST(root.left, val);
        if (leftRes != null) {
            return leftRes;
        }
        TreeNode rightRes = searchBST(root.right, val);
        if (rightRes != null) {
            return rightRes;
        }
        return null;

    }
}
