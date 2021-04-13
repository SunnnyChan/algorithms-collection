package me.sunny.demo.algos.ds.list;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 循环有序数组
 * @author sunny
 */
public class CircularOrderedArray {

  /**
   * 查找元素
   * 注：a1 = an 这种情况没有考虑。
   */
  public Integer find(ArrayList<Integer> arrayList, Integer ele) {
    if (arrayList.isEmpty()) {
      return -1;
    }
    if (arrayList.size() == 1) {
      return ele.intValue() == arrayList.get(0).intValue() ? 0 : -1;
    }
    // 判断总体上是递增还是递减数组
    if (arrayList.get(0) > arrayList.get(arrayList.size() - 1)) {
      return findInIncreasingArray(arrayList, ele);
    } else if (arrayList.get(0) < arrayList.get(arrayList.size() - 1)) {
      return findInDecreasingArray(arrayList, ele);
    } else {
      throw new IllegalArgumentException("非单调有序数组");
    }
  }

  /**
   * 递增数组中查找
   * @param arrayList 循环有序数组
   * @param ele 待查找元素
   * @return 数组索引
   */
  private Integer findInIncreasingArray(ArrayList<Integer> arrayList, Integer ele) {
    int start = 0;
    int end = arrayList.size() - 1;
    while (start <= end) {
      int mid = start + (end - start)/2;
      int midValue = arrayList.get(mid);
      if (midValue >= arrayList.get(start)) {
        if (ele.intValue() == arrayList.get(mid)) {
          return mid;
        } else if (ele < arrayList.get(mid) && ele >= arrayList.get(start)) {
          end = mid - 1;
        } else {
          start = mid + 1;
        }
      } else {
        if (ele.intValue() == arrayList.get(mid)) {
          return mid;
        } else if (ele > arrayList.get(mid) && ele <= arrayList.get(end)) {
          start = mid + 1;
        } else {
          end = mid - 1;
        }
      }
    }
    return -1;
  }

  /**
   * 递减数组中查找
   * @param arrayList 循环有序数组
   * @param ele 待查找元素
   * @return 数组索引
   */
  private Integer findInDecreasingArray(ArrayList<Integer> arrayList, Integer ele) {
    int start = 0;
    int end = arrayList.size() - 1;
    while (start <= end) {
      int mid = start + (end - start)/2;
      int midValue = arrayList.get(mid);
      if (midValue >= arrayList.get(start)) {
        if (ele.intValue() == arrayList.get(mid)) {
          return mid;
        } else if (ele < arrayList.get(mid) && ele >= arrayList.get(end)) {
          start = mid + 1;
        } else {
          end = mid - 1;
        }
      } else {
        if (ele.intValue() == arrayList.get(mid)) {
          return mid;
        } else if (ele <= arrayList.get(start) && ele > arrayList.get(mid)) {
          end = mid - 1;
        } else {
          start = mid + 1;
        }
      }
    }
    return -1;
  }

  public int findMax(int[] array) {
    if (array.length == 0) {
      return -1;
    }
    if (array.length == 1) {
      return array[0];
    }
    if (array.length == 2) {
      return array[0] > array[1] ? array[0] : array[1];
    }
    if (array[0] > array[array.length -1]) {
      int i  = 0;
      while (i < array.length && array[i + 1] >= array[i]) {
        i++;
      }
      return array[i];
    } else if (array[0] < array[array.length -1]) {
      int i  = array.length -1;
      while (i >= 0 && array[i-1] >= array[i]) {
        i--;
      }
      return array[i];
    } else {
      throw new IllegalArgumentException("非单调有序数组");
    }
  }

  public static void main(String[] args) {
    CircularOrderedArray circularOrderedArray = new CircularOrderedArray();

    // find() Test
    findTest(new ArrayList<>(), circularOrderedArray);
    findTest(new ArrayList<>(Arrays.asList(15)), circularOrderedArray);
    findTest(new ArrayList<>(Arrays.asList(15, 1)), circularOrderedArray);
    // 单调 递增
    // return 3、4、6、-1、1、9
    findTest(new ArrayList<>(Arrays.asList(12, 16, 18, 20, 41, 100, 1, 4, 6, 9)), circularOrderedArray);
    // 单调 递减
    // return 6、5、3、-1、8、0
    findTest(new ArrayList<>(Arrays.asList(9, 6, 4, 1, 100, 41, 20, 18, 16, 12)), circularOrderedArray);
    try {
      // 非单调有序数组
      findTest(new ArrayList<>(Arrays.asList(9, 16, 18, 20, 41, 100, 1, 4, 6, 9)), circularOrderedArray);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    // findMax Test
    int[] arr = {};
    System.out.println(circularOrderedArray.findMax(arr));
    int[] arr1 = {15};
    System.out.println(circularOrderedArray.findMax(arr1));
    int[] arr2 = {15, 1};
    System.out.println(circularOrderedArray.findMax(arr2));
    int[] arr3 = {12, 16, 18, 20, 41, 100, 1, 4, 6, 9};
    System.out.println(circularOrderedArray.findMax(arr3));
    int[] arr4 = {9, 6, 4, 1, 100, 41, 20, 18, 16, 12};
    System.out.println(circularOrderedArray.findMax(arr4));
    int[] arr5 = {9, 16, 18, 20, 41, 100, 1, 4, 6, 9};
    try {
      // 非单调有序数组
      circularOrderedArray.findMax(arr5);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private static void findTest(ArrayList<Integer> arrayList,  CircularOrderedArray circularOrderedArray) {
    System.out.println("Test :");
    // return 3
    System.out.println(circularOrderedArray.find(arrayList, 20));
    // return 4
    System.out.println(circularOrderedArray.find(arrayList, 41));
    // return 6
    System.out.println(circularOrderedArray.find(arrayList, 1));
    // return -1
    System.out.println(circularOrderedArray.find(arrayList, 15));
    // return 1
    System.out.println(circularOrderedArray.find(arrayList, 16));
    // return 9
    System.out.println(circularOrderedArray.find(arrayList, 9));
  }
}
