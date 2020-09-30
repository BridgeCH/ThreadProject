package com.clapton.lamada.functionJava;

class Soft implements Strategy{

    public String approach(String msg) {
        return  "默认策略："+msg;
    }
}
