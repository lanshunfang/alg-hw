package org.neu.alg.practice.medium;

public class LongestPalindrome {
  public static void main(String... args) {
    LongestPalindrome longestPalindrome = new LongestPalindrome();
//
    System.out.println(
        longestPalindrome.longestPalindrome(
            "abbacbda"
        )
    );

    System.out.println(
        longestPalindrome.longestPalindrome(
            "cdccdc"
        )
    );
    System.out.println(
        longestPalindrome.longestPalindrome(
            "ccc"
        )
    );
    System.out.println(
        longestPalindrome.longestPalindrome(
            "a"
        )
    );
    System.out.println(
        longestPalindrome.longestPalindrome(
            ""
        )
    );
    System.out.println(
        longestPalindrome.longestPalindrome(
            "cccddddddcc"
        )
    );
    System.out.println(
        longestPalindrome.longestPalindrome(
            "aaabaaaa"
        )
    );
  }

  /**
   * - Set variable:
   * - leftPos = 0 - the longest palindrome left bound
   * - rightPos = 0 - the longest palindrome right bound
   * - len = 0 - the length of the longest palindrome
   * -
   * - i = 0, j = i + 1 - cursor to traverse the array
   * <p>
   * - i++; j++ (while at j < s.length()); until (i == j || i - 1 == j), named as `test#1`;
   * - REPEAT i-- and j++, WHILE the evaluation of expression `test#1` is still truthy
   * - if j - i > len
   * - len = j - i, leftPos = i, rightPos = j;
   * - END_REPEAT, Set i = j; j = i + 1;
   * <p>
   * - RETURN s.substring(leftPos, rightPos);
   *
   * @param s
   * @return
   */
  public String longestPalindrome(String s) {

    int leftPos = 0;
    int rightPos = 0;
    int len = 0;

    int maxStringLen = s.length();

//    int sameCharSequenceLength = 0;

    for (int i = 0, j = i + 1, palindromePatternFoundPos = 0; j < maxStringLen; i++, j++) {

      palindromePatternFoundPos = i;


      boolean isOddPalindrome =  i > 0 && s.charAt(i - 1) == s.charAt(j);

      boolean isNeighborEqual = s.charAt(i) == s.charAt(j);


      if (isNeighborEqual || isOddPalindrome) {

        if (isNeighborEqual && isOddPalindrome) {
          boolean isJChanged = false;
          boolean isIChanged = false;
          while (j < maxStringLen && s.charAt(i) == s.charAt(j)) {
            isJChanged = true;
            j++;
          }
          if (isJChanged) {
            j--;
          }
          while (i >= 0 && s.charAt(i) == s.charAt(j)) {
            isIChanged = true;
            i--;
          }
          if (isIChanged) {
            i++;
          }
          isOddPalindrome = (j - i + 1) % 2 != 0;
          i = palindromePatternFoundPos;
          j = i + 1;
        }

        if (isOddPalindrome) {
          i--;
        }

        while (
            i >= 0
                && j < maxStringLen
                &&
                s.charAt(i) == s.charAt(j)

        ) {
//          isOddPalindrome = isOddPalindrome || i > 0 && s.charAt(i - 1) == s.charAt(j);
          i--;
          j++;

        }

        j--;
        i++;

        // reduce 2 so that last j and i in the while loop could be restored to the the last successful loop)
        if (j - i >= len) {
//          leftPos = isOddPalindrome ? i - 1 : i;
          leftPos = i;
          rightPos = j;
          len = rightPos - leftPos;

        }
        i = palindromePatternFoundPos;
        j = i + 1;
      }




    }

    if (s.length() == 0) {
      return "";
    }

    return s.substring(leftPos, rightPos + 1);
  }
}
