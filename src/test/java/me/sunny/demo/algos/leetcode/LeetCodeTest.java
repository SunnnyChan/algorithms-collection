package me.sunny.demo.algos.leetcode;

import java.io.Serializable;

import me.sunny.demo.algos.leetcode.easy.RemoveLinkedListElements;
import me.sunny.demo.algos.leetcode.easy.RemoveLinkedListElements.ListNode;
import me.sunny.demo.algos.leetcode.easy.HappyNumber;
import org.junit.Test;
import org.testng.Assert;

public class LeetCodeTest implements Serializable {

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
