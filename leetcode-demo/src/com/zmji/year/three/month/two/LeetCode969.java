package com.zmji.year.three.month.two;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhongmou.ji
 * @date : 2022/2/19 7:51 PM
 **/
public class LeetCode969 {

    public List<Integer> pancakeSort(int[] arr) {

        List<Integer> res = new ArrayList<>();
        for (int i = arr.length - 1; i > 0; i--) {
            int index = i;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[index] < arr[j]) {
                    index = j;
                }
            }
            if (index == i) {
                continue;
            }
            reserve(arr, index);
            reserve(arr, i);
            res.add(index + 1);
            res.add(i);
        }

        return res;

    }

    private void reserve(int[] arr, int index) {
        for (int i = 0, j = index; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
