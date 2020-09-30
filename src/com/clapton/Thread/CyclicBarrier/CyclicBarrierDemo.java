package com.clapton.Thread.CyclicBarrier;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static class Soldier implements Runnable{

        private String soldier;
        private final CyclicBarrier cyclic;

        Soldier(String soldierName,CyclicBarrier cyclic) {
            this.soldier = soldierName;
            this.cyclic = cyclic;
        }


        @Override
        public void run() {
            try{
                cyclic.await();
                dowork();
                cyclic.wait();
            }catch (Exception E){
                E.printStackTrace();
            }
        }

        void dowork(){
            try {
                Thread.sleep(Math.abs(new Random().nextInt()%10000));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(soldier+" 任务完成");
        }
    }

    public static class BarrierRun implements  Runnable{
        boolean flag;
        int N;

        public BarrierRun(boolean flag , int N){
            this.flag = flag;
            this.N = N;
        }
        @Override
        public void run() {
            if(flag){
                System.out.println("司令：【士兵，"+N+"个，任务完成】");
            }else {
                System.out.println("司令：【士兵，"+N+"个，集合完毕】");
                flag = true;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        final  int N = 10;
        Thread[] allSolider = new Thread[N];

        boolean flag = false;
        CyclicBarrier cyclic = new CyclicBarrier(N, new BarrierRun(flag, N));

        System.out.println("集合队伍");
        for (int i=0;i<N;++i){
            System.out.println("士兵"+i+" 报道！");
            allSolider[i] = new Thread(new Soldier( "士兵" + i + "号",cyclic));
            allSolider[i].start();
        }

    }
}
