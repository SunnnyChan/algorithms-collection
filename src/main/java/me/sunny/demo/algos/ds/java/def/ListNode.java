package me.sunny.demo.algos.ds.java.def;

public class ListNode {

  private Integer value;

  private  ListNode next;

  public ListNode(Integer value, ListNode next) {
    this.value = value;
    this.next = next;
  }

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  public ListNode getNext() {
    return next;
  }

  public void setNext(ListNode next) {
    this.next = next;
  }
}
