package me.sunny.demo.algos.lc.easy;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 *
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *  
 *
 * 提示：
 *
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 *
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 */
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
public class MergeTwoSortedLinkedList {
  // 新建 链表
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode retList = null;
    ListNode retListPoint = null;
    ListNode l1Point = l1;
    ListNode l2Point = l2;
    while (l1Point != null || l2Point != null) {
      ListNode newNode = new ListNode();
      newNode.next = null;
      if (l1Point != null && l2Point != null) {
        if (l1Point.val <= l2Point.val) {
          newNode.val = l1Point.val;
          l1Point = l1Point.next;
        } else {
          newNode.val = l2Point.val;
          l2Point = l2Point.next;
        }
      } else {
        if (l1Point != null) {
          newNode.val = l1Point.val;
          l1Point = l1Point.next;
        } else {
          newNode.val = l2Point.val;
          l2Point = l2Point.next;
        }
      }
      if (retList == null) {
        retList = newNode;
        retListPoint = newNode;
      } else {
        retListPoint.next = newNode;
        retListPoint = newNode;
      }
    }
    return retList;
  }

  // 修改原有链表
  public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
    ListNode retList = new ListNode();
    ListNode retListPoint = retList;
    ListNode l1Point = l1;
    ListNode l2Point = l2;
    while (l1Point != null && l2Point != null) {
      if (l1Point.val <= l2Point.val) {
        retListPoint.next = l1Point;
        l1Point = l1Point.next;
      } else {
        retListPoint.next = l2Point;
        l2Point = l2Point.next;
      }
      retListPoint = retListPoint.next;
    }
    if (l1Point != null) {
      retListPoint.next = l1Point;
    }
    if (l2Point != null) {
      retListPoint.next = l2Point;
    }
    return retList.next;
  }

  // 递归
  public ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    }
    if (l2 == null) {
      return l1;
    }
    if (l1.val <= l2.val) {
      l1.next = mergeTwoLists3(l1.next, l2);
      return l1;
    } else {
      l2.next = mergeTwoLists3(l1, l2.next);
      return l2;
    }
  }

  public static class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
