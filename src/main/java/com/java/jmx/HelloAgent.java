package com.java.jmx;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;  
import javax.management.ObjectName;  
import com.sun.jdmk.comm.HtmlAdaptorServer;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/** 
 *  
 * @author Administrator 该类是一个Agent类，说明： 先创建了一个MBeanServer，用来做MBean的容器 
 *         将Hello这个类注入到MBeanServer中，注入需要创建一个ObjectName类 
 *         创建一个AdaptorServer，这个类将决定MBean的管理界面，这里用最普通的Html型界面。 
 *         AdaptorServer其实也是一个MBean。 
 *         zhenghongqiang:name=HelloWorld的名字是有一定规则的，格式为：“域名:name=MBean名称”， 
 *         域名和MBean名称都可以任意取。 
 *  
 *  
 */  
public class HelloAgent {  
    public static void main(String[] args) throws Exception {  
        //创建一个MBeanServer，用来做MBean的容器  
        MBeanServer server = MBeanServerFactory.createMBeanServer();  
        //将Hello这个类注入到MBeanServer中，注入需要创建一个ObjectName类  
        ObjectName helloName = new ObjectName("testJMX:name=HelloWorld");
         Hello hello = new Hello();
         hello.setName("frank");
        server.registerMBean(hello, helloName);
        ObjectName adapterName = new ObjectName(  
                "HelloAgent:name=htmladapter,port=6666");  
        //创建一个AdaptorServer，这个类将决定MBean的管理界面，这里用最普通的Html型界面  
        HtmlAdaptorServer adapter = new HtmlAdaptorServer();  
        server.registerMBean(adapter, adapterName);  
        System.out.print(adapter.getPort());
        adapter.start();  
        System.out.println("start.....");
        ScheduledExecutorService ses =  Executors.newScheduledThreadPool(1);
        Random r = new Random();
        ses.schedule(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String newName = String.valueOf(r.nextInt(100000));
                    hello.setName(newName);
                }
            }
        }, 2, TimeUnit.SECONDS);
    }


}  