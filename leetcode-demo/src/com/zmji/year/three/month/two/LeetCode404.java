package com.zmji.year.three.month.two;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * @author : zhongmou.ji
 * @date : 2022/2/21 2:46 PM
 **/
public class LeetCode404 {

    private static final Set<String> FORMULA_FUNCTION_SET = Set.of("sum", "avg");

    private static final Set<Character> FORMULA_OPERATOR_SET = Set.of('+', '-', '*', '/');

    public static void main(String[] args) {
        System.out.println(check1("-sum(ab  + b) / avg(c) + 11.00", Set.of("ab", "b", "c")));
    }

    public static boolean check1(String expression, Set<String> parameterSet) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        int n = expression.length();
        while (index < n) {
            char c = expression.charAt(index);
            if (c == '(' || c == ')') {
                sb.append(c);
                index++;
            } else if (Character.isLetter(c)) {
                StringBuilder currWord = new StringBuilder();
                while (Character.isLetter(expression.charAt(index))) {
                    currWord.append(expression.charAt(index));
                    index++;
                }
                if (FORMULA_FUNCTION_SET.contains(currWord.toString())) {
                    // 自定义函数后面必须跟'('
                    if (expression.charAt(index) != '(') {
                        throw new RuntimeException();
                    }
                } else if (parameterSet.contains(currWord.toString())) {
                    sb.append(1);
                    // 参数后面只能跟上 ')'或者运算符号，后面没有东西了
                    while (index < n && expression.charAt(index) == ' ') {
                        index++;
                    }
                    if (index == n) {
                        break;
                    }
                    char nextChar = expression.charAt(index);
                    if (nextChar == ')' || FORMULA_OPERATOR_SET.contains(nextChar)) {
                        sb.append(nextChar);
                        index++;
                        continue;
                    }
                    int tempIndex = index;
                    while (tempIndex < n && expression.charAt(tempIndex) == ' ') {
                        tempIndex++;
                    }
                    if (tempIndex == n) {
                        break;
                    }
                    throw new RuntimeException();
                } else {
                    throw new RuntimeException();
                }
            } else if (FORMULA_OPERATOR_SET.contains(c)) {
                sb.append(c);
                index++;
                // 操作符号后必须是参数
            } else if (Character.isDigit(c)) {
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
                throw new RuntimeException();
            }
        }

        String simpleExpression = sb.toString();
        System.out.println(simpleExpression);

        // 校验()是否有效
        if (!checkValid(simpleExpression)) {
            return false;
        }

