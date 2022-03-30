package com.zmji.year.two.december.mid;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1034. 边界着色 给你一个大小为 m x n 的整数矩阵 grid ，表示一个网格。另给你三个整数 row、col 和 color 。网格中的每个值表示该位置处的网格块的颜色。
 *
 * 当两个网格块的颜色相同，而且在四个方向中任意一个方向上相邻时，它们属于同一 连通分量 。
 *
 * 连通分量的边界 是指连通分量中的所有与不在分量中的网格块相邻（四个方向上）的所有网格块，或者在网格的边界上（第一行/列或最后一行/列）的所有网格块。
 *
 * 请你使用指定颜色 color 为所有包含网格块 grid[row][col] 的 连通分量的边界 进行着色，并返回最终的网格 grid 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：grid = [[1,1],[1,2]], row = 0, col = 0, color = 3 输出：[[3,3],[3,2]] 示例 2：
 *
 * 输入：grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3 输出：[[1,3,3],[2,3,3]] 示例 3：
 *
 * 输入：grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2 输出：[[2,2,2],[2,1,2],[2,2,2]]
 *
 *
 * 提示：
 *
 * m == grid.length n == grid[i].length 1 <= m, n <= 50 1 <= grid[i][j], color <= 1000 0 <= row < m 0 <= col < n
 * 
 * @author : zhongmou.ji
 * @date : 2021/12/7 8:13 上午
 **/
public class LeetCode1034 {

    private static final int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        // 广度有限遍历 加 记忆搜索
        if (grid == null || grid[0].length == 0) {
            return grid;
        }
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visit = new boolean[m][n];
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(row, col));
        int originColor = grid[row][col];
        // 标记
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> pair = queue.poll();
            int currX = pair.first;
            int currY = pair.second;
            if (currX == 1 && currY == 1) {
                int z = 1;
                System.out.println(z);
            }
            visit[currX][currY] = true;
            // 将着色逻辑写在广度遍历中

            for (int[] dir : dirs) {
                int x = currX + dir[0];
                int y = currY + dir[1];
                if (isInArrRange(x, y, m, n) && !visit[x][y] && grid[x][y] == originColor) {
                    queue.add(new Pair<>(x, y));
                } else {
                    if (!isInArrRange(x, y, m, n) || Boolean.FALSE.equals(visit[x][y])) {
                        grid[currX][currY] = color;
                    }
                }
            }
        }
        // 着色
        // for (int i = 0; i < m; i++) {
        // for (int j = 0; j < n; j++) {
        // judgeIsRangeAndColorElement(i, j, visit, grid, color);
        // }
        // }
        return grid;
    }

    private void judgeIsRangeAndColorElement(int x, int y, boolean[][] visit, int[][] grid, int color) {
        if (Boolean.FALSE.equals(visit[x][y])) {
            return;
        }
        int m = grid.length;
        int n = grid[0].length;
        if (x == 0 || x == m - 1 || y == 0 || y == n - 1) {
            grid[x][y] = color;
            return;
        }
        for (int[] dir : dirs) {
            int neighborX = x + dir[0];
            int neighborY = y + dir[1];
            if (Boolean.FALSE.equals(visit[neighborX][neighborY])) {
                grid[x][y] = color;
                return;
            }
        }
    }

    private boolean isInArrRange(int x, int y, int m, int n) {
        return (x >= 0 && x < m) && (y >= 0 && y < n);
    }

    public static class Pair<T, U> {

        public Pair(T first, U second) {
            this.second = second;
            this.first = first;
        }

        public final T first;
        public final U second;

        // Because 'pair()' is shorter than 'new Pair<>()'.
        // Sometimes this difference might be very significant (especially in a
        // 80-ish characters boundary). Sorry diamond operator.
        public static <T, U> Pair<T, U> pair(T first, U second) {
            return new Pair<>(first, second);
        }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }

    public static void main(String[] args) {
        LeetCode1034 main = new LeetCode1034();
        main.colorBorder(new int[][] {{1, 2, 1, 2, 1, 2}, {2, 2, 2, 2, 1, 2}, {1, 2, 2, 2, 1, 2}}, 1, 3, 1);
    }
}
