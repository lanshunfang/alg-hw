package org.neu.alg.hw.hw4;

import org.neu.alg.hw.*;

/**
 * File Name: TruthTable.java
 * Print 'n' input truth table
 * <p>
 * To compile: RandomInt.java IntUtil.java TruthTable.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2018
 */

class TruthTable {
  private int n;
  private boolean display;
  static IntUtil u = new IntUtil();

  TruthTable(int n, boolean b) {
    this.n = n;
    this.display = b;
    printTruthTable();
  }


  /*
   * ----------  YOU CANNOT CHANGE ANYTHING BELOW--------------------------
   */
  private static void testBench() {
    for (int i = 1; i < 31; ++i) {
      boolean display = (i > 10) ? false : true;
      System.out.println("------------Truth table of " + i + " inputs function --------------");
      long startTime = System.nanoTime();
      //If display is true, you should print like this:
      //------------Truth table of 2 inputs function --------------
      //00
      //01
      //10
      //11
      //For 2 inputs, table size is 4


      //If display is false, you must go through the same procedure
      //buy you should print only the last line like this
      //------------Truth table of 29 inputs function --------------
      //For 29 inputs, table size is 536870912

      TruthTable t = new TruthTable(i, display);
      long endTime = System.nanoTime();
      double d = u.timeInSec(endTime, startTime);
      System.out.println("Cpu time for n = " + i + " is " + d + " seconds");
    }
  }

  private void printTruthTable() {
    printTruthTableTimeOptimized(n, 27);
    if (n > 27) {
      printTruthTableSpaceOptimized(n, 28);
    }
  }

  /**
   * @param n
   * @param lowN - the start point to print the table, used with combination to #printTruthTableTimeOptimized;
   */
  private void printTruthTableSpaceOptimized(int n, int lowN) {

    int len = (int) Math.pow(2, n);
    int lowLen = (int) Math.pow(2, lowN);

    for (int i = lowLen; i < len; i++) {
      display(getBinaryDescriptorFromDecimal(i), n);
    }

    displayTableLength();

  }

  public String getBinaryDescriptorFromDecimal(int decimalNum) {
    int[] binDescriptor = new int[30];
    int index = 0;
    while (decimalNum > 0) {
      binDescriptor[index++] = decimalNum % 2;
      decimalNum = decimalNum / 2;
    }

    StringBuilder reverseBinaryDescriptor = new StringBuilder();

    for (int i = index - 1; i >= 0; i--) {
      reverseBinaryDescriptor.append("" + binDescriptor[i]);
    }

    return reverseBinaryDescriptor.toString();
  }


  /**
   * The time optimized solution
   * - reduces converting a decimal to binary converting time
   * - as well as appeal to dynamic programming to reduce unnecessary computation
   *
   * @param maxN, the max n could be proceeded by default JAVA heap size
   */
  private void printTruthTableTimeOptimized(int n, int maxN) {

    if (n > maxN) {
      return;
    }

//    int n = 5;
    int[][] cachedResult = new int[n][];

    int significantMultiplier = 1;

    // init
    cachedResult[0] = new int[2];
    cachedResult[0][0] = 0;
    cachedResult[0][1] = 1;

    display(cachedResult[0], n);

    if (n == 1) {
      displayTableLength();
      return;
    }

    int newLength = 1;

    for (int i = 1; i < n; i++) {
      // the idea here, is to follow the ste:
      //  2^2 has the following number
      // {00, 01, 10, 11}
      // - If we observe the first half {00, 01}, we could see that it's equal to the outcome of 2^1 with a prefix 0;
      // - If we observe the second half {10, 11}, we could see that it's equal to its counter part in 2^1 pls 10,
      //   10 is the significantMultiplier here

      // The same idea applied to 2^3
      // {000, 001, 010, 011, 100, 101, 110, 111}
      // - which has its first half equal to 2^2;
      //   and its second half is the same value plus 100, the significantMultiplier
      // with this approach, we could easily remember and print out all numbers

      // Note that, the maximum `n` in `2^n` is `27` as that's the RAM size cap a JVM allocated to its heap by default.
      significantMultiplier = significantMultiplier * 10;

      newLength = newLength * 2;

      int arrIndex = newLength - 1;

      cachedResult[i] = new int[newLength];
      int[] accumulated = new int[newLength];

      for (int j = i - 1; j >= 0; j--) {

        int[] currentCachedItem = cachedResult[j];

        for (int k = currentCachedItem.length - 1; k >= 0; k--) {
          final int lookBackNum = currentCachedItem[k];
          accumulated[arrIndex] = lookBackNum;

          final int newNum = lookBackNum + significantMultiplier;

          cachedResult[i][arrIndex] = newNum;

          arrIndex--;

        }


      }

      display(cachedResult[i], n);
    }

    displayTableLength();

  }

  private void displayTableLength() {
    System.out.println("[INFO] The Truth Table Length is " + (int) Math.pow(2, n));

  }


  private void display(String stringifyNum, int expectedLength) {

    if (!this.display) {
      return;
    }

    StringBuilder stringBuilder = new StringBuilder();

    int lenNumStr = stringifyNum.length();

    for (int i = expectedLength; i > lenNumStr; i--) {
      stringBuilder.append('0');
    }

    stringBuilder.append(stringifyNum);
    System.out.println(stringBuilder);


  }

  private void display(int[] nums, int expectedLength) {

    if (!this.display) {
      return;
    }

    for (int num : nums) {

      String numToStr = "" + num;
      display(numToStr, expectedLength);

    }

  }

  public static void main(String[] args) {
    System.out.println("TruthTable.java Starts");
    testBench();
    System.out.println("YOU CANNOT USE bigInteger class");
    System.out.println("Must use only basic java to solve this problem");
    System.out.println("Attach TruthTable.java and output of this program as a pdf file for full grade");
    System.out.println("TruthTable.java ends");
  }
}