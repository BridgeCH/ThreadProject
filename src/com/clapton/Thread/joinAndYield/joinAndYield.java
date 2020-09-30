package com.clapton.Thread.joinAndYield;

public class joinAndYield {
    /**
     * join() : 等待线程结束
     * yield（） 谦让（给其他线程一些工作机会），让出CPU （不代表不会再进行CPU资源的争夺）
     */

    public volatile static int i = 0;
    public static  class AddThread extends Thread{
        @Override
        public void run() {
            for (i=0;i<10000000;i++){}
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread addThread = new AddThread();
        addThread.start();
        addThread.join();
        System.out.println( i );
    }
}
