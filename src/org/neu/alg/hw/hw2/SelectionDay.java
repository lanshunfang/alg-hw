package org.neu.alg.hw.hw2;


import java.util.*;


/**
 * File Name: SelectionDay.java
 * SelectionDay concrete class
 * <p>
 * To Compile: IntUtil.java RandomInt.java SelectionDay.java SelectionDayBase.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

class SelectionDay extends SelectionDayBase {

  class Cell {
    int rawIndex = 0;
    int rawOrder = 0;

    Cell(int i, int o) {
      this.rawIndex = i;
      this.rawOrder = o;
    }
  }

  int itr;

  SelectionDay() {
    //NOTHING CAN BE CHANGED HERE
    testBench();
  }

  @Override
  protected int race() {
    //NOTHING CAN BE CHANGED HERE
    itr = 0;
    alg();
    return itr;
  }

  //You can have any number of private data structures and procedure
  //YOU WRITE YOUR CODE BELOW. DO NOT CRAM entire code in one procedure
  //Your output must be such that, any idiot, executes your steps should be able to conduct the race.

  //Number of tracks
  private int NT = N / 5;

  /**
   * Solve flow:
   * <p>
   * # STEP 1
   * <p>
   * Matrix dimensionality reducing to 5 * 5 square matrix or 4 * 4 matrix, which first column and all rows are sorted
   * e.g.
   * <p>
   * The target matrix:
   * <p>
   * [
   * <p>
   * a[0][*] a[1][*] a[2][*] a[3][*] a[4][*]
   * a[5][*] a[6][*] a[7][*] a[8][*] a[9][*]
   * a[10][*] a[11][*] a[12][*] a[13][*] a[14][*]
   * a[15][*] a[16][*] a[17][*] a[18][*] a[19][*]
   * a[20][*] a[21][*] a[22][*] a[23][*] a[24][*]
   * <p>
   * ]
   * <p>
   * - For each a[n] (0 <= n < 5; n++), a[n] > a[n-1] (smaller number means less time, that is, faster)
   * - And, a[0] <= a[5] <= a[10] <= a[15] <= a[20] (smaller number means less time, that is, faster)
   * <p>
   * Reduce the matrix to 3 * 3, omitting other numbers
   * [
   * <p>
   * a[0][*] a[1][*] a[2][*]
   * a[10][*] a[11][*] a[12][*]
   * a[15][*] a[16] a[17][*]
   * <p>
   * ]
   * When we could generate this matrix,
   * - a[0][*] must be TOP1,
   * - a[12][*], a[16][*], a[17][*] which are to the bottom-right of the diagonal of the square matrix
   * must be less equal than TOP4
   *
   * <p>
   * So, the last race would only occurs between a[1]a[0][*], a[2]a[0][*], a[10]a[0][*], a[11]a[0][*] and a[15]a[0][*]
   * And the last race would give us the TOP2 and TOP2
   * <p>
   * # STEP 2, if N > 25 (not in our case, but just for a note of the alg)
   * <p>
   * In order to generate the 5 * 5 or 4 * 4 first-col and all-row sorted matrix,
   * when the student number is bigger than 25,
   * we need to:
   * <p>
   * REPEAT {
   * <p>
   * ## Get a basic row sorted matrix
   * - We need to divide all students (N) per racing track number(NT)
   * - To generate a N/NT * 5 matrix ( N/NT may have remnant, round them up to a row)
   * - Let them race, that is, sort every row, ASC (smaller number means less time, that is, faster)
   * <p>
   * ## Matrix dimensional reduction by taking only the first sorted column of the big matrix
   * - Only take all the first column students to race again
   * <p>
   * } UNTIL we reach 5 * 5 or 4 * 4 or 3 * 3 matrices
   * <p>
   * Goto # STEP 1
   * <p>
   * Problem solved.
   * <p>
   * As we only have 25 students here, the # STEP 2 would not be necessary.
   */
  private void alg() {

    final Cell[][] studentMatrix = constructStudentMatrix();
    sortAllRowsOfMatrix(studentMatrix);
    sortMatrixByFirstColumn(studentMatrix);

    s[0] = studentMatrix[0][0].rawIndex;

    final Cell[][] last5ItemsOfTheMatrixToCompare = new Cell[1][];
    last5ItemsOfTheMatrixToCompare[0] = new Cell[]{
        studentMatrix[0][1],
        studentMatrix[0][2],
        studentMatrix[1][0],
        studentMatrix[1][1],
        studentMatrix[2][0],
    };

    sortAllRowsOfMatrix(last5ItemsOfTheMatrixToCompare);

    s[1] = last5ItemsOfTheMatrixToCompare[0][0].rawIndex;
    s[2] = last5ItemsOfTheMatrixToCompare[0][1].rawIndex;


  }

  /**
   * Generate matrix 5 * 5 (NT)
   *
   * @return
   */
  private Cell[][] constructStudentMatrix() {

    Cell[][] arrayMulti = new Cell[N / NT][5];

    for (int i = 0; i < a.length; i++) {
      final int rowIndex = i / 5;
      final int colIndex = i % 5;
      Cell cell = new Cell(i, a[i]);
      arrayMulti[rowIndex][colIndex] = cell;
    }

    return arrayMulti;

  }

  private void sortAllRowsOfMatrix(Cell[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      Arrays.sort(matrix[i], Comparator.comparing(e -> e.rawOrder));
      itr++;
    }
  }

  private void sortMatrixByFirstColumn(Cell[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      Arrays.sort(matrix, Comparator.comparingInt(
          (row) -> row[0].rawOrder
      ));
      itr++;
    }
  }


  public static void main(String[] args) {
    //NOTHING CAN BE CHANGED HERE
    System.out.println("SelectionDay problem STARTS");
    SelectionDay m = new SelectionDay();
    System.out.println("SelectionDay problem ENDS");
  }
}