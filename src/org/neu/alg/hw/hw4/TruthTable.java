package org.neu.alg.hw.hw4;

import org.neu.alg.hw.*;

import java.util.Arrays;


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
    for (int i = 1; i < 2; ++i) {
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
//    TBDException tbdException = new TBDException("printTruthTable");

//    int n = this.n;
    int n = 5;
    int[][] cachedResult = new int[10][];

    int significantMultiplier = 1;

    cachedResult[0] = new int[2];
    cachedResult[0][0] = 0;
    cachedResult[0][1] = 1;

    int newLength = 1;


    for (int i = 1; i < n; i++) {
      significantMultiplier = significantMultiplier * 10;

      newLength = newLength * 2;

      int arrIndex = newLength - 1;

      cachedResult[i] = new int[newLength];

      for (int j1 = i - 1; j1 >= 0; j1--) {

        int[] currentCachedItem = cachedResult[j1];

        for (int k = currentCachedItem.length - 1; k >= 0; k--) {
          if (arrIndex >= 0) {
            cachedResult[i][arrIndex] = currentCachedItem[k] + significantMultiplier;
            arrIndex--;
          }

        }

//      cachedResult[i][0] = significantMultiplier;


      }


      System.out.println(Arrays.toString(cachedResult[i]));


    }

    System.out.println(Arrays.toString(cachedResult[cachedResult.length - 1]));


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