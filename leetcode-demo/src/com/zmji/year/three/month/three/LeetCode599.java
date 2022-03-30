package com.zmji.year.three.month.three;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : zhongmou.ji
 * @date : 2022/3/14 9:10 上午
 **/
public class LeetCode599 {

    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> restaurantIndexMap1 = new HashMap<>();
        Map<String, Integer> restaurantIndexMap2 = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            restaurantIndexMap1.put(list1[i], i);
        }
        for (int i = 0; i < list2.length; i++) {
            restaurantIndexMap2.put(list2[i], i);
        }

        int minIndexSum = -1;
        List<String> sameRestaurants = new ArrayList<>();
        for (Map.Entry<String, Integer> restaurantIndexEntry : restaurantIndexMap1.entrySet()) {
            String restaurant1 = restaurantIndexEntry.getKey();
            if (!restaurantIndexMap2.containsKey(restaurant1)) {
                continue;
            }
            Integer index1 = restaurantIndexEntry.getValue();
            Integer index2 = restaurantIndexMap2.get(restaurant1);
            int currIndexSum = index1 + index2;
            if (currIndexSum < minIndexSum) {
                minIndexSum = currIndexSum;
                sameRestaurants.clear();
                sameRestaurants.add(restaurant1);
            } else if (currIndexSum == minIndexSum) {
                sameRestaurants.add(restaurant1);
            }
        }
        return sameRestaurants.toArray(new String[sameRestaurants.size()]);
    }

    public static void main(String[] args) {
        LeetCode599 leetCode599 = new LeetCode599();
        leetCode599.findRestaurant(new String[] {"Shogun", "Tapioca Express", "Burger King", "KFC"},
            new String[] {"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"});
    }
}
