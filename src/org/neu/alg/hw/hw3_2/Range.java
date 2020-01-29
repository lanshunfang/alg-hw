package org.neu.alg.hw.hw3_2;

import org.neu.alg.hw.*;


/**
 * File Name: Range.java
 * Range concrete class
 * <p>
 * <p>
 * To Compile: IntUtil.java RandomInt.java Range.java RangeBase.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

class Range extends RangeBase {
  Range() {
    //NOTHING CAN BE CHANGED HERE
    testBed();
  }

  @Override
  int[] Range(int[] a, int n) {
    //NOTHING CAN BE CHANGED HERE
    numSteps = 0; //MUST update this data. Without this assert fails
    return alg(a, n);
  }

  /**
   * @param a
   * @param n
   * @return
   * @example { 1, 1, 2, 2, 2, 2, 2, 2, 4, 9, 10}
   * @example { 2, 2, 2, 2, 2, 2}
   */
  private int[] alg(int[] a, int n) {

    return new int[]{
        findBound(a, 0, a.length - 1, n, false),
        findBound(a, 0, a.length - 1, n, true),
    };

    //WRITE CODE
//    int rightPos = a.length - 1;
//    int lastRightPos = rightPos;
//    int leftPos = rightPos;
//    boolean leftMinFound = false;
//    int lastLeftPos = leftPos;
//
//    while (!leftMinFound) {
//
//      if (a[leftPos] > n ) {
//        lastLeftPos = leftPos;
//        leftPos = leftPos / 2;
//      } else if (a[leftPos] == n) {
//        if (leftPos == 0 || a[leftPos - 1] < n) {
//          // found min leftPos
//          leftMinFound = true;
////          break;
//        }
//
//        lastLeftPos = leftPos;
//
//        leftPos = leftPos / 2;
//      } else {
//        // a[leftPos] < n
//        leftPos = leftPos + (lastLeftPos - leftPos) / 2;
//
//      }
//
//    }

//    return new int[]{leftPos, rightPos};
  }

  private int findBound(int[] a, int leftBound, int rightBound, int n, boolean isFindMax) {

    numSteps++;

    if (a[0] > n || a[a.length - 1] < n) {
      // the number is not in the range of the array
      return -1;
    }

    if (isFindMax) {
      if (a[leftBound] == n && (leftBound == 0 || a[leftBound + 1] > n)) {
        return leftBound;
      }
      if (a[rightBound] == n && (rightBound == 0 || a[rightBound + 1] > n)) {
        return rightBound;
      }
    } else {
      if (a[leftBound] == n && (leftBound == 0 || a[leftBound - 1] < n)) {
        return leftBound;
      }
      if (a[rightBound] == n && (rightBound == 0 || a[rightBound - 1] < n)) {
        return rightBound;
      }
    }

    if (isFindMax) {
      if (a[rightBound] <= n) {
        leftBound = rightBound;
        rightBound = rightBound + (a.length - rightBound) / 2;
        return findBound(a, leftBound, rightBound, n, isFindMax);
      } else {

        int newMidPos = leftBound + (rightBound - leftBound) / 2;

        if (a[newMidPos] <= n) {
          return findBound(a, newMidPos, rightBound, n, isFindMax);
        } else {
          return findBound(a, leftBound, newMidPos, n, isFindMax);
        }

      }
    } else {
      if (a[leftBound] > n) {
        rightBound = leftBound;
        leftBound = leftBound / 2;
        return findBound(a, leftBound, rightBound, n, isFindMax);
      } else if (a[leftBound] == n) {
        rightBound = leftBound;
        leftBound = rightBound / 2;
        return findBound(a, leftBound, rightBound, n, isFindMax);

      } else {

        int newMidPos = leftBound + (rightBound - leftBound) / 2;

        if (a[newMidPos] >= n) {
          if (newMidPos == leftBound) {
            return -1;
          }
          return findBound(a, leftBound, newMidPos, n, isFindMax);
        } else {
          if (newMidPos == rightBound) {
            return -1;
          }
          return findBound(a, newMidPos, rightBound, n, isFindMax);
        }

      }
    }

  }

  private int findMax(int[] a, int leftBound, int rightBound, int n) {
    if (a[rightBound] == n && (rightBound == 0 || a[rightBound + 1] > n)) {
      return rightBound;
    }
    numSteps++;
    if (a[rightBound] < n) {
      leftBound = rightBound;
      rightBound = rightBound + (a.length - rightBound) / 2;
      return findMax(a, leftBound, rightBound, n);
    } else {

      int newMidPos = leftBound + (rightBound - leftBound) / 2;

      if (a[newMidPos] <= n) {
        return findMax(a, newMidPos, rightBound, n);
      } else {
        return findMax(a, leftBound, newMidPos, n);
      }

    }
  }


  public static void main(String[] args) {
    //NOTHING CAN BE CHANGED HERE
    System.out.println("Range problem STARTS");
    Range m = new Range();
    System.out.println("All Range tests passed. Get me a bar of candy");
    System.out.println("Range problem ENDS");
  }
}