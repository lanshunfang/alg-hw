package org.neu.alg.hw.hw3_2;

/**
 * File Name: Search12D.java
 * Search12D concrete class
 * <p>
 * <p>
 * To Compile: IntUtil.java RandomInt.java Search12D.java Search12DBase.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

class Search12D extends Search12DBase {
  Search12D() {
    //NOTHING CAN BE CHANGED HERE
    testBench();
  }

  @Override
  protected boolean search12D(int t) {
    //NOTHING CAN BE CHANGED HERE
    return alg(t);
  }

  private boolean alg(int t) {
    //WRITE CODE

    int[] matrixDescriptor = getMatrixDescriptor();

    int[] leftBoundPos = new int[]{
        0,
        0
    };

    int[] minLeftPos = new int[2];
    int[] maxRightPos = new int[2];

    int[] rightBoundPos = new int[]{
        matrixDescriptor[0] - 1,
        matrixDescriptor[1] - 1
    };

    boolean isFound = false;

    while (!isFound) {

      int[] midPos = getMidPos(
          leftBoundPos,
          rightBoundPos
      );

      int midValue = getValueAtPos(midPos);

      if (midValue == t) {
        isFound = true;
        break;
      } else if (
          isRangeCollapsed(leftBoundPos, rightBoundPos)
              || isRangeCollapsed(leftBoundPos, midPos)
              || isRangeCollapsed(rightBoundPos, midPos)
      ) {
        // left / right collapsed;
        break;
      } else if (midValue < t) {

        leftBoundPos = midPos;

        if (minLeftPos[0] <= leftBoundPos[0] && minLeftPos[1] <= leftBoundPos[1]) {
          minLeftPos = leftBoundPos;
        }

      } else {
        rightBoundPos = midPos;

        if (maxRightPos[0] >= rightBoundPos[0] && maxRightPos[1] >= rightBoundPos[1]) {
          minLeftPos = rightBoundPos;
        }
      }

    }

    return isFound;
  }

  private boolean isRangeCollapsed(int[] leftBound, int[] rightBound) {
    return leftBound[0] == rightBound[0] && rightBound[1] == leftBound[0];
  }

  private int getValueAtPos(int[] pos) {
    return get(pos[0], pos[1]);
  }

  private int getMidPosAbsolute(int[] currentLeftPos, int[] currentRightPos) {
    int colCount = c();

    int leftPosAbsolute = colCount * currentLeftPos[0] + currentLeftPos[1];
    int rightPosAbsolute = colCount * currentRightPos[0] + currentRightPos[1];
    int diffPosAbsolute = rightPosAbsolute - leftPosAbsolute;

    int midPosAbsolute = leftPosAbsolute + diffPosAbsolute / 2;
    return midPosAbsolute;
  }

  private int[] getMidPos(int[] currentLeftPos, int[] currentRightPos) {
    int colCount = c();

    int midPosAbsolute = getMidPosAbsolute(currentLeftPos, currentRightPos);

    return new int[]{
        midPosAbsolute / colCount,
        midPosAbsolute % colCount,
    };

  }

  private int[] getMatrixDescriptor() {
    return new int[]{
        r(),
        c()
    };
  }


  public static void main(String[] args) {
    //NOTHING CAN BE CHANGED HERE
    System.out.println("Search12D problem STARTS");
    Search12D m = new Search12D() ;
    System.out.println("All Search12D tests passed. You are genius");
    System.out.println("Search12D problem ENDS");
  }
}