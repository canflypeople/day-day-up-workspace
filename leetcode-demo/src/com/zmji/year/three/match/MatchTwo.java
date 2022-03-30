package com.zmji.year.three.match;

import java.util.Arrays;

/**
 * @author : zhongmou.ji
 * @date : 2022/3/20 10:36 上午
 **/
public class MatchTwo {

    // 模拟
    public int countHillValley(int[] nums) {
        int res = 0;
        int distance = -2;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                if (distance == -1) {
                    res++;
                }
                distance = 1;
            } else if (nums[i] < nums[i - 1]) {
                if (distance == 1) {
                    res++;
                }
                distance = -1;
            } else if (nums[i] == nums[i - 1]) {

            }
        }
        return res;
    }

    public int countCollisions(String directions) {
        int res = 0;
        int index = 0;
        int n = directions.length();
        char[] directionArr = directions.toCharArray();
        char[] stopArr = new char[directionArr.length];
        Arrays.fill(stopArr, '0');
        while (index + 1 < n) {
            char curr = directionArr[index];
            char next = directionArr[index + 1];
            char currStop = stopArr[index];
            index++;
            if (currStop != '0') {
                if (currStop == 'L') {
                    if (next != 'R') {
                        res++;
                        stopArr[index] = next;
                    }
                } else if (currStop == 'R') {
                    if (next != 'L') {
                        res++;
                        stopArr[index] = next;
                    }
                } else if (currStop == 'S') {
                    if (next != 'S') {
                        res++;
                        stopArr[index] = next;
                    }
                }
            } else {
                if (curr == next || curr == 'L' && next == 'R') {
                    continue;
                }

                if (curr == 'R' && next == 'L') {
                    res += 2;
                } else {
                    res += 1;
                }
                stopArr[index] = next;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        MatchTwo main = new MatchTwo();
        main.countCollisions("RLRSLL");
    }
}
