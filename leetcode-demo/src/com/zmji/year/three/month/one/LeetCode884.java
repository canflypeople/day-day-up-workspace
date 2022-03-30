package com.zmji.year.three.month.one;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author : zhongmou.ji
 * @date : 2022/1/30 11:39 下午
 **/
public class LeetCode884 {

    public String[] uncommonFromSentences(String s1, String s2) {
        Set<String> strSet1 = getSet(s1);
        Set<String> strSet2 = getSet(s2);
        Iterator<String> iterator = strSet1.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            if (strSet2.contains(str)) {
                iterator.remove();
                strSet2.remove(str);
            }
        }
        String[] res = new String[strSet1.size() + strSet2.size()];
        int index = 0;
        for (String str : strSet1) {
            res[index++] = str;
        }

        for (String str : strSet2) {
            res[index++] = str;
        }

        return res;
    }

    Set<String> getSet(String s) {
        Set<String> set = new HashSet<>();
        for (String str : s.split(" ")) {
            if (set.contains(str)) {
                set.remove(str);
            } else {
                set.add(str);
            }
        }
        return set;
    }

    public static void main(String[] args) {
        LeetCode884 main = new LeetCode884();
        main.uncommonFromSentences("this apple is sweet", "this apple is sour");
    }
}
