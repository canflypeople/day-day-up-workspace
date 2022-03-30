package com.zmji.year.two.november.match;

import java.util.Objects;

/**
 * @author : zhongmou.ji
 * @date : 2021/11/13 下午10:50
 **/
public class MatchOne {

    /**
     * 5910. 检查两个字符串是否几乎相等 显示英文描述 通过的用户数1595 尝试过的用户数1634 用户总通过次数1602 用户总提交次数1986 题目难度Easy 如果两个字符串 word1 和 word2 中从 'a' 到
     * 'z' 每一个字母出现频率之差都 不超过 3 ，那么我们称这两个字符串 word1 和 word2 几乎相等 。
     *
     * 给你两个长度都为 n 的字符串 word1 和 word2 ，如果 word1 和 word2 几乎相等 ，请你返回 true ，否则返回 false 。
     *
     * 一个字母 x 的出现 频率 指的是它在字符串中出现的次数。
     * 
     * 
     *
     * n == word1.length == word2.length 1 <= n <= 100 word1 和 word2 都只包含小写英文字母。
     * 
     * @param word1
     * @param word2
     * @return
     */
    public boolean checkAlmostEquivalent(String word1, String word2) {
        int[] counts = new int[26];
        for (char c : word1.toCharArray()) {
            counts[c - 'a']++;
        }
        for (char c : word2.toCharArray()) {
            counts[c - 'a']--;
        }
        for (int count : counts) {
            if (Math.abs(count) > 3) {
                return false;
            }
        }
        return true;
    }

    /**
     * 5911. 模拟行走机器人 II 显示英文描述 通过的用户数37 尝试过的用户数284 用户总通过次数37 用户总提交次数448 题目难度Medium 给你一个在 XY 平面上的 width x height 的网格图，左下角
     * 的格子为 (0, 0) ，右上角 的格子为 (width - 1, height - 1) 。网格图中相邻格子为四个基本方向之一（"North"，"East"，"South" 和 "West"）。一个机器人 初始 在格子
     * (0, 0) ，方向为 "East" 。
     *
     * 机器人可以根据指令移动指定的 步数 。每一步，它可以执行以下操作。
     *
     * 沿着当前方向尝试 往前一步 。 如果机器人下一步将到达的格子 超出了边界 ，机器人会 逆时针 转 90 度，然后再尝试往前一步。 如果机器人完成了指令要求的移动步数，它将停止移动并等待下一个指令。
     *
     * 请你实现 Robot 类：
     *
     * Robot(int width, int height) 初始化一个 width x height 的网格图，机器人初始在 (0, 0) ，方向朝 "East" 。 void move(int num) 给机器人下达前进
     * num 步的指令。 int[] getPos() 返回机器人当前所处的格子位置，用一个长度为 2 的数组 [x, y] 表示。 String getDir() 返回当前机器人的朝向，为 "North" ，"East"
     * ，"South" 或者 "West" 。
     */
    static class Robot {
        private int currColumn;
        private int currRow;
        private DirectionEnum currDirectionEnum;
        private final int width;
        private final int height;

        private final int circumference;

        public Robot(int width, int height) {
            this.width = width;
            this.height = height;
            this.currColumn = 0;
            this.currRow = 0;
            currDirectionEnum = DirectionEnum.EAST;
            circumference = 2 * (width + height);
        }

        public void move(int num) {
            num = (num % circumference);
            for (int i = 0; i < num; i++) {
                while (isChangeDirection()) {
                    currDirectionEnum = currDirectionEnum.getAnticlockwiseDirectionEnum();
                }
                int[] moveArr = currDirectionEnum.getMoveLenByDirection();
                currColumn += moveArr[0];
                currRow += moveArr[1];
            }
        }

        /**
         * 是否需要移动方向
         * 
         * @return true 是; false 否
         */
        private boolean isChangeDirection() {
            if (currRow == 0 && Objects.equals(DirectionEnum.WEST, currDirectionEnum)) {
                return true;
            }
            if (currRow == width - 1 && Objects.equals(DirectionEnum.EAST, currDirectionEnum)) {
                return true;
            }
            if (currColumn == height - 1 && Objects.equals(DirectionEnum.NORTH, currDirectionEnum)) {
                return true;
            }
            if (currColumn == 0 && Objects.equals(DirectionEnum.SOUTH, currDirectionEnum)) {
                return true;
            }
            return false;
        }

        public int[] getPos() {
            return new int[] {currRow, currColumn};
        }

        public String getDir() {
            return currDirectionEnum.getDirection();
        }
    }

    enum DirectionEnum {
        /**
         * 东
         */
        EAST("East"),

        /**
         * 西
         */
        WEST("West"),

        /**
         * 北
         */
        NORTH("North"),

        /**
         * 南
         */
        SOUTH("South");

        /**
         * 方向
         */
        String direction;

        DirectionEnum(String direction) {
            this.direction = direction;
        }

        public String getDirection() {
            return direction;
        }

        public DirectionEnum getAnticlockwiseDirectionEnum() {
            switch (this) {
                case EAST:
                    return NORTH;
                case WEST:
                    return SOUTH;
                case NORTH:
                    return WEST;
                case SOUTH:
                    return EAST;
                default:
                    return null;
            }
        }

        public int[] getMoveLenByDirection() {
            switch (this) {
                case EAST:
                    return new int[] {0, 1};
                case WEST:
                    return new int[] {0, -1};
                case NORTH:
                    return new int[] {1, 0};
                case SOUTH:
                    return new int[] {-1, 0};
                default:
                    return new int[] {0, 0};
            }
        }
    }

    public static void main(String[] args) {
        Robot robot = new Robot(6, 3); // 初始化网格图，机器人在 (0, 0) ，朝东。
        robot.move(2); // 机器人朝东移动 2 步，到达 (2, 0) ，并朝东。
        robot.move(2); // 机器人朝东移动 2 步，到达 (4, 0) ，并朝东。
        robot.getPos(); // 返回 [4, 0]
        robot.getDir(); // 返回 "East"
        robot.move(2); // 朝东移动 1 步到达 (5, 0) ，并朝东。
        // 下一步继续往东移动将出界，所以逆时针转变方向朝北。
        // 然后，往北移动 1 步到达 (5, 1) ，并朝北。
        robot.move(1); // 朝北移动 1 步到达 (5, 2) ，并朝 北 （不是朝西）。
        robot.move(4); // 下一步继续往北移动将出界，所以逆时针转变方向朝西。
        // 然后，移动 4 步到 (1, 2) ，并朝西。
        robot.getPos(); // 返回 [1, 2]
        robot.getDir(); // 返回 "West"
    }

    /**
     * Your Robot object will be instantiated and called as such: Robot obj = new Robot(width, height); obj.move(num);
     * int[] param_2 = obj.getPos(); String param_3 = obj.getDir();
     */

}
