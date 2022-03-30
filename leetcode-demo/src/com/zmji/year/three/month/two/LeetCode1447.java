package com.zmji.year.three.month.two;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhongmou.ji
 * @date : 2022/2/10 4:27 PM
 **/
public class LeetCode1447 {

    public List<String> simplifiedFractions(int n) {
        List<String> ans = new ArrayList<String>();
        for (int denominator = 2; denominator <= n; ++denominator) {
            for (int numerator = 1; numerator < denominator; ++numerator) {
                if (gcd(numerator, denominator) == 1) {
                    ans.add(numerator + "/" + denominator);
                }
            }
        }
        return ans;
    }

    public int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }
}
