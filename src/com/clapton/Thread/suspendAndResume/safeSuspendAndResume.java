package com.clapton.Thread.suspendAndResume;

public class safeSuspendAndResume {
    public static Object obj = new Object();

    public static class ChangeObjThread extends Thread{
        volatile boolean suspendme = false;
        public void suspendMe(){
            suspendme = true;
        }
        public void resumeMe(){
            suspendme = false;
            synchronized (this){
                notify();
            }
        }

        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (this){
                    while (suspendme){
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                synchronized (obj){
                    System.out.println(" in ChangeObjThread");
                }
                Thread.yield();
            }
        }
    }

    public static  class ReadObjThread extends Thread{
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj){
                    System.out.println(" in ReadObjThread");
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ChangeObjThread changeObjThread = new ChangeObjThread();
        changeObjThread.start();
        ReadObjThread readObjThread = new ReadObjThread();
        readObjThread.start();
        Thread.sleep(1000);
        changeObjThread.suspendMe();
        System.out.println( " suspend changeObjThread 2 sec ");
        Thread.sleep(2000);
        System.out.println(" resume changeObjThread");
        changeObjThread.resumeMe();
    }
}
