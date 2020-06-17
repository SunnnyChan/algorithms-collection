package me.sunny.demo.algos.leetcode;

import java.util.Objects;

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
