package com.clapton.Thread.synchronizedField;

public class AccountingVol implements Runnable {

    static  AccountingVol accountingVol = new AccountingVol();

    static int i = 0;

    public static synchronized void increase(){
        i++;
    }

    @Override
    public void run() {
        for (int j=0;j<1000000;j++){
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /**
         * 即便两个线程指向不同的Runnable对象，但是同步锁请求的是当前类的锁，而非当前实例
         */
        Thread t1 = new Thread(new AccountingVol());
        Thread t2 = new Thread(new AccountingVol());
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(i);
    }
}
