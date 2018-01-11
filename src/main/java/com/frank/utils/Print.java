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
        System.out.println(format);
        for (int h = 0 ; h < args.length; h++){
            System.out.print(" " + args[h] + " ");
        }
        StringBuilder sb = new StringBuilder();
        int index = 0, i = 0;
        while (i < format.length()){
            if (format.charAt(i) == '{' && i + 1 < format.length() && format.charAt(i+1) == '}' && index < args.length){
                sb.append(String.valueOf(args[index]));
                i = i + 2;
                index++;
            }else {
                i++;
            }
        }
        if (index < args.length){
            sb.append("[");
            for (i = index; i < args.length; i++){
                sb.append(args[i]);
                sb.append(",");
            }
            sb.append("]");
        }
        System.out.println(sb.toString());
    }
}
