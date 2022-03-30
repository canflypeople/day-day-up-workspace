package com.zmji.year.three.month.two;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : zhongmou.ji
 * @date : 2022/2/12 8:10 PM
 **/
public class LeetCode1020 {

    private static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int numEnclaves(int[][] grid) {
        // bfs
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 1) {
                visited[0][j] = true;
                queue.add(new int[] {0, j});
            }
            if (grid[m - 1][j] == 1) {
                visited[m - 1][j] = true;
                queue.add(new int[] {0, j});
            }
        }

        for (int i = 0; i < m - 1; i++) {
            if (grid[i][0] == 1) {
                visited[i][0] = true;
                queue.add(new int[] {i, 0});
            }
            if (grid[i][n - 1] == 1) {
                visited[i][n - 1] = true;
                queue.add(new int[] {i, n - 1});
            }
        }

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int currRow = cell[0], currCol = cell[1];
            for (int[] dir : dirs) {
                int nextRow = currRow + dir[0], nextCol = currCol + dir[1];
                if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n && !visited[nextRow][nextCol]
                    && grid[nextRow][nextCol] == 1) {
                    visited[nextRow][nextCol] = true;
                    queue.add(new int[] {nextRow, nextCol});
                }
            }
        }

        int enclaves = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) {
                    enclaves++;
                }
            }
        }

        return enclaves;

    }
}
