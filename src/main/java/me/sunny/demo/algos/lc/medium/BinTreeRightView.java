package me.sunny.demo.algos.lc.medium;

/**
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例:
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

public class BinTreeRightView {
  /**
   * 整体思路：
   * 层序遍历，找最右边的节点
   */
  public List<Integer> rightSideView(TreeNode root) {
    if (root == null) {
      return new LinkedList<Integer>();
    }
    // 当前遍历的层 节点队列
    LinkedList<TreeNode> pl = new LinkedList<>();
    pl.add(root);
    // 下一层节点队列
    LinkedList<TreeNode> sl = new LinkedList<>();
    // 返回值
    List<Integer> lret = new ArrayList<>();
    // 首先加入根节点
    lret.add(root.val);
    // 按层遍历
    while (!pl.isEmpty()) {
      // 每层的最右侧节点
      Integer levelRightNodeVal = null;
      // 遍历每一层的节点
      while (!pl.isEmpty()) {
        TreeNode treeNode = pl.pop();
        // 获取下一层最右侧的节点，同时将下一层的节点如队列，等待被遍历
        if (treeNode.left != null) {
          levelRightNodeVal = treeNode.left.val;
          sl.add(treeNode.left);
        }
        if (treeNode.right != null) {
          levelRightNodeVal = treeNode.right.val;
          sl.add(treeNode.right);
        }
      }
      // 写入输出链表
      if (levelRightNodeVal != null) {
        lret.add(levelRightNodeVal);
      }
      // 交换队列，将下一层节点队列，作为当前队列，继续遍历
      // 原 sl 指向的队列是需要遍历的队列
      // 原 pl 指向的队列此时已经空，作为存储下一层节点的队列
      LinkedList<TreeNode> tl = pl;
      pl = sl;
      sl = tl;
    }
    return lret;
  }

  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }
}
