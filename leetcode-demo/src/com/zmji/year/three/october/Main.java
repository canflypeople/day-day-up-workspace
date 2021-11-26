package com.zmji.year.three.october;

import java.util.*;

/**
 * @author : zhongmou.ji
 * @date : 2021/10/15 9:51
 **/
public class Main {

    /**
     * 38. 外观数列 给定一个正整数 n ，输出外观数列的第 n 项。
     * <p>
     * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
     * <p>
     * 你可以将其视作是由递归公式定义的数字字符串序列：
     * <p>
     * countAndSay(1) = "1" countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。 前五项如下：
     * <p>
     * 1. 1 2. 11 3. 21 4. 1211 5. 111221 第一项是数字 1 描述前一项，这个数是 1 即 “ 一 个 1 ”，记作 "11" 描述前一项，这个数是 11 即 “ 二 个 1 ” ，记作 "21"
     * 描述前一项，这个数是 21 即 “ 一 个 2 + 一 个 1 ” ，记作 "1211" 描述前一项，这个数是 1211 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "111221" 要 描述
     * 一个数字字符串，首先要将字符串分割为 最小 数量的组，每个组都由连续的最多 相同字符
     * 组成。然后对于每个组，先描述字符的数量，然后描述字符，形成一个描述组。要将描述转换为数字字符串，先将每组中的字符数量用数字替换，再将所有描述组连接起来。
     * <p>
     * 例如，数字字符串 "3322251" 的描述如下图：
     * <p>
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 1 输出："1" 解释：这是一个基本样例。 示例 2：
     * <p>
     * 输入：n = 4 输出："1211" 解释： countAndSay(1) = "1" countAndSay(2) = 读 "1" = 一 个 1 = "11" countAndSay(3) = 读 "11" = 二 个 1
     * = "21" countAndSay(4) = 读 "21" = 一 个 2 + 一 个 1 = "12" + "11" = "1211"
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= n <= 30
     *
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
     * 2021/10/18 476. 数字的补数 给你一个 正 整数 num ，输出它的补数。补数是对该数的二进制表示取反。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：num = 5 输出：2 解释：5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出 2 。 示例 2：
     * <p>
     * 输入：num = 1 输出：0 解释：1 的二进制表示为 1（没有前导零位），其补数为 0。所以你需要输出 0 。
     * <p>
     * <p>
     * 提示：
     * <p>
     * 给定的整数 num 保证在 32 位带符号整数的范围内。 num >= 1 你可以假定二进制数不包含前导零位。 本题与 1009
     * https://leetcode-cn.com/problems/complement-of-base-10-integer/ 相同 通过次数40,545提交次数57,490
     *
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

    /**
     * 211. 添加与搜索单词 - 数据结构设计 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
     * <p>
     * 实现词典类 WordDictionary ：
     * <p>
     * WordDictionary() 初始化词典对象 void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配 bool search(word) 如果数据结构中存在字符串与 word
     * 匹配，则返回 true ；否则，返回 false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
     * <p>
     * <p>
     * 示例：
     * <p>
     * 输入： ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
     * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]] 输出： [null,null,null,null,false,true,true,true]
     * <p>
     * 解释： WordDictionary wordDictionary = new WordDictionary(); wordDictionary.addWord("bad");
     * wordDictionary.addWord("dad"); wordDictionary.addWord("mad"); wordDictionary.search("pad"); // return False
     * wordDictionary.search("bad"); // return True wordDictionary.search(".ad"); // return True
     * wordDictionary.search("b.."); // return True
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= word.length <= 500 addWord 中的 word 由小写英文字母组成 search 中的 word 由 '.' 或小写英文字母组成 最多调用 50000 次 addWord 和 search
     */
    class WordDictionary {
        private Trie root;

        public WordDictionary() {
            root = new Trie();
        }

        public void addWord(String word) {
            root.insert(word);
        }

        public boolean search(String word) {
            return dfs(word, 0, root);
        }

