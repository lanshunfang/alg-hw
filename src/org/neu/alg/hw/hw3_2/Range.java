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

  /**
   * The min index
   */
  private int rangeLeft;

  /**
   * The max index
   */
  private int rangeRight;

  /**
   * flag indicating if min is found
   */
  private boolean isRangeLeftFound;

  /**
   * flag indicating if max is found
   */
  private boolean isRangeRightFound;

  /**
   * When we know the sub-range to search DOES NOT include max
   */
  private boolean isOnlyFindRangeLeft;
  /**
   * When we know the sub-range to search DOES NOT include min
   */
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

  /**
   * If the given bound is a range min
   *
   * @param a
   * @param bound
   * @param n
   * @return
   */
  private boolean isNewLeftBoundaryDetected(int[] a, int bound, int n) {
    return !this.isRangeLeftFound && a[bound] == n && (bound == 0 || bound > 0 && a[bound - 1] < n);
  }

  /**
   * If the given bound is a range max
   *
   * @param a
   * @param bound
   * @param n
   * @return
   */
  private boolean isNewRightBoundaryDetected(int[] a, int bound, int n) {
    return !this.isRangeRightFound && a[bound] == n && (bound == a.length - 1 || bound < a.length - 1 && a[bound + 1] > n);
  }

  /**
   * Detect boundary and update found result if any
   * <p>
   * The boundary detection is not just to detect if the given left bound or right bound meet the range min/max,
   * but also store it
   *
   * @param a
   * @param leftBound
   * @param rightBound
   * @param n
   * @return
   */
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

  /**
   * Indicate if we should continue to search range min
   * @return
   */
  private boolean isShouldSearchRangeLeft() {
    return !this.isRangeLeftFound && !this.isOnlyFindRangeRight;
  }

  /**
   * Indicate if we should continue to search range max
   * @return
   */
  private boolean isShouldSearchRangeRight() {
    return !this.isRangeRightFound && !this.isOnlyFindRangeLeft;
  }

  /**
   * Reset class private values
   */
  private void init() {
    this.rangeLeft = this.rangeRight = -1;
    this.isRangeRightFound = this.isRangeLeftFound = false;
  }

  /**
   * Binary search the boundary
   * <p>
   * For each iteration, we detect both leftBound and rightBound are in the range bound
   *
   * @param a
   * @param leftBound
   * @param rightBound
   * @param n
   */
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
      if (leftBound != newMidPos && this.isShouldSearchRangeLeft()) {
        this.isOnlyFindRangeLeft = true;
        findBound(a, leftBound, newMidPos, n);
      }

      if (rightBound != newMidPos && this.isShouldSearchRangeRight()) {
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