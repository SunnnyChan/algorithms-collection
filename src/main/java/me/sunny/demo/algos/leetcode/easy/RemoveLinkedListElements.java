package me.sunny.demo.algos.leetcode.easy;

import java.util.Objects;
/**
 * 203. 移除链表元素
 *
 * 删除链表中等于给定值 val 的所有节点。
 *
 * 示例:
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 *
 * @author sunnychan@gmail.com
 */
public class RemoveLinkedListElements {

    public ListNode removeElements(ListNode head, int val) {
        ListNode node = head;
        while(!Objects.isNull(node)) {
            if (Objects.isNull(node.next)) {
                break;
            }
            if (node.next.val == val) {
                node.next = node.next.next;
            }
            node = node.next;
        }
        return head;
    }

    public ListNode removeElements2(ListNode head, int val) {
        ListNode node = head;
        ListNode preNode = null;
        while(!Objects.isNull(node)) {
            if (node.val == val) {
                if (Objects.isNull(preNode)) {
                    head = node.next;
                } else {
                    preNode.next = node.next;
                }
            } else {
                preNode = node;
            }
            node = node.next;
        }
        return head;
    }

     public class ListNode {
        int val;
        ListNode next;
        public ListNode(int x) { val = x; }

         public ListNode getNext() {
             return next;
         }

         public void setNext(ListNode next) {
             this.next = next;
         }
     }
}
