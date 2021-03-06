package com.urise.webapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private  int counter;
    //   public static final Object LOCK = new Object();
    public static final Lock lock = new ReentrantLock();
    private final AtomicInteger atomicCounter = new AtomicInteger();
    public static final ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(SimpleDateFormat::new);

    public static void main(String[] args) throws InterruptedException {


        final MainConcurrency mainConcurrency = new MainConcurrency();

        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
                //               throw new IllegalStateException();
            }
        };
        thread0.start();
        new Thread(() -> System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState())).start();

        System.out.println(thread0.getState());

        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newCachedThreadPool();
        //       CompletionService completionService = new ExecutorCompletionService(executorService);
        // List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Future<?> submit = executorService.submit(() ->


//            Thread thread = new Thread(() ->
            {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                    threadLocal.get().format(new Date());
                }
                latch.countDown();
            });
//            thread.start();
//          threads.add(thread);
        }

//        threads.forEach(thread -> {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();
//        System.out.println(counter);
        System.out.println(mainConcurrency.atomicCounter.get());

//deadlock
//        final String lock1 = "lock1";
//        final String lock2 = "lock2";
//        deadlock(lock1, lock2);
//        deadlock(lock2, lock1);
    }

    private static void deadlock(Object lock1, Object lock2) {

        new Thread(() -> {
            System.out.println("Waiting " + lock1);
            synchronized (lock1) {
                System.out.println("Holding " + lock1);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Waiting " + lock2);
                synchronized (lock2) {
                    System.out.println("Holding " + lock2);
                }
            }
        }).start();

    }

    private void inc() {
        atomicCounter.incrementAndGet();
//        lock.lock();
//        try {
//            counter++;
//        } finally {
//            lock.unlock();
//        }
    }
}
