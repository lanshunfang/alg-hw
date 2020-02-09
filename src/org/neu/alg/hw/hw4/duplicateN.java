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

    for (int i = 0; i < a.length; i++) {
      final int ele = a[i];
      boolean isFound = false;
      for (int j = 0; j < a.length; j++) {
        final int eleInner = a[j];

        if (ele == eleInner && i != j) {
          isFound = true;
        }

      }

      if (isFound) {
        count++;
      }

    }

    return count;

  }

  private int alg_ntime_n_space() {
    int[] descriptorArr = new int[a.length];

    int count = 0;

    for (int i = 0; i < a.length; i++) {
      descriptorArr[a[i]]++;
    }

    for (int i = 0; i < descriptorArr.length; i++) {
      int ele = descriptorArr[i];
      if (ele > 1) {
        count += ele;
      }
    }

    return count;
  }

  private int alg_ntime_constant_space() {

    System.out.println("-----------------");
    System.out.print("\tduplicate {");
    int arrayLen = a.length;

    int count = 0;

    // {1, 3, 3, 2}
    for (int i = 0; i < arrayLen; i++) {
      int eleAsIndex = a[i];
      if (eleAsIndex < 0) {
        eleAsIndex = getRawValue(eleAsIndex, arrayLen);
      }

      a[eleAsIndex] -= arrayLen;

      if (a[eleAsIndex] < -2 * arrayLen) {
        count++;
        a[eleAsIndex] += arrayLen;
      }

    }

    for (int i = 0; i < arrayLen; i++) {
      int ele = a[i];
      boolean isDuplicate = false;
      if (ele < -arrayLen) {
        int currentElementCountFound = (ele + 1) / -arrayLen;
        count += currentElementCountFound + 1;
        a[i] += currentElementCountFound * arrayLen;
        isDuplicate = true;
      }

      if (a[i] < 0) {
        a[i] += arrayLen;
      }

      if (isDuplicate) {
        System.out.print("" + i + ",");
      }
    }

    System.out.print("}");
    System.out.println("");
    System.out.println("-----------------");

    return count;


  }

  private int getRawValue(int currentValue, int arrLength) {
    while (currentValue < 0) {
      currentValue += arrLength;
    };
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