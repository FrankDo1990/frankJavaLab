package com.java.design;

/**
 * Created by dufeng on 2017/2/16.
 */
public class Singleton {
    private static Singleton instance;

    private static class LazyHolder{
        private static final Singleton INSTANCE;
        static {
            INSTANCE = new Singleton();
        }
    }

    public static final Singleton getInstance(){
        return LazyHolder.INSTANCE;
    }
}

