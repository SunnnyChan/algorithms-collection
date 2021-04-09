package me.sunny.demo.algos.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sunnnychan@gmail.com
 */
public class JavaArrayList {

  // 初始化

  public void init() {
    // Create ArrayList in single statement
    ArrayList<String> names = new ArrayList<String>(Arrays.asList("alex", "brian", "charles"));

    System.out.println(names);

    // Create list in single statement  (Java 9)
    // names = List.of("alex", "brian");
    // System.out.println(names);

    // Create arraylist with constructor
    ArrayList<String> names1 = new ArrayList<>();

    names1.add("alex");
    names1.add("brian");
    names1.add("charles");

    System.out.println(names1);

    // ArrayList of lists
    List<List<Integer>> marks = new ArrayList<>();

    marks.add(Arrays.asList(10, 20, 30));
    marks.add(Arrays.asList(40, 50, 60));
    marks.add(Arrays.asList(70, 80, 90));

    for (List<Integer> mark : marks) {
      System.out.println(mark);
    }
  }

  // 清空
  public void clear() {
    ArrayList<String> list = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));
    list.clear();   //clear the list
    System.out.println(list);
    System.out.println("size : " + list.size());

    ArrayList<String> list1 = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));
    //remove all elements from self
    list1.removeAll(list1);

    System.out.println(list1);
    System.out.println("size : " + list1.size());
  }

  public static void main(String[] args) {
    new JavaArrayList().init();
    new JavaArrayList().clear();
  }

}
