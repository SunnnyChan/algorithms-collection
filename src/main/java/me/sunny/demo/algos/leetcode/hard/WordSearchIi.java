package me.sunny.demo.algos.leetcode.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 212. 单词搜索 II
 *
 * 给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，
 * 其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母在一个单词中不允许被重复使用。
 *
 * 示例:
 *
 * 输入:
 * words = ["oath","pea","eat","rain"]
 *
 * and board =
 * [
 * ['o','a','a','n'],
 * ['e','t','a','e'],
 * ['i','h','k','r'],
 * ['i','f','l','v']
 * ]
 *
 * 输出: ["eat","oath"]
 * 说明: 你可以假设所有输入都由小写字母 a-z 组成。
 *
 * 提示:
 *
 * 你需要优化回溯算法以通过更大数据量的测试。
 * 你能否早点停止回溯？
 * 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。
 * 什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？前缀树如何？
 *
 * 如果你想学习如何实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。
 *
 * @author sunnnychan@gmail.com
 */

public class WordSearchIi {

  private static int[][] directs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
  private int rowLen, colLen;
  private boolean found = false;
  private boolean[][] visited;

  /**
   *   采用 和 079. 单词搜索 相同的思路
   */
  public List<String> findWords(char[][] board, String[] words) {
    List<String> ret = new ArrayList<>();
    for (String s : words) {
      if (exist(board, s)) {
        ret.add(s);
      }
    }
    return ret;
  }

  public boolean exist(char[][] board, String word) {
    found = false;
    // 判空
    if (Objects.isNull(word) || word.isEmpty()) {
      return true;
    }
    rowLen = board.length;
    colLen = board[0].length;
    // 如果 字符串长度 大于 数组元素个数，则 搜索失败
    if (word.length() > rowLen * colLen) {
      return false;
    }

    visited = new boolean[rowLen][colLen];

    // 遍历数据，把数组中的每一个节点作为一个起始点，做深度搜索
    for (int i = 0; i < rowLen; i++) {
      for (int j = 0; j < colLen; j++) {
        if (board[i][j] == word.charAt(0)) {
          deepSearchByRecursive(word, board, i, j, 1);
          if (found) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private void deepSearchByRecursive(String word, char[][] board, int i, int j, int k) {
    if (found) {
      return;
    }
    if (k == word.length()) {
      found = true;
      return;
    }
    visited[i][j] = true;
    for (int[] direct : directs) {
      int newA = i + direct[0];
      int newB = j + direct[1];
      // 满足三个条件，搜索成功：1. 索引在数组边界内 2.字符相等 3. 不在已经访问的路径上
      if (isIn(newA, newB) && word.charAt(k) == board[newA][newB] && !visited[newA][newB]) {
        // 搜索下一个元素
        deepSearchByRecursive(word, board, newA, newB, k+1);
      }
    }
    // 回溯时，到这里表示，下一步的搜索不成功，把节点从访问路径上删除
    visited[i][j] = false;
  }

  private boolean isIn(int x, int y){
    return x >= 0 && x < rowLen && y >= 0 && y < colLen;
  }
}
