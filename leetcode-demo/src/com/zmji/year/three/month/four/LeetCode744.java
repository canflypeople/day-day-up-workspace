package com.zmji.year.three.month.four;

/**
 * @author : zhongmou.ji
 * @date : 2022/4/3 9:50 下午
 **/
public class LeetCode744 {

    public char nextGreatestLetter(char[] letters, char target) {
        int minDistance = -1;
        char minValue = letters[0];
        for (char letter : letters) {
            if (letter == target) {
                continue;
            }
            int currDistance = getDistance(target, letter);
            if (currDistance != 0 && currDistance < minDistance) {
                minDistance = currDistance;
                minValue = letter;
            }
        }
        return minValue;
    }

    private int getDistance(char target, char ch) {
        if (target > ch) {
            return ch + 26 - target;
        }
        return ch - target;
    }

    public static void main(String[] args) {
        LeetCode744 main = new LeetCode744();
        main.nextGreatestLetter(new char[] {'c', 'l', 'j'}, 'a');
    }
}
