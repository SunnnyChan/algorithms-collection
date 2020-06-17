package me.sunny.demo.algos.leetcode;

import java.util.Stack;

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
