package com.zmji.year.three.month.two;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author : zhongmou.ji
 * @date : 2022/2/23 11:24 AM
 **/
public class LeetCode405 {

    public static void main(String[] args) {
        System.out.println(checkCalculationFormulaValid("(a + 1 ++ sum(a)) + a", Set.of("a")));
    }

    /**
     * 自定义函数
     */
    public static final Set<String> FORMULA_FUNCTION_SET = Set.of("sum", "avg", "count", "min");

    /**
     * 运算符
     */
    public static final Set<Character> FORMULA_OPERATOR_SET = Set.of('+', '-', '*', '/');

    public static boolean checkCalculationFormulaValid(String expression, Set<String> parameterSet) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        int n = expression.length();
        // 将表达式中的 数字都替换成1, 参数名称替换成1, 去除自定义函数
        while (index < n) {
            char c = expression.charAt(index);
            if (c == '(' || c == ')' || FORMULA_OPERATOR_SET.contains(c)) {
                sb.append(c);
                index++;
            } else if (Character.isLetter(c)) {
                // 字符为英文，则往后截取直到遇到非英文或到达字符串尾
                StringBuilder currWord = new StringBuilder();
                while (index < n && Character.isLetter(expression.charAt(index))) {
                    currWord.append(expression.charAt(index));
                    index++;
                }

                if (FORMULA_FUNCTION_SET.contains(currWord.toString())) {

                    index = skipSpace(expression, index);
                    if (index == n) {
                        break;
                    }

                    /*
                     自定义函数后面必须跟'(' + 参数(只能是一个) + ')'
                     */
                    if (expression.charAt(index) != '(') {
                        return false;
                    }

                    sb.append('(');
                    index++;

                    index = skipSpace(expression, index);
                    if (index == n) {
                        return false;
                    }

                    // 判断其后是否是一个参数
                    StringBuilder nextWord = new StringBuilder();
                    while (index < n && Character.isLetter(expression.charAt(index))) {
                        nextWord.append(expression.charAt(index));
                        index++;
                    }

                    if (!parameterSet.contains(nextWord.toString())) {
                        return false;
                    }
                    sb.append(1);

                    if (index == n) {
                        return false;
                    }

                    index = skipSpace(expression, index);
                    if (index == n) {
                        return false;
                    }

                    if (expression.charAt(index) != ')') {
                        return false;
                    }
                    sb.append(')');
                    index++;

                } else if (parameterSet.contains(currWord.toString())) {
                    sb.append(1);
                    // 往后遍历直到遇到非空格字符
                    index = skipSpace(expression, index);
                    if (index == n) {
                        break;
                    }

                    // 参数后面只能跟上 ')'或者运算符号
                    char nextChar = expression.charAt(index);
                    if (!(nextChar == ')' || FORMULA_OPERATOR_SET.contains(nextChar))) {
                        return false;
                    }
                } else {
                    // 不为自定义函数也不为参数
                    return false;
                }
            } else if (Character.isDigit(c)) {
                /*
                 判断数字是否合法, 数字只能包含 数字字母和一个小数点 
                 */
                int pointCount = 0;
                while (index < n && (Character.isDigit(expression.charAt(index)) || expression.charAt(index) == '.')) {
                    if (expression.charAt(index) == '.') {
                        pointCount++;
                        if (pointCount > 1) {
                            return false;
                        }
                    }
                    index++;
                }
                sb.append(1);
            } else if (c == ' ') {
                index++;
            } else {
                // 存在不合法字符
                return false;
            }
        }

        String simpleExpression = sb.toString();
        // 校验()是否有效
        if (!checkBracketValid(simpleExpression)) {
            return false;
        }

