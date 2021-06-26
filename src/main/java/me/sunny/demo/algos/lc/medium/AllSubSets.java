package me.sunny.demo.algos.lc.medium;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 *
 * 链接：https://leetcode-cn.com/problems/subsets
 */

public class AllSubSets {
  // 递归
  public List<List<Integer>> subsets(int[] nums) {
    if (nums == null || nums.length == 0) {
      return new ArrayList<>();
    }
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    getSubSets(res, nums, 0, nums.length - 1);
    return res;
  }

  private void getSubSets(List<List<Integer>> res, int[] nums, int start, int end) {
    //递归到只包含一个元素，建设该元素为 a，则它的全排列是 {},{a}
    if (start == end) {
      List<Integer> list = new ArrayList<Integer>();
      list.add(nums[start]);
      res.add(new ArrayList<>());
      res.add(list);
      return;
    }
    // 求不包含起始节点的全排列
    getSubSets(res, nums, start + 1, end);
    int currSize = res.size();
    // 包含当前节点的全排列为：把当前节点全部加入到求得的全排列中，再加上原来已经求得的排列
    for (int i = 0; i < currSize; i++) {
      List<Integer> nl = new ArrayList<>(res.get(i));
      nl.add(nums[start]);
      res.add(nl);
    }
  }

  // 位图
  public List<List<Integer>> subsets1(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    if (nums == null || nums.length == 0) {
      return res;
    }
    int n = nums.length;
    // 最大的二进制数，2的n次方-1
    int max = 1;
    for (int i = 1; i <= n; i++) {
      max = max << 1;
    }
    max = max - 1;
    // 从0 开始一直遍历到 max
    for (int i = 0; i <= max; i++) {
      List<Integer> list = new ArrayList<>();
      int j  = i;
      int k = 0;
      // 遍历整数的二进制位，该位上为1 则加入 集合，遍历一次则得到一个排列
      while (j > 0 && k < n) {
        if (j % 2 == 1) {
          list.add(nums[k]);
        }
        j = j >> 1;
        k++;
      }
      // 加入结果集
      res.add(new ArrayList<>(list));
    }
    return res;
  }
  // 回溯
  public List<List<Integer>> subsets2(int[] nums) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if (nums == null || nums.length == 0) {
      return res;
    }
    // 标记节点是不是在 子列
    BitSet visit = new BitSet(nums.length);
    // 回溯
    backtrack(res, nums, visit, 0, nums.length - 1);
    return res;
  }

  private void backtrack(List<List<Integer>> res, int[] nums, BitSet visit, int start, int end){
    // 所有节点都有设置标识，然后输出
    if (start > end) {
      List<Integer> set = new ArrayList<>();
      for (int i = 0; i < nums.length; i++) {
        if (visit.get(i)) {
          set.add(nums[i]);
        }
      }
      res.add(set);
      return;
    }
    // 默认 当前节点不在 子列
    backtrack(res, nums, visit, start + 1, end);
    // 当前节点 在子列
    visit.set(start);
    backtrack(res, nums, visit, start + 1, end);
    // 清除标记
    visit.set(start, false);
  }

  // 回溯2
  public List<List<Integer>> subsets3(int[] nums) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if (nums == null || nums.length == 0) {
      return res;
    }
    // 回溯
    List<Integer> subset = new ArrayList<>();
    backtrack1(res, nums, subset,0, nums.length - 1);
    // 处理不到空集，单独处理
    res.add(new ArrayList<>());
    return res;
  }

  private void backtrack1(List<List<Integer>> res, int[] nums, List<Integer> subset, int start, int end){
    while (start <= end) {
      subset.add(nums[start]);
      res.add(new ArrayList<>(subset));
      start++;
      backtrack1(res, nums, subset, start, end);
      subset.remove(subset.size() - 1);
    }
  }

  public static void main(String[] args) {
    int[] nums = {1, 2, 3};
    System.out.println(new AllSubSets().subsets3(nums));
  }
}
