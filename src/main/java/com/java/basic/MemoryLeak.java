package com.java.basic;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;

/**
 * Created by dufeng on 2017/6/15.
 */
public class MemoryLeak {
    static class KeyClass{
        private String id;

        public KeyClass(String id) {
            this.id = id;
        }
    }
    public static void main(String...args){
        leakTest();
    }

    public static void leakTest(){
        Executors.newSingleThreadExecutor().submit(
                new Runnable() {
                    @Override
                    public void run() {

//                        WeakHashMap<KeyClass, KeyClass> hashMap = new WeakHashMap<>();
                        HashMap<KeyClass, KeyClass> hashMap = new HashMap<>();
                        for (int i = 0; i < 1000000000; i++){
                            KeyClass key = new KeyClass(String.valueOf(i));
                            KeyClass value = new KeyClass(String.valueOf(i));
                            hashMap.put(key, value);
                            hashMap.remove(value);
                        }
                        while (true){
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
    }
}
