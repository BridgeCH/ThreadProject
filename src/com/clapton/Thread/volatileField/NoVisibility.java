package com.clapton.Thread.volatileField;

public class NoVisibility {

    private static boolean ready;
    private static int number = 10;

    private static class ReaderThread extends Thread{
        @Override
        public void run() {
            while(!ready){
//                System.out.print(number);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        System.out.println("start");
        Thread.sleep(1000);
        number=42;
        ready = true;
        Thread.sleep(10000);
    }
}
