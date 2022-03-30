package com.zmji.year.three.month.one;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author : zhongmou.ji
 * @date : 2022/1/11 4:12 下午
 **/
public class LeetCode1036 {

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        // 广度优先
        final int len = 1000_000;
        int[][] dps = new int[][] {new int[] {1, 0}, new int[] {-1, 0}, new int[] {0, 1}, new int[] {0, -1}};
        boolean[][] visit = new boolean[len][len];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(source);
        Set<Pair> hashBlocked = new HashSet<>();
        for (int[] pos : blocked) {
            hashBlocked.add(new Pair(pos[0], pos[1]));
        }
        while (!queue.isEmpty()) {
            int[] currPos = queue.poll();
            visit[currPos[0]][currPos[1]] = true;
            for (int[] dp : dps) {
                int x = currPos[0] + dp[0];
                int y = currPos[1] + dp[1];
                if ((x < 0 || x >= len) || (y < 0 || y >= len)) {
                    continue;
                }
                if (hashBlocked.contains(new Pair(x, y)) || visit[x][y]) {
                    continue;
                }
                if (target[x] == x && target[y] == y) {
                    return true;
                }
                queue.add(new int[] {x, y});
            }
        }

        return false;
    }

    public static void main(String[] args) {
        LeetCode1036 main = new LeetCode1036();
        main.isEscapePossible(new int[][] {new int[] {0, 1}, new int[] {1, 0}}, new int[] {0, 0}, new int[] {0, 2});
    }

    class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return (int)((long)x << 20 | y);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Pair) {
                Pair pair2 = (Pair)obj;
                return x == pair2.x && y == pair2.y;
            }
            return false;
        }
    }
}
