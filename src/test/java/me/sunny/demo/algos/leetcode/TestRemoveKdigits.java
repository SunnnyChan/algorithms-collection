package me.sunny.demo.algos.leetcode;

import org.junit.Test;
import org.testng.Assert;

public class TestRemoveKdigits {
    @Test
    public void testRemoveKdigits11() {
        String num = "10200";
        int k = 1;
        RemoveKdigits removeKdigits = new RemoveKdigits();
        Assert.assertEquals(removeKdigits.solution1(num, 1), "200");
    }

    @Test
    public void testRemoveKdigits12() {
        String num = "10";
        int k = 2;
        RemoveKdigits removeKdigits = new RemoveKdigits();
        Assert.assertEquals(removeKdigits.solution1(num, 1), "0");
    }

    @Test
    public void testRemoveKdigits13() {
        String num = "10";
        int k = 1;
        RemoveKdigits removeKdigits = new RemoveKdigits();
        Assert.assertEquals(removeKdigits.solution1(num, 1), "0");
    }

    @Test
    public void testRemoveKdigits21() {
        String num = "10200";
        int k = 1;
        RemoveKdigits removeKdigits = new RemoveKdigits();
        Assert.assertEquals(removeKdigits.solution2(num, 1), "200");
    }

    @Test
    public void testRemoveKdigits22() {
        String num = "10";
        int k = 2;
        RemoveKdigits removeKdigits = new RemoveKdigits();
        Assert.assertEquals(removeKdigits.solution2(num, 1), "0");
    }

    @Test
    public void testRemoveKdigits23() {
        String num = "10";
        int k = 1;
        RemoveKdigits removeKdigits = new RemoveKdigits();
        Assert.assertEquals(removeKdigits.solution2(num, 1), "0");
    }

    @Test
    public void testRemoveKdigits24() {
        String num = "112";
        int k = 1;
        RemoveKdigits removeKdigits = new RemoveKdigits();
        Assert.assertEquals(removeKdigits.solution2(num, 1), "11");
    }
}
