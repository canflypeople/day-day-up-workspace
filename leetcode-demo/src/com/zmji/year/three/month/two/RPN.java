package com.zmji.year.three.month.two;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author : zhongmou.ji
 * @date : 2022/2/22 2:24 PM
 **/
public class RPN {

    public static void main(String[] args) {
        System.out.println(checkCalculationFormulaValid("-sum(ab  + b) / avg(c+1) + 11.00", Set.of("ab", "b", "c")));
    }

    /**
     * 自定义函数
     */
    public static final Set<String> FORMULA_FUNCTION_SET = Set.of("sum", "avg", "count", "max", "min");

    /**
     * 运算符
     */
    public static final Set<Character> FORMULA_OPERATOR_SET = Set.of('+', '-', '*', '/');

    /**
     * 校验表达式是否合法
     *
     * @param expression
     *            表达式
     * @param parameterSet
     *            参数集合
     * @return true-合法 false-不合法
     */
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
                    // 自定义函数后面必须跟'('
                    if (expression.charAt(index) != '(') {
                        return false;
                    }
                } else if (parameterSet.contains(currWord.toString())) {
                    sb.append(1);
                    // 往后遍历直到遇到非空格字符
                    while (index < n && expression.charAt(index) == ' ') {
                        index++;
                    }
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
        System.out.println(simpleExpression);
        // 校验()是否有效
        if (!checkBracketValid(simpleExpression)) {
            return false;
        }

        // 校验简单表达式, 只包含运算符，1，和()
        return checkSimpleExpression(simpleExpression);
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
