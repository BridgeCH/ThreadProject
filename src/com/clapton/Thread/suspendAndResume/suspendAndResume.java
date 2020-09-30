package com.clapton.Thread.suspendAndResume;

public class suspendAndResume {

    /**
     * suspend 不释放锁资源
     */
    public static Object obj = new Object();
    static ChangeObjThread t1 = new ChangeObjThread(("thread one"));
    static ChangeObjThread t2 = new ChangeObjThread(("thread two"));

    public static class ChangeObjThread extends Thread{
        public ChangeObjThread(String name){
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (obj){
                System.out.println( Thread.currentThread().getName()+"");
                Thread.currentThread().suspend();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(1000);
        t2.start();
        t1.resume();
        t2.resume();
        t1.join();
        t2.join();
        /**
         * t1 获取锁 挂起，
         * t2 请求锁，挂起
         * t1resume 释放锁,t2reume,
         * t2被永久挂起
         */
    }
}
