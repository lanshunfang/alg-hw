package org.neu.alg.practice.medium;

import java.util.HashMap;

public class LeetCodeSolution_LengthOfLongestSubstring {

  public static void main(String... args) {
    Solution solution = new Solution();

    System.out.println(
        solution.lengthOfLongestSubstring("abba")
    );
    System.out.println(
        solution.lengthOfLongestSubstring("abcabcbb")
    );

  }
}


class Solution {
  public int lengthOfLongestSubstring(String s) {

    return this.alg__TO_n__SO_n(s);
//    return this.alg__O_n__O_n(s);

  }

  public int alg__TO_n__SO_n(String s) {

    /**
     * Recording all visited chars
     * @param Character - the char visited
     * @param Integer - last index of the visited char
     */
    HashMap<Character, Integer> charRecorderMap = new HashMap<Character, Integer>();

    int maxNonDuplicateSubstringLength = 0;
    int newSubstringStartPos = 0;
    int lenString = s.length();

    for (int currentPos = 0; currentPos < lenString; currentPos++) {

      // luckily, String.charAt(N) takes O(1) in Time Complexity
      char theChar = s.charAt(currentPos);

      if (charRecorderMap.containsKey(theChar)) {
        // newSubstringStartPos is indeed the last occurrence of the same char + 1
        // say, for string "abbac",
        // when
        // - `i` == 0, theChar == "a", charRecorderMap.containsKey(theChar) == false, `newSubstringStartPos` == 0; 
        // - `i` == 1, theChar == "b", charRecorderMap.containsKey(theChar) == false, `newSubstringStartPos` == 0; 
        // - `i` == 2, theChar == "b", charRecorderMap.containsKey(theChar) == true,  `newSubstringStartPos` == 2;
        //    charRecorderMap.get(theChar) == 1;
        // that means, if we found any char (say, "b") is duplicated, 
        // we should reset the substring start position to the position right after the duplicated char (+1)

        // - `i` == 3, theChar == "a", charRecorderMap.containsKey(theChar) == true,  `newSubstringStartPos` == 2;
        //    charRecorderMap.get(theChar) == 0;
        //    charRecorderMap.get(theChar) + 1 < newSubstringStartPos // charRecorderMap.get(theChar)  is actually the first "a", which is at pos 0;

        // That is to say, we should always set `newSubstringStartPos` to the rightmost position of any duplicated chars
        newSubstringStartPos = Math.max(newSubstringStartPos, charRecorderMap.get(theChar) + 1);
      }

      // Why `currentPos - newSubstringStartPos + 1`? 
      // Well, `currentPos - newSubstringStartPos` is the diff of start pos and end pos of the current substring
      // `+ 1` is because we should add 1 to the diff. Say for substring "ab", "a" is at pos 0, "b" is at pos 1, 
      // the diff is `1`, but the count of the total string length is 2.
      maxNonDuplicateSubstringLength = Math.max(maxNonDuplicateSubstringLength, currentPos - newSubstringStartPos + 1);

      charRecorderMap.put(theChar, currentPos);
    }

    return maxNonDuplicateSubstringLength;
  }
}