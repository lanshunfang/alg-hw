package org.neu.alg.practice.easy;

class StringToIntegerSolution {
  public int myAtoi(String str) {
    int result = 0;
    boolean firstNoWhiteSpaceRegister = false;
    boolean isNegative = false;
    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);

      if (!firstNoWhiteSpaceRegister && ch == ' ') {
        continue;
      }

      int theDigit = ch - 48;

      if (!firstNoWhiteSpaceRegister && (theDigit == -3 || theDigit == -5)) {
        if (theDigit == -3) {
          isNegative = true;
        }
        firstNoWhiteSpaceRegister = true;
      } else if (theDigit >= 0 && theDigit <= 9) {
        firstNoWhiteSpaceRegister = true;

        int accumulated = result * 10 + theDigit;

        if ((accumulated - theDigit) / 10 != result || accumulated < 0) {
          return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }

        result = accumulated;

      } else {
        break;
      }

    }

    return isNegative ? -result : result;

  }
}

public class StringToInteger {

  public static void main(String... args) {
    StringToIntegerSolution stringToIntegerSolution = new StringToIntegerSolution();

    System.out.println(stringToIntegerSolution.myAtoi("123"));
    System.out.println(stringToIntegerSolution.myAtoi(" 123"));

    System.out.println(stringToIntegerSolution.myAtoi("-123"));
    System.out.println(stringToIntegerSolution.myAtoi("4193 with words"));
    System.out.println(stringToIntegerSolution.myAtoi("words and 987"));
    System.out.println(stringToIntegerSolution.myAtoi("-91283472332"));
    System.out.println(stringToIntegerSolution.myAtoi("91283472332"));
    System.out.println(stringToIntegerSolution.myAtoi("2147483648"));
    System.out.println(stringToIntegerSolution.myAtoi("+1"));
    System.out.println(stringToIntegerSolution.myAtoi("+-2"));
    System.out.println(stringToIntegerSolution.myAtoi("-+2"));
  }
}
