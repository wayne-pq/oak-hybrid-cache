package cn.xxywithpq.infrastructure.cache.lc.semaphore; /**
 * 线程顺序执行，Semaphore方式
 */

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args) throws InterruptedException {
        // 创建信号量 semaphore1 和 semaphore2 都被初始化为 0
        Semaphore semaphore1 = new Semaphore(0); // 线程2在执行时需要等线程1释放许可
        Semaphore semaphore2 = new Semaphore(0); // 线程3在执行时需要等线程2释放许可

        // 线程1：首先执行
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("线程1开始执行");
                Thread.sleep(2000);
                System.out.println("线程1执行完毕");
                semaphore1.release();  // 释放许可，允许线程2执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 线程2：在线程1执行完之后执行
        Thread t2 = new Thread(() -> {
            try {
                semaphore1.acquire();  // 线程2获取许可，只有等待线程1执行完，才可以拿到许可
                System.out.println("线程2开始执行");
                Thread.sleep(1000);
                System.out.println("线程2执行完毕");
                semaphore2.release();  // 释放许可，允许线程3执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 线程3：在线程2执行完之后执行
        Thread t3 = new Thread(() -> {
            try {
                semaphore2.acquire();  // 等待线程2执行完
                System.out.println("线程3开始执行");
                Thread.sleep(1000);
                System.out.println("线程3执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 启动线程
        t1.start();
        t2.start();
        t3.start();

        // 等待所有线程执行完
        t2.join();
        t1.join();
        t3.join();
    }
}