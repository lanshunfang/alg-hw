package org.neu.alg.practice.easy;

class Solution {
  public int reverse(int x) {

    int accumulation = 0;
    boolean isNagetive = x < 0;

    boolean isLeadingZero = true;

    while (x != 0) {
      int currentDigit = x % 10;
      x = x / 10;
      if (currentDigit != 0) {
        isLeadingZero = false;
      }

      if (!isLeadingZero) {
        int newAccMultiply = accumulation * 10;
        int newAcc = newAccMultiply + currentDigit;

        if (
            newAccMultiply / 10 != accumulation || newAcc - currentDigit != newAccMultiply
        ) {
          return 0;
        }

        accumulation = newAcc;
      }
    }

    return accumulation;

  }
}

public class RevertInteger {

  public static void main(String... args) {
    Solution solution = new Solution();

    System.out.println(solution.reverse(1534236469));
  }
}
