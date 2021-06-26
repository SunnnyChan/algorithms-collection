package me.sunny.demo.algos.lc.medium;

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 *
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *  
 *
 * 提示：
 *
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
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
public class RemoveNthNodeFromEnd {
  /**
   * 快慢指针
   */
  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode lPoint = head;
    ListNode nSlowPoint = head;
    while (lPoint != null) {
      if (n < 0) {
        nSlowPoint = nSlowPoint.next;
      }
      n--;
      lPoint = lPoint.next;
    }
    if (n > 0) {
      // 列表长度小于 n
      return head;
    } else if (n == 0) {
      // 列表长度等于 n ，删除表头元素要单独处理
      return head.next;
    } else {
      // 列表长度大于 n
      nSlowPoint.next = nSlowPoint.next.next;
    }
    return head;
  }

  public static class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }
    ListNode(int val) {
      this.val = val;
    }
    ListNode(int val, ListNode next) {
      this.val = val; this.next = next;
    }
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

    new RemoveNthNodeFromEnd().removeNthFromEnd(l, 5);
  }
}
