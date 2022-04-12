package com.zmji.year.three.match;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhongmou.ji
 * @date : 2022/4/10 11:25 上午
 **/
public class Match41 {

    public int largestInteger(int num) {
        String s = String.valueOf(num);
        int n = s.length();
        boolean[] isOddNums = new boolean[n];
        List<Integer> oddNums = new ArrayList<>();
        List<Integer> evenNums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int currNum = c - '0';
            boolean isOddNum = currNum % 2 != 0;
            if (isOddNum) {
                oddNums.add(currNum);
            } else {
                evenNums.add(currNum);
            }
            isOddNums[i] = isOddNum;
        }
        StringBuilder sb = new StringBuilder();
        oddNums.sort((o1, o2) -> o1 > o2 ? -1 : 1);
        evenNums.sort((o1, o2) -> o1 > o2 ? -1 : 1);
        for (int i = 0; i < n; i++) {
            if (isOddNums[i]) {
                sb.append(oddNums.get(0));
                oddNums.remove(0);
            } else {
                sb.append(evenNums.get(0));
                evenNums.remove(0);
            }
        }
        return Integer.parseInt(sb.toString());
    }

    public String minimizeResult(String expression) {
        // 暴力破解
        String[] strs = expression.split("/+");
        int len1 = strs[0].length();
        int len2 = strs[1].length();
        int num1 = Integer.parseInt(strs[0]);
        int num2 = Integer.parseInt(strs[1]);
        int minValue = num1 + num2;
        String res = "";
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                int mult1, currNum1;
                if (i == 0 || i == len1) {
                    mult1 = 1;
                    currNum1 = num1;
                } else {
                    mult1 = Integer.parseInt(strs[0].substring(0, i));
                    currNum1 = Integer.parseInt(strs[0].substring(i, len1));
                }
                int mult2, currNum2;
                if (j == 0 || j == len1) {
                    mult2 = 1;
                    currNum2 = num2;
                } else {
                    currNum2 = Integer.parseInt(strs[0].substring(0, i));
                    mult2 = Integer.parseInt(strs[0].substring(i, len1));
                }
                int num = mult1 * mult2 * (currNum1 + currNum2);
                if (num < minValue) {
                    minValue = num;
                }
            }
        }
        return String.valueOf(minValue);
    }
}
