package nianyang.mny.personal.interview.leetcode;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

public class P15 {
    public String t="aa";
    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(1, 5, 1, 2, 7, 2, -6, 7);
        Collections.sort(list);
        System.out.println(list);
    }

    public List<Integer> sort(List<Integer> list) {
        int i = 1, j = list.size() - 1;

        while (i < j) {

            int mark = list.get(0);
            if (list.get(i) > list.get(j)) {
                
            }
        }


        return list;

    }
}
