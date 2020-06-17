package me.sunny.demo.algos.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

import me.sunny.demo.algos.leetcode.utils.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

public class WordSearchTest {

  @Test
  public void test() {
    String[] words = {"oath","pea","eat","rain"};
    char[][] board = {{'o','a','a','n'}, {'e','t','a','e'}, {'i','h','k','r'}, {'i','f','l','v'}};

    List<String> searchRet = new WordSearchIi().findWords(board, words);
    List<String> expectRet = new ArrayList<String>();
    expectRet.add("oath");
    expectRet.add("eat");

    ArrayUtils.print(board);

    Assert.assertEquals(expectRet, searchRet);
  }

  @Test
  public void test1() {
    String[] words = {"oao","pea","eat","rain"};
    //测试重复情况
    char[][] board = {{'o','a','a','n'}, {'e','t','a','e'}, {'i','h','k','r'}, {'i','f','l','v'}};

    List<String> searchRet = new WordSearchIi().findWords(board, words);
    List<String> expectRet = new ArrayList<String>();
    expectRet.add("eat");
    Assert.assertEquals(expectRet, searchRet);
  }
}
