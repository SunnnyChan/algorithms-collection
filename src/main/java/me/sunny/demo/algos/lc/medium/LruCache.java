package me.sunny.demo.algos.lc.medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 *
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；
 * 如果关键字不存在，则插入该组「关键字-值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *  
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 * 示例：
 *
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 *  
 *
 * 提示：
 *
 * 1 <= capacity <= 3000
 * 0 <= key <= 3000
 * 0 <= value <= 104
 * 最多调用 3 * 104 次 get 和 put
 *
 * 链接：https://leetcode-cn.com/problems/lru-cache
 */
// TODO
public class LruCache {

  private Map<Integer, Integer> cache;
  private int capacity;
  private LinkedList<Integer> keys;

  public LruCache(int capacity) {
    this.cache = new HashMap<>();
    this.keys = new LinkedList<>();
    this.capacity = capacity;

  }

  public int get(int key) {
    // 刷新 排序
    if (keys.remove(new Integer(key))) {
      keys.add(key);
    }
    // 注意：如果 key 不存在，返回值 是null，如果函数返回值是基本类型，则是无法赋值的，会报 NullPointerException 异常
    // 所以不智能直接 get()
    return this.cache.getOrDefault(key, -1);
  }

  public void put(int key, int value) {
    // key 存在，直接更新值
    if (this.cache.containsKey(key)) {
      this.cache.put(key, value);
      // 刷新 排序
      if (keys.remove(new Integer(key))) {
        keys.add(key);
      }
      return;
    }
    // 缓存空间已满，删除后加入
    if (this.cache.size() >= this.capacity) {
      int delKey = this.keys.pop();
      this.cache.remove(delKey);
    }
    this.keys.add(key);
    this.cache.put(key, value);
  }

  public static void main(String[] args) {
    // ["LRUCache","put","put","get","put","get","put","get","get","get"]
    //[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
    /*
    LruCache cache = new LruCache(2);
    cache.put(1, 1);
    cache.put(2, 2);
    System.out.println(cache.get(1));
    cache.put(3, 3);
    System.out.println(cache.get(2));
    cache.put(4, 4);
    System.out.println(cache.get(1));
    System.out.println(cache.get(3));
    System.out.println(cache.get(4));
     */

    //["LRUCache","get","put","get","put","put","get","get"]
    //[[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
    /*
    LruCache cache1 = new LruCache(2);
    System.out.println(cache1.get(2));
    System.out.println(cache1.get(2));
    cache1.put(2, 6);
    System.out.println(cache1.get(1));
    cache1.put(1, 5);
    cache1.put(1, 2);
    System.out.println(cache1.get(1));
    System.out.println(cache1.get(2));
     */

    // ["LRUCache","put","put","put","put","get","get","get","get","put","get","get","get","get","get"]
    // [[3],[1,1],[2,2],[3,3],[4,4],[4],[3],[2],[1],[5,5],[1],[2],[3],[4],[5]]
    LruCache cache2 = new LruCache(3);
    cache2.put(1, 1);
    cache2.put(2, 2);
    cache2.put(3, 3);
    cache2.put(4, 4);
    System.out.println(cache2.get(4));
    System.out.println(cache2.get(3));
    System.out.println(cache2.get(2));
    System.out.println(cache2.get(1));
    cache2.put(5, 5);
    System.out.println(cache2.get(1));
    System.out.println(cache2.get(2));
    System.out.println(cache2.get(3));
    System.out.println(cache2.get(4));
    System.out.println(cache2.get(5));
  }
}
