package me.sunny.demo.algos.leetcode.medium;

import java.util.Objects;

/**
 * 079. 单词搜索
 *
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，
 * 其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 *
 * 示例:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *  
 * 提示：
 *
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 *
 * @author sunnnychan@gmail.com
 *
 */

public class WordSearchImplByRecursive {

  private static int[][] directs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
  private int rowLen, colLen;
  private boolean found = false;
  private boolean[][] visited;

  public boolean exist(char[][] board, String word) {
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
