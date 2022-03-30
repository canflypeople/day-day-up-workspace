package com.zmji.year.three.month.one;

/**
 * @author : zhongmou.ji
 * @date : 2022/1/15 10:50 下午
 **/
public class LeetCode1716 {

    public static final int[] count = new int[] {};

    public int totalMoney(int n) {
        int weekNumber = n / 7;
        int firstWeekMoney = (1 + 7) * 7 / 2;
        int lastWeekMoney = firstWeekMoney + 7 * (weekNumber - 1);
        int weekMoney = (firstWeekMoney + lastWeekMoney) * weekNumber / 2;
        // 剩下的不能构成一个完整的周的天数里存的钱
        int dayNumber = n % 7;
        int firstDayMoney = 1 + weekNumber;
        int lastDayMoney = firstDayMoney + dayNumber - 1;
        int dayMoney = (firstDayMoney + lastDayMoney) * dayNumber / 2;
        return weekMoney + dayMoney;
    }

}
