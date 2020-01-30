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

  private int rangeLeft;
  private int rangeRight;
  private boolean isRangeLeftFound;
  private boolean isRangeRightFound;
  private boolean isOnlyFindRangeLeft;
  private boolean isOnlyFindRangeRight;

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

    init();
    findBound(a, 0, a.length - 1, n);

    return new int[]{
        this.rangeLeft,
        this.rangeRight,
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

  private void storeFound(boolean isLeft, int value) {
    if (isLeft) {
      this.rangeLeft = value;
      this.isRangeLeftFound = true;
    } else {
      this.rangeRight = value;
      this.isRangeRightFound = true;

    }
  }

  private void setRangeLeft(int foundPos) {
    if (this.isRangeLeftFound) {
      return;
    }
    this.storeFound(true, foundPos);
  }

  private void setRangeRight(int foundPos) {
    if (this.isRangeRightFound) {
      return;
    }
    this.storeFound(false, foundPos);
  }

  private void setGivenValueIsNotInRange() {
    this.storeFound(true, -1);
    this.storeFound(false, -1);
  }

  /**
   * Check if the given value is not in range
   *
   * @param a
   * @param n
   * @return
   * @example n == 4
   * {1, 2, 2, 3}
   */
  private boolean isGivenValueOutOfRange(int[] a, int n) {
    return a[0] > n || a[a.length - 1] < n;
  }

  /**
   * If range is found
   *
   * @return
   */
  private boolean isRangeDetermined() {
    return this.isRangeLeftFound && this.isRangeRightFound;
  }

  /**
   * leftBound == rightBound
   *
   * @return
   */
  private boolean isBoundaryCollapsed(int leftBound, int rightBound) {
    return leftBound == rightBound;
  }

  private boolean isNewLeftBoundaryDetected(int[] a, int bound, int n) {
    return !this.isRangeLeftFound && a[bound] == n && (bound == 0 || bound > 0 && a[bound - 1] < n);
  }

  private boolean isNewRightBoundaryDetected(int[] a, int bound, int n) {
    return !this.isRangeRightFound && a[bound] == n && (bound == a.length - 1 || bound < a.length - 1 && a[bound + 1] > n);
  }

  private boolean isBoundaryDetectedAndSet(int[] a, int leftBound, int rightBound, int n) {

    boolean isDetected = false;
    if (this.isNewLeftBoundaryDetected(a, leftBound, n)) {
      this.setRangeLeft(leftBound);
      isDetected = true;
    }

    if (this.isNewLeftBoundaryDetected(a, rightBound, n)) {
      this.setRangeLeft(rightBound);
      isDetected = true;

    }

    if (this.isNewRightBoundaryDetected(a, leftBound, n)) {
      this.setRangeRight(leftBound);
      isDetected = true;
    }

    if (this.isNewRightBoundaryDetected(a, rightBound, n)) {
      this.setRangeRight(rightBound);
      isDetected = true;
    }

    return isDetected;

  }

  private void init() {
    this.rangeLeft = this.rangeRight = -1;
    this.isRangeRightFound = this.isRangeLeftFound = false;
  }

  private void findBound(int[] a, int leftBound, int rightBound, int n) {

    if (this.isRangeDetermined()) {
      // found
      return;
    }

    numSteps++;

    if (this.isGivenValueOutOfRange(a, n)) {
      // the number is not in the range of the array
      this.setGivenValueIsNotInRange();
      return;
    }


    if (this.isBoundaryDetectedAndSet(a, leftBound, rightBound, n)) {
      return;
    }

    if (this.isRangeDetermined()) {
      // found
      return;
    }

    if (this.isBoundaryCollapsed(leftBound, rightBound)) {
      return;
    }

    int newMidPos = leftBound + (rightBound - leftBound) / 2;

    if (a[newMidPos] == n) {
      if (leftBound != newMidPos && !this.isRangeLeftFound && !this.isOnlyFindRangeRight) {
        this.isOnlyFindRangeLeft = true;
        findBound(a, leftBound, newMidPos, n);
      }

      if (rightBound != newMidPos && !this.isRangeRightFound && !this.isOnlyFindRangeLeft) {
        this.isOnlyFindRangeRight = true;
        findBound(a, newMidPos, rightBound, n);
      }

    } else if (a[newMidPos] < n) {
      if (rightBound != newMidPos) {
        findBound(a, newMidPos, rightBound, n);
      }
    } else {
      if (leftBound != newMidPos) {
        findBound(a, leftBound, newMidPos, n);
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