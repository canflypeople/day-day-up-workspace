package com.zmji.year.three.month.one;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * @author : zhongmou.ji
 * @date : 2022/1/23 1:24 下午
 **/
public class LeetCode2034 {

    public static class StockPrice {

        int maxTimestamp;
        HashMap<Integer, Integer> timePriceMap;
        TreeMap<Integer, Integer> prices;

        public StockPrice() {
            maxTimestamp = 0;
            timePriceMap = new HashMap<Integer, Integer>();
            prices = new TreeMap<Integer, Integer>();
        }

        public void update(int timestamp, int price) {
            maxTimestamp = Math.max(maxTimestamp, timestamp);
            int prevPrice = timePriceMap.getOrDefault(timestamp, 0);
            timePriceMap.put(timestamp, price);
            if (prevPrice > 0) {
                prices.put(prevPrice, prices.get(prevPrice) - 1);
                if (prices.get(prevPrice) == 0) {
                    prices.remove(prevPrice);
                }
            }
            prices.put(price, prices.getOrDefault(price, 0) + 1);
        }

        public int current() {
            return timePriceMap.get(maxTimestamp);
        }

        public int maximum() {
            return prices.lastKey();
        }

        public int minimum() {
            return prices.firstKey();
        }
    }

    public static void main(String[] args) {
        LeetCode2034.StockPrice stockPrice = new StockPrice();
        stockPrice.update(1, 10);
        stockPrice.update(2, 5);
        stockPrice.update(1, 3);
        stockPrice.maximum();
    }
}
