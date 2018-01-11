package com.frank.zookeeper;

import com.frank.utils.Print;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
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
                if (Event.KeeperState.SyncConnected == event.getState()){
                    if (Event.EventType.None == event.getType() && null == event.getPath()){
                        latch.countDown();
                        try {
                            zk.exists(event.getPath(), true);
                        } catch (KeeperException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else if (Event.EventType.NodeCreated == event.getType()){
                        Print.println("Node ({}) Created", event.getPath());
                        try {
                            zk.exists(event.getPath(), true);
                        } catch (KeeperException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else if (Event.EventType.NodeDataChanged == event.getType()){
                        Print.println("Node ({}) Changed", event.getPath());
                        try {
                            zk.exists(event.getPath(), true);
                        } catch (KeeperException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        Watcher watcher = new ZookeeperWatcher();
        try {
            zk = new ZooKeeper(connStr, 3000,watcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        latch.await();

        String path = "/zk-book/client";
        zk.delete(path, -1);
//        zk.create(path, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public static void main(String...args) throws KeeperException, InterruptedException {
        testBaseZK();

    }
}