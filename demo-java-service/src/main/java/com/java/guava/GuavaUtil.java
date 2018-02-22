package com.java.guava;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * Created by iss on 18/1/19.
 */
public class GuavaUtil {




    public static void main(String arg[]) {


        Multiset<String> multiset = HashMultiset.create();

        multiset.add("火车");
        multiset.add("火车");
        multiset.add("火车");
        multiset.add("飞机");
        multiset.add("火车");
        multiset.add("飞机");
        multiset.add("火车");
        multiset.add("大炮");

        Integer count  = multiset.count("火车");
        System.out.println(count);
    }
}
