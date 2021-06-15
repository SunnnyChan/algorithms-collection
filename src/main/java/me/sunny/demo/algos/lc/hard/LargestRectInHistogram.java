package me.sunny.demo.algos.lc.hard;

import java.util.Stack;

/**
 *
 * 84. 柱状图中最大的矩形
 *
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 *
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * 示例:
 *
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 *
 * @author sunnychan@gmail.com
 */

public class LargestRectInHistogram {

    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int top = 0, area = 0, maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            if (stack.empty() || heights[i] >= heights[stack.peek()]) {
                stack.push(i);
            } else {
                while (!stack.empty() && heights[i] < heights[stack.peek()]) {
                    top = stack.pop();
                    area = (i - top) * heights[top];
                    if (area > maxArea) {
                        maxArea = area;
                    }
                }
                stack.push(top);
                heights[top] = heights[i];
            }
        }
        while (stack.size() >= 1){
            top = stack.pop();
            maxArea = Math.max(maxArea, (heights.length - top) * heights[top]);
        }
        return maxArea;
    }

    public int largestRectangleArea2(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int top = 0, area = 0, maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            if (stack.empty() || heights[i] >= heights[stack.peek()]) {
                stack.push(i);
            } else {
                while (!stack.empty() && heights[i] < heights[stack.peek()]) {
                    top = stack.pop();
                    area = (i - top) * heights[top];
                    if (area > maxArea) {
                        maxArea = area;
                    }
                }
                stack.push(top);
                heights[top] = heights[i];
            }
        }
        return maxArea;
    }


}
