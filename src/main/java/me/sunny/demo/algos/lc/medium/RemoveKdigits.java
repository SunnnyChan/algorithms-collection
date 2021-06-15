package me.sunny.demo.algos.lc.medium;

import java.util.Objects;

/**
 *  https://leetcode.com/problems/remove-k-digits/
 *  402. Remove K Digits
 *
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 *
 * 注意:
 *
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * 示例 1 :
 *
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 示例 2 :
 *
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 :
 *
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 *
 * @author sunnychan@gmail.com
 */

public class RemoveKdigits {

    private String ret;

    public String solution1(String num, int k) {
        if (num.length() <= k) {
            return "0";
        }

        Digit firstDigit = new Digit(num.charAt(0), 0);
        for (int i = 1; i < num.length(); i++) {
            Digit digit = new Digit(num.charAt(i), i);
            if (digit.getNum() < firstDigit.getNum()) {
                digit.setNextDigit(firstDigit);
                firstDigit = digit;
            } else {
                Digit digitVar = firstDigit;
                while (digitVar.getNextDigit() != null && digit.getNum() >= digitVar.getNextDigit().getNum()) {
                    digitVar = digitVar.getNextDigit();
                }
                digit.setNextDigit(digitVar.getNextDigit());
                digitVar.setNextDigit(digit);
            }
        }

        StringBuilder ret = new StringBuilder();
        Digit digitVar = firstDigit;
        Digit nextDigit = null;
        int i = 1;
        int newNumLen = num.length() - k;
        while (i <= newNumLen) {
            nextDigit = firstDigit;
            while (!Objects.isNull(nextDigit)) {
                if ((num.length() - nextDigit.getIndex() - 1 >= newNumLen - i)
                    && (i == 1 || nextDigit.getIndex() > digitVar.getIndex())) {
                    ret.append(nextDigit.getNum());
                    i++;
                    digitVar = nextDigit;
                    break;
                }
                nextDigit = nextDigit.getNextDigit();
            }
        }
        while (ret.length() > 1 && ret.charAt(0) == '0') {
            ret.delete(0, 1);
        }
        return ret.toString();
    }

    private class Digit {
        char digit;
        int index;
        Digit nextDigit = null;

        public Digit(char num, int index) {
           this.digit = num;
           this.index = index;
        }

        public char getNum() {
            return digit;
        }

        public int getIndex() {
            return index;
        }

        public Digit getNextDigit() {
            return nextDigit;
        }

        public void setNextDigit(Digit nextDigit) {
            this.nextDigit = nextDigit;
        }
    }

    public String solution2(String num, int k) {
        if (num.length() <= k) {
            return "0";
        }
        this.recursive(num, k, "");
        this.ret = this.ret.replaceFirst("^0*", "");
        return this.ret.length() > 0 ? this.ret : "0";
    }

    private void recursive(String num, int k, String numSelected) {
        if (num.length() < k) {
            return;
        }
        if (k == 0) {
            String numDelK = numSelected + num;
            this.ret = Objects.isNull(this.ret) || this.str1IntLessStr2Int(numDelK, this.ret)?
                numDelK : this.ret;
            return;
        }
        recursive(num.substring(1), k, numSelected + num.substring(0, 1));
        recursive(num.substring(1), k - 1, numSelected);
    }

    private boolean str1IntLessStr2Int(String str1, String str2) {
        for (int i = 0; i < str1.length(); i++){
            if (str1.charAt(i) == str2.charAt(i)) {
                continue;
            }
            return str1.charAt(i) < str2.charAt(i);
        }
        return false;
    }

    public static void main(String[] args) {
        String num = "10";
        int k = 1;
        System.out.println(new RemoveKdigits().solution1(num, k));
        System.out.println(new RemoveKdigits().solution2(num, k));
    }
}
