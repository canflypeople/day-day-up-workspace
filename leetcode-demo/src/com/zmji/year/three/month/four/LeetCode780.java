package com.zmji.year.three.month.four;

/**
 * @author : zhongmou.ji
 * @date : 2022/4/9 3:36 下午
 **/
public class LeetCode780 {

    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        // 数学， 逆向思维，从结果推断出开始, 递归
        while (tx > sx && ty > sy && tx != ty) {
            if (tx > ty) {
                tx %= ty;
            } else {
                ty %= tx;
            }
        }
        if (tx == sx && ty == sy) {
            return true;
        } else if (tx == sx) {
            return ty > sy && (ty - sy) % tx == 0;
        } else if (ty == sy) {
            return tx > sx && (tx - sx) % ty == 0;
        } else {
            return false;
        }
    }
}
