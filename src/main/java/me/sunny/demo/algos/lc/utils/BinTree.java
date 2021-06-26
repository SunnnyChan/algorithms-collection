package me.sunny.demo.algos.lc.utils;

import java.util.LinkedList;

public class BinTree {

  public TreeNode createTree(String str) {
    String[] arrStr = str.split(",");
    TreeNode treeNode = new TreeNode(new Integer(arrStr[0]));
    LinkedList<TreeNode> list = new LinkedList<>();
    list.add(treeNode);
    createTreeCur(arrStr, list);
    return treeNode;
  }

  private void createTreeCur(String[] strings, LinkedList<TreeNode> list) {
    int idx = 1;
    while (idx <= strings.length - 1) {
      TreeNode treeNode = list.pop();
      if ("null".equals(strings[idx])) {
        treeNode.left = null;
      } else {
        treeNode.left = new TreeNode(new Integer(strings[idx]));
        list.add(treeNode.left);
      }
      if (idx + 1 <= strings.length - 1 ) {
        if ("null".equals(strings[idx + 1])) {
          treeNode.right = null;
        } else {
          treeNode.right = new TreeNode(new Integer(strings[idx + 1]));
          list.add(treeNode.right);
        }
      }
      idx = idx + 2;
    }
  }


  public static void main(String[] args) {
    String str = "79,99,77,null,null,null,69,null,60,53,null,73,11,null,null,null,62,27,62,null,null,98,50,null,null,90,48,82,null,null,null,55,64,null,null,73,56,6,47,null,93,null,null,75,44,30,82,null,null,null,null,null,null,57,36,89,42,null,null,76,10,null,null,null,null,null,32,4,18,null,null,1,7,null,null,42,64,null,null,39,76,null,null,6,null,66,8,96,91,38,38,null,null,null,null,74,42,null,null,null,10,40,5,null,null,null,null,28,8,24,47,null,null,null,17,36,50,19,63,33,89,null,null,null,null,null,null,null,null,94,72,null,null,79,25,null,null,51,null,70,84,43,null,64,35,null,null,null,null,40,78,null,null,35,42,98,96,null,null,82,26,null,null,null,null,48,91,null,null,35,93,86,42,null,null,null,null,0,61,null,null,67,null,53,48,null,null,82,30,null,97,null,null,null,1,null,null";
    // String str = "3,2,3,null,3,null,1";
    // String str = "79,99,77,null,null,null,69,null,60,53,null,73,11,null,null,null,62,27,62,null,null,98,50,null,null,90,48,82,null,null,null,55,64,null,null,73,56,6,47,null,93,null,null,75,44,30,82,null,null,null,null,null,null,57,36,89,42,null,null,76,10,null,null,null,null,null,32,4,18,null,null,1,7,null,null,42,64,null,null,39,76,null,null,6,null,66,8,96,91,38,38,null,null,null,null,74,42,null,null,null,10,40,5,null,null,null,null,28,8,24,47"
    // + ",null,null,null,17,36,50,19,63,33,89,null,null,null,null,null,null,null,null,94,72,null,null"
    // + ",79,25,null,null,51,null,70,84,43,null,64,35,null,null,null,null,40,78,null,null,35,42,98,96,null,null,82,26,null,null,null,null";
    TreeNode treeNode = new BinTree().createTree(str);
  }
}
