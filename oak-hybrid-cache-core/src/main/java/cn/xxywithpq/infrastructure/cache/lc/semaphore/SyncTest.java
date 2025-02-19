package cn.xxywithpq.infrastructure.cache.lc.semaphore;

/**
 * 线程顺序执行，Semaphore方式
 */

public class SyncTest {

    public static Object lock = new Object();
    public static int shouldRunThreadId = 1;
    public static int num = 1;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Task(1));
        Thread thread2 = new Thread(new Task(2));
        Thread thread3 = new Thread(new Task(3));

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

    }

    static class Task implements Runnable {

        private int threadId;

        public Task(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (Task.class) {
                    while (threadId != shouldRunThreadId) {
                        try {
                            Task.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if (num == 76) {
                        return;
                    }
                    for (int i = 0; i < 5; i++) {
                        System.out.println("threadId:" + threadId + ":" + num++);
                    }

                    if (++shouldRunThreadId > 3) {
                        shouldRunThreadId = 1;
                    }
                    Task.class.notifyAll();
                }
            }
        }
    }
}