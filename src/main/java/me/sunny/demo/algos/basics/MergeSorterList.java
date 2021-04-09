package me.sunny.demo.algos.basics;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import me.sunny.demo.algos.ds.def.ListNode;
/**
 * 问题：2个有序链表合并为一个有序链表，链表值int类型
 */
public class MergeSorterList {

  public List<Integer> merge1 (List<Integer> l1, List<Integer> l2) {
    if (l1.isEmpty()) {
      return l2;
    }
    if (l2.isEmpty()) {
      return l1;
    }
    List<Integer> lre = new LinkedList<>();
    // 链表迭代器
    Iterator<Integer> iterator1 = l1.iterator();
    Iterator<Integer> iterator2 = l2.iterator();

    // current 表示当前值
    Integer current1 = iterator1.next();
    Integer current2 = iterator2.next();
    // 同时遍历两个链表，直到一个链表遍历完成
    while (!Objects.isNull(current1) && !Objects.isNull(current2)){
      if (current1 < current2) {
        lre.add(current1);
        current1 = iterator1.hasNext() ? iterator1.next() : null;
      } else {
        lre.add(current2);
        current2 = iterator2.hasNext() ? iterator2.next() : null;
      }
    }
    // 处理未遍历完的链表
    while (!Objects.isNull(current1)) {
      lre.add(current1);
      current1 = iterator1.hasNext() ? iterator1.next() : null;
    }
    while (!Objects.isNull(current2)) {
      lre.add(current2);
      current2 = iterator2.hasNext() ? iterator2.next() : null;
    }
    return lre;
  }

  public ListNode merge2 (ListNode l1, ListNode l2) {
    if (Objects.isNull(l1)) {
      return l2;
    }
    if (Objects.isNull(l2)) {
      return l1;
    }
    ListNode lre = null;
    if (l1.getValue() < l2.getValue()) {
      lre = l1;
      lre.setNext(merge2(l1.getNext(), l2));
    } else {
      lre = l2;
      lre.setNext(merge2(l1, l2.getNext()));
    }
    return lre;
  }
}
