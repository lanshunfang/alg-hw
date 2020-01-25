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

  // a cell of the representation matrix
  class Cell {
    // the student's ID in a (all students)
    int rawIndex = 0;
    // the student's capability (running speed) in a (all students)
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
   *
   * The alg for selection of racing students
   *
   * With my solution, we only require  8 iterations for 25 students to select TOP 3 on 5-track racing playground
   *
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
   * Cell[0] Cell[1] Cell[2] Cell[3] Cell[4]
   * Cell[5] Cell[6] Cell[7] Cell[8] Cell[9]
   * Cell[10] Cell[11] Cell[12] Cell[13] Cell[14]
   * Cell[15] Cell[16] Cell[17] Cell[18] Cell[19]
   * Cell[20] Cell[21] Cell[22] Cell[23] Cell[24]
   * <p>
   * ]
   * <p>
   * - For each Cell[n] (0 <= n < 5; n++), Cell[n].rawOrder > Cell[n-1].rawOrder (smaller number means less time, that is, faster)
   * - And, Cell[0].rawOrder <= Cell[5].rawOrder <= Cell[10].rawOrder <= Cell[15].rawOrder <= Cell[20].rawOrder
   * <p>
   * Reduce the matrix to 3 * 3, omitting other numbers
   * [
   * <p>
   * Cell[0] Cell[1] Cell[2]
   * Cell[10] Cell[11] Cell[12]
   * Cell[15] Cell[16] Cell[17]
   * <p>
   * ]
   * When we could generate this matrix,
   * - Cell[0] must be TOP1,
   * - Cell[12], Cell[16], Cell[17] which are to the bottom-right of the diagonal of the square matrix
   * must be less equal than TOP4
   *
   * <p>
   * So, the last race would only occurs between Cell[1], Cell[2], Cell[10],  Cell[11] and Cell[15]
   * And the last race would give us the TOP2 and TOP3
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
   * Generate matrix 5 * 5 (NT) to represent the raw a (all student's ID and its order - running speed)
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
    Arrays.sort(matrix, Comparator.comparingInt(
        (row) -> row[0].rawOrder
    ));
    itr++;
  }


  public static void main(String[] args) {
    //NOTHING CAN BE CHANGED HERE
    System.out.println("SelectionDay problem STARTS");
    SelectionDay m = new SelectionDay();
    System.out.println("SelectionDay problem ENDS");
  }
}