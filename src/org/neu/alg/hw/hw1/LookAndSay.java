package org.neu.alg.hw.hw1;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.HashMap;

/**
 * File Name: LookAndSay.java
 * LookAndSay concrete class
 * <p>
 * <p>
 * To Compile: IntUtil.java RandomInt.java LookAndSay.java LookAndSayBase.java
 *
 * @author Jagadeesh Vasudevamurthy, Paul Lan<lan.sh@husky.neu.edu>
 * @year 2019
 */

class LookAndSay extends LookAndSayBase {

  /**
   * Cache lookAndSay result so that alg(String s) could have better performance
   * <p>
   * It takes O(n) space. If we want to optimize the space, we could limit the HashMap size by omitting stale data
   */
  private final HashMap<String, String> cacheStringParamHashMap =
      new HashMap<String, String>();


  /**
   * For alg(int n)
   *
   * @link #cacheStringParamHashMap
   */
  private final HashMap<Integer, String> cacheNumberParamHashMap =
      new HashMap();


  /**
   * Set the first alg(1) result
   */
  private String firstLookAndSayNumeric = "1";

  LookAndSay() {
    //NOTHING CAN BE CHANGED HERE
    testBench();
  }

  @Override
  protected String lookAndSay(String s) {
    //NOTHING CAN BE CHANGED HERE
    return alg(s);
  }

  @Override
  protected String lookAndSay(int n) {
    //NOTHING CAN BE CHANGED HERE
    return alg(n);

  }

  /**
   * Return a natural language speaking for the given string
   * <p>
   * Imagine you are telling someone a large number on phone
   * <p>
   * The algorithm only takes O(n) with 2 loops
   *
   * @param s - the input string
   * @return
   * @example assert(alg ( " 123 ")).toBe("111213")
   * @example assert(alg ( " 11231 ")).toBe("21121311")
   * @example assert(alg ( " 99999999 ")).toBe("109")
   * @example assert(alg ( " 9876543210 ")).toBe("19181716151413121110")
   */
  private String alg(String s) {
    //WRITE CODE
    //You can have any number of private functions and variables

    // find cached result first
    // we use caching to reduce CPU call
    final String cachedResult = cacheStringParamHashMap.get(s);
    if (cachedResult != null) {
      return cachedResult;
    }

    // the statics of the current number-like string
    // say,
    // statArrForStringInput[1] == 0
    // statArrForStringInput[2] == 2
    // statArrForStringInput[3] == 4
    // this means we have 2243
    final int[] statArrForStringInput = new int[10];

    StringBuilder readOutResult = new StringBuilder();

    // use iterator instead of s.charAt(i) as charAt(i) take O(n) while iterator only take O(1)
    CharacterIterator it = new StringCharacterIterator(s);

    int lastCol1 = -1;

    while (it.current() != CharacterIterator.DONE) {
      int col1 = charToInt(it.current());
      if (col1 != lastCol1 && lastCol1 != -1) {
        // reset counter (statArrForStringInput[col1]) to 0 if the current col1 is different from last one

        // gather statistic of the continual number
        // say in string  1123, when we col1 == 2, lastCol1 == 1, we know the 11 have been already counted
        readOutResult.append("" + statArrForStringInput[lastCol1] + lastCol1);

        // say in string  11231, when we col1 == 2, lastCol1 == 1,
        // we should reset statArrForStringInput[lastCol1] as it's already read into readOutResult
        // so that when we meet any new 1, it's counting number should not be accumulated
        statArrForStringInput[lastCol1] = 0;

      }

      statArrForStringInput[col1]++;
      lastCol1 = col1;

      it.next();
    }

    // Do NOT forget to proceed the last number in the stack
    if (lastCol1 != -1) {
      readOutResult.append("" + statArrForStringInput[lastCol1] + lastCol1);
    }

    final String resultStr = readOutResult.toString();

    // cache result
    cacheStringParamHashMap.put(s, resultStr);

    return resultStr;
  }

  /**
   * * Look back the number and say it in natural language
   *
   * @param n
   * @return
   * @example default count:
   * assert(alg(1)).toBe("1")
   * @example Look back last output 1 and say how many 1 in it
   * assert(alg(2)).toBe("11")
   * @example Look back last output "11" and say how many 1 in it
   * assert(alg(3)).toBe("21")
   * @example Look back last output "21" and say how many 2 and how many 1 in it
   * assert(alg(4)).toBe("1211")
   * @example assert(alg ( 5)).toBe("111221")
   * @example assert(alg ( 6)).toBe("312211")
   * @example assert(alg ( 7)).toBe("13112221")
   */
  private String alg(int n) {
    //WRITE CODE
    //You can have any number of private functions and variables

    // do nothing if n is less equal than 0
    if (n > 0) {
      for (int i = 1; i <= n; ++i) {
        constructLookAndSayNumeric(i);

      }
    }

    return cacheNumberParamHashMap.get(n);
  }

  private void constructLookAndSayNumeric(int i) {
    if (i == 1) {
      // set default cached value as "1"
      cacheNumberParamHashMap.put(1, firstLookAndSayNumeric);
      return;
    }

    String cachedResult = cacheNumberParamHashMap.get(i);
    if (cachedResult != null) {
      return;
    }

    cacheNumberParamHashMap
        .put(
            i,
            // loopback last readout string and call alg(String s) to do the trick
            alg(
                cacheNumberParamHashMap.get(i - 1)
            )
        );


  }

  private int charToInt(char s) {
    return Integer.parseInt(String.valueOf(s));
  }

  // shorthand for System.out.println();
  private void consoleLog(String s) {
    System.out.println(s);
  }

  public static void main(String[] args) {
    //NOTHING CAN BE CHANGED HERE
    System.out.println("LookAndSay problem STARTS");
    LookAndSay m = new LookAndSay();
    System.out.println("All LookAndSay tests passed. You are genius");
    System.out.println("LookAndSay problem ENDS");
  }
}