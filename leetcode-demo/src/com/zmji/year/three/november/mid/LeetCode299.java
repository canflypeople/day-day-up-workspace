package com.zmji.year.three.november.mid;

import java.util.HashMap;
import java.util.Map;

/**
 * 299. 猜数字游戏 你在和朋友一起玩 猜数字（Bulls and Cows）游戏，该游戏规则如下：
 *
 * 写出一个秘密数字，并请朋友猜这个数字是多少。朋友每猜测一次，你就会给他一个包含下述信息的提示：
 *
 * 猜测数字中有多少位属于数字和确切位置都猜对了（称为 "Bulls", 公牛）， 有多少位属于数字猜对了但是位置不对（称为 "Cows", 奶牛）。也就是说，这次猜测中有多少位非公牛数字可以通过重新排列转换成公牛数字。 给你一个秘密数字
 * secret 和朋友猜测的数字 guess ，请你返回对朋友这次猜测的提示。
 *
 * 提示的格式为 "xAyB" ，x 是公牛个数， y 是奶牛个数，A 表示公牛，B 表示奶牛。
 *
 * 请注意秘密数字和朋友猜测的数字都可能含有重复数字。
 *
 *
 *
 * 示例 1:
 *
 * 输入: secret = "1807", guess = "7810" 输出: "1A3B" 解释: 数字和位置都对（公牛）用 '|' 连接，数字猜对位置不对（奶牛）的采用斜体加粗标识。 "1807" | "7810" 示例 2:
 *
 * 输入: secret = "1123", guess = "0111" 输出: "1A1B" 解释: 数字和位置都对（公牛）用 '|' 连接，数字猜对位置不对（奶牛）的采用斜体加粗标识。 "1123" "1123" | or |
 * "0111" "0111" 注意，两个不匹配的 1 中，只有一个会算作奶牛（数字猜对位置不对）。通过重新排列非公牛数字，其中仅有一个 1 可以成为公牛数字。 示例 3：
 *
 * 输入：secret = "1", guess = "0" 输出："0A0B" 示例 4：
 *
 * 输入：secret = "1", guess = "1" 输出："1A0B"
 *
 *
 * 提示：
 *
 * 1 <= secret.length, guess.length <= 1000 secret.length == guess.length secret 和 guess 仅由数字组成 通过次数36,154提交次数68,887
 * 
 * @author : zhongmou.ji
 * @date : 2021/11/8 上午9:45
 **/
public class LeetCode299 {
    private static final String RES_FORMAT = "%sA%sB";

    public String getHint(String secret, String guess) {
        /*
         哈希表
         公牛：位置和数字都匹配
         母牛：数字和答案的数字匹配， 但位置不对
         使用哈希存储 字符，出现的数字和个数
         步骤：
         1. 先遍历，若下标元素匹配则公牛+1，若不匹配则放入哈希钟哥
         2. 遍历两个哈希，若密码存在，猜测里也存在 母牛+ 两个重合的个数
         返回
         */
        int bullCount = 0;
        int cowCount = 0;
        Map<Integer, Integer> secretNumCountMap = new HashMap<>();
        Map<Integer, Integer> guessNumCountMap = new HashMap<>();
        int len = secret.length();
        for (int i = 0; i < len; i++) {
            int secretNum = Integer.valueOf(secret.charAt(i));
            int guessNum = Integer.valueOf(guess.charAt(i));
            if (secretNum == guessNum) {
                bullCount++;
            } else {
                secretNumCountMap.put(secretNum, secretNumCountMap.getOrDefault(secretNum, 0) + 1);
                guessNumCountMap.put(guessNum, guessNumCountMap.getOrDefault(guessNum, 0) + 1);
            }
        }
        for (Map.Entry<Integer, Integer> secretNumCountEntry : secretNumCountMap.entrySet()) {
            int guessNumCount = guessNumCountMap.getOrDefault(secretNumCountEntry.getKey(), 0);
            int overlapNum = Math.min(secretNumCountEntry.getValue(), guessNumCount);
            cowCount += overlapNum;
        }
        return String.format(RES_FORMAT, bullCount, cowCount);
    }

}