        private boolean dfs(String word, int index, Trie node) {
            if (index == word.length()) {
                return node.isEnd();
            }
            char ch = word.charAt(index);
            if (Character.isLetter(ch)) {
                int childIndex = ch - 'a';
                Trie child = node.getChildren()[childIndex];
                if (child != null && dfs(word, index + 1, child)) {
                    return true;
                }
            } else {
                for (Trie child : node.getChildren()) {
                    if (child != null && dfs(word, index + 1, child)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    class Trie {
        private Trie[] children;
        private boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public Trie[] getChildren() {
            return children;
        }

        public boolean isEnd() {
            return isEnd;
        }
    }

    /**
     * 453. 最小操作次数使数组元素相等 给你一个长度为 n 的整数数组，每次操作将会使 n - 1 个元素增加 1 。返回让数组所有元素相等的最小操作次数。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,2,3] 输出：3 解释： 只需要3次操作（注意每次操作会增加两个元素的值）： [1,2,3] => [2,3,3] => [3,4,3] => [4,4,4] 示例 2：
     * <p>
     * 输入：nums = [1,1,1] 输出：0
     * <p>
     * <p>
     * 提示：
     * <p>
     * n == nums.length 1 <= nums.length <= 105 -109 <= nums[i] <= 109 答案保证符合 32-bit 整数
     *
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        // 先排序
        return 0;
    }

    /**
     * 66. 加一 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
     * <p>
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * <p>
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：digits = [1,2,3] 输出：[1,2,4] 解释：输入数组表示数字 123。 示例 2：
     * <p>
     * 输入：digits = [4,3,2,1] 输出：[4,3,2,2] 解释：输入数组表示数字 4321。 示例 3：
     * <p>
     * 输入：digits = [0] 输出：[1]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= digits.length <= 100 0 <= digits[i] <= 9
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        /*
        三种情况： 根据末尾是包含多少连续的9来分类
        1. 不包含9，末尾+1，返回
        2. 包含n - 1及以下个连续9，最前面9的前一位+1，返回
        3. 都是9，新建数组，第一个数为1，其余都为0，返回
         */
        int n = digits.length;
        for (int i = n - 1; i >= 0; i++) {
            if (digits[i] != 9) {
                digits[i]++;
                for (int j = n - 1; j > i; j++) {
                    digits[j] = 0;
                }
                return digits;
            }
        }
        int[] ans = new int[n + 1];
        ans[0] = 1;
        return ans;
    }

    /**
     * 229. 求众数 II 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：[3,2,3] 输出：[3] 示例 2：
     * <p>
     * 输入：nums = [1] 输出：[1] 示例 3：
     * <p>
     * 输入：[1,1,1,3,3,2,2,2] 输出：[1,2]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 5 * 104 -109 <= nums[i] <= 109
     * <p>
     * <p>
     * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。
     * <p>
     * 通过次数39,893提交次数84,202
     *
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> numCountMap = new HashMap<>();
        for (int num : nums) {
            numCountMap.put(num, numCountMap.getOrDefault(num, 0) + 1);
        }
        final int overCount = nums.length / 3;
        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : numCountMap.entrySet()) {
            if (entry.getValue() > overCount) {
                res.add(entry.getKey());
            }
        }
        return res;
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = nums2.length - 1; i >= 0; --i) {
            int num = nums2[i];
            while (!stack.isEmpty() && num >= stack.peek()) {
                stack.pop();
            }
            map.put(num, stack.isEmpty() ? -1 : stack.peek());
            stack.push(num);
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; ++i) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

    public String[] findWords(String[] words) {
        Map<Character, Integer> charLineMap = new HashMap<>();
        String[] strArr = new String[] {"charLineMap", "asdfghjkl", "zxcvbnm"};
        for (int i = 0; i < strArr.length; i++) {
            for (char c : strArr[i].toCharArray()) {
                charLineMap.put(c, i);
            }
        }
        List<String> validWordList = new ArrayList<>();
        for (String word : words) {
            if (isValidWord(charLineMap, word)) {
                validWordList.add(word);
            }
        }
        String[] res = new String[validWordList.size()];
        for (int i = 0; i < validWordList.size(); i++) {
            res[i] = validWordList.get(i);
        }
        return res;
    }

    public boolean isValidWord(Map<Character, Integer> charLineMap, String word) {
        int line = charLineMap.get(Character.toLowerCase(word.charAt(0)));
        for (int i = 1; i < word.length(); i++) {
            if (charLineMap.get(Character.toLowerCase(word.charAt(i))) != line) {
                return false;
            }
        }
        return true;
    }

    /**
     * 5899. 两个最好的不重叠活动 给你一个下标从 0 开始的二维整数数组 events ，其中 events[i] = [startTimei, endTimei, valuei] 。第 i 个活动开始于 startTimei
     * ，结束于 endTimei ，如果你参加这个活动，那么你可以得到价值 valuei 。你 最多 可以参加 两个时间不重叠 活动，使得它们的价值之和 最大 。
     * <p>
     * 请你返回价值之和的 最大值 。
     * <p>
     * 注意，活动的开始时间和结束时间是 包括 在活动时间内的，也就是说，你不能参加两个活动且它们之一的开始时间等于另一个活动的结束时间。更具体的，如果你参加一个活动，且结束时间为 t ，那么下一个活动必须在 t + 1
     * 或之后的时间开始。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * <p>
     * <p>
     * 输入：events = [[1,3,2],[4,5,2],[2,4,3]] 输出：4 解释：选择绿色的活动 0 和 1 ，价值之和为 2 + 2 = 4 。 示例 2：
     * <p>
     * Example 1 Diagram
     * <p>
     * 输入：events = [[1,3,2],[4,5,2],[1,5,5]] 输出：5 解释：选择活动 2 ，价值和为 5 。 示例 3：
     * <p>
     * <p>
     * <p>
     * 输入：events = [[1,5,3],[1,5,1],[6,6,5]] 输出：8 解释：选择活动 0 和 2 ，价值之和为 3 + 5 = 8 。
     * <p>
     * <p>
     * 提示：
     * <p>
     * 2 <= events.length <= 105 events[i].length == 3 1 <= startTimei <= endTimei <= 109 1 <= valuei <= 106
     *
     * @param events
     * @return
     */
    public int maxTwoEvents(int[][] events) {
        // 构造事件 [op 事件的类型 0事件开始 1事件结束， ts事件对应的时间 开始或者结束， val 事件的价值]
        int len = events.length;
        int[][] evs = new int[len * 2][3];
        for (int i = 0; i < events.length; i++) {
            evs[2 * i] = new int[] {0, events[i][0], events[i][2]};
            evs[2 * i + 1] = new int[] {1, events[i][1], events[i][2]};
        }
        Arrays.sort(evs, (ev1, ev2) -> {
            if (ev1[1] > ev2[1]) {
                return 1;
            } else if (ev1[1] < ev2[1]) {
                return -1;
            }
            if (ev1[0] >= ev2[0]) {
                return 1;
            } else if (ev1[0] < ev2[0]) {
                return -1;
            }
            return 1;
        });
        return 1;

    }

    public int distributeCandies(int[] candyType) {
        int candyTypeCount = getCandyTypeCount(candyType);
        return Math.min(candyTypeCount, candyType.length / 2);
    }

    private int getCandyTypeCount(int[] candyType) {
        for (int i = 0; i < candyType.length; i++) {
            candyType[i] += 100_000;
        }

        // 把值映射至bitMap中
        BitMap bitMap = new BitMap(10_000);
        for (int type : candyType) {
            // 找到当前值对应的位
            bitMap.setN(type);
        }
        int res = 0;

        for (int bit : bitMap.bitsMap) {
            res += countBinaryOne(bit);
        }
        return res;

    }

    public static int countBinaryOne(int x) {
        int count = 0;
        while (x != 0) {
            x = x & (x - 1);
            count++;
        }
        return count;
    }

    public class BitMap {
        private long length;
        public int[] bitsMap;
        private final int[] BIT_VALUE = {0x00000001, 0x00000002, 0x00000004, 0x00000008, 0x00000010, 0x00000020,
            0x00000040, 0x00000080, 0x00000100, 0x00000200, 0x00000400, 0x00000800, 0x00001000, 0x00002000, 0x00004000,
            0x00008000, 0x00010000, 0x00020000, 0x00040000, 0x00080000, 0x00100000, 0x00200000, 0x00400000, 0x00800000,
            0x01000000, 0x02000000, 0x04000000, 0x08000000, 0x10000000, 0x20000000, 0x40000000, 0x80000000};

        public BitMap(long length) {
            this.length = length;
            /**
             * 根据长度算出，所需数组大小 当 length%32=0 时大小等于 = length/32 当 length%32>0 时大小等于 = length/32+l
             */
            bitsMap = new int[(int)(length >> 5) + ((length & 31) > 0 ? 1 : 0)];
        }

        /**
         * @param n
         *            要被设置的值为n
         */
        public void setN(long n) {
            if (n < 0 || n > length) {
                throw new IllegalArgumentException("length value " + n + " is  illegal!");
            }
            // 求出该n所在bitMap的下标,等价于"n/5"
            int index = (int)n >> 5;
            // 求出该值的偏移量(求余),等价于"n%31"
            int offset = (int)n & 31;
            /**
             * 等价于 int bits = bitsMap[index]; bitsMap[index]=bits| BIT_VALUE[offset]; 例如,n=3时,设置byte第4个位置为1
             * （从0开始计数，bitsMap[0]可代表的数为：0~31，从左到右每一个bit位表示一位数） bitsMap[0]=00000000 00000000 00000000 00000000 | 00000000
             * 00000000 00000000 00001000=00000000 00000000 00000000 00000000 00001000 即: bitsMap[0]= 0 | 0x00000008 = 3
             *
             * 例如,n=4时,设置byte第5个位置为1 bitsMap[0]=00000000 00000000 00000000 00001000 | 00000000 00000000 00000000
             * 00010000=00000000 00000000 00000000 00000000 00011000 即: bitsMap[0]=3 | 0x00000010 = 12
             */
            bitsMap[index] |= BIT_VALUE[offset];

        }

        /**
         * 获取值N是否存在
         * 
         * @return 1：存在，0：不存在
         */
        public int isExist(long n) {
            if (n < 0 || n > length) {
                throw new IllegalArgumentException("length value illegal!");
            }
            int index = (int)n >> 5;
            int offset = (int)n & 31;
            int bits = (int)bitsMap[index];
            // System.out.println("n="+n+",index="+index+",offset="+offset+",bits="+Integer.toBinaryString(bitsMap[index]));
            return ((bits & BIT_VALUE[offset])) >>> offset;
        }

    }

    public int distributeCandiess(int[] candyType) {

        // 数字大小[-100,000, 100,000]
        // 数组长度 [2, 10,000] 确定为偶数
        byte[] byteArr = new byte[1000_000];
        // 100_000为界 右边为正数 左边为负数
        int count = 0;
        int index;
        int position;
        int i1;
        // 取中值 左边放负数 右边正数
        int mid = byteArr.length / 2;
        int abs;
        for (int i : candyType) {
            // 取绝对值，防止符号位进行位移扰乱结果
            abs = Math.abs(i);
            // 右移三位 因为是byte数组 一个byte=8位，获取所在的字节下标
            index = abs >>> 3;
            // 在所在字节位置的偏移量
            position = abs & 0x07;
            // 1左移，获取到字节指定偏移量为1的二进制码 如 偏移量1 -> 0010 放在倒数第二位 0则放 0001 最后一位
            i1 = 1 << position;
            if (i >= 0) {
                // 如果在bit数组中不存在，即指定下标，指定偏移量为1，说明之前set过了，进行下一次循环
                if ((byteArr[index + mid] & i1) != 0) {
                    continue;
                }
                // set值
                byteArr[index + mid] |= 1 << position;
                // 种类加1
                count++;
            } else {
                if ((byteArr[mid - index] & i1) != 0) {
                    continue;
                }
                byteArr[mid - index] |= i1;
                count++;
            }
        }
        return Math.min(count, candyType.length / 2);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.distributeCandies(new int[] {1, 1, 2, 2, 3, 3});
        main.findWords(new String[] {"omk"});
        main.nextGreaterElement(new int[] {4, 1, 2}, new int[] {1, 3, 4, 2});
        main.findComplement(2);
        main.countAndSay(5);
    }
}
