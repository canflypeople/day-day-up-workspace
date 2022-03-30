package com.zmji.year.three.month.three;

import java.util.Objects;

/**
 * @author : zhongmou.ji
 * @date : 2022/3/28 4:58 下午
 **/
public class LeetCode693 {

    public boolean hasAlternatingBits(int n) {
        boolean currValue = Objects.equals(n % 2, 1);
        n = n / 2;
        while (n > 0) {
            if (currValue) {
                if (n % 2 != 0) {
                    return false;
                }
            } else {
                if (n % 2 != 1) {
                    return false;
                }
            }
            currValue = !currValue;
            n /= 2;
        }
        return true;
    }

    public boolean hasAlternatingBits1(int n) {
        int prev = 2;
        while (n != 0) {
            int curr = n % 2;
            if (curr == prev) {
                return false;
            }
            prev = curr;
            n /= 2;
        }
        return true;
    }

    public boolean hasAlternatingBits2(int n) {
        int a = n ^ (n >> 1);
        return (a & (a + 1)) == 0;
    }

    public static void main(String[] args) {}

}
