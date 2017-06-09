import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dufeng on 2017/2/14.
 */

public class TestJavaBasic {
    @Test
    public void testUnmodified(){
        Map<Integer, List<Integer>> first = Maps.newHashMap();
        first.put(1, Lists.newArrayList(1, 2, 3));
        first.put(2, Lists.newArrayList(1, 2, 3));
        System.out.println(first);
        Map<Integer, List<Integer>> unmodified = Collections.unmodifiableMap(first);
        List<Integer> unmodifiedList = unmodified.get(2);
        unmodifiedList.add(4);
        System.out.println(first);
    }
    @Test
    public void testBase64() throws IOException {
        String picStr = "";
        byte[] fcontent = new BASE64Decoder().decodeBuffer(picStr);
        if (fcontent == null || fcontent.length == 0) {
            System.out.println(picStr);
        }else {
            Image img = ImageIO.read(new ByteArrayInputStream(fcontent));
            System.out.println(img);
        }
    }
    @Test
    public void testFinally(){
        String s = null;
        try{
            s.length();
        }finally {
            System.out.println("finally runned!");
        }
    }
    @Test
    public void testCache(){
        Set<Integer> set = Sets.newHashSet();
        int a = 11;
        set.add(a);
        System.out.println(set.contains(a));
        Set<String> strSet = Sets.newHashSet();
        strSet.add(new String("11"));
        System.out.println(strSet.contains(new String("11")));
    }
    @Test
    public void testJavaNio(){
        Path path = Paths.get("D:\\test%20case\\api", "test whiete.txt");
        try {
            List<String> res = Files.readAllLines(path);
            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testThread() throws InterruptedException {
        class MyThread extends Thread{
            @Override
            public void run() {
                super.run();
                while (true){
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("one sec passed~");
                }
            }
        }
        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(10000);
        myThread.start();
    }
    @Test
    public void testCyclicBarrier(){
        ExecutorService exec = Executors.newCachedThreadPool();
        CyclicBarrier barrier = new CyclicBarrier(4);
        for (int i = 0; i < 4; i++){
            System.out.println(" ok !");
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        barrier.await();
                        System.out.println(" ok !");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    @Test
    public void testJoin(){
        Thread threadA = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 10; i++){
                    try {
                        Thread.sleep(200);
                        System.out.print("thread A runs");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                super.run();
            }
        };
        threadA.start();
        System.out.println("Thread main runs");
    }
    @Test
    public void testReplace(){
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(1));
    }
    @Test
    public void testRe(){
        String pic= "http://q3.qlogo.cn/g?b=qq&k=4JxhiakzPbzcT2p6ZdiazEsw&s=140&t=1483344395";
        String cdnUrl = "http://cache.soso.com/qlogo/";
        System.out.println(pic.replaceFirst("http://q\\d+\\.qlogo\\.cn/", cdnUrl));
    }
}
