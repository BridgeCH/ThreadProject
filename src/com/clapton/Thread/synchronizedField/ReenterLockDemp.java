package com.clapton.Thread.synchronizedField;

import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockDemp implements  Runnable{
    public static  ReentrantLock lock1  = new ReentrantLock();
    public static  ReentrantLock lock2  = new ReentrantLock();

    int lock;


    public ReenterLockDemp(int lock){
        this.lock = lock;
    }
    @Override
    public void run() {
        try {
            if(lock==1){
                lock1.lockInterruptibly();
                try{

                    Thread.sleep(500);
                }catch (InterruptedException e){}

                lock2.lockInterruptibly();

            }else {
                lock2.lockInterruptibly();
                try{
                    Thread.sleep(500);
                }catch (InterruptedException e){}
                lock1.lockInterruptibly();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            if(lock1.isHeldByCurrentThread()){
                lock1.unlock();
            }
            if(lock2.isHeldByCurrentThread()){
                lock2.unlock();

            }

            System.out.println(Thread.currentThread().getName()+" : 线程退出");
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockDemp r1 = new ReenterLockDemp(1);
        ReenterLockDemp r2 = new ReenterLockDemp(2);

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();

        Thread.sleep(2000);


        t2.interrupt();


    }
}
