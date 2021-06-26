package me.sunny.demo.algos.lc.medium;
/**
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 * 示例 2：
 *
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 *  
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 *
 * 链接：https://leetcode-cn.com/problems/rotate-list
 */

import me.sunny.demo.algos.lc.medium.RemoveNthNodeFromEnd.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class RotateLinkedList {
  public ListNode rotateRight(ListNode head, int k) {
    if (head == null) {
      return null;
    }
    if (k <= 0) {
      return  head;
    }
    // 计算链表长度，并获取尾部节点（可能需要修改Next指针）
    int len = 1;
    ListNode lPoint = head;
    while (lPoint.next != null) {
      len++;
      lPoint = lPoint.next;
    }
    int i = 1;
    // 转换的元素个数为链表长度的整数倍，等于不发生旋转
    if (k % len == 0) {
      return head;
    }
    // 设置尾节点指向头结点，形成环
    lPoint.next = head;
    // 计算断开环的位置（新链表的尾节点）
    lPoint = head;
    while (i < (len - k % len)) {
      i++;
      lPoint = lPoint.next;
    }
    // 获取头节点
    ListNode ret = lPoint.next;
    // 断开环
    lPoint.next = null;
    // 返回新链表头结点
    return ret;
  }

  public static class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
  public static void main(String[] args) {
    ListNode l = new ListNode(1);
    ListNode lidx = l;
    lidx.next = new ListNode(2);
    lidx = lidx.next;
    lidx.next = new ListNode(3);
    lidx = lidx.next;
    lidx.next = new ListNode(4);
    lidx = lidx.next;
    lidx.next = new ListNode(5);
    lidx = lidx.next;

    new RotateLinkedList().rotateRight(l, 2);
  }
}
