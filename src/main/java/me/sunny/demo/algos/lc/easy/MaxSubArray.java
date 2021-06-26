package me.sunny.demo.algos.lc.easy;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例 1：
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：0
 * 示例 4：
 *
 * 输入：nums = [-1]
 * 输出：-1
 * 示例 5：
 *
 * 输入：nums = [-100000]
 * 输出：-100000
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * -105 <= nums[i] <= 105
 *  
 *
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 *
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 */
public class MaxSubArray {

  // 递归方式
  public int maxSubArray(int[] nums) {
    return this.maxSub(nums, 0, nums.length - 1)[0];
  }

  private int[] maxSub(int[] nums, int start, int end) {
     // ret 返回两个值
    int[] ret = new int[2];
    // 只有一个元素时
    if (start == end) {
      // 当前已经求出的所有值中的最大值
      ret[0] = nums[start];
      // f(i)
      ret[1] = nums[start];
      return ret;
    }
    int[] fNext = maxSub(nums, start + 1, end);
    int fCurrent = (fNext[1] + nums[start]) > nums[start] ? fNext[1] + nums[start] : nums[start];
    ret[0] = fCurrent > fNext[0] ? fCurrent : fNext[0];
    ret[1] = fCurrent;
    return ret;
  }

  /**
   *   非递归 （动态规划）
   *   思路有两个重要的点：
   *   f(i) 代表以第 i 个数结尾的「连续子数组的最大和」，那么最终的解就是 遍历数组 求 f(0) ~ f(n-1)，取其中的最大值
   *   f(i) 取决于 nums[i] 和 f(i-1) + nums[i] 的大小
   *
   *   动态规划转移方程：
   *   f(i) = max{ f(i−1) + nums[i], nums[i] }
   *
   *   时间复杂度：O(n)，其中 n 为  数组的长度。
   *   空间复杂度：O(1)，只需要常数空间存放若干变量。
   */
  public int maxSubArray1(int[] nums) {
    int max = nums[0];
    int pre = 0;
    for (int i : nums) {
      // 求 f(i)
      pre = (i + pre) > i ? (i + pre) : i;
      // 求出 f(i) 后，找到当前已经求出的所有值中的最大值
      max = max > pre ? max : pre;
    }
    return max;
  }

  // 分治算法：线段树思想
  // https://leetcode-cn.com/problems/maximum-subarray/solution/zui-da-zi-xu-he-by-leetcode-solution/
  // 贪心算法：
}
