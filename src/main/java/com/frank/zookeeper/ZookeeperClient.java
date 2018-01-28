package com.frank.zookeeper;

import com.frank.utils.Print;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Frank on 2017/3/5.
 */
public class ZookeeperClient {
    static ZooKeeper zk;
    static String connStr = "127.0.0.1:2181";

    static {

    }

    public static void testBaseZK() throws InterruptedException, KeeperException {
        CountDownLatch latch = new CountDownLatch(1);

        class ZookeeperWatcher implements Watcher{
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event);
                if (event.getState() == Event.KeeperState.SyncConnected){
                    latch.countDown();
                }
            }
        }

        class IStringCallback implements AsyncCallback.StringCallback {
            @Override
            public void processResult(int i, String s, Object o, String s1) {
                Print.println("i={},s={},o={},s1={}", i,s,o,s1);
            }
        }

        Watcher watcher = new ZookeeperWatcher();
        try {
            zk = new ZooKeeper(connStr, 3000,watcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        latch.await();
        Watcher watcher1 = new ChildrenCallBack();
        List<String> children = zk.getChildren("/book", watcher1);
        byte[] data = zk.getData("/book",watcher1, new Stat());
        zk.setData("/book", "123".getBytes(), 22);
        Thread.sleep(Integer.MAX_VALUE);
    }

    private static class ChildrenCallBack implements Watcher{
        @Override
        public void process(WatchedEvent watchedEvent) {
            if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged){
                try {
                    List<String> children = zk.getChildren("/book", this);
                    Print.println("children: {}",children);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else if (watchedEvent.getType() == Event.EventType.NodeDataChanged){
                Print.println("{}", watchedEvent);
                try {
                    Stat stat = new Stat();
                    byte[] data = zk.getData("/book", this, stat);
                    Print.println("data content : {}", new String(data));
                    Print.println("stat version : {}, pzxid : {}, zxid : {}", stat.getVersion(), stat.getPzxid(), stat.getMzxid());
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String...args) throws KeeperException, InterruptedException {
        testBaseZK();

    }
}