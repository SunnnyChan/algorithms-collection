package me.sunny.demo.algos.leetcode.hard;

/**
 * [233. 数字 1 的个数]
 *
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 *
 * 示例:
 *
 * 输入: 13
 * 输出: 6
 * 解释: 数字 1 出现在以下数字中: 1, 10, 11, 12, 13 。
 *
 * @author  sunnnychan@gmail.com
 */

public class NumOfDigitOne {

  /**
   *  归纳法：
   *
   *  按位统计，当统计一个位的数字为1时，不考虑其他位，全部位遍历一遍，最终获得结果。
   *
   *  最高位区分 1 和 > 1的情况
   *  （1）如果最高位是 1，如123，则以最高位为1的数字是 100 .. 123， 即 23 + 1个。
   *  （2）如果最高位 > 1，如233，则以最高位为1的数字是 100 .. 199，即 低位位数 * 10，也就是 2 * 10 = 20
   *
   *  统计其他位，分为3中情况：
   *  （1）当前位为 > 1，如 123，则以当前位为1的数字是 10 .. 19，110 .. 119，即高位部分 + 1 * (10^低位位数)，
   *  （2）当前位为 = 1，如 113，则以当前位为1的数字是 10 .. 19，110 .. 113，即高位部分 * (10^低位位数) + （低位部分 + 1）
   *  （3）当前位为 = 0，如 103，则以当前位为1的数字是 10 .. 19 高位部分 * (10^低位位数)
   */
  public int count(int n) {
    if (n <= 0) {
      return 0;
    }
    int bits = 0;
    long tenPower = 1;
    int i = n;
    while (i > 0) {
      bits++;
      tenPower = tenPower * 10;
      i = i / 10;
    }
    tenPower = tenPower / 10;

    // 处理 bits 位的数据，首先处理首位是1的情况，如果 首位数字 大于 1
    int numOfDigitOne = 0;
    if (n / tenPower > 1) {
      numOfDigitOne += tenPower;
    } else {
      numOfDigitOne += n % tenPower + 1;
    }
    // 处理其他位是1的情况
    i = bits - 1;
    long t = tenPower / 10;

    while (i > 0) {
      // 当前位
      long currNum = (n / t) % 10;

      if (currNum > 1) {
        numOfDigitOne += (n / t / 10 + 1) * t;
      } else if (currNum == 0) {
        numOfDigitOne += (n / t / 10) * t;
      } else {
        // (n / t / 10) 高位部分，（n % t）低位部分
        numOfDigitOne += (n / t / 10) * t + n % t + 1;
      }
      t = t / 10;
      i--;
    }
    return numOfDigitOne;
  }

  public int countBase(int n) {
    int numOfDigitOne = 0;
    for (int i = 1; i <= n; i++) {
      int j = i;
      while (j > 0) {
        if (j % 10 == 1) {
          numOfDigitOne++;
        }
        j = j / 10;
      }
    }
    return numOfDigitOne;
  }

}
