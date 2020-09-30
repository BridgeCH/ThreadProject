package com.clapton.Thread.waitAndNotify;

public class simpleDemoForWaitAndNotify {

    /**
     * T1           T2
     * 获取锁
     * wait()
     * 释放锁
     *              获取锁
     *              nofity()
     * 等待锁        释放锁
     * 重获锁
     * 继续执行
     */
    final  static  Object obj = new Object();
    private static  class T1 extends  Thread{
        @Override
        public void run() {
            synchronized (obj){
                System.out.println(System.currentTimeMillis() + "T1 start!");
                try{
                    System.out.println(System.currentTimeMillis() + "T1 wait for obj");
                    obj.wait();
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + "T1 end");
            }
        }
    }

    private static  class T2 extends  Thread{
        @Override
        public void run() {
            synchronized (obj){
                System.out.println(System.currentTimeMillis() + "T2 notify obj");
                obj.notify();
                System.out.println(System.currentTimeMillis() + "T2 end");
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        /**
         * T2 等待2s 后释放锁，让   T1 重新执行
         * wait 与sleep 都可以让线程等待若干时间，区别在于wait 会释放目标锁资源，而sleep不会
         */
        new T1().start();
        new T2().start();
    }
}
