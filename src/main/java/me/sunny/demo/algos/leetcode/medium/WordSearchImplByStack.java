package me.sunny.demo.algos.leetcode.medium;

import java.util.Objects;
import java.util.Stack;

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

public class WordSearchImplByStack {

  private static int[][] directs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
  private int rowLen, colLen;

  public boolean exist(char[][] board, String word) {
    rowLen =  board.length;
    colLen =  board[0].length;
    // 判空
    if (Objects.isNull(word) || word.isEmpty()) {
      return true;
    }
    // 如果 字符串长度 大于 数组元素个数，则 搜索失败
    if (word.length() > rowLen * colLen) {
      return false;
    }
    // 遍历数据，把数组中的每一个节点作为一个起始点，做深度搜索
    for(int i = 0; i < rowLen; i++) {
      for (int j = 0; j < colLen; j++) {
        if (board[i][j] != word.charAt(0)) {
          continue;
        }
        if (deepSearchByStack(word, board, i, j)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean deepSearchByStack(String word, char[][] board, int i, int j) {
    // 深度遍历使用的栈
    Stack<Node> stack = new Stack<>();
    // 节点，主要保持数组下标
    Node node = new Node(i, j, 0);
    // 初始，放入第一个节点
    stack.add(node);

    int k = 0;
    // 位置上的值为true 标识该位置对应的 board 中的元素在搜索路径上
    boolean[][] visited = new boolean[rowLen][colLen];

    // stack 为空，或者 搜索路径长度已经等于字符串长度，搜索结束
    while (!stack.isEmpty()) {
      // 栈中保存了可以遍历的位置，包括有多个分支路径的情况
      node = stack.peek();
      // 如果栈顶元素已经访问过了，表示路径不能往下走了，因为如果能往下走，栈顶会有新的为访问的元素
      if (visited[node.getA()][node.getB()]) {
        // 回溯到上一个未访问的位置，（实际上是上一个存在分支路径的地方）
        stack.pop();
        // 恢复访问状态
        visited[node.getA()][node.getB()] = false;
        // 搜索的位置和栈保持一致
        k--;
        continue;
      }
      // 找到未访问节点，取出对应的搜索位置，如果已经到字符串的末尾，则结束搜索
      k = node.getK();
      if (k >= word.length() - 1) {
        break;
      }
      // 标识当前节点已经在访问路径上
      visited[node.getA()][node.getB()] = true;
      // 看看当前节点周围的节点是否和下一个搜索字符串相匹配，如果匹配入栈
      // 要注意过滤掉，已经访问过的节点
      int newA, newB;
      for (int[] direct : directs){
        newA = node.getA() + direct[0];
        newB = node.getB() + direct[1];
        if (isIn(newA, newB) && board[newA][newB] == word.charAt(k + 1)
            && !visited[newA][newB]) {
          Node nextNode = new Node(newA , newB, k + 1);
          stack.push(nextNode);
        }
      }
    }
    // 从搜索的索引位置和字符串长度判断搜索是否成功
    return k + 1 == word.length();
  }

  private boolean isIn(int x, int y){
    return x >= 0 && x < rowLen && y >= 0 && y < colLen;
  }

  private class Node {
    int a;
    int b;
    int k;

    public Node(int a, int b, int k) {
      this.a = a;
      this.b = b;
      this.k = k;
    }

    public int getA() {
      return a;
    }

    public int getB() {
      return b;
    }

    public int getK() {
      return k;
    }
  }

}
