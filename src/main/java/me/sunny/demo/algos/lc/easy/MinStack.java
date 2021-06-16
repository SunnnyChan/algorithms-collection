package me.sunny.demo.algos.lc.easy;

/**
 * 155. 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *
 * 示例:
 *
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 *
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *
 * 提示：
 * pop、top 和 getMin 操作总是在 非空栈 上调用。
 *
 */
public class MinStack {
  private static final int STACK_MAX_ELEM_COUNT = 2000;
  private int idxMinEle;
  private int[] stack;
  private int idxTop;
  private static final int idxEnd = 0;

  /** initialize your data structure here. */
  public MinStack() {
    stack = new int[STACK_MAX_ELEM_COUNT];
    idxTop = 0;
  }

  public void push(int val) {
    // 栈满
    if (idxTop >= STACK_MAX_ELEM_COUNT - 1) {
      return;
    }
    stack[++idxTop] = val;
    if (idxTop - 1 == idxEnd) {
      // 第一个元素
      idxMinEle = idxTop;
    } else {
      // 新元素与最小元素比较
      idxMinEle = stack[idxMinEle] <= val ? idxMinEle : idxTop;
    }
  }

  public void pop() {
    // 栈空
    if (idxTop == idxEnd) {
      return;
    }
    /// 出栈
    idxTop--;
    if (idxTop + 1 == idxMinEle && idxTop != idxEnd) {
      // 查找最小值的索引
      idxMinEle = idxTop;
      for (int i = idxEnd + 1; i < idxTop; i++) {
        if (stack[i] < stack[idxMinEle]) {
          idxMinEle = i;
        }
      }
    }
  }

  public int top() {
    /*
    题的前提是栈不空
    if (idxTop == idxEnd) {
      return null;
    }
    */
    return stack[idxTop];
  }

  public Integer getMin() {
    if (idxTop == idxEnd) {
      return null;
    }
    return stack[idxMinEle];
  }

  public static void main(String[] args) {
    // Test 1
    MinStack minStack = new MinStack();
    minStack.push(-2);
    minStack.push(0);
    minStack.push(-3);
    System.out.println(minStack.getMin());
    minStack.pop();
    minStack.top();
    System.out.println(minStack.getMin());
    // Test 2
    MinStack minStack1 = new MinStack();
    minStack1.push(2);
    minStack1.push(0);
    minStack1.push(3);
    minStack1.push(0);
    minStack1.pop();
    minStack1.pop();
    minStack1.pop();
    System.out.println(minStack1.getMin());
  }

}