        // 校验简单字符, 只包含运算符，1，和()
        return checkSimpleExpression(simpleExpression);
    }

    public static boolean checkSimpleExpression(String simpleExpression) {
        /*
        1. 数字后面必须是)或者运算符，数字前面必须是(或者+，-
        2. (后面必须是数字，或者 + -, (前面必须是 ( 或者运算符
        3. )前面必须是 )或数字
        4. + - 前面必须是( 或者数字
        5. * / 前面必须是数字
         */
        int index = 0;
        int n = simpleExpression.length();
        while (index < n) {
            char c = simpleExpression.charAt(index);
            if (c == '(') {
                if (index > 0) {
                    char preChar = simpleExpression.charAt(index - 1);
                    if (!(preChar == '(' || FORMULA_OPERATOR_SET.contains(preChar))) {
                        return false;
                    }
                }

                char nextChar = simpleExpression.charAt(index + 1);
                if (!(Character.isDigit(nextChar) || nextChar == '+' || nextChar == '-')) {
                    return false;
                }

            } else if (c == ')') {
                char preChar = simpleExpression.charAt(index - 1);
                if (!(preChar == ')' || Character.isDigit(preChar))) {
                    return false;
                }

                if (index + 1 < n) {
                    char nextChar = simpleExpression.charAt(index + 1);
                    if (!FORMULA_OPERATOR_SET.contains(nextChar)) {
                        return false;
                    }
                }

            } else if (Character.isDigit(c)) {

                /*
                1. 数字后面必须是)或者运算符，数字前面必须是(或者+，-
                2. (后面必须是数字，或者 + -, (前面必须是 ( 或者运算符
                3. )前面必须是 )或数字
                4. + - 前面必须是( 或者数字
                5. * / 前面必须是数字
                */
                if (index > 0) {
                    char preChar = simpleExpression.charAt(index - 1);
                    if (!(preChar == '(' || FORMULA_OPERATOR_SET.contains(preChar))) {
                        return false;
                    }
                }

                if (index + 1 < n) {
                    char nextChar = simpleExpression.charAt(index + 1);
                    if (!(nextChar == ')' || FORMULA_OPERATOR_SET.contains(nextChar))) {
                        return false;
                    }
                }

            } else if (c == '+' || c == '-') {
                if (index > 0) {
                    char preChar = simpleExpression.charAt(index - 1);
                    if (!(preChar == '(' || preChar == ')' || Character.isDigit(preChar))) {
                        return false;
                    }
                }

                if (index == n - 1) {
                    return false;
                }

                char nextChar = simpleExpression.charAt(index + 1);
                if (!(nextChar == '(' || Character.isDigit(nextChar))) {
                    return false;
                }

            } else if (c == '/' || c == '*') {

                /*
                1. 数字后面必须是)或者运算符，数字前面必须是(或者+，-
                2. (后面必须是数字，或者 + -, (前面必须是 ( 或者运算符
                3. )前面必须是 )或数字
                4. + - 前面必须是( 或者数字
                5. * / 前面必须是数字
                */
                if (index == 0) {
                    return false;
                }

                char preChar = simpleExpression.charAt(index - 1);
                if (!(Character.isDigit(preChar) || preChar == ')')) {
                    return false;
                }

                if (index == n - 1) {
                    return false;
                }
                char nextChar = simpleExpression.charAt(index + 1);
                if (!(Character.isDigit(nextChar) || nextChar == '(')) {
                    return false;
                }

            }
            index++;
        }

        return true;

    }

    private static boolean checkValid(String simpleExpression) {
        int index = 0;
        int n = simpleExpression.length();
        Stack<Integer> stack = new Stack<>();
        while (index < n) {
            char c = simpleExpression.charAt(index);
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

    static class Parameter {
        int beginIndex;
        int endIndex;
        List<Parameter> sonParameters;

        public Parameter(int beginIndex, int endIndex) {
            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
            this.sonParameters = new ArrayList<>();
        }
    }

    public void parse(String expression) {
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        int n = expression.length();
        Parameter parameter = new Parameter(0, n - 1);
        while (index < n) {
            char c = expression.charAt(index);
            if (c == '(') {
                stack.push(index);
            }
        }
    }

    public static boolean check(String expression, Set<String> parameterSet) {

        StringBuilder sb = new StringBuilder();
        int index = 0;
        int n = expression.length();
        while (index < n) {
            char c = expression.charAt(index);
            if (c == '(' || c == ')') {
                sb.append(c);
                index++;
            } else if (Character.isLetter(c)) {
                StringBuilder currWord = new StringBuilder();
                while (Character.isLetter(expression.charAt(index))) {
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
                    // 参数后面只能跟上 ')'或者运算符号
                    char nextChar = expression.charAt(index);
                    if (!(nextChar == ')' || FORMULA_OPERATOR_SET.contains(nextChar))) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else if (FORMULA_OPERATOR_SET.contains(c)) {
                sb.append(c);
                index++;
            } else if (Character.isDigit(c)) {
                int beginIndex = index;
                int pointCount = 0;
                while (index < n && (Character.isDigit(expression.charAt(index)) || expression.charAt(index) == '.')) {
                    if (expression.charAt(index) == '.') {
                        pointCount++;
                        if (pointCount > 1) {
                            return false;
                        }
                    }
                    sb.append(expression, beginIndex, index);
                    index++;
                }
            } else {
                // 存在不合法字符
                return false;
            }
        }

        String simpleExpression = sb.toString();
        System.out.println(simpleExpression);
        return false;

    }

    private static boolean checkNumExpression(String simpleExpression, int beginIndex, int endIndex) {
        int index = beginIndex;
        for (char c : simpleExpression.toCharArray()) {
            if (!Character.isDigit(c) || !FORMULA_OPERATOR_SET.contains(c)) {
                return false;
            }
        }
        // 开头的字符不能为 *或/
        if (simpleExpression.charAt(beginIndex) == '*' || simpleExpression.charAt(beginIndex) == '/') {
            return false;
        }
        // 末尾不能存在运算符
        if (FORMULA_OPERATOR_SET.contains(simpleExpression.charAt(endIndex))) {
            return false;
        }
        boolean preIsNum = Character.isDigit(index);
        index++;
        return false;
    }

    // private void checkCalculationFormulaValid(String calculationFormula, Set<String> parameterSet) {
    //
    // int index = 0;
    // int n = calculationFormula.length();
    // Stack<String> parameterStack = new Stack<>();
    // boolean nextNeedParameter = false;
    // while (index < n) {
    // char c = calculationFormula.charAt(index);
    // if (c == '*' || c == '/') {
    // if (parameterSet.isEmpty()) {
    // throw new RuntimeException("表达式不合法");
    // }
    // nextNeedParameter = true;
    // } else if (FORMULA_OPERATOR_SET.contains(c)) {
    // if (nextNeedParameter) {
    // throw new RuntimeException("表达式不合法");
    // }
    // nextNeedParameter = true;
    // } else if ()
    // index++;
    // }
    // }

    public boolean checkExpression(String expression, int index, Set<String> parameterSet) {
        // 在进行校验时忽略运算符的优先级
        int n = expression.length();
        Stack<Integer> bracketIndexStack = new Stack<>();
        int parameterCount = 0;
        while (index < n) {
            char c = expression.charAt(index);
            if (Character.isLetter(c)) {
                StringBuilder sb = new StringBuilder();
                while (Character.isLetter(expression.charAt(index))) {
                    sb.append(expression.charAt(index));
                    index++;
                }
                String str = sb.toString();
                if (FORMULA_FUNCTION_SET.contains(str)) {

                    // 后面必须跟上括号
                } else if (parameterSet.contains(str)) {
                    // 就是一个参数
                } else {
                    // 有不合法的参数
                    return false;
                }
            }
        }
        return false;
    }

    public boolean checkExpression(String expression, Set<String> parameterSet) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        int n = expression.length();
        while (index < n) {
            char c = expression.charAt(index);
            if (c == '(' || c == ')') {
                sb.append(c);
                index++;
            } else if (Character.isLetter(c)) {
                StringBuilder currWord = new StringBuilder();
                while (Character.isLetter(expression.charAt(index))) {
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
                    // 参数后面只能跟上 ')'或者运算符号
                    char nextChar = expression.charAt(index);
                    if (!(nextChar == ')' || FORMULA_OPERATOR_SET.contains(nextChar))) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else if (FORMULA_OPERATOR_SET.contains(c)) {
                sb.append(c);
                index++;
            } else if (Character.isDigit(c)) {
                int beginIndex = index;
                int pointCount = 0;
                while (Character.isDigit(expression.charAt(index)) || expression.charAt(index) == '.') {
                    if (expression.charAt(index) == '.') {
                        pointCount++;
                        if (pointCount > 1) {
                            return false;
                        }
                    }
                    sb.append(expression.substring(beginIndex, index));
                    index++;
                }
            } else {
                // 存在不合法字符
                return false;
            }
        }
        return false;
    }
}
