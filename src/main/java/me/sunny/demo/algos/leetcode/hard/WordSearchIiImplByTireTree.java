package me.sunny.demo.algos.leetcode.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

public class WordSearchIiImplByTireTree {

  private static int[][] directs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
  private int rowLen, colLen;
  private boolean[][] visited;
  /*
   * 因为是按照 数组的元素做深度遍历，从不同的路径有可能会搜索到同一个字符串，所以要去重，
   * 实际上也可以在字典树上加标识进行控制。
   */
  private Set<String> ret = new HashSet<>();

  /**
   * 遍历 String 的方式，会存在重复检索的情况，可以做优化。
   * 当字符串数组中的字符串前缀相似的比例越大，越能体现这种算法的价值
   *
   * 采用 字典树 + 回溯
   *
   * 总体思路：
   * 把待搜索的字符串构造为一颗字典树
   * 把二维数组中的每一个元素作为起始入口，递归遍历 上下左右 元素，是否在 字典树中，
   * 是，则以匹配的 数组元素 和 字典树节点为起点，再重新检索
   * 否则，回溯
   * 同时判断，字典树是否搜索到了 字符串结束节点，如果是 字符串结束节点代表从字典树的根到当前节点存在一个可以搜索到的字符串。
   */
  public List<String> findWords(char[][] board, String[] words) {

    if (Objects.isNull(words) || words.length == 0 || board.length == 0) {
      return new ArrayList<>(ret);
    }
    // 构建字典树
    TreeNode tree = createTrieTree(words);

    rowLen = board.length;
    colLen = board[0].length;
    visited = new boolean[rowLen][colLen];
    // 从数组元素的每一个节点，以及树根节点开始同步搜索
    for (int i = 0; i < rowLen; i++) {
      for (int j = 0; j < colLen; j++) {
          deepSearchByRecursive(tree, board, i, j);
      }
    }
    return new ArrayList<>(ret);
  }

  private void deepSearchByRecursive(TreeNode curr, char[][] board, int i, int j) {
    // 如果索引范围超限，或者节点已经遍历过，则回溯
    if (!isIn(i, j) || visited[i][j]) {
      return;
    }
    // 如果当前节点没有子树，无法继续搜索，回溯
    if (Objects.isNull(curr.child)) {
      return;
    }
    // 在子树中匹配，当前的元素，如果失败，则回溯
    curr = curr.child[board[i][j] - 'a'];
    if (Objects.isNull(curr)) {
      return;
    }
    // 标识已在访问路径上
    visited[i][j] = true;
    // 如果是 有 字符串结束 标识的节点，则标识检索成功
    if (curr.isStringEnd) {
      ret.add(curr.val);
    }
    // 对当前元素的 上下左右 邻接 位置做检索
    for (int[] direct : directs) {
      deepSearchByRecursive(curr, board, i + direct[0], j + direct[1]);
    }
    // 回溯时，置未访问标识
    visited[i][j] = false;
  }

  private TreeNode createTrieTree(String[] strings) {
    TreeNode tree = new TreeNode();
    TreeNode curNode;
    for (String s : strings) {
      curNode = tree;
      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (Objects.isNull(curNode.child)) {
          curNode.child = new TreeNode[26];
        }
        if (Objects.isNull(curNode.child[c - 'a'])) {
          curNode.child[c - 'a'] = new TreeNode();
        }
        curNode = curNode.child[c - 'a'];
      }
      curNode.isStringEnd = true;
      curNode.val = s;
    }
    return tree;
  }

  class TreeNode {
    /**
     * 当 isStringEnd 为 true 时，保存到当前节点位置搜索路径上的字符串。
     * 非字符串结束节点时，该值为 null
     */
    String val;
    /**
     *  当前节点的子树，是一个 TreeNode 数组，由于都是小写字母，所以数组下标为 0 - 25，对于 a - z
     *  非树根节点的情况下，如果当前子树是空，可以认为 当前节点是 字符串结束节点，也即 存在从跟节点到当前节点的 字符串。
     */
    public TreeNode[] child;
    /**
     * 由于存在一个字符串 是 另一个字符串的前缀的情况，所以单独设置一个标志来标识是否为字符串结束节点
     * 当遍历到 字符串结束节点 时，标识从根到 字符串结束节点 路径上的字符串是可以搜索到的
     * 这个值保存在 val 遍历中，以便输出
     */
    boolean isStringEnd;
  }

  private boolean isIn(int x, int y){
    return x >= 0 && x < rowLen && y >= 0 && y < colLen;
  }
}
