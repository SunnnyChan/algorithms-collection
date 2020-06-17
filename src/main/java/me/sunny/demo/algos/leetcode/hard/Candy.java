package me.sunny.demo.algos.leetcode.hard;

/**
 * [Candy]
 *
 * 题目描述
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 *
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 *
 * 每个孩子至少分配到 1 个糖果。
 * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 *
 * 示例 1:
 *
 * 输入: [1,0,2]
 * 输出: 5
 * 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 *
 * 示例 2:
 *
 * 输入: [1,2,2]
 * 输出: 4
 * 解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
 *      第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 *
 * @author  sunnnychan@gmail.com
 */

public class Candy {

  /**
   * 思路：
   * 采用两轮变量：
   * 第一轮处理当前元素和前面元素的关系，
   * 第二轮处理当前元素和后面元素的关系。
   *
   * 第二轮的处理，没有破坏第一轮达成的效果，所以最终结果符合要求。
   *
   * @param arrPoints 孩子的评分
   * @return 最少发出的糖果数
   */
  public static int distribute(int[ ] arrPoints) {
    // 最终派发的糖果总数
    int sum = 0;
    // 处理空的情况
    if (arrPoints.length == 0) {
      return sum;
    }
    sum = 1;
    // 处理只要1个小孩的情况
    if (arrPoints.length == 1) {
      return sum;
    }
    // 记录某个小孩当前派发的糖果数
    int[] arrCandyNum = new int[arrPoints.length];
    // 从 索引 0 开始，第一个小孩先派发1个糖果
    arrCandyNum[0] = 1;
    // 从 索引 1 开始遍历
    for (int i = 1; i < arrPoints.length; i++) {
      // 如果分数不比前一个大，先直接派发一个糖果
      if (arrPoints[i] <= arrPoints[i - 1]) {
        arrCandyNum[i] = 1;
      } else {
        // 如果分数比前一个大，则派发的糖果数比前一个小孩多一个
        arrCandyNum[i] = arrCandyNum[i - 1] + 1;
      }
      sum = sum + arrCandyNum[i];
    }
    // 到这里，可以保证每个小孩向索引小的方向比较时，糖果是符合要求的
    // 再处理向索引大的方向比较的情况
    for (int i = arrPoints.length - 1; i > 0; i--) {
      // 当索引小的元素评分较高，同时第一轮派发的糖果不比索引大的元素高时
      if (arrPoints[i - 1] > arrPoints[i] && arrCandyNum[i - 1] <= arrCandyNum[i]) {
        sum = sum + arrCandyNum[i] + 1 - arrCandyNum[i - 1];
        // 直接把评分较高元素派发的糖果变更为评分小的元素派发的糖果数+1
        arrCandyNum[i - 1] = arrCandyNum[i] + 1;
      }
    }
    return sum;
  }

}
