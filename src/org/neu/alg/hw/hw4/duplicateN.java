package org.neu.alg.hw.hw4;

import org.neu.alg.hw.*;

import java.util.Arrays;
import java.util.List;

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

    List<int[]> targetListToCheckDuplication = Arrays.asList(this.a);

    return (int) targetListToCheckDuplication
        .stream()
        .filter(
            (ele) -> {
              return targetListToCheckDuplication
                  .stream()
                  .anyMatch(
                      (eleInner) -> {
                        return eleInner == ele;
                      }
                  );
            }
        )
        .count();

  }

  private int alg_ntime_n_space() {
    TBDException tbdException = new TBDException("alg_ntime_n_space");
    return 1;
  }

  private int alg_ntime_constant_space() {
    TBDException tbdException = new TBDException("alg_ntime_constant_space");
    return 1;
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