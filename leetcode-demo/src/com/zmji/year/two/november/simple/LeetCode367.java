package com.zmji.year.two.november.simple;

/**
 * 
 * 367. 有效的完全平方数 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 *
 * 进阶：不要 使用任何内置的库函数，如 sqrt 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：num = 16 输出：true 示例 2：
 *
 * 输入：num = 14 输出：false
 *
 *
 * 提示：
 *
 * 1 <= num <= 2^31 - 1
 * 
 * @author : zhongmou.ji
 * @date : 2021/11/4 下午4:03
 **/
public class LeetCode367 {

    // 使用Math自带的求幂方法
    public boolean isPerfectSquare(int num) {
        int x = (int)Math.sqrt(num);
        return x * x == num;
    }

    // 暴力破解
    public boolean isPerfectSquare1(int num) {
        long x = 1, square = 1;
        while (square <= num) {
            if (square == num) {
                return true;
            }
            ++x;
            square = x * x;
        }
        return false;
    }

    // 二分法
    public boolean isPerfectSquare2(int num) {
        int left = 0, right = num;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            long square = (long)mid * mid;
            if (square < num) {
                left = mid + 1;
            } else if (square > num) {
                right = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    // 牛顿迭代法
    public boolean isPerfectSquare3(int num) {
        return true;
    }

}
