package me.sunny.demo.algos.leetcode;

import org.junit.Test;
import org.testng.Assert;

public class TestLargestRectInHistogram {

    @Test
    public void testLargestRectInHistogram1() {
        int[] heights = {1};
        LargestRectInHistogram largestRectInHistogram = new LargestRectInHistogram();
        Assert.assertEquals(largestRectInHistogram.largestRectangleArea(heights), 1);
    }

    @Test
    public void testLargestRectInHistogram2() {
        int[] heights = {2, 1, 5, 6, 2, 3};
        LargestRectInHistogram largestRectInHistogram = new LargestRectInHistogram();
        Assert.assertEquals(largestRectInHistogram.largestRectangleArea(heights), 10);
    }

    @Test
    public void testLargestRectInHistogram3() {
        int[] heights = {2, 1, 5, 6, 2, 3, 0};
        LargestRectInHistogram largestRectInHistogram = new LargestRectInHistogram();
        Assert.assertEquals(largestRectInHistogram.largestRectangleArea2(heights), 10);
    }
}
