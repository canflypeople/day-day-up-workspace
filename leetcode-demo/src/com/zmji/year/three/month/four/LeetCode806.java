package com.zmji.year.three.month.four;

/**
 * @author : zhongmou.ji
 * @date : 2022/4/12 9:52 上午
 **/
public class LeetCode806 {

    private static final int LINE_WIDTH_LIMIT = 100;

    public int[] numberOfLines(int[] widths, String s) {
        int lines = 1;
        int currWidth = 0;
        for (char c : s.toCharArray()) {
            int charWidth = widths[c - 'a'];
            if (currWidth + charWidth > LINE_WIDTH_LIMIT) {
                lines++;
                currWidth = charWidth;
            } else {
                currWidth += charWidth;
            }
        }
        return new int[] {lines, currWidth};
    }
}
