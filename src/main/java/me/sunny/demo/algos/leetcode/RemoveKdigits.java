package me.sunny.demo.algos.leetcode;

import java.util.Objects;

/**
 *  https://leetcode.com/problems/remove-k-digits/
 *  402. Remove K Digits
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
