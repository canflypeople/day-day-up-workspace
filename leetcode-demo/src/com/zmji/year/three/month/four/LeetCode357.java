package com.zmji.year.three.month.four;

/**
 * @author : zhongmou.ji
 * @date : 2022/4/11 8:43 上午
 **/
public class LeetCode357 {

    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 0;
        }
        int prevCount = 10;
        for (int i = 2; i <= n; i++) {
            int currCount = 9;
            int multNum = 9;
            int tempI = i - 1;
            while (tempI > 0) {
                currCount = currCount * multNum;
                multNum--;
                tempI--;
            }
            prevCount += currCount;
        }
        return prevCount;
    }

    public static void main(String[] args) {
        LeetCode357 main = new LeetCode357();
        main.countNumbersWithUniqueDigits(2);
    }
}
