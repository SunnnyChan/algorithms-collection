package me.sunny.demo.algos.lc.hard;

/**
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。
 * 滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 *
 * 示例 1：
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------          -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 * 示例 2：
 * 输入：nums = [1], k = 1
 * 输出：[1]
 *
 * 示例 3：
 * 输入：nums = [1,-1], k = 1
 * 输出：[1,-1]
 *
 * 示例 4：
 * 输入：nums = [9,11], k = 2
 * 输出：[11]
 *
 * 示例 5：
 * 输入：nums = [4,-2], k = 2
 * 输出：[4]
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 *
 * @author SunnyChan
 * @date 2020-04-20
 */
public class SlidingWindowMaximum239 {

  public int[] findMaximumsInSlidingWindow(int[] arr, int slideWindowLen) {
    // 参数校验
    if (arr == null || slideWindowLen <= 0) {
      return null;
    }
    // 临界场景判断：数组长度为1或0 结果就是数组本身
    if (arr.length < 2) {
      return arr;
    }
    // 双向队列，队列元素为数组索引
    int[] dqueue = new int[arr.length];
    // 队列初始化
    dqueue[0] = 0;
    int start = 0;
    int end = 0;
    // 结果数组：当滑动窗口大小大于数组大小，结果数组大小为1，否则为 数组长度 - 滑动窗口长度 + 1
    int[] maximums = new int[slideWindowLen > arr.length ? 1 : arr.length - slideWindowLen + 1];
    // 存储下一个最大值的索引
    int maximumsCurrIndex = 0;
    // 如果滑动窗口大小为1，第一个数也是最大值，因为后面我这里是从第二个数开始遍历的
    if (slideWindowLen == 1) {
      maximums[maximumsCurrIndex++] = arr[dqueue[start]];
    }
    // 遍历数组
    int i = 1;
    while (i < arr.length) {
      // 保持队列是递减的，如果当前遍历的元素（ae）>=队尾元素（qe），队尾元素需要一直出队，直到ae < qe 或队列为空
      while (end >= start && arr[i] >= arr[dqueue[end]]) {
        end--;
      }
      // ae 入队
      dqueue[++end] = i;
      // 当遍历元素 >= 滑动窗口大小，输出一个最大值，后续每滑动一次输出一个最大值
      if (i >= slideWindowLen - 1) {
        // 如果滑动窗口开始处的索引比队头存储的索引值要大，说明队头元素不在滑动窗口内
        // 由于每次只滑动一个元素，所以只要出队一次即可
        if (end >= start && (i - slideWindowLen + 1 > dqueue[start])) {
          start++;
        }
        // 输出当前窗口的最大值（队列是单调递减，取队头即可）
        maximums[maximumsCurrIndex++] = arr[dqueue[start]];
      }
      i++;
    }
    // 如果题中没有 1 <= k <= nums.length 条件，则需要以下代码
    if (i < slideWindowLen) {
      maximums[0] = arr[dqueue[start]];
    }
    return maximums;
  }
}
