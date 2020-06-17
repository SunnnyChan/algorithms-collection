package me.sunny.demo.algos.leetcode.medium;

import me.sunny.demo.algos.leetcode.utils.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

public class WordSearchImplByStackTest {

  @Test
  public void test() {
    String word = "ABCCED";
    char[][] board = {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};

    Assert.assertTrue(new WordSearchImplByStack().exist(board, word));
  }

  @Test
  public void test1() {
    String word = "SEE";
    char[][] board = {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};

    Assert.assertTrue(new WordSearchImplByStack().exist(board, word));
  }

  @Test
  public void test2() {
    String word = "ABCB";
    char[][] board = {{'A','B','C','E'}, {'S','F','B','S'}, {'A','D','E','E'}};

    Assert.assertTrue(new WordSearchImplByStack().exist(board, word));
  }

  @Test
  public void test3() {
    String word = "POLAND";
    /*
     *   char[][] board
     *
     *  		0	1	2	3	4	5	6
	 *         ------------------------------
     *     0  |	F	Y	C	E	N	R	D
     *     1  |	K	L	N	F	I	N	U
     *     2  |	A	A	A	R	A	H	R
     *     3  |	N	D	K	L	P	N	E
     *     4  |	A	L	A	N	S	A	P
     *     5  |	O	O	G	O	T	P	N
     *     6  |	H	P	O	L	A	N	O
     */
    char[][] board = {{'F','Y','C','E','N','R','D'}, {'K','L','N','F','I','N','U'}, {'A','A','A','R','A','H','R'}, {'N','D','K','L','P','N','E'},
        {'A','L','A','N','S','A','P'}, {'O','O','G','O','T','P','N'}, {'H','P','O','L','A','N','O'}};
    ArrayUtils.print(board);
    Assert.assertTrue(new WordSearchImplByStack().exist(board, word));
  }

  @Test
  public void test4() {
    String word = "ABCESEEEFS";
    /*
     *  		0	1	2   3
	 *        ------------------------------
     *    0  |	A	B	C	E
     *    1  |	S	F	E	S
     *    2  |	A	D	E	E
     *
     */
    char[][] board = {{'A','B','C','E'}, {'S','F','E','S'}, {'A','D','E','E'}};
    ArrayUtils.print(board);
    Assert.assertTrue(new WordSearchImplByStack().exist(board, word));
  }

}
