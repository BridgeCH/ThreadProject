package com.clapton.CompletableFutureTest;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TestCompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> thenRunAsync = CompletableFuture.supplyAsync(TestCompletableFuture::returnSth);
        CompletableFuture<String> thenApply = thenRunAsync.thenApply(a -> acceptAndreturnSth(a));
        CompletableFuture<Void> thenAccept = thenRunAsync.thenAccept(a -> acceptAndreturnVoid(a));
        CompletableFuture<Void> thenRun = thenRunAsync.thenRun(TestCompletableFuture::acceptNothingreturnVoid);

        System.out.println("主线程============");
        System.out.println(thenRunAsync.get());
        System.out.println(thenApply.get());
        System.out.println("主线程============");
    }

    private static  void returnVoid(){
        System.out.println("thenRunAsync 异步无返回值");
    }
    private static  String returnSth(){
        System.out.println("supplyAsync 异步有返回值");
        return "supplyAsync 异步有返回值";
    }

    private static  String acceptAndreturnSth(String A){

        System.out.println("-thenApply 接收并返回");
        return new StringBuilder().append(A).append("-thenApply 接收并返回").toString();
    }

    private static  void acceptAndreturnVoid(String A){

        System.out.println("thenAccept 接收但不返回值");
    }

    private static  void acceptNothingreturnVoid(){

        System.out.println("thenRun 不接收 不返回");
    }
}
