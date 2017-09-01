package syl.study.volatilekeyword;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 测试volatile关键字的作用
 *
 * @author 史彦磊
 * @create 2017-07-31 13:19.
 */
public class VolatileMain {

    private static final int THREAD_COUNT=3000;

    static CountDownLatch cdl = new CountDownLatch(THREAD_COUNT);
    static CountDownLatch cdl1 = new CountDownLatch(THREAD_COUNT);
    static CyclicBarrier cb = new CyclicBarrier(THREAD_COUNT);


//    private static AtomicInteger count = new AtomicInteger();
    private static int count = 0;

    private  static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(new Worker(cb)).start();
        }
        Thread.sleep(15000);
        System.out.println(count);


    }

    static class Worker implements Runnable{

        private CyclicBarrier _countDown;

        Worker(CyclicBarrier countDown){
            this._countDown = countDown;
        }

        @Override
        public void run() {
            try {
                _countDown.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            increatment();

        }
    }



    public static void increatment(){
        count++;
    }






}
