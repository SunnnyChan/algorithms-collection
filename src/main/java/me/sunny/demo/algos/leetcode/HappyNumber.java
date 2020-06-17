package me.sunny.demo.algos.leetcode;

import java.util.HashSet;

public class HappyNumber {

    public boolean isHappy(int n) {
        HashSet<Integer> numSet = new HashSet<>();
        int digitsSquaresSum = n;
        while(true) {
            int digitsSquaresSumTemp = 0;
            while (digitsSquaresSum > 0) {
                int digit = digitsSquaresSum % 10;
                digitsSquaresSumTemp = digitsSquaresSumTemp + digit * digit;
                digitsSquaresSum = digitsSquaresSum / 10;
            }
            if (digitsSquaresSumTemp == 1) {
                return true;
            }
            if (numSet.contains(digitsSquaresSumTemp)) {
                return false;
            }
            numSet.add(digitsSquaresSumTemp);
            digitsSquaresSum = digitsSquaresSumTemp;
        }
    }
}
