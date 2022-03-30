package com.zmji.year.three.month.two;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author : zhongmou.ji
 * @date : 2022/2/15 12:05 PM
 **/
public class LeetCode1380 {

    public List<Integer> luckyNumbers(int[][] matrix) {
        List<Integer> minRows = new ArrayList<>();
        List<Integer> maxCols = new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            int minCol = 0;
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] < matrix[i][minCol]) {
                    minCol = j;
                }
            }
            minRows.add(minCol);
        }

        for (int j = 0; j < n; j++) {
            int maxRow = 0;
            for (int i = 1; i < m; i++) {
                if (matrix[i][j] > matrix[maxRow][j]) {
                    maxRow = i;
                }
            }
            maxCols.add(maxRow);
        }

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int minRow = minRows.get(i);
            int maxCol = maxCols.get(minRow);
            if (Objects.equals(maxCol, i)) {
                res.add(matrix[i][minRow]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode1380 main = new LeetCode1380();
        main.luckyNumbers(new int[][] {{3, 7, 8}, {9, 11, 13}, {15, 16, 17}});
    }
}
