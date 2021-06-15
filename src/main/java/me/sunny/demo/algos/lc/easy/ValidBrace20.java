package me.sunny.demo.algos.lc.easy;

import java.util.Map;

/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 示例 1：
 *
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例 3：
 *
 * 输入：s = "(]"
 * 输出：false
 * 示例 4：
 *
 * 输入：s = "([)]"
 * 输出：false
 * 示例 5：
 *
 * 输入：s = "{[]}"
 * 输出：true
 *
 * 提示：
 *
 * 1 <= s.length <= 10的四次方
 * s 仅由括号 '()[]{}' 组成
 *
 */
public class ValidBrace20 {

  public Boolean isValid(String s) {
    // 栈结构
    char[] stack = new char[s.length() + 1];
    // 栈底 index
    final int sEnd = 0;
    // 栈顶 index
    int sTop = 0;

    char[] sArray = s.toCharArray();
    // 遍历字符串
    for (char c : sArray) {
      // 左括号直接入栈
      if (c == '(' || c == '[' || c == '{') {
        // 注意 底部的单元 不存储元素
        sTop++;
        stack[sTop] = c;
      } else {
        // 右括号 直接和 栈顶元素匹配，如果匹配不上 可以直接判断字符串无效，否则继续遍历下一个字符
        if (c == ')' && stack[sTop] == '(') {
          sTop--;
          continue;
        }
        if (c == ']' && stack[sTop] == '[') {
          sTop--;
          continue;
        }
        if (c == '}' && stack[sTop] == '{') {
          sTop--;
          continue;
        }
        return false;
      }
    }
    // 栈空，标识字符串有效，否则无效
    return sTop == sEnd;
  }
}
