package me.sunny.demo.algos.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;

public class NumOfDigitOneTest {

  @Test
  public void testCount() {
    int n = 9;
    Assert.assertEquals(1 * 1, new NumOfDigitOne().count(n));
  }

  @Test
  public void testCountBase() {
    int n = 9;
    Assert.assertEquals(1 * 1, new NumOfDigitOne().countBase(n));
  }

  @Test
  public void testCount1() {
    int n = 99;
    Assert.assertEquals(10 * 2, new NumOfDigitOne().count(n));
  }

  @Test
  public void testCountBase1() {
    int n = 99;
    Assert.assertEquals(10 * 2, new NumOfDigitOne().countBase(n));
  }

  @Test
  public void testCountBase2() {
    int n = 999;
    Assert.assertEquals(100 * 3, new NumOfDigitOne().countBase(n));
  }

  @Test
  public void testCount2() {
    int n = 999;
    Assert.assertEquals(100 * 3, new NumOfDigitOne().count(n));
  }

  @Test
  public void testCountBase3() {
    int n = 9999;
    Assert.assertEquals(1000 * 4, new NumOfDigitOne().countBase(n));
  }

  @Test
  public void testCountBase4() {
    int n = 99999;
    Assert.assertEquals(10000 * 5, new NumOfDigitOne().countBase(n));
  }

  @Test
  public void testCountBase5() {
    int n = 899;
    Assert.assertEquals(20 + (99 + 1) + 80 + 80, new NumOfDigitOne().countBase(n));
    Assert.assertEquals( (99 + 1) + 90 + 90, new NumOfDigitOne().countBase(n));
  }

  @Test
  public void testCountBase6() {
    int n = 889;
    Assert.assertEquals( 10 * 10 + 90 + 80 + 9, new NumOfDigitOne().countBase(n));
  }

  @Test
  public void testCountBase7() {
    int n = 189;
    Assert.assertEquals( 20 + 90 + 10 + 9, new NumOfDigitOne().countBase(n));
  }

  @Test
  public void testCountBase8() {
    NumOfDigitOne numOfDigitOne = new NumOfDigitOne();
    int n = 1189;
    Assert.assertEquals(numOfDigitOne.count(n), numOfDigitOne.countBase(n));
  }

  @Test
  public void testCountBase9() {
    NumOfDigitOne numOfDigitOne = new NumOfDigitOne();
    int n = 2189;
    Assert.assertEquals(numOfDigitOne.count(n), numOfDigitOne.countBase(n));
  }

  @Test
  public void testCountBase10() {
    NumOfDigitOne numOfDigitOne = new NumOfDigitOne();
    int n = 2109;
    Assert.assertEquals(numOfDigitOne.count(n), numOfDigitOne.countBase(n));
  }

  @Test
  public void testCountBase11() {
    NumOfDigitOne numOfDigitOne = new NumOfDigitOne();
    int n = 1410065408;
    Assert.assertEquals(numOfDigitOne.count(n), numOfDigitOne.countBase(n));
  }
}
