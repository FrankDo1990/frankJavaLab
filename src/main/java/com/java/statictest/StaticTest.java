package com.java.statictest;

import com.google.common.base.Splitter;

import java.lang.management.ManagementFactory;

/**
 * Created by dufeng on 2017/2/15.
 */
public class StaticTest {
    static {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.print(name);
    }

    public static void main(String...args){

    }


}
