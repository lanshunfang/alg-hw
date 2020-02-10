package org.neu.alg.hw.hw4;

import org.neu.alg.hw.*;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * File Name: duplicateN.java
 * duplicateN concrete class
 * <p>
 * To Compile: IntUtil.java RandomInt.java duplicateN.java duplicateNBase.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

class duplicateN extends duplicateNBase {
  duplicateN() {
    //NOTHING CAN BE CHANGED HERE
    testBed();
  }


  private int alg_nsquare_time_constant_space() {
//    TBDException tbdException = new TBDException("alg_nsquare_time_constant_space");
    /**
     * @example
     * arr == {0, 1, 2, 2, 3, 3, 2, 1, 4}
     * assert(maxElementValue(arr)).isLessEqualThan(arr.length - 1)
     */

    int count = 0;

    int arrLen = a.length;

    for (int i = 0; i < arrLen; i++) {
      final int ele = a[i];
      boolean isAlreadyCounted = false;
      boolean isFound = false;

      for (int j = 0; j < arrLen; j++) {

        if (j == i) {
          continue;
        }

        final int eleInner = a[j];

        if (eleInner == ele) {
          isFound = true;
        }

        if (eleInner == ele + arrLen || ele == eleInner + arrLen) {
          isAlreadyCounted = true;
        }

      }

      if (!isAlreadyCounted && isFound) {
        a[i] += arrLen;
      }

    }

    for (int i = 0; i < arrLen; i++) {
      if (a[i] >= arrLen) {
        a[i] -= arrLen;
        count++;

      }
    }

    return count;

  }

  private int alg_ntime_n_space() {

    int count = 0;
    int arrLen = a.length;

    int[] descriptorArr = new int[arrLen];

    for (int i = 0; i < arrLen; i++) {
      if (descriptorArr[a[i]] < 2) {
        descriptorArr[a[i]]++;
      }
    }

    for (int i = 0; i < arrLen; i++) {
      int ele = descriptorArr[i];
//      if (ele > 1) {
      if (ele > 1) {
//        count += ele;
        count++;
      }
    }

    return count;
  }

  private int alg_ntime_constant_space() {


    printStart();

    int arrayLen = a.length;

    int count = 0;

    // {1, 3, 3, 2}
    for (int i = 0; i < arrayLen; i++) {
      int eleAsIndex = a[i];
      if (eleAsIndex < 0) {
        eleAsIndex = getRawValue(eleAsIndex, arrayLen);
      }

//      a[eleAsIndex] -= arrayLen;

//      if (a[eleAsIndex] < -2 * arrayLen) {
      if (a[eleAsIndex] > -2 * arrayLen) {
//        count++;
        a[eleAsIndex] -= arrayLen;
      }

    }

    for (int i = 0; i < arrayLen; i++) {
      int ele = a[i];
      boolean isDuplicate = false;
      if (ele < -arrayLen) {
//        int currentElementCountFound = (ele + 1) / -arrayLen;
//        count += currentElementCountFound + 1;
        count += 1;
        a[i] += 2 * arrayLen;
        isDuplicate = true;
      }

      if (a[i] < 0) {
        a[i] += arrayLen;
      }

      if (isDuplicate) {
        printDuplicateNum(i);
      }
    }

    printEnd();

    return count;

  }

  private void printStart() {
    if (show) {
      System.out.println("-----------------");
      System.out.print("\tduplicate {");
    }
  }

  private void printEnd() {
    if (show) {
      System.out.print("}");
      System.out.println("");
      System.out.println("-----------------");
    }
  }

  private void printDuplicateNum(int num) {
    if (show) {
      System.out.print("" + num + ",");
    }
  }

  private int getRawValue(int currentValue, int arrLength) {
    while (currentValue < 0) {
      currentValue += arrLength;
    }
    ;
    return currentValue % arrLength;
  }

  @Override
  protected int nsquare_time_constant_space() {
    //NOTHING CAN BE CHANGED HERE
    return alg_nsquare_time_constant_space();
  }

  @Override
  protected int ntime_n_space() {
    //NOTHING CAN BE CHANGED HERE
    return alg_ntime_n_space();
  }

  @Override
  protected int ntime_constant_space() {
    //NOTHING CAN BE CHANGED HERE
    return alg_ntime_constant_space();
  }

  //You can have any number of private data structures and procedure
  //YOU WRITE YOUR CODE BELOW. DO NOT CRAM entire code in one procedure


  public static void main(String[] args) {
    //NOTHING CAN BE CHANGED HERE
    System.out.println("duplicateN problem STARTS");
    duplicateN m = new duplicateN();
    System.out.println("You now understand why we should reduce O(n^2) algorithm to O(n) algorithm");
    System.out.println("duplicateN problem ENDS");
  }
}