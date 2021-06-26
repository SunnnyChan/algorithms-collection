package me.sunny.demo.algos.lc.easy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入整数数组 arr ，找出其中最小的 k 个数。
 * 例如，输入4、5、1、6、2、7、3、8 这8个数字，则最小的4个数字是1、2、3、4。
 *
 * 示例 1：
 *
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 *
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *  
 *
 * 限制：
 *
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 *
 *
 * 链接：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof
 */
public class MimKnum {

  public int[] getLeastNumbers(int[] arr, int k) {
    if (arr == null || arr.length == 0 || k <=0) {
      return new int[0];
    }
    if (arr.length <= k) {
      return arr;
    }
    int[] ret = new int[k];
    ret[k-1] = arr[0];
    int eleN = 1;
    for (int i = 1; i < arr.length; i++) {
      if (eleN < k) {
        insertSorted(ret, k - eleN, k - 1, arr[i]);
        eleN++;
      } else {
        if (arr[i] < ret[0]) {
          insertSorted(ret, 1, k - 1, arr[i]);
        }
      }
    }
    return ret;
  }

  /**
   * 采用插入排序 维护K的数列
   */
  private void insertSorted(int[] arr, int start,  int end, int k) {
    int i = start;
    while (i <= end) {
      if (k >= arr[i]) {
        break;
      }
      i++;
    }
    for (int j = start; j < i; j++) {
      arr[j-1] = arr[j];
    }
    arr[i-1] = k;
  }

  // 快排的变形
  public int[] getLeastNumbers2(int[] arr, int k) {
    if (k <= 0) {
      return new int[0];
    }
    if (arr == null || arr.length <= k) {
      return arr;
    }
    // 做类似快排的划分
    partitionArray(arr, 0, arr.length - 1, k);
    int[] res = new int[k];
    // 数组的前 k个元素即为解
    for (int i = 0; i < k; i++) {
      res[i] = arr[i];
    }
    return res;
  }

  private void partitionArray(int[] arr, int start, int end, int k) {
    // 当每个划分只有一个元素时
    if (start >= end) {
      return;
    }
    // 做一次划分，Pos 返回 标兵 的位置
    int pos = partition(arr, start, end);
    // 计算 Pos 右侧（包含Pos）的元素个数
    int nums = pos - start + 1;
    // 如果刚好是 K个元素，那解题完成
    if (k == nums) {
      return;
    } else if (k < nums) {
      // k 小于 nums，说明题的解在 划分后的右侧区域，继续在左侧区域查找 k 个数
      partitionArray(arr, start, pos - 1, k);
    } else {
      // k > nums，划分后的右侧区域都是解，同时 k - nums 个解元素在右侧区域，继续在右侧查找 k - nums 数
      partitionArray(arr, pos + 1, end, k - nums);
    }
  }

  private int partition(int[] arr, int start, int end) {
    // 将末尾的节点 作为 标兵
    int pivot = arr[end];
    // i 标识 在标兵 左侧区域元素的地址
    int i = start - 1;
    for (int j = start; j < end; j++) {
      // 该数应该放在标兵的左侧区域
      if (arr[j] <= pivot) {
        i++;
        swap(arr, i, j);
      }
    }
    // 把标兵放到 目标位置
    swap(arr, i + 1, end);
    // 返回标兵元素的 位置
    return i + 1;
  }

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  // 基于 堆
  public int[] getLeastNumbers3(int[] arr, int k) {
    int[] res = new int[k];
    if (k <= 0) {
      return res;
    }
    if (arr == null || arr.length <= k) {
      return arr;
    }
    PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o2 - o1;
      }
    });
    for (int i = 0; i < k; i++) {
      queue.offer(arr[i]);
    }
    for (int i = k; i < arr.length; i++) {
      if (queue.peek() > arr[i]) {
        queue.poll();
        queue.offer(arr[i]);
      }
    }
    for (int i = 0; i < k; i++) {
      res[i] = queue.poll();
    }
    return res;
  }

  public static void main(String[] args) {
    int[] test = {0,0,1,2,4,2,2,3,1,4};
    new MimKnum().getLeastNumbers(test, 8);
  }
}
