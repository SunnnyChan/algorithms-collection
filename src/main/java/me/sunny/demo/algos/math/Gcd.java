package me.sunny.demo.algos.math;

public class Gcd {

    public int divisor(int a, int b) {
        System.out.println(a + "    " + b);
        if (a % b == 0) {
            return b;
        } else {
            return divisor(b, a % b);
        }
    }

}
