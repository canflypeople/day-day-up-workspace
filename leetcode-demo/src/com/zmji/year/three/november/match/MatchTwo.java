package com.zmji.year.three.november.match;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author : zhongmou.ji
 * @date : 2021/11/14 上午11:13
 **/
public class MatchTwo {

    public int timeRequiredToBuy(int[] tickets, int k) {
        int n = tickets.length;
        int[] queueIndexArr = new int[n];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
            queueIndexArr[i] = i;
        }

        int currTime = 0;
        Queue<Integer> indexQueue = new LinkedList<>();
        if (tickets[k] == 0) {
            return currTime;
        }
        indexQueue.add(0);
        while (!indexQueue.isEmpty()) {
            int index = indexQueue.poll();
            tickets[index]--;
            currTime++;
            if (tickets[index] == 0) {
                if (index == k) {
                    return currTime;
                }
                list.remove(Integer.valueOf(index));
            }
            indexQueue.add(index == n - 1 ? list.get(0) : index);
        }
        return 0;
    }

}
