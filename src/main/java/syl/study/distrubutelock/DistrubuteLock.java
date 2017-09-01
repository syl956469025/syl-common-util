package syl.study.distrubutelock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 基于zookeeper的分布式锁的实现
 *
 * 使用zookeeper的k开源框架Curator -- 是apache的一个子项目 地址curator.apache.org
 *
 *
 * @author 史彦磊
 * @create 2017-07-31 23:44.
 */
public class DistrubuteLock {

    private static final String path = "/mydistributelock/mylock";

    private static final String zkhost= "bidev191:2181,bidev192:2181,bidev193:2181";

    public static void main(String[] args) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkhost, retryPolicy);
        client.start();
        InterProcessMutex lock = new InterProcessMutex(client,path);
        try {
            System.out.println("线程"+Thread.currentThread().getName()+"开始获取锁");
            lock.acquire();
            System.out.println("线程"+Thread.currentThread().getName()+"获取到锁");
            Thread.sleep(500);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                lock.release();
                System.out.println("线程"+Thread.currentThread().getName()+"释放锁");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }



}
