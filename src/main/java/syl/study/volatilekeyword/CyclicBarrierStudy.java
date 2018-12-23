package syl.study.volatilekeyword;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 学习使用CyclicBarrier类
 * CyclicBarrier 是java.util.concurrent并发包下的一个类
 *
 * CyclicBarrier的一个主要方法是await()。await()每被调用一次，
 * 计数便会减少1，并阻塞主当前线程，当计数减为0时，阻塞解除，所有在此
 * CyclicBarrier上阻塞的线程开始运行。在这之后，如果再次调用await()
 * 方法，计数又会变成N-1，新一轮重新开始。
 *
 * @author 史彦磊
 * @create 2017-07-31 17:09.
 */
public class CyclicBarrierStudy {


    static CyclicBarrier barrier = new CyclicBarrier(5);
    static CountDownLatch cdl = new CountDownLatch(5);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Worker(barrier,i)).start();
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行完毕");
    }


    static class Worker implements Runnable {
        private CyclicBarrier barrier;
        private int num;

        public Worker(CyclicBarrier barrier, int num) {
            this.barrier = barrier;
            this.num = num;
            Thread.currentThread().setName("线程"+num);
        }


        @Override
        public void run() {
            System.out.println("线程"+Thread.currentThread().getName()+"等待执行");
            try {
                Thread.sleep(3000);
                barrier.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("所有线程"+Thread.currentThread().getName()+"开始同时执行");
            cdl.countDown();
        }
    }


}
