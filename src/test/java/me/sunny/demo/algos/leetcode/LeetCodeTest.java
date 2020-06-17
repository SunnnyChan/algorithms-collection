package me.sunny.demo.algos.leetcode;

import me.sunny.demo.algos.leetcode.RemoveLinkedListElements.ListNode;
import org.junit.Test;
import org.testng.Assert;

public class LeetCodeTest {

    @Test
    public void testHappyNumber() {
        int n = 19;
        HappyNumber happyNumber = new HappyNumber();
        Assert.assertTrue(happyNumber.isHappy(n));
    }

    @Test
    public void testRemoveLinkedListElements() {
        RemoveLinkedListElements removeLinkedListElements = new RemoveLinkedListElements();
        ListNode list = removeLinkedListElements.new ListNode(-1);
        ListNode node = removeLinkedListElements.new ListNode(1);
        node.setNext(null);
        list.setNext(node);
        removeLinkedListElements.removeElements(list, 1);
    }

}
