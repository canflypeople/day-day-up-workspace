package com.zmji.year.two.december.simple;

import java.util.Objects;

/**
 * @author : zhongmou.ji
 * @date : 2021/12/6 7:48 上午
 **/
public class LeetCode1816 {

    public String truncateSentence(String s, int k) {
        char space = ' ';
        StringBuilder res = new StringBuilder();
        int count = 0;
        int index = 0;
        while (true) {
            if (Objects.equals(s.charAt(index), space)) {
                count++;
            }
            if (count == k) {
                break;
            }
            res.append(s.charAt(index));
            index++;
        }
        return res.toString();
    }

    public static void main(String[] args) {
        LeetCode1816 leetCode1816 = new LeetCode1816();
        leetCode1816.truncateSentence("Hello how are you Contestant", 4);
    }
}
