package com.zmji.year.three.month.three;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.zmji.util.TreeNode;

/**
 * @author : zhongmou.ji
 * @date : 2022/3/21 8:41 上午
 **/
public class LeetCode653 {

    public boolean findTarget(TreeNode root, int k) {
        // 通过前序遍历获取有序数组，针对数组进行二分查找
        List<Integer> list = new ArrayList<>();
        prevOrder(root, list);
        Integer[] arr = new Integer[list.size()];
        list.toArray(arr);

        int n = arr.length;
        int left = 0, right = n - 1;
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == k) {
                return true;
            } else if (sum < k) {
                left++;
            } else if (sum > k) {
                right--;
            }
        }
        return false;
    }

    private void prevOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        prevOrder(root.left, list);
        list.add(root.val);
        prevOrder(root.right, list);
    }

    public static void main(String[] args) {
        BigDecimal bigDecimal = BigDecimal.ZERO;
        bigDecimal.add(new BigDecimal("1"));
        System.out.println(bigDecimal);
    }
}
