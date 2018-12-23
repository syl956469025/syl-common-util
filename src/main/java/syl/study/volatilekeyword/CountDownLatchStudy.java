package syl.study.volatilekeyword;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 *
 * CountDownLatch 是位于java.util.concurrent包下的类
 *
 * CountDownLatch 是一个递减的计数器，countDown方法减一
 *
 * ConutDownLatch类可以使一个线程等待一组线程完成各自任务后再继续执行
 *
 * 学习使用CountDownLatch 类
 *
 * @author 史彦磊
 * @create 2017-07-31 15:54.
 */
public class CountDownLatchStudy {

    //初始化一个递减的计数器，5 ，
    static CountDownLatch cdl = new CountDownLatch(5);

    Map m = new HashMap();

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Worker(cdl,i)).start();
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("继续执行主线程");
    }

    static class Worker implements Runnable{
        private CountDownLatch _cdl;
        private int num;

        public Worker(CountDownLatch _cdl,int i){
            this._cdl = _cdl;
            this.num = i;
            Thread.currentThread().setName("线程:"+num);
        }


        @Override
        public void run() {
            System.out.println("线程："+Thread.currentThread().getName() + "开始执行");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this._cdl.countDown();
            System.out.println("线程："+Thread.currentThread().getName() + "执行完毕");
        }
    }





}
