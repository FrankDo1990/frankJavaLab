package com.frank.utils;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * Created by Frank on 2017/3/5.
 */
public class Print {
    /**
     *
     * @param format
     * @param args
     */
    public static void println(String format, Object...args){

        if (Strings.isNullOrEmpty(format)) return;
        if (args == null || args.length == 0) return;
        for (Object obj : args){
            format = format.replace("{}", String.valueOf(obj));
        }
        System.out.println(format);
    }
}
