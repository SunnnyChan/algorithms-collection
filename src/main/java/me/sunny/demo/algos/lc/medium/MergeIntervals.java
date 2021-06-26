package me.sunny.demo.algos.lc.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import java.util.List;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 *
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *  
 *
 * 提示：
 *
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 *
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 */
public class MergeIntervals {
  /**
   * 采用位图 Hash 的方式，最后连续 set值的为一个区间
   * 问题：
   * 题目的测试用例 [[1,4],[5,6]] 认为 这两个区间并不是重复区间。
   * 下面的解法解决不了这个问题。
   */
  public int[][] merge(int[][] intervals) {
    if (intervals == null || intervals.length == 0) {
      return intervals;
    }
    BitSet bitSet = new BitSet();
    // 记录最大的索引，方便后续遍历
    int max = -1;
    for(int[] segment : intervals) {
      //将一个区间全部置位
      for (int i = segment[0];  i <= segment[1]; i++) {
        bitSet.set(i);
      }
      if (segment[1] > max) {
        max = segment[1];
      }
    }
    int[][] res = new int[intervals.length][2];
    int resIdx = 0;

    int[] newInterval = null;
    boolean status = false;
    // 遍历 BitSet 找新的区间
    for ( int i = 0; i <= max + 1; i++) {
      if (bitSet.get(i)) {
        if (!status) {
          newInterval = new int[2];
          newInterval[0] = i;
          status = true;
        }
      } else {
        if (status == true) {
          newInterval[1] = i - 1;
          res[resIdx] = newInterval;
          resIdx++;
          status = false;
        }
      }
    }
    int[][] res1 = new int[resIdx][2];
    for (int i = 0; i < resIdx; i++) {
      res1[i] = res[i];
    }
    return res1;
  }

  public int[][] merge1(int[][] intervals) {
    if (intervals == null || intervals.length == 0) {
      return intervals;
    }
    // 区间段按照起始位置排序
    Arrays.sort(intervals, new Comparator<int[]>() {
      @Override
      public int compare(int[] interval1, int[] interval2) {
        return interval1[0] - interval2[0];
      }
    });
    List<int[]> merged = new ArrayList<int[]>();
    for (int i = 0; i < intervals.length; ++i) {
      int l = intervals[i][0], r = intervals[i][1];
      // 如果是第一个区间，获取当前区间的起始 比 新区间列表中最后一个要大，则开启一个新的区间
      if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < l) {
        merged.add(new int[]{l, r});
      } else {
        // 否则有重叠，结束位置取两个重叠区间的较大的结束位置
        merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], r);
      }
    }
    return merged.toArray(new int[merged.size()][]);
  }

  public static void main(String[] args) {
    int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
    new MergeIntervals().merge(intervals);
  }
}
