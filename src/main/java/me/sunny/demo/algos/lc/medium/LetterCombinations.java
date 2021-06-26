package me.sunny.demo.algos.lc.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 示例 1：
 *
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 示例 2：
 *
 * 输入：digits = ""
 * 输出：[]
 * 示例 3：
 *
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *  
 *
 * 提示：
 *
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 *
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 */
public class LetterCombinations {

  public List<String> letterCombinations(String digits) {
    List<String> combinations = new ArrayList<String>();
    if (digits.length() == 0) {
      return combinations;
    }
    Map<Character, String> phoneMap = new HashMap<Character, String>(){{
      put('2', "abc");
      put('3', "def");
      put('4', "ghi");
      put('5', "jkl");
      put('6', "mno");
      put('7', "pqrs");
      put('8', "tuv");
      put('9', "wxyz");
    }};
    backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
    return combinations;
  }

  /**
   * @param combinations 结果
   * @param phoneMap 数字与字符的Map
   * @param digits 数字组合入参
   * @param index 入参数字索引
   * @param combination 目标字符串
   */
  public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits,
      int index, StringBuffer combination) {
    // 获得的 字符数 等于目标字符串长度，则为目标结果
    if (index == digits.length()) {
      combinations.add(combination.toString());
    } else {
      // 获取数字对于的字符
      char digit = digits.charAt(index);
      String letters = phoneMap.get(digit);
      // 遍历字符
      for (int i = 0; i < letters.length(); i++) {
        // 去当前遍历到的字符，先选择当前字符在目标字符串中
        combination.append(letters.charAt(i));
        // 遍历下一个 数字
        backtrack(combinations, phoneMap, digits, index + 1, combination);
        // 选择 遍历的字符不在 目标字符串，for 循环继续去下一个字符
        combination.deleteCharAt(index);
      }
    }
  }

}
