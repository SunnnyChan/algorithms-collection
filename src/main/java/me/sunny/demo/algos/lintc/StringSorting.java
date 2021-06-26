package me.sunny.demo.algos.lintc;

/**
 * 描述
 * 给定一些由,隔开的字符串，请将他们按字典序排列。
 *
 * 字符串仅包含小写字母。
 * 字符串数量\leq 1\,000≤1000且总长度\leq 10^5≤10
 * 5
 *
 * 样例
 * 样例 1
 *
 * 输入: "bb,aa,lintcode,c"
 * 输出: "aa,bb,c,lintcode"
 * 说明: 在字典序中，"aa" < "bb" < "c" < "lintcode"
 * 挑战
 * 你可以自己写一个排序函数吗？
 */
public class StringSorting {
  /**
   * @param s: string
   * @return: sort string in lexicographical order
   */
  public String sorting(String s) {
    if (s == null) {
      return null;
    }
    if (s.isEmpty()) {
      return s;
    }
    String[] strings = s.split(",");
    sortStrings(strings, 0, strings.length - 1);
    StringBuilder stringBuilder = new StringBuilder();
    for (String str : strings) {
      stringBuilder.append(str);
      stringBuilder.append(",");
    }
    return  stringBuilder.substring(0, stringBuilder.length() - 1).toString();
  }

  /**
   * 快速排序
   */
  private void sortStrings(String[] strings, int start, int end) {
    if (start >= end) {
      return;
    }
    String pivot = strings[start];
    int l = start;
    int r = end;
    while (l < r) {
      while (compare(strings[r], pivot) && l < r) {
        r--;
      }
      while (!compare(strings[l], pivot) && l < r) {
        l++;
      }
      if (l < r) {
        String t = strings[l];
        strings[l] = strings[r];
        strings[r] = t;
      }
    }
    strings[start] = strings[l];
    strings[l] = pivot;
    sortStrings(strings, start, r - 1);
    sortStrings(strings, r + 1, end);
  }

  /**
   * 比较两个字符
   * @return  true str1 > str2
   */
  private boolean compare(String str1, String str2) {
    int i = 0;
    while (i < str1.length()) {
      if (i < str2.length()) {
        if (str1.charAt(i) != str2.charAt(i)) {
          return str1.charAt(i) > str2.charAt(i);
        }
      }
      i++;
    }
    // 如果是 aa, aab 则排序为 aab, aa
    return i > str2.length();
  }

  public static void main(String[] args) {
    String str = "bb,aa, aab,lintcode,c";
    System.out.println(new StringSorting().sorting(str));
  }
}
