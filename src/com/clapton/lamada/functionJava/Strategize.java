package com.clapton.lamada.functionJava;

public class Strategize {

    Strategy strategy;
    String msg;
    Strategize(String msg) {
        strategy = new Soft(); // [1]
        this.msg = msg;
    }

    void communicate() {
        System.out.println(strategy.approach(msg));
    }

    void changeStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public static void main(String[] args) {
        Strategy[] a = {
                new Strategy() {
                    @Override
                    public String approach(String msg) {
                        return "策略一:"+msg;
                    }
                },
                msg1 ->"策略二："+msg1,
                Unreleated::twice

        };


        Strategize s = new Strategize("Hello there");
        s.communicate();
        for(Strategy newStrategy : a) {
            s.changeStrategy(newStrategy); // [5]
            s.communicate(); // [6]
        }
    }
}
