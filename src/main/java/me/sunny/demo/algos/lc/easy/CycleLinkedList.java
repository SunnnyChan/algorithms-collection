package me.sunny.demo.algos.lc.easy;
/**
 * 141. 环形链表
 *
 * 给定一个链表，判断链表中是否有环。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是-1，则在该链表中没有环。
 * 注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 *
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 *
 * 进阶：
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 *
 * 示例 1：
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 示例 2：
 *
 *
 *
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * 示例 3：
 *
 *
 *
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 *
 * 提示：
 *
 * 链表中节点的数目范围是 [0, 104]
 * -105 <= Node.val <= 105
 * pos 为 -1 或者链表中的一个 有效索引 。
 *
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle
 *
 *
 *
 * 142. 环形链表 II
 *
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 *
 * 说明：不允许修改给定的链表。
 *
 * 进阶：
 *
 * 你是否可以使用 O(1) 空间解决此题？
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 示例 2：
 *
 *
 *
 * 输入：head = [1,2], pos = 0
 * 输出：返回索引为 0 的链表节点
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * 示例 3：
 *
 *
 *
 * 输入：head = [1], pos = -1
 * 输出：返回 null
 * 解释：链表中没有环。
 *  
 *
 * 提示：
 *
 * 链表中节点的数目范围在范围 [0, 104] 内
 * -105 <= Node.val <= 105
 * pos 的值为 -1 或者链表中的一个有效索引
 *
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii
 */

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class CycleLinkedList {
  /**
   * 是否有环
   */
  public boolean hasCycle(ListNode head) {
    if (head == null || head.next == null) {
      return false;
    }
    ListNode qPoint = head.next;
    ListNode sPoint = head;
    while (qPoint != sPoint) {
      if (qPoint.next == null || qPoint.next.next == null) {
        return false;
      }
      qPoint = qPoint.next.next;
      sPoint = sPoint.next;
    }
    return true;
  }

  /**
   * 142. 环形链表 II
   * 计算入口位置
   */
  public ListNode entryNodeOfLoop(ListNode head) {
    if (!hasCycle(head)) {
      return null;
    }
    // 要注意：要从一个起点出发才能得到 每次 快指针走过的路径是 慢指针的两倍，计算换入口位置的公式是基于 Lfast = 2 Lslow 的
    ListNode fast = head;
    ListNode slow = head;
    do {
      fast = fast.next.next;
      slow = slow.next;
    } while (slow != fast);
    slow = head;
    // 一个从头开始走 ，一个从相遇地方开始走，走同样的步数
    // 因为 x= z+(n-1)L  ，L是环长，设 a 是环入口，x为链表头到 a 的距离，z 为 第一次相遇点 到 a的距离
    while(slow != fast){
      slow = slow.next;
      fast = fast.next;
    }
    return slow;
  }

  static class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  public static void main(String[] args) {
    ListNode l = new ListNode(3);
    ListNode lidx = l;

    ListNode node2 = new ListNode(2);
    lidx.next = node2;
    lidx = lidx.next;

    lidx.next = new ListNode(0);
    lidx = lidx.next;

    ListNode node4 = new ListNode(-4);
    lidx.next = node4;
    lidx = lidx.next;
    node4.next = node2;

    System.out.println(new CycleLinkedList().hasCycle(l));
    System.out.println(new CycleLinkedList().entryNodeOfLoop(l).val);
  }
}
