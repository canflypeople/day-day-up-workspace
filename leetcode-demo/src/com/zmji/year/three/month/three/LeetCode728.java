package com.zmji.year.three.month.three;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhongmou.ji
 * @date : 2022/3/31 9:33 上午
 **/
public class LeetCode728 {

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isSelfDividing(i)) {
                ans.add(i);
            }
        }
        return ans;

    }

    private boolean isSelfDividing(int num) {
        int temp = num;
        while (temp > 0) {
            int digit = temp % 10;
            if (digit == 0 || num % digit != 0) {
                return false;
            }
            temp /= 10;
        }
        return true;
    }

}
