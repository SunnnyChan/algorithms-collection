package me.sunny.demo.algos.lc.medium;

/**
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层序遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
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
public class LevelOrderBinTree {
  public List<List<Integer>> levelOrder(TreeNode root) {
    if (root == null) {
      return new LinkedList<>();
    }
    // pl 是父节点队列
    LinkedList<TreeNode> pl = new LinkedList<>();
    pl.add(root);
    // sl 子节点队列，在遍历一层节点时，把这层的每个节点的子节点放入 sl
    LinkedList<TreeNode> sl = new LinkedList<>();
    // 结果链表
    LinkedList<List<Integer>> lret = new LinkedList<>();
    // 每一层的遍历结果链表
    List<Integer> nl = new LinkedList<>();
    while (true) {
      // 从最上层开始遍历，初始是 遍历根，pl 队列不空就一直遍历输出
      if (!pl.isEmpty()) {
        TreeNode treeNode = pl.pop();
        nl.add(treeNode.val);
        // 将字节点放入待遍历的队列
        if (treeNode.left != null) {
          sl.add(treeNode.left);
        }
        if (treeNode.right != null) {
          sl.add(treeNode.right);
        }
      } else {
        // 如果当前层的遍历结果链表不为空，加入 结果链表
        if (!nl.isEmpty()) {
          lret.add(nl);
          nl = new LinkedList<>();
        }
        // 如果 sl 队列不为空，说明下层有节点需要遍历，否则遍历结束
        if (!sl.isEmpty()) {
          // 交换 sl 与 pl，pl始终指向要遍历的队列，sl 指向待遍历的下一层节点队列
          LinkedList<TreeNode> tl = pl;
          pl = sl;
          sl = tl;
        } else {
          break;
        }
      }
    }
    return lret;
  }

  /**
   * 二叉树的之字形层序遍历
   *
   * 总体思路：
   * 层序遍历方法不变，在每次遍历的时候，需要遍历两边，一次是找 子节点，一次是按输出的顺序要求做一次遍历输出，（标记输出方向）
   *
   * 优化：
   * 正向（从左到右）输出其实只需要遍历一次，逆向则需要两次
   */
  public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }
    LinkedList<TreeNode> pl = new LinkedList<>();
    pl.add(root);
    LinkedList<TreeNode> sl = new LinkedList<>();

    ArrayList<ArrayList<Integer>> lret = new ArrayList<>();
    ArrayList<Integer> nl = new ArrayList<>();
    // 标记输出方向
    int flag = 0;

    while (!pl.isEmpty()) {
      // 获取子节点（子节点在队列的顺序一直保持正向）
      // 这里我采用的是 统一获取下层节点，统一通过标记输出
      // 可以优化的点：
      // 正向时，直接遍历获取子节点并同时输出
      // 逆向时，再分开为两步
      for (TreeNode treeNode : pl) {
        if (treeNode.left != null) {
          sl.add(treeNode.left);
        }
        if (treeNode.right != null) {
          sl.add(treeNode.right);
        }
      }
      // 根据输出方向的标记输出
      if (flag == 0) {
        while (!pl.isEmpty()) {
          nl.add(pl.pop().val);
        }
        flag = 1;
      } else {
        while (!pl.isEmpty()) {
          nl.add(pl.pollLast().val);
        }
        flag = 0;
      }
      // 每层的输出加入结果集
      lret.add(nl);
      // 进入下一层
      nl = new ArrayList<>();
      // 交换队列
      LinkedList<TreeNode> tl = pl;
      pl = sl;
      sl = tl;
    }
    return lret;
  }

  public static class TreeNode {
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

  public static void main(String[] args) {
    TreeNode t1 = new TreeNode(3);
    TreeNode t2 = new TreeNode(9);
    TreeNode t3 = new TreeNode(20);
    TreeNode t4 = new TreeNode(15);
    TreeNode t5 = new TreeNode(7);
    t1.left = t2;
    t1.right = t3;
    t3.left = t4;
    t3.right = t5;
    System.out.println(new LevelOrderBinTree().levelOrder(t1));

    TreeNode t11 = new TreeNode(1);
    TreeNode t21 = new TreeNode(2);
    t11.right = t21;
    System.out.println(new LevelOrderBinTree().zigzagLevelOrder(t11));
  }
}
