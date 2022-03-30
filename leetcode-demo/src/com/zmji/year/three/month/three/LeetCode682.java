package com.zmji.year.three.month.three;

import java.util.Objects;
import java.util.Stack;

/**
 * @author : zhongmou.ji
 * @date : 2022/3/26 9:49 下午
 **/
public class LeetCode682 {

    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();
        for (String op : ops) {
            if (Objects.equals(op, "C")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (Objects.equals(op, "D")) {
                if (!stack.isEmpty()) {
                    stack.push(stack.peek() * 2);
                }
            } else if (Objects.equals(op, "+")) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num1);
                stack.push(num2);
                stack.push(num1 + num2);
            } else if (Character.isDigit(op.charAt(0)) || op.charAt(0) == '-') {
                stack.push(Integer.parseInt(op));
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode682 main = new LeetCode682();
        main.calPoints(new String[] {"5", "-2", "4", "C", "D", "9", "+", "+"});
    }
}
