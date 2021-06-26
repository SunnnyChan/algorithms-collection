package me.sunny.demo.algos.lc.easy;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 */
public class MoveZeroes {
  /**
   * 核心点在于 不是 0的元素 要移动的位置数，取决于前面有几个0
   * 遍历完数组可以统计出 0的个数，此时 非0元素全部移动完成，把数组最后的元素按照0的个数置 0 即可
   */
  public void moveZeroes(int[] nums) {
    if (nums == null || nums.length == 1) {
      return;
    }
    int zCount = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 0) {
        zCount++;
        continue;
      } else {
        nums[i -  zCount] = nums[i];
      }
    }
    for (int i = nums.length - 1; i >= (nums.length - zCount); i--) {
      nums[i] = 0;
    }
  }
}
