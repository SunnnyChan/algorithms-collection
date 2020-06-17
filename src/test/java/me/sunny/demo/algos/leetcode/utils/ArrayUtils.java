package me.sunny.demo.algos.leetcode.utils;

public class ArrayUtils {

  public static void print(char[][] a) {

    System.out.print("\t\t");
    for (int i = 0; i < a[0].length; i++) {
      System.out.print(i + "\t");
    }
    System.out.print("\n\t" + "------------------------------");
    System.out.println();
    for (int i = 0; i < a.length; i++) {
      System.out.print(i + "  |\t");

      for (int j = 0; j < a[i].length; j++) {
        System.out.print(a[i][j] + "\t");
      }
      System.out.println();
    }
  }

}
