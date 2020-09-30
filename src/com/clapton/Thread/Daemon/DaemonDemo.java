package com.clapton.Thread.Daemon;

public class DaemonDemo {
    public static class DaemonT extends Thread{
        @Override
        public void run() {
            while (true){
                System.out.println("I am alive");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DaemonT daemonT = new DaemonT();
        daemonT.start();
        /**
         * 守护线程需要在start 之前设置
         */
        daemonT.setDaemon(true);

        Thread.sleep(5000);
    }
}
