package me.sunny.demo.algos.lc.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 *
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：[[1]]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 *
 * 链接：https://leetcode-cn.com/problems/permutations
 */
public class PermuteAll {

  public List<List<Integer>> permute(int[] nums) {
    if (nums == null || nums.length == 0) {
      return new LinkedList<>();
    }
    return recur(nums, 0, nums.length - 1);
  }

  private List<List<Integer>> recur(int[] nums, int start, int end) {
    // 返回一个元素的全排列
    if (start == end) {
      List<Integer> l = new LinkedList<>();
      l.add(nums[start]);
      List<List<Integer>> ls = new LinkedList<>();
      ls.add(l);
      return ls;
    }
    List<List<Integer>> lres = recur(nums, start + 1, end);
    List<List<Integer>> ls = new LinkedList<>();
    // 在计算得到更小规模元素的全排列基础上，计算加上当前元素的全排列
    for (List<Integer> le : lres) {
      for (int i = 0; i <= le.size(); i++) {
        List<Integer> lt = new LinkedList<>(le);
        lt.add(i, nums[start]);
        ls.add(lt);
      }
    }
    return ls;
  }


  public List<List<Integer>> permute1(int[] nums) {
    if (nums == null || nums.length == 0) {
      return new LinkedList<>();
    }
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    List<Integer> output = new ArrayList<Integer>();
    for (int num : nums) {
      output.add(num);
    }

    backtrack(nums.length, output, res, 0);

    return res;
  }

  private void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
    if (n == first) {
      res.add(new ArrayList<Integer>(output));
    }
    for (int i = first; i < n; i++) {
      Collections.swap(output, first, i);
      backtrack(n, output, res, first + 1);
      Collections.swap(output, i, first);
    }
  }

  public static void main(String[] args) {
    int[] nums = {1};
    new PermuteAll().permute1(nums);
  }

}
