package me.sunny.demo.algos.math;

import me.sunny.demo.algos.math.Gcd;
import org.junit.Assert;
import org.junit.Test;

public class TestGcd {
    @Test
    public void test1() {
        int a = 104;
        int b = 40;

        Gcd gcd = new Gcd();
        Assert.assertEquals("Test gcd(104, 40)", 8, gcd.divisor(a, b));
    }

    @Test
    public void test2() {
        int a = 40;
        int b = 104;

        Gcd gcd = new Gcd();
        Assert.assertEquals("Test gcd(40, 104)", 8, gcd.divisor(a, b));
    }
}
