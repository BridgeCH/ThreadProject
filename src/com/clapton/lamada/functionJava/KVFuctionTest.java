package com.clapton.lamada.functionJava;

import java.util.function.Function;

public class KVFuctionTest {
    public static void main(String[] args) {

        System.out.println(test(a->add(a),4));

    }

    private static  <K,V> Integer test(Function<Integer,Integer> function,Integer num){
        return function.apply(num);
    }

    public static Integer add(Integer a){
        return a*5;
    }

}
