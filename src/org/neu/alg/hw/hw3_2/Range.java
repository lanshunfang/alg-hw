package org.neu.alg.hw.hw3_2;
import org.neu.alg.hw.*;


/**
 * File Name: Range.java
 * Range concrete class
 *
 *
 * To Compile: IntUtil.java RandomInt.java Range.java RangeBase.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

class Range extends RangeBase{
  Range() {
    //NOTHING CAN BE CHANGED HERE
    testBed() ;
  }

  @Override
  int[] Range(int [] a, int n)  {
    //NOTHING CAN BE CHANGED HERE
    numSteps = 0 ; //MUST update this data. Without this assert fails
    return alg(a,n) ;
  }

  private int[] alg(int [] a, int n) {
    //WRITE CODE
  }

  public static void main(String[] args) {
    //NOTHING CAN BE CHANGED HERE
    System.out.println("Range problem STARTS");
    Range m = new Range() ;
    System.out.println("All Range tests passed. Get me a bar of candy");
    System.out.println("Range problem ENDS");
  }
}