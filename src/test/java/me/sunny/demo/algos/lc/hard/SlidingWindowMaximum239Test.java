package me.sunny.demo.algos.lc.hard;

import org.junit.Assert;
import org.junit.Test;

public class SlidingWindowMaximum239Test {
  @Test
  public void testFindMaxs1() {
    int[] testArr = {1, 3, -1, -3, 5, 3, 6, 7};
    Assert.assertArrayEquals(new int[]{3,3,5,5,6,7},
        new SlidingWindowMaximum239().findMaximumsInSlidingWindow(testArr, 3));
  }

  @Test
  public void testFindMaxs2() {
    int[] testArr = {1};
    Assert.assertArrayEquals(new int[]{1},
        new SlidingWindowMaximum239().findMaximumsInSlidingWindow(testArr, 1));
  }

  @Test
  public void testFindMaxs3() {
    int[] testArr = {1, -1};
    Assert.assertArrayEquals(new int[]{1, -1},
        new SlidingWindowMaximum239().findMaximumsInSlidingWindow(testArr, 1));
  }

  @Test
  public void testFindMaxs4() {
    int[] testArr = {9, 11};
    Assert.assertArrayEquals(new int[]{11},
        new SlidingWindowMaximum239().findMaximumsInSlidingWindow(testArr, 2));
  }

  @Test
  public void testFindMaxs5() {
    int[] testArr = {4, -2};
    Assert.assertArrayEquals(new int[]{4},
        new SlidingWindowMaximum239().findMaximumsInSlidingWindow(testArr, 2));
  }

  @Test
  public void testFindMaxs6() {
    int[] testArr = {4, -2};
    Assert.assertArrayEquals(new int[]{4},
        new SlidingWindowMaximum239().findMaximumsInSlidingWindow(testArr, 3));
  }

  @Test
  public void testFindMaxs7() {
    int[] testArr = {3, -1, -3, -2, 6};
    Assert.assertArrayEquals(new int[]{3,-1,6},
        new SlidingWindowMaximum239().findMaximumsInSlidingWindow(testArr, 3));
  }
}
