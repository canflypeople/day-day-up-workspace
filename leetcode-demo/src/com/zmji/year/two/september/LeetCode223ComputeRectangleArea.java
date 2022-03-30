package com.zmji.year.two.september;

/**
 * 223. 矩形面积 给你 二维 平面上两个 由直线构成的 矩形，请你计算并返回两个矩形覆盖的总面积。
 * <p>
 * 每个矩形由其 左下 顶点和 右上 顶点坐标表示：
 * <p>
 * 第一个矩形由其左下顶点 (ax1, ay1) 和右上顶点 (ax2, ay2) 定义。 第二个矩形由其左下顶点 (bx1, by1) 和右上顶点 (bx2, by2) 定义。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * Rectangle Area 输入：ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2 输出：45 示例 2：
 * <p>
 * 输入：ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2 输出：16
 * <p>
 * <p>
 * 提示：
 * <p>
 * -104 <= ax1, ay1, ax2, ay2, bx1, by1, bx2, by2 <= 104 通过次数22,552提交次数47,275
 *
 * @author : zhongmou.ji
 * @date : 2021/9/30 11:26
 **/
public class LeetCode223ComputeRectangleArea {

    /**
     * @param ax1
     * @param ay1
     * @param ax2
     * @param ay2
     * @param bx1
     * @param by1
     * @param bx2
     * @param by2
     * @return
     */
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        // 计算两个矩形的面积
        int area1 = Math.abs(ax1 - ax2) * Math.abs(ay2 - ay1);
        int area2 = Math.abs(bx1 - bx2) * Math.abs(by1 - by2);

        // 计算矩形的重叠面积= 重叠的长*重叠的宽
        int x = Math.min(ax2, bx2) - Math.max(ax1, bx1);
        int y = Math.min(ay2, by2) - Math.max(ay1, by1);
        int overLapArea;
        if (x > 0 && y > 0) {
            overLapArea = x * y;
        } else {
            overLapArea = 0;
        }

        // 返回结果 矩形1面积 + 矩形2面积 - 重叠面积
        return area1 + area2 - overLapArea;
    }

    public static void main(String[] args) {
        LeetCode223ComputeRectangleArea leetCode = new LeetCode223ComputeRectangleArea();
        leetCode.computeArea(-3, 0, 3, 4, 0, -1, 9, 2);
    }
}