        // 校验简单表达式, 只包含运算符，1，和()
        return checkSimpleExpression(simpleExpression);
    }

    /**
     * 从当前下标 往后遍历 跳过连续的空格，返回其第一个非空格下标
     *
     * @param str
     *            字符串
     * @param index
     *            开始遍历的下标
     * @return 非空格下表
     */
    private static int skipSpace(String str, int index) {
        int n = str.length();
        while (index < n && str.charAt(index) == ' ') {
            index++;
        }
        return index;
    }

    /**
     * 校验表达式中的 括号是否有效
     *
     * @param expression
     *            表达式
     * @return true-合法 false-不合法
     */
    private static boolean checkBracketValid(String expression) {
        int index = 0;
        int n = expression.length();
        Deque<Integer> stack = new LinkedList<>();
        while (index < n) {
            char c = expression.charAt(index);
            if (c == '(') {
                stack.push(index);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
            index++;
        }
        return stack.isEmpty();
    }

    /**
     * 校验简单表达式是否有效
     *
     * 1. '(' 前面必须是 ( 或 运算符; 后面必须是 数字 或 + -
     *
     * 2. ')' 前面必须是 ) 或 数字; 后面必须是 ) 或 运算符
     *
     * 3. 数字 前面 必须是 ( 或 运算符; 后面 必须是 ) 或 运算符
     *
     * 4. '+' '-' 前面必须是 ( ) 或 数字; 后面必须是 ( 或数字
     *
     * 5. '/' '*' 不能在第一位; 前面必须是 ) 或 数字; 后面必须是 ( 或 数字
     *
     * @param expression
     *            表达式，只包含数字,(,)和运算符，且表达式中的 ()都是有效的
     * @return true-合法 false-不合法
     */
    public static boolean checkSimpleExpression(String expression) {
        int index = 0;
        int n = expression.length();
        while (index < n) {
            char c = expression.charAt(index);
            if (c == '(') {
                /*
                '('
                前面必须是 ( 或 运算符
                后面必须是 数字 或 + -
                 */
                if (index > 0) {
                    char preChar = expression.charAt(index - 1);
                    if (!(preChar == '(' || FORMULA_OPERATOR_SET.contains(preChar))) {
                        return false;
                    }
                }

                char nextChar = expression.charAt(index + 1);
                if (!(Character.isDigit(nextChar) || nextChar == '+' || nextChar == '-')) {
                    return false;
                }

            } else if (c == ')') {
                /*
                ')'
                前面必须是 ) 或 数字
                后面必须是 ) 或 运算符
                 */
                char preChar = expression.charAt(index - 1);
                if (!(preChar == ')' || Character.isDigit(preChar))) {
                    return false;
                }

                if (index + 1 < n) {
                    char nextChar = expression.charAt(index + 1);
                    if (!(nextChar == ')' || FORMULA_OPERATOR_SET.contains(nextChar))) {
                        return false;
                    }
                }

            } else if (Character.isDigit(c)) {
                /*
                数字
                前面 必须是 ( 或 运算符
                后面 必须是 ) 或 运算符
                 */
                if (index > 0) {
                    char preChar = expression.charAt(index - 1);
                    if (!(preChar == '(' || FORMULA_OPERATOR_SET.contains(preChar))) {
                        return false;
                    }
                }

                if (index + 1 < n) {
                    char nextChar = expression.charAt(index + 1);
                    if (!(nextChar == ')' || FORMULA_OPERATOR_SET.contains(nextChar))) {
                        return false;
                    }
                }

            } else if (c == '+' || c == '-') {
                /*
                '+' '-'
                前面必须是 ( ) 或 数字
                后面必须是 ( 或数字
                 */
                if (index > 0) {
                    char preChar = expression.charAt(index - 1);
                    if (!(preChar == '(' || preChar == ')' || Character.isDigit(preChar))) {
                        return false;
                    }
                }

                if (index == n - 1) {
                    return false;
                }

                char nextChar = expression.charAt(index + 1);
                if (!(nextChar == '(' || Character.isDigit(nextChar))) {
                    return false;
                }

            } else if (c == '/' || c == '*') {
                /*
                '/' '*'
                不能在第一位
                前面必须是 ) 或 数字
                后面必须是 ( 或 数字
                 */
                if (index == 0) {
                    return false;
                }

                char preChar = expression.charAt(index - 1);
                if (!(Character.isDigit(preChar) || preChar == ')')) {
                    return false;
                }

                if (index == n - 1) {
                    return false;
                }
                char nextChar = expression.charAt(index + 1);
                if (!(Character.isDigit(nextChar) || nextChar == '(')) {
                    return false;
                }

            }
            index++;
        }

        return true;
    }
}
