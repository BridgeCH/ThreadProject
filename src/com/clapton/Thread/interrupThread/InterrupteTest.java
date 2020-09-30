package com.clapton.Thread.interrupThread;

public class InterrupteTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("进入循环体");
                while (true){
                    System.out.println("循环中");
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("退出线程");
                        break;
                    }
                    try {
                        System.out.println("循环中：sleep :2 s");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        /**
                         * ,此时会清除中断标，若不加处理，下一次循环中将捕捉不到中断标，，因此在catch中再次打中断标
                         */
                        System.out.println("睡眠时被中断");
                        Thread.currentThread().interrupt();
                    }
                    Thread.yield();
                }
            }
        };
        long startTime = System.currentTimeMillis();
        thread.start();
        System.out.println("wait 2 s");
        Thread.sleep(1900);
        System.out.println("睡了"+(double)(System.currentTimeMillis()-startTime)/1000+" 秒 ");
        System.out.println("设置中断");
        thread.interrupt();
    }
}

//        try {
//        } catch (InterruptedException e) {
////            System.out.println("睡眠时被中断");
////            thread.interrupt();
//        }
        /**
         *   .interrupt（）打中断标
         *   .isInterrupted（） 判断是否中断
         *   .Interrupted（）判断是否中断并清除中断状态
         * 如果中断后，线程立刻无条件退出了，岂不是和 stop()方法一样了？
         * 因此 中断仅仅是给线程中断的位置打个标(这里便是睡眠3秒后的位置)，需要配合 isInterrupted()方法给线程中断处理
         */

        /**
         * .yield()应该做的是让当前运行线程回到可运行状态，以允许具有相同优先级的其他线程获得运行机会。
         * 因此，使用yield()的目的是让相同优先级的线程之间能适当的轮转执行。
         * 但是，实际中无法保证yield()达到让步目的，因为让步的线程还有可能被线程调度程序再次选中。
         */
