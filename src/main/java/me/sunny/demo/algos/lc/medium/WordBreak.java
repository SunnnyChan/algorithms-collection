package me.sunny.demo.algos.lc.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 139. 单词拆分
 *
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 *
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 *
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 *
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 * 链接：https://leetcode-cn.com/problems/word-break
 */
public class WordBreak {

  /**
   * 将 字典 构造为一颗 字典树
   * 通过 搜索字典树 来判断是否可以分解单词
   *
   */
  public boolean wordBreak(String s, List<String> wordDict) {
    TrieTreeNode[]  tree = createTree(wordDict);
    return search(tree, s);
  }

  /**
   * 构造字典树
   */
  private  TrieTreeNode[]  createTree(List<String> strings) {
    TrieTreeNode[] tree = new TrieTreeNode[26];
    for (String s : strings) {
      TrieTreeNode[] treeNodes = tree;
      for (int i = 0; i < s.length(); i++) {
        int idx = s.charAt(i) - 'a';
        if (treeNodes[idx] == null) {
          TrieTreeNode treeNode = new TrieTreeNode();
          treeNodes[idx] = treeNode;
        }
        if (i == s.length() - 1) {
          treeNodes[idx].isLeaf = true;
        } else {
          treeNodes = treeNodes[idx].next;
        }
      }
    }
    return tree;
  }

  /**
   * 搜索字典树
   *
   * 思路：
   * 遍历被搜索字符串，先按照深入遍历字典树，中间有前缀字符的，先记录下位置，放入一个队列，待后续继续遍历
   */
  private boolean search( TrieTreeNode[] tree, String s) {
    // 队列，由于 可能存在 前缀一致的 字典元素，需要记录下位置
    LinkedList<Integer> strIdxs = new LinkedList<>();
    // 初始，第一个待搜索位置 0 放入队列
    strIdxs.add(0);
    // HashSet 用来过滤 重复的搜索位置，可能会产生重复的搜索位置，而重复搜索是没有意义的。
    Set<Integer> strIdxSet = new HashSet<>();
    strIdxSet.add(0);
    // 搜索，直到队列为空
    while (!strIdxs.isEmpty()) {
      // 取一个 位置开始搜索
      int strIdx = strIdxs.pop();
      // 每次都是从最上层开始搜索（字典中 字符串开始的位置）
      TrieTreeNode[] treeNodes = tree;
      Integer treeIdx = null;
      // 校验搜索位置 和 字典树是否 已经搜索到 null 节点
      while (strIdx < s.length() && treeNodes != null) {
        // 计算 字符的 索引位置
        treeIdx = s.charAt(strIdx) - 'a';
        // 如果 字符不存在，则本次搜索结束
        if (treeNodes[treeIdx] == null) {
          break;
        }
        // 如果是叶节点，就标识 当前搜索到的节点 是一个 字典 元素的结束符，表示这是一个需要继续搜索的路径
        if (treeNodes[treeIdx].isLeaf) {
          // 如果路径不是已经加入过队列的，则加入队列
          if (!strIdxSet.contains(strIdx +  1)) {
            strIdxSet.add(strIdx + 1);
            strIdxs.add(strIdx + 1);
          }
          // 如果 搜索的位置 已经到 字符结尾，则搜索成功
          if (strIdx == s.length() - 1) {
            return true;
          }
        }
        // 继续本次搜索
        treeNodes = treeNodes[treeIdx].next;
        strIdx++;
      }
    }
    return false;
  }

  static class TrieTreeNode {
    TrieTreeNode[] next = null;
    boolean isLeaf = false;
    TrieTreeNode() {
      next = new TrieTreeNode[26];
    }
  }

  /**
   * 动态规划
   */
  public boolean wordBreak2(String s, List<String> wordDict) {
    Set<String> wordDictSet = new HashSet<>(wordDict);
    boolean[] dp = new boolean[s.length() + 1];
    dp[0] = true;
    for (int i = 1; i <= s.length(); i++) {
      for (int j = 0; j < i; j++) {
        // substring 不包括 endIndex 位置的字符
        if (dp[j] & wordDictSet.contains(s.substring(j, i))) {
          dp[i] = true;
          break;
        }
      }
    }
    return dp[s.length()];
  }

  public static void main(String[] args) {
    String s = "applepenapple";
    List<String> strings = new LinkedList<>();
    strings.add("apple");
    strings.add("pen");

    String s1 = "abcd";
    List<String> strings1 = new LinkedList<>();
    strings1.add("a");
    strings1.add("abc");
    strings1.add("b");
    strings1.add("cd");

    String s2 = "aaaaaaa";
    List<String> strings2 = new LinkedList<>();
    strings2.add("aaaa");
    strings2.add("aa");

    String s3 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
    List<String> strings3 = new LinkedList<>();
    strings3.add("a");
    strings3.add("aa");
    strings3.add("aaa");
    strings3.add("aaaa");
    strings3.add("aaaaa");
    strings3.add("aaaaaa");
    strings3.add("aaaaaaa");
    strings3.add("aaaaaaaa");
    strings3.add("aaaaaaaaa");
    strings3.add("aaaaaaaaaa");
    /*
    System.out.println(new WordBreak().wordBreak(s, strings));
    System.out.println(new WordBreak().wordBreak(s1, strings1));
    System.out.println(new WordBreak().wordBreak(s2, strings2));
     */
    System.out.println(new WordBreak().wordBreak(s3, strings3));
  }
}
