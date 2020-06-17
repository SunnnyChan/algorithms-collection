package me.sunny.demo.algos.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;

public class CandyTest {

  @Test
  public void test() {
    int[] arrPoints = {1, 0, 2};
    Assert.assertEquals(5, Candy.distribute(arrPoints));
  }

  @Test
  public void test1() {
    int[] arrPoints = {1, 2, 2};
    Assert.assertEquals(4, Candy.distribute(arrPoints));
  }

  @Test
  public void test2() {
    int[] arrPoints = {1, 4, 7, 5};
    Assert.assertEquals(7, Candy.distribute(arrPoints));
  }

  @Test
  public void test3() {
    int[] arrPoints = {1, 4, 7, 5, 3};
    Assert.assertEquals(9, Candy.distribute(arrPoints));
  }

  @Test
  public void test4() {
    int[] arrPoints =  {1,2,3,5,4,3,2,1,4,3,2,1,3,2,1,1,2,3,4,4,3,2,1};
    Assert.assertEquals(57, Candy.distribute(arrPoints));
  }
}
