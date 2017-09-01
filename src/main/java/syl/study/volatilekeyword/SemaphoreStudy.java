package syl.study.volatilekeyword;

import java.util.concurrent.Semaphore;

/**
 * 学习Semaphore类
 * Semaphore 是java.util.concurrent并发包下的类。
 * Semaphore 可以理解为 许可量 ，可以初始化一个定量的许可。
 * 通过acquire()方法获取许可，release()释放许可。
 *
 * @author 史彦磊
 * @create 2017-07-31 17:54.
 */
public class SemaphoreStudy {

    static Semaphore sp = new Semaphore(2);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(new Worker(sp,i)).start();
        }
        Thread.currentThread().join();
    }



    static class Worker implements Runnable{

        private Semaphore semaphore;
        private int num;

        public Worker(Semaphore semaphore, int num) {
            this.semaphore = semaphore;
            this.num = num;
            Thread.currentThread().setName("线程"+num);
        }

        @Override
        public void run() {
            try {
                this.semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程"+Thread.currentThread().getName()+"获取到许可");
            try {
                Thread.currentThread().sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.semaphore.release();
            System.out.println("线程"+Thread.currentThread().getName()+"释放许可");
        }
    }








}
