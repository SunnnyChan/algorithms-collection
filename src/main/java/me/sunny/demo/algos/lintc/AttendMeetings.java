package me.sunny.demo.algos.lintc;

import java.util.BitSet;
import java.util.List;

/**
 * 描述
 * 给定一系列的会议时间间隔，包括起始和结束时间[[s1,e1]，[s2,e2]，…(si < ei)，确定一个人是否可以参加所有会议。
 *
 * (0,8),(8,10)在8这一时刻不冲突
 *
 * 样例
 * 样例1
 *
 * 输入: intervals = [(0,30),(5,10),(15,20)]
 * 输出: false
 * 解释:
 * (0,30), (5,10) 和 (0,30),(15,20) 这两对会议会冲突
 * 样例2
 *
 * 输入: intervals = [(5,8),(9,15)]
 * 输出: true
 * 解释:
 * 这两个时间段不会冲突
 */
public class AttendMeetings {

  public boolean canAttendMeetings(List<Interval> intervals) {
    if (intervals == null || intervals.isEmpty()) {
      return true;
    }
    BitSet hours = new BitSet(100000);
    for (Interval interval : intervals) {
      for (int i = interval.start + 1; i < interval.end; i++) {
        if (hours.get(i)) {
          return false;
        }
        hours.set(i);
      }
    }
    return true;
  }

  public static class Interval {
     int start, end;
      Interval(int start, int end) {
          this.start = start;
          this.end = end;
      }
  }
}
