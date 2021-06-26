package me.sunny.demo.algos.lc.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。每个 LED 代表一个 0 或 1，最低位在右侧。
 *
 * 例如，下面的二进制手表读取 "3:25" 。
 *
 * 给你一个整数 turnedOn ，表示当前亮着的 LED 的数量，返回二进制手表可以表示的所有可能时间。
 * 你可以 按任意顺序 返回答案。
 *
 * 小时不会以零开头：
 * 例如，"01:00" 是无效的时间，正确的写法应该是 "1:00" 。
 *
 * 分钟必须由两位数组成，可能会以零开头：
 * 例如，"10:2" 是无效的时间，正确的写法应该是 "10:02" 。
 *
 * 示例 1：
 *
 * 输入：turnedOn = 1
 * 输出：["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
 * 示例 2：
 *
 * 输入：turnedOn = 9
 * 输出：[]
 *
 * 解释：
 * 0 <= turnedOn <= 10
 *
 * 链接：https://leetcode-cn.com/problems/binary-watch
 */
public class BinWatch {
  /**
   * 枚举时分，统计 二进制 1 的个数
   */
  public List<String> readBinaryWatch(int turnedOn) {
    if (turnedOn > 8 || turnedOn < 0) {
      return new ArrayList<String>();
    }
    List<String> res = new LinkedList<>();
    for (int i = 0; i <= 11; i++) {
      int count = count(i);
      if (count == turnedOn) {
        res.add(Integer.toString(i) + ":00");
      } else if (count < turnedOn) {
        for (int j = 0; j <= 59; j++) {
          if (count(j) == turnedOn - count) {
            res.add(i + ":" + (j < 10 ? "0" : "") + j);
          }
        }
      }
    }
    return res;
  }

  private int count(int n) {
    int res = 0;
    while (n > 0) {
      if (n % 2 == 1) {
        res++;
      }
      n = n >> 1;
    }
    return res;
  }

  /**
   * 二进制枚举
   */
  public List<String> readBinaryWatch1(int turnedOn) {
    if (turnedOn > 8 || turnedOn < 0) {
      return new ArrayList<String>();
    }
    List<String> res = new ArrayList<String>();
    for (int i = 0; i < 1024; i++) {
      int h = i >> 6;
      int m = i & 63;
      if (h < 12 && m < 60 && Integer.bitCount(i) == turnedOn) {
        res.add(h + ":" + (m < 10 ? "0" : "") + m);
      }
    }
    return res;
  }

  private static final int[] watch = new int[]{8, 4, 2, 1, 32, 16, 8, 4, 2, 1};
  private List<String> res = new ArrayList<String>();
  // 回溯法
  public List<String> readBinaryWatch2(int turnedOn) {
    if (turnedOn > 8 || turnedOn < 0) {
      return new ArrayList<String>();
    }
    backtrace(turnedOn, 0, 0, 0);
    return res;
  }

  private void backtrace(int turnedOn, int h, int m, int idx) {
    // 剪枝
    if (h > 11 || m > 59) {
      return;
    }
    // 符合要求的结果
    if (turnedOn == 0) {
      StringBuffer sb = new StringBuffer();
      // 小时不会以零开头，比如 “01:00” 是不允许的，应为 “1:00”。
      sb.append(h).append(":");
      // 分钟必须由两位数组成，可能会以零开头，比如 “10:2” 是无效的，应为 “10:02”。
      if (m < 10) {
        sb.append("0").append(m);
      } else {
        sb.append(m);
      }
      res.add(sb.toString());
      // res.add(h + ":" + (m < 10 ? "0" : "") + m);
      return;
    }
    // 选择结束
    if (idx >= watch.length) {
      return;
    }
    // 当前节点不选择
    backtrace(turnedOn, h, m, idx + 1);
    // 当前节点选择
    if (idx <= 3) {
      h = h + watch[idx];
    } else {
      m = m + watch[idx];
    }
    backtrace(turnedOn - 1, h, m, idx + 1);
  }

}
