package com.zmji.three.october;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : zhongmou.ji
 * @date : 2021/10/15 9:51
 **/
public class Main {

    /**
     * 38. 外观数列
     * 给定一个正整数 n ，输出外观数列的第 n 项。
     *
     * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
     *
     * 你可以将其视作是由递归公式定义的数字字符串序列：
     *
     * countAndSay(1) = "1"
     * countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
     * 前五项如下：
     *
     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     * 第一项是数字 1
     * 描述前一项，这个数是 1 即 “ 一 个 1 ”，记作 "11"
     * 描述前一项，这个数是 11 即 “ 二 个 1 ” ，记作 "21"
     * 描述前一项，这个数是 21 即 “ 一 个 2 + 一 个 1 ” ，记作 "1211"
     * 描述前一项，这个数是 1211 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "111221"
     * 要 描述 一个数字字符串，首先要将字符串分割为 最小 数量的组，每个组都由连续的最多 相同字符 组成。然后对于每个组，先描述字符的数量，然后描述字符，形成一个描述组。要将描述转换为数字字符串，先将每组中的字符数量用数字替换，再将所有描述组连接起来。
     *
     * 例如，数字字符串 "3322251" 的描述如下图：
     *
     *
     *
     *
     * 示例 1：
     *
     * 输入：n = 1
     * 输出："1"
     * 解释：这是一个基本样例。
     * 示例 2：
     *
     * 输入：n = 4
     * 输出："1211"
     * 解释：
     * countAndSay(1) = "1"
     * countAndSay(2) = 读 "1" = 一 个 1 = "11"
     * countAndSay(3) = 读 "11" = 二 个 1 = "21"
     * countAndSay(4) = 读 "21" = 一 个 2 + 一 个 1 = "12" + "11" = "1211"
     *
     *
     * 提示：
     *
     * 1 <= n <= 30
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        String res = String.valueOf(1);
        for (int i = 2; i <= n; i++) {
            res = getNumCountStr(res);
        }
        return res;


    }

    private String getNumCountStr(String numStr) {
        if (numStr.length() == 0) {
            return new String();
        }
        int numCount = 1;
        char[] numArray = numStr.toCharArray();
        StringBuilder sb = new StringBuilder();
        int length = numArray.length;
        for (int i = 1; i < length; i++) {
            if (numArray[i] == numArray[i - 1]) {
                numCount++;
            } else {
                sb.append(numCount);
                sb.append(numArray[i - 1]);
                numCount = 1;
            }
        }
        if (numCount != 0) {
            sb.append(numCount);
            sb.append(numArray[length - 1]);
        }
        return sb.toString();
    }

    /**
     *
     * 2021/10/18
     * 476. 数字的补数
     * 给你一个 正 整数 num ，输出它的补数。补数是对该数的二进制表示取反。
     *
     *
     *
     * 示例 1：
     *
     * 输入：num = 5
     * 输出：2
     * 解释：5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出 2 。
     * 示例 2：
     *
     * 输入：num = 1
     * 输出：0
     * 解释：1 的二进制表示为 1（没有前导零位），其补数为 0。所以你需要输出 0 。
     *
     *
     * 提示：
     *
     * 给定的整数 num 保证在 32 位带符号整数的范围内。
     * num >= 1
     * 你可以假定二进制数不包含前导零位。
     * 本题与 1009 https://leetcode-cn.com/problems/complement-of-base-10-integer/ 相同
     * 通过次数40,545提交次数57,490
     * @param num
     * @return
     */
    public int findComplement(int num) {
        int[] arr = new int[2];
        arr[0] = 1;
        arr[1] = 0;
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(num % 2);
            num /= 2;
        }
        int res = 0;
        for (char c : sb.reverse().toString().toCharArray()) {
            res = res * 2 + arr[c - '0'];
        }
        return res;

    }

    public static void main(String[] args) {
        Main main = new Main();
        main.findComplement(2);
        main.countAndSay(5);
    }
}
