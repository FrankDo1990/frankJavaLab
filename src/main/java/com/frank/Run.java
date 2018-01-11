package com.frank;

/**
 * Created by Frank on 2017/5/19.
 */
public class Run {

    public static void main(String...args){
        long start = System.currentTimeMillis();
        Long sum = 0L;
        for (long i = 0l; i <= Integer.MAX_VALUE; i++){
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
