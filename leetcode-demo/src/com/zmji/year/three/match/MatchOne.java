package com.zmji.year.three.match;

/**
 * @author : zhongmou.ji
 * @date : 2022/1/16 10:36 上午
 **/
public class MatchOne {

    public String[] divideString(String s, int k, char fill) {
        int len = s.length();
        int resLen = (len % k == 0) ? len / k : (len / k + 1);
        String[] res = new String[resLen];
        for (int i = 0; i < resLen - 1; i++) {
            res[i] = s.substring(i * k, (i + 1) * k);
        }
        int restFillLen = k * resLen - s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < restFillLen; i++) {
            sb.append(fill);
        }
        res[resLen - 1] = s.substring((resLen - 1) * k, s.length()) + sb;
        return res;
    }

    private int minTime = Integer.MAX_VALUE;

    public int minMoves(int target, int maxDoubles) {
        // 贪心，模拟，回溯
        // 动态规划
        return 0;
    }

    // private void backtrack(int target, int value, int operateTime, int maxDoubles) {
    // if (target == value) {
    // minTime = Math.min(minTime, operateTime);
    // }
    // for (int i = 0; )
    // }
}
