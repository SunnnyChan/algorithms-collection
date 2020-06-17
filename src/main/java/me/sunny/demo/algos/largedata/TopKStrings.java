package me.sunny.demo.algos.largedata;

/**
 * 2、搜索引擎会通过日志文件把用户每次检索使用的所有检索串都记录下来，每个查询串的长度为1-255字节。
 *
 * 假设目前有一千万个记录（这些查询串的重复度比较高，虽然总数是1千万，但如果除去重复后，不超过3百万个。
 * 一个查询串的重复度越高，说明查询它的用户越多，也就是越热门。），请你统计最热门的10个查询串，要求使用的内存不能超过1G。
 *
 * 典型的 Top K 算法，还是在这篇文章里头有所阐述，详情请参见：十一、从头到尾彻底解析Hash表算法。
 *
 * 文中，给出的最终算法是：
 * 第一步、先对这批海量数据预处理，在O（N）的时间内用Hash表完成统计（之前写成了排序，特此订正。July、2011.04.27）；
 * 第二步、借助堆这个数据结构，找出Top K，时间复杂度为N‘logK。即，借助堆结构，我们可以在log量级的时间内查找和调整/移动。
 * 因此，维护一个K(该题目中是10)大小的小根堆，然后遍历300万的Query，分别和根元素进行对比所以，
 *
 * 我们最终的时间复杂度是：O（N）+ N’*O（logK），（N为1000万，N’为300万）。
 *
 * ok，更多，详情，请参考原文。或者：采用trie树，关键字域存该查询串出现的次数，没有出现为0。
 * 最后用10个元素的最小推来对出现频率进行排序。
 * @author sunnnychan@gmail.com
 *
 */

public class TopKStrings {
}