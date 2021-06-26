package me.sunny.demo.algos.lc.easy;

/**
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通队列的全部四种操作（push、top、pop 和 empty）。
 *
 * 实现 MyStack 类：
 *
 * void push(int x) 将元素 x 压入栈顶。
 * int pop() 移除并返回栈顶元素。
 * int top() 返回栈顶元素。
 * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 *  
 *
 * 注意：
 * 你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
 * 你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 *  
 * 示例：
 *
 * 输入：
 * ["MyStack", "push", "push", "top", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * 输出：
 * [null, null, null, 2, 2, false]
 *
 * 解释：
 * MyStack myStack = new MyStack();
 * myStack.push(1);
 * myStack.push(2);
 * myStack.top(); // 返回 2
 * myStack.pop(); // 返回 2
 * myStack.empty(); // 返回 False
 *  
 *
 * 提示：
 *
 * 1 <= x <= 9
 * 最多调用100 次 push、pop、top 和 empty
 * 每次调用 pop 和 top 都保证栈不为空
 *  
 *
 * 进阶：
 * 你能否实现每种操作的均摊时间复杂度为 O(1) 的栈？换句话说，执行 n 个操作的总时间复杂度 O(n) ，尽管其中某个操作可能需要比其他操作更长的时间。
 * 你可以使用两个以上的队列。
 *
 */
public class StackBasedQueue {
  private static final int STACK_MAX_LEN = 100;
  private Queue q1;
  private Queue q2;
  private Queue dataQ;
  private Queue emptyQ;

  /** Initialize your data structure here. */
  public StackBasedQueue() {
    q1 = new Queue(STACK_MAX_LEN);
    q2 = new Queue(STACK_MAX_LEN);
    dataQ = q1;
    emptyQ = q2;
  }

  /** Push element x onto stack. */
  public void push(int x) {
    dataQ.push(x);
  }

  /** Removes the element on top of the stack and returns that element. */
  public Integer pop() {
    if (dataQ.empty()) {
      return null;
    }
    Integer top = null;
    while (!dataQ.empty()) {
      top = dataQ.pop();
      if (!dataQ.empty()) {
        emptyQ.push(top);
      }
    }
    convertQ();
    return top;
  }

  /** Get the top element. */
  public Integer top() {
    if (dataQ.empty()) {
      return null;
    }
    Integer top = null;
    while (!dataQ.empty()) {
      top = dataQ.pop();
      emptyQ.push(top);
    }
    convertQ();
    return top;
  }

  /** Returns whether the stack is empty. */
  public boolean empty() {
    return dataQ.empty();
  }

  private void convertQ() {
    Queue qt = dataQ;
    dataQ = emptyQ;
    emptyQ = qt;
  }

  static class Queue {
    int [] queue;
    int tail;
    int head;

    public Queue(int len) {
      queue = new int[len];
      head = 0;
      tail = 0;
    }

    public void push(int x) {
      if (tail + 1 >= queue.length) {
        return;
      }
      queue[++tail] = x;
    }

    public Integer pop() {
      return empty() ? null : queue[++head];
    }

    public boolean empty() {
      return head >= tail;
    }
  }

  public static void main(String[] args) {
    StackBasedQueue myStack = new StackBasedQueue();
    myStack.push(1);
    myStack.push(2);
    myStack.top(); // 返回 2
    myStack.pop(); // 返回 2
    myStack.empty(); // 返回 False
  }

}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
