package me.sunny.demo.algos.ds;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import me.sunny.demo.algos.basics.MergeSorterList;
import me.sunny.demo.algos.ds.def.ListNode;
import org.junit.Test;

public class JavaMergeSortedListTest {
  @Test
  public void test11() {
    List<Integer> lt1 = new LinkedList<>();
    List<Integer> lt2 = new LinkedList<>();

    lt1.add(1);
    lt1.add(10);
    lt1.add(20);
    lt1.add(30);

    lt2.add(4);
    lt2.add(7);
    lt2.add(21);
    lt2.add(21);
    lt2.add(50);

    System.out.println(new MergeSorterList().merge1(lt1, lt2));
  }
  @Test
  public void test12() {
    List<Integer> lt1 = new LinkedList<>();
    List<Integer> lt2 = new LinkedList<>();

    lt1.add(1);

    System.out.println(new MergeSorterList().merge1(lt1, lt2));
  }

  @Test
  public void test13() {
    List<Integer> lt1 = new LinkedList<>();
    List<Integer> lt2 = new LinkedList<>();

    lt1.add(1);
    lt2.add(4);

    System.out.println(new MergeSorterList().merge1(lt1, lt2));
  }

  @Test
  public void test14() {
    List<Integer> lt1 = new LinkedList<>();
    List<Integer> lt2 = new LinkedList<>();

    lt1.add(4);
    lt2.add(1);

    System.out.println(new MergeSorterList().merge1(lt1, lt2));
  }

  @Test
  public void test21() {
    ListNode l1 = new ListNode(4, null);
    ListNode l2 = new ListNode(1, null);

    ListNode lre = new MergeSorterList().merge2(l1, l2);
    while (!Objects.isNull(lre)) {
      System.out.println(lre.getValue() + ", ");
      lre = lre.getNext();
    }
  }
  @Test
  public void test22() {
    ListNode l1 = new ListNode(1, null);
    ListNode cc1 = l1;
    cc1.setNext(new ListNode(10, null));
    cc1 = cc1.getNext();
    cc1.setNext(new ListNode(20, null));
    cc1 = cc1.getNext();
    cc1.setNext(new ListNode(30, null));

    ListNode l2 = new ListNode(4, null);
    ListNode cc2 = l2;
    cc2.setNext(new ListNode(7, null));
    cc2 = cc2.getNext();
    cc2.setNext(new ListNode(21, null));
    cc2 = cc2.getNext();
    cc2.setNext(new ListNode(21, null));
    cc2 = cc2.getNext();
    cc2.setNext(new ListNode(50, null));
    cc2 = cc2.getNext();

    ListNode lre = new MergeSorterList().merge2(l1, l2);
    while (!Objects.isNull(lre)) {
      System.out.println(lre.getValue() + ", ");
      lre = lre.getNext();
    }
  }
}
